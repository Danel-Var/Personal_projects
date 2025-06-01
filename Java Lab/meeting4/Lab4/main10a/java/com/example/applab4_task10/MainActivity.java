package com.example.applab4_task10;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
//    private Button shareButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Initialize views
        editText = findViewById(R.id.editTextText);
//        shareButton = findViewById(R.id.button);



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // This method is called by the Button via android:onClick="sharing"
    public void sharing(View view) {
        String textToShare = editText.getText().toString().trim();
        if (!textToShare.isEmpty()) {
            Intent intent = new Intent();                         // Create intent
            intent.setAction(Intent.ACTION_SEND);                // Set action
            intent.setType("text/plain");                        // Set MIME type
            intent.putExtra(Intent.EXTRA_TEXT, textToShare);     // Attach text

            // Wrap in chooser intent
            Intent share_intent = Intent.createChooser(intent, null);
            startActivity(share_intent);
        }
    }
}