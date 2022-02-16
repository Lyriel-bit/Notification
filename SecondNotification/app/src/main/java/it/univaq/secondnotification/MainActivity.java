package it.univaq.secondnotification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public static final String CHANNEL_1_ID = "mychannel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //richiamo il metodo per la creazione di un canale per le notifiche\<
        createNotificationChannel();
    }

    /**
     * Inovoc il metodo della creazione delle notifiche a seguito del tap del bottone
     * @param view
     */
    public void onClick(View view)
    {
        showNotification();
    }

    /**
     * Metodo per la creazione e gestione delle notifiche
     */

    protected void showNotification()
    {
        /*
        *Apre un'activity quando si preme sulla notifica
         */
        // Create an explicit intent for an Activity in your app
        Intent intent = new Intent(this, AlertDetail.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        //creiamo la notifica
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "CHANNEL_1_ID")
                .setSmallIcon(R.drawable.pet)
                .setContentTitle("My notification")
                .setContentText("Hi this is my first notification!!!!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                // Set the intent that will fire when the user taps the notification
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(1, builder.build());

    }

    /**
     * imposto il canale per la gestione delle notifiche
     */

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "notChannel";
            String description = "Channel for my first notification apps";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("CHANNEL_1_ID", "mychannel", NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }






















}