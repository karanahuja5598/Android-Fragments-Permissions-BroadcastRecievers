package com.example.kahuja3project3app1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private Button button1;
    private Button button2;

    private TextView text1;
    private TextView text2;

    private static final String permission = "edu.uic.cs478.sp2020.project3";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text1 = (TextView) findViewById(R.id.text1);
        button1 = (Button) findViewById(R.id.button1) ;

        text2 = (TextView) findViewById(R.id.text2);
        button2 = (Button) findViewById(R.id.button2) ;

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED)
                    broadcastRestaurantsIntent();
                else
                    requestPermissions(new String[]{permission}, 1);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED)
                    broadcastAttractionsIntent();
                else
                    requestPermissions(new String[]{permission}, 0);
            }
        });
    }

    public void broadcastAttractionsIntent() {
        Toast.makeText(getApplicationContext(), "Opening List of Attractions", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        intent.setAction("com.example.kahuja3project3app1.Attractions");
        sendBroadcast(intent);
    }

    public void broadcastRestaurantsIntent(){
        Toast.makeText(getApplicationContext(), "Opening List of Restaurants", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        intent.setAction("com.example.kahuja3project3app1.Restaurants");
        sendBroadcast(intent);
    }


    public void onRequestPermissionsResult(int code, String[] permissions, int[] results) {
        if (results.length > 0) {
            if (results[0] == PackageManager.PERMISSION_GRANTED) {
                if (code == 0) {
                    broadcastAttractionsIntent();
                } else if (code == 1) {
                    broadcastRestaurantsIntent();
                }
            }
            else {
                Toast.makeText(this, "Permission is not granted", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
