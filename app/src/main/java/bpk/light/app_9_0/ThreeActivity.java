package bpk.light.app_9_0;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.SoundPool;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Timer;
import java.util.TimerTask;

public class ThreeActivity extends AppCompatActivity {
    Button btnScan, btnBrut, btnSet, btnCancel;
    ImageView sandClock;
    Long scanSleep;
    int compliteSound, brutSleep;
    String LL = "LightLog",password;
    TextView tablo;
    SoundPool sp;
    SharedPreferences sharedPreferences;
    Timer timer = new Timer();
    Timer timer1 = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);
        Log.d(LL,"Created");
        btnScan = (Button) findViewById(R.id.btnScan);
        btnBrut = (Button) findViewById(R.id.btnBrut);
        btnSet = (Button) findViewById(R.id.btnSet);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        sandClock = (ImageView) findViewById(R.id.sandClock);
        sandClock.setVisibility(View.INVISIBLE);
        tablo = (TextView) findViewById(R.id.tablo);
        sp = new SoundPool(1, AudioManager.STREAM_MUSIC,0);
        compliteSound = sp.load(this,R.raw.complitesound,1);
        btnBrut.setEnabled(false);
        View.OnClickListener listenerScan = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sandClock.setVisibility(View.VISIBLE);
                try {
                    Thread.sleep(scanSleep);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Log.d(LL,"Can't wait");
                }
                sp.play(compliteSound,1,1,0,0,1);
                String defPass = "";
                for (int i=0; i<password.length();i++){
                    defPass = defPass+"0";
                }
                tablo.setText(defPass);
                btnBrut.setEnabled(true);
                sandClock.setVisibility(View.INVISIBLE);
            }
        };
        View.OnClickListener listenerBrut = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sandClock.setVisibility(View.VISIBLE);
                timer.schedule(new BrutTask(),brutSleep*1000);
                timer1.schedule(new BrutRand(),0,100);
            }
        };
        View.OnClickListener listenerSet = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThreeActivity.this, FourActivity.class);
                startActivity(intent);
            }
        };
        View.OnClickListener listenerCancel = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnBrut.setClickable(false);
                tablo.setText("-------");
            }
        };
        btnScan.setOnClickListener(listenerScan);
        btnBrut.setOnClickListener(listenerBrut);
        btnSet.setOnClickListener(listenerSet);
        btnCancel.setOnClickListener(listenerCancel);
    }

    @Override
    protected void onResume() {
    super.onResume();
        Log.d(LL,"Resume");
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        scanSleep = (long)1000*sharedPreferences.getInt("scan",2);
        brutSleep = sharedPreferences.getInt("brut",2);
        password = sharedPreferences.getString("password","12345678");
        Log.d(LL,"scan - "+scanSleep);
        Log.d(LL,"brute - "+brutSleep);
        Log.d(LL,"pass - "+password);
    }

   public class BrutTask extends TimerTask{

       @Override
       public void run() {
           runOnUiThread(new Runnable() {
               @Override
               public void run() {
                   timer1.cancel();
                   sandClock.setVisibility(View.INVISIBLE);
                   tablo.setText(password);
                   sandClock.setVisibility(View.INVISIBLE);
                   sp.play(compliteSound,1,1,0,0,1);
               }
           });

       }
   }
    public class BrutRand extends TimerTask{

        @Override
        public void run() {
           Log.d(LL,"Timer start");
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                     tablo.setText(""+Math.random());
                }
            });
        }
    }
}
