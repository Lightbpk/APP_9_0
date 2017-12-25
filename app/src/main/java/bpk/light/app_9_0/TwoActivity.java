package bpk.light.app_9_0;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TwoActivity extends AppCompatActivity {
    Button btn2;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        btn2 = (Button) findViewById(R.id.btn2);
        sharedPreferences = this.getPreferences(Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        i = sharedPreferences.getInt("inc",0);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TwoActivity.this, ThreeActivity.class);
                startActivity(intent);
                i++;
                editor.putInt("inc",i);
                editor.commit();
            }
        };
        btn2.setOnClickListener(listener);
    }
}
