package bpk.light.app_9_0;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FourActivity extends AppCompatActivity {
    Button btnSet2;
    EditText scanTime, brutTime;
    String LL = "LightLog";
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four);
        btnSet2 = (Button)findViewById(R.id.btnSet2);
        scanTime = (EditText)findViewById(R.id.scanTime);
        brutTime = (EditText) findViewById(R.id.brutTime);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FourActivity.this, FiveActivity.class);
                startActivity(intent);
            }
        };
        btnSet2.setOnClickListener(listener);
        scanTime.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    editor.putInt("scan", Integer.parseInt(scanTime.getText().toString()));
                    editor.commit();
                }
                catch (NumberFormatException e){
                    e.printStackTrace();
                }
            }
        });
        brutTime.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                editor.putInt("brut",Integer.parseInt(brutTime.getText().toString()));
                editor.apply();
            }
        });
    }
    @Override
    public void onBackPressed() {
        //int b = SharedPreferences.getInt("brut",2);
        Intent intent = new Intent(FourActivity.this, ThreeActivity.class);
        startActivity(intent);
        //Log.d(LL,""+SharedPreferences.getInt("brut",2));
    }
}
