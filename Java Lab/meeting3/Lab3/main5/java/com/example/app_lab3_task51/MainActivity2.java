package com.example.app_lab3_task51;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity2 extends AppCompatActivity {

    private ImageView imageView_p;
    private ImageView imageView_m;
    private String url_plus, url_minus;
    private Button button_finish;
    private boolean showFirst = true;
    private int result;
    private TextView textView_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        imageView_p = findViewById(R.id.imageView_p);
        imageView_m = findViewById(R.id.imageView_m);
        button_finish =  findViewById(R.id.button_finish);
        textView_result = findViewById(R.id.textView_result);

        url_plus = getString(R.string.plus_url);
        url_minus = getString(R.string.minus_url);

        String initial = getIntent().getStringExtra(getString(R.string.initialNumber));
        if (initial != null && !initial.isEmpty()) {
            result = Integer.parseInt(initial);
            textView_result.setText(String.valueOf(result));
        } else {
            result = 0;
        }


//        result = Integer.parseInt(getIntent().getStringExtra("initialNumber"));

//        loadImageFromUrl(url_plus, imageView_p);  // load first image
//        loadImageFromUrl(url_minus, imageView_m);  // load first image

        imageView_m.setOnClickListener(v -> {
            textView_result.setText(String.valueOf(--result));
        });

        imageView_p.setOnClickListener(v -> {
            textView_result.setText(String.valueOf(++result));
        });

        button_finish.setOnClickListener(v -> {
            Intent returnIntent = new Intent();
            returnIntent.putExtra(getString(R.string.finalResult), result);
            setResult(RESULT_OK, returnIntent);
            finish(); // Close MainActivity2
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    private void loadImageFromUrl(String str_url, ImageView im) {
        new Thread(() -> {
            try {
                Bitmap bitmap = drawableFromUrl(str_url);
                runOnUiThread(() -> im.setImageBitmap(bitmap));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }


    // This must be outside onCreate!
    private Bitmap drawableFromUrl(String str_url) throws java.net.MalformedURLException, java.io.IOException {
        Bitmap x;
        HttpURLConnection connection = (HttpURLConnection) new URL(str_url).openConnection();
        connection.setRequestProperty(getString(R.string.userAgent), getString(R.string.Mozilla_4));
        connection.connect();
        InputStream input = connection.getInputStream();
        x = BitmapFactory.decodeStream(input);
        return x;
    }



}
