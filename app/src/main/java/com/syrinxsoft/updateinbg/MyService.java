package "com.YOUR_PACKAGE.updateinbg";

import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.v7.app.NotificationCompat;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MyService extends Service
{
    private GETConnection getConnection;

    private String link;
    private int separateCount, separateSupport;
    private TimerHandler thnd;

    public MyService()
    {
        link = "YOUR_URL";
        separateCount = 0;
        separateSupport = 2;
    }

    @Override
    public IBinder onBind(Intent intent) { throw new UnsupportedOperationException("Not yet implemented"); }

    @Override//Run once at start service
    public void onCreate()
    {
        super.onCreate();
    }

    @Override//Run and restart service if itself close
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        Toast.makeText(getApplicationContext(), "LOADING SERVICE...", Toast.LENGTH_LONG).show();

        getConnection = new GETConnection(getApplicationContext());
        getConnection.execute(link);

        return (START_STICKY);
    }
    //----------------------------------


    private void IntervalToCheckUpdates()
    {
        thnd = new TimerHandler(10);
        thnd.start();
        while (!thnd.isFinished())
        {
            try
            {
                Thread.sleep(10);
            }
            catch (Exception e) { e.printStackTrace(); }
        }

        Notification();
    }

    private void Notification()
    {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher_round);
        builder.setContentTitle("UPDATE!");
        builder.setContentText("UPDATING...");
        builder.setTicker("NEW UPDATE!");
        //-----------------------------------------------------------------------------------
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(9999, builder.build());
    }
}