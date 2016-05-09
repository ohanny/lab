package fr.icodem.demolistapp.old;

import android.graphics.Bitmap;
import android.util.LruCache;

public class ImageCache<T> {
    private LruCache<T, Bitmap> mMemoryCache;

    public ImageCache() {
        // Get max available VM memory, exceeding this amount will throw an
        // OutOfMemory exception. Stored in kilobytes as LruCache takes an
        // int in its constructor.
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);

        // Use 1/10th of the available memory for this memory cache.
        final int cacheSize = maxMemory / 10;

        mMemoryCache = new LruCache<T, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(T key, Bitmap bitmap) {
                // The cache size will be measured in kilobytes rather than
                // number of items.
                return bitmap.getByteCount() / 1024;
            }
        };
    }

    public void addBitmapToMemoryCache(T key, Bitmap bitmap) {
        if (getBitmapFromMemCache(key) == null) {
            mMemoryCache.put(key, bitmap);
        }
    }

    public Bitmap getBitmapFromMemCache(T key) {
        return mMemoryCache.get(key);
    }
}
