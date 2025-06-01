package com.example.app_lab3_task2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private RatingBar ratingBar;
    private TextView textView;
    private TextView textView2;
    private Button resetButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ratingBar = findViewById(R.id.ratingBar);
        textView = findViewById(R.id.textView);
        resetButton = findViewById(R.id.reset_button);
        textView2 = findViewById(R.id.textView2);

        ratingBar.setOnRatingBarChangeListener((ratingBar, rating, fromUser) -> {
            String message = getString(R.string.you_rated_str, rating);
            textView2.setText(String.valueOf(rating));
            textView.setText(R.string.rating_str);




            // Toast message here
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
        });


        // Reset button resets the rating and updates text
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ratingBar.setRating(0);
                textView.setText(R.string.rating_str);
                textView2.setText(String.valueOf(ratingBar.getRating()));

                Toast.makeText(MainActivity.this, R.string.str_reset, Toast.LENGTH_SHORT).show();

            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}