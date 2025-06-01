package com.example.applab4_task9;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private AnimationDrawable drawable;
    private ImageView imageView;
    private Button button;

    private boolean isAnimating = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        button= findViewById(R.id.ss_button);
        imageView= findViewById(R.id.imageView);

        // Set the background animation
        imageView.setBackgroundResource(R.drawable.animations);
        drawable = (AnimationDrawable) imageView.getBackground();  // Cast to AnimationDrawable

        // Button to start/stop animation
        button.setOnClickListener(v -> onStartStopAnimation());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // Method to start/stop the animation when button is clicked
    public void onStartStopAnimation() {
        if (isAnimating) {
            drawable.stop();  // Stop the animation
            button.setText(R.string.start_ani);
        } else {
            drawable.start();  // Start the animation
            button.setText(R.string.stop_ani);
        }
        isAnimating = !isAnimating;  // Toggle the state
    }
}