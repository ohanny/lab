package fr.icodem.demolistapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class IOUtils {
    public static String readStream(InputStream is){
        InputStreamReader isr = null;
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        try {
            isr = new InputStreamReader(is);
            br = new BufferedReader(isr);
            String str;

            while ((str=br.readLine())!= null){
                sb.append(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(br!=null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(isr!=null){
                try {
                    isr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return sb.toString();
    }

    public static void copy(InputStream in, OutputStream out) throws IOException {
        int i = -1;

        while ((i = in.read()) != -1) {
            out.write((byte) i);
        }
    }

    public static void close(InputStream is) {
        try {
            if (is != null) {
                is.close();
            }
        } catch (IOException e) {}

    }

    public static void close(OutputStream os) {
        try {
            if (os != null) {
                os.close();
            }
        } catch (IOException e) {}

    }

}
