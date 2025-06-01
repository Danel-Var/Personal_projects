package com.example.applab3_task4;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private String url1, url2;
    private boolean showFirst = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        setContentView(R.layout.activity_main);
        EdgeToEdge.enable(this);


        imageView = findViewById(R.id.imageView);
        url1 = getString(R.string.url_str_2);
        url2 = getString(R.string.url_str_1);

        loadImageFromUrl(url1);  // load first image

        imageView.setOnClickListener(v -> {
            String nextUrl = showFirst ? url2 : url1;
            loadImageFromUrl(nextUrl);
            showFirst = !showFirst;
        });


        // Example usage (you can't call network stuff on main thread though — will explain below)
//        imageView.Bitmap(bmp)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    private void loadImageFromUrl(String str_url) {
        new Thread(() -> {
            try {
                Bitmap bitmap = drawableFromUrl(str_url);
                runOnUiThread(() -> imageView.setImageBitmap(bitmap));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }


    // This must be outside onCreate!
    private Bitmap drawableFromUrl(String str_url) throws java.net.MalformedURLException, java.io.IOException {
        Bitmap x;
        HttpURLConnection connection = (HttpURLConnection) new URL(str_url).openConnection();
        connection.setRequestProperty("User-agent", "Mozilla/4.0");
        connection.connect();
        InputStream input = connection.getInputStream();
        x = BitmapFactory.decodeStream(input);
        return x;
    }
}
