package com.example.app_lab3_task51;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private TextView textViewForResult;
    private EditText editNumber;
    private Button button;
    private String oldRes= null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        textViewForResult = findViewById(R.id.textViewForResult);
        editNumber= findViewById(R.id.editNumber);
        button= findViewById(R.id.button);

        // Set an OnClickListener for the button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String input = editNumber.getText().toString().trim();
                if(!input.equals(oldRes)) {
                    if(!input.isEmpty()) {
                        oldRes= input;
                        Intent intent = new Intent(getBaseContext(), MainActivity2.class);
                        intent.putExtra(getString(R.string.initialNumber), input);

                        startActivityForResult(intent, 1);
                    }
                    else{
                            Toast.makeText(MainActivity.this, getString(R.string.str_enter_num_pls), Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(MainActivity.this, getString(R.string.str_enter_num_pls_diff), Toast.LENGTH_SHORT).show();
                }
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            int finalResult= data.getIntExtra(getString(R.string.finalResult), 0);
            textViewForResult.setText(String.valueOf(finalResult));
        }
    }


}