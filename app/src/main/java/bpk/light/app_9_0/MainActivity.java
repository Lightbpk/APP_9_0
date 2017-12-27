package bpk.light.app_9_0;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btn1;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = (Button) findViewById(R.id.btn1);
        sharedPreferences =  PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();
        i = sharedPreferences.getInt("inc",0);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TwoActivity.class);
                startActivity(intent);
                i++;
                editor.putInt("inc",i);
                editor.commit();
            }
        };
        btn1.setOnClickListener(listener);
    }
}
