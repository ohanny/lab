package fr.icodem.demolistapp.old;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.URL;

import fr.icodem.demolistapp.R;

public class BitmapLoader<K> {
    private ImageCache<K> imageCache = new ImageCache<K>();

    private String baseUrl;

    public BitmapLoader(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public void loadBitmap(Context mContext, K resId, ImageView imageView) {
        // first check if bitmap is already in cache
        Bitmap bitmap = imageCache.getBitmapFromMemCache(resId);
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
            return;
        }

        // bitmap not in cache, load it
        if (cancelPotentialWork(resId, imageView)) {
            final BitmapWorkerTask task = new BitmapWorkerTask(imageView, baseUrl, imageCache);
            Bitmap mPlaceHolderBitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.ic_action_help);
            final AsyncDrawable asyncDrawable =
                    new AsyncDrawable(mContext.getResources(), mPlaceHolderBitmap, task);
            imageView.setImageDrawable(asyncDrawable);
            task.execute(resId);
        }
    }

    private static <V> boolean cancelPotentialWork(V data, ImageView imageView) {
        final BitmapWorkerTask<V> bitmapWorkerTask = BitmapLoader.<V>getBitmapWorkerTask(imageView);

        if (bitmapWorkerTask != null) {
            final V bitmapData = bitmapWorkerTask.resId;
            if (bitmapData != data) {
                // Cancel previous task
                bitmapWorkerTask.cancel(true);
            } else {
                // The same work is already in progress
                return false;
            }
        }
        // No task associated with the ImageView, or an existing task was cancelled
        return true;
    }

    private static <V> BitmapWorkerTask getBitmapWorkerTask(ImageView imageView) {
        if (imageView != null) {
            final Drawable drawable = imageView.getDrawable();
            if (drawable instanceof AsyncDrawable) {
                final AsyncDrawable asyncDrawable = (AsyncDrawable) drawable;
                return asyncDrawable.getBitmapWorkerTask();
            }
        }
        return null;
    }

    private static class AsyncDrawable extends BitmapDrawable {
        private final WeakReference<BitmapWorkerTask> bitmapWorkerTaskReference;

        public AsyncDrawable(Resources res, Bitmap bitmap,
                             BitmapWorkerTask bitmapWorkerTask) {
            super(res, bitmap);
            bitmapWorkerTaskReference =
                    new WeakReference<BitmapWorkerTask>(bitmapWorkerTask);
        }

        public BitmapWorkerTask getBitmapWorkerTask() {
            return bitmapWorkerTaskReference.get();
        }
    }

    private static class BitmapWorkerTask<U> extends AsyncTask<U, Void, Bitmap> {
        private final WeakReference<ImageView> imageViewReference;
        private U resId;
        private ImageCache<U> imageCache;
        private String baseUrl;

        public BitmapWorkerTask(ImageView imageView, String baseUrl, ImageCache<U> imageCache) {
            this.baseUrl = baseUrl;
            this.imageCache = imageCache;

            // Use a WeakReference to ensure the ImageView can be garbage collected
            imageViewReference = new WeakReference<ImageView>(imageView);
        }

        // Decode image in background.
        @Override
        protected Bitmap doInBackground(U... params) {
            resId = params[0];

            URL url = null;
            Bitmap bitmap = null;
            InputStream in = null;
            BufferedOutputStream out = null;

            try {
                url = new URL(baseUrl + resId);
                in = new BufferedInputStream(url.openStream());

                final ByteArrayOutputStream dataStream = new ByteArrayOutputStream();
                out = new BufferedOutputStream(dataStream);
                IOUtils.copy(in, out);
                out.flush();

                final byte[] data = dataStream.toByteArray();
                BitmapFactory.Options options = new BitmapFactory.Options();
                bitmap = BitmapFactory.decodeByteArray(data, 0, data.length, options);

                // add bitmap to cache
                if (bitmap != null) {
                    imageCache.addBitmapToMemoryCache(resId, bitmap);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                IOUtils.close(in);
                IOUtils.close(out);
            }
            return bitmap;
        }

        // Once complete, see if ImageView is still around and set bitmap.
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (imageViewReference != null && bitmap != null) {
                final ImageView imageView = imageViewReference.get();
                if (imageView != null) {
                    imageView.setImageBitmap(bitmap);
                }
            }
        }

    }
}