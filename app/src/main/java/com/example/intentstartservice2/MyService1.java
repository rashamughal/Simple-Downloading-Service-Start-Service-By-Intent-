package com.example.intentstartservice2;

import android.app.DownloadManager;
import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyService1 extends Service {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("TAG","Service Created");
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        // Retrieve data from the intent

        String url = intent.getStringExtra("url");
        Log.d("TAG", "Service Started with URL: " + url);

        // Show a message that the service has started
        Toast.makeText(this, "Service Started: Downloading File", Toast.LENGTH_SHORT).show();


        // Start the download
        downloadFile(url);


        // Your service logic here
        return START_STICKY;
    }

    public void downloadFile(String URL){
        // Use DownloadManager to download the file
        //getSystemService(DOWNLOAD_SERVICE) returns a generic Object representing a
        // system service for handling downloads typecasted in DownloadManager class type object
        //(DownloadManager) casts that Object to the specific DownloadManager class, allowing you to
        // call methods like enqueue() that are specific to the DownloadManager API.
        DownloadManager downloadManager= (DownloadManager) getSystemService(DOWNLOAD_SERVICE); //DOWNLOAD_SERVICE="download" in context class
        Uri downloadUri = Uri.parse(URL);// the URL is parsed to a 'Uri', which is a structured
        // representation of the URL, because the DownloadManager.Request requires URL in Uri representation

        //DownloadManager.Request object, which holds all the information about what to download and how to
        //handle the download
        DownloadManager.Request request = new DownloadManager.Request(downloadUri);//the URL to download is passed
        //It specifies the types of networks allowed for the download of req. Here, both WiFi and mobile data are allowed.
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI |
                DownloadManager.Request.NETWORK_MOBILE);
        //Sets the title of the download that will be shown in the notification.
        request.setTitle("File Download");
        //Sets a description for the download, which will also be displayed in the notification.
        request.setDescription("Downloading file from the provided URL");
        //This makes the download visible in the system’s notification bar, and once the download is complete,
        // the notification will update to "Download complete."
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        //Specifies the location on the device’s external storage where the file will be saved. In this case,
        // it will be saved in the Downloads directory with the name downloadedfile.zip.
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,
                "downloadedfile.zip");

        // This sends the download request to the DownloadManager and starts the download process. here the download is initiated
        downloadManager.enqueue(request);

        // Notify the user that the download has started
        Toast.makeText(this, "Download Started", Toast.LENGTH_SHORT).show();// pop-up notification that provides feedback to the user.
        // It appears for a short duration at the bottom of the screen and disappears automatically.
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Service Stopped", Toast.LENGTH_SHORT).show();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
/*
https://thetestdata.com/samplefiles/pdf/thetestdata_pdf0kb.zip
 */

/*
DownloadManager is a system service provided by Android that efficiently handles downloading tasks.
We need this service to initiate and control the file download process.
*/

/*
The method public IBinder onBind(Intent intent) is used in Android services to allow binding.
When an activity or another component wants to communicate directly with a service, this
method provides an interface (IBinder) for that communication.

However,if you don't need binding (i.e., your service just runs in the background without interaction),
you return null.

In Simple Terms:
onBind() is for connecting (binding) to a service.
If you don’t want the service to be bound, you override the method and return null.
*/