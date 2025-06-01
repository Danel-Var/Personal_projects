package com.example.applab3;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private Button b_clr;
    private Button b_enter;
    private EditText editText;
    private RadioGroup radioGroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        // Set the layout to activity_main.xml
        setContentView(R.layout.activity_main);

        // Initialize the UI components by referencing their IDs from the XML
        textView = findViewById(R.id.myColorText);
        editText = findViewById(R.id.editTextText);
        radioGroup =  findViewById(R.id.radioGroup);
        b_clr = findViewById(R.id.b_clr);
        b_enter = findViewById(R.id.b_enter);


        // Set an OnClickListener for the button
        b_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = editText.getText().toString();
                textView.setText(msg);  // Set the text based on EditText

            }
        });

        radioGroup.setOnCheckedChangeListener((radioGroup,i) -> {
            if (i == R.id.rb_red) {
                textView.setTextColor(Color.RED);
            } else if (i == R.id.rb_blue) {
                textView.setTextColor(Color.BLUE);
            } else if (i == R.id.rb_green) {
                textView.setTextColor(Color.GREEN);
            }
        });

        // Set an OnClickListener for the Clear button to reset everything
        b_clr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Unselect the RadioButton
                radioGroup.clearCheck();

                // Reset the TextView text and color
                textView.setTextColor(Color.BLACK);  // Default text color
                textView.setText(R.string.myColorText);

                // Clear the EditText
                editText.setText(R.string.ENTER_TEXT);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}