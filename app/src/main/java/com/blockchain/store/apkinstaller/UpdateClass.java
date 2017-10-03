package com.blockchain.store.apkinstaller;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by samsheff on 23/08/2017.
 */

public class UpdateClass extends AsyncTask<String,Void,Void> {
    private Context context;
    public void setContext(Context contextf){
        context = contextf;
    }

    @Override
    protected Void doInBackground(String... arg0) {
        try {
            URL url = new URL(arg0[0]);
            HttpURLConnection c = (HttpURLConnection) url.openConnection();
            c.setRequestMethod("GET");
            c.setDoOutput(true);

            String PATH = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
            File file = new File(PATH);
            file.mkdirs();
            File outputFile = new File(file, "update.apk");
            if(outputFile.exists()){
                outputFile.delete();
            }

            HttpDownloadUtility downloader = new HttpDownloadUtility();
            try {
                downloader.downloadFile(arg0[0], outputFile);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.parse("file://" + outputFile), "application/vnd.android.package-archive");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // without this flag android returned a intent error!
            context.startActivity(intent);


        } catch (Exception e) {
            Log.e("UpdateClass", "Update error! " + e.getMessage());
        }

        return null;
    }
}