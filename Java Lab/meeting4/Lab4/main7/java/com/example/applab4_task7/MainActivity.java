package com.example.applab4_task7;

import android.media.MediaMetadataRetriever;
import android.os.Bundle;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.media.MediaPlayer;
import android.widget.Toast;

import java.io.IOException;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private final MediaPlayer mediaPlayer = new MediaPlayer();
    private TextView artist_text;
    private TextView title_text;
    private TextView album_text;
    private ImageView imageView;
    private EditText editTextText;
    private Button pp_button;
    private String url;
    private String old_url;
    private Boolean isPrepered;
    private MediaMetadataRetriever retriever = new MediaMetadataRetriever();


//    private Button playButton, pauseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Initialize the views using findViewById
        artist_text = findViewById(R.id.artist_text);
        title_text = findViewById(R.id.title_text);
        album_text = findViewById(R.id.album_text);
        editTextText = findViewById(R.id.editTextText);
        pp_button = findViewById(R.id.pp_button);
        isPrepered= false;
        imageView = findViewById(R.id.imageView);
        imageView.setImageDrawable(null);

        // Set an OnClickListener for the Button
        pp_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mediaPlayer.isPlaying()) {
                    // If the media is currently playing, pause it
                    mediaPlayer.pause();
                    pp_button.setText(R.string.str_play); // Change the button text to "Play"
                    Toast.makeText(MainActivity.this, R.string.media_paused, Toast.LENGTH_SHORT).show();
                } else { //not playing
                    // If the media is not playing, start it
                    url = editTextText.getText().toString().trim();
                    if(URLUtil.isHttpsUrl(url)) {  //check if the URL is correct
                        if(!isPrepered || !url.equals(old_url)){
                            old_url=url;
                            try {
                                mediaPlayer.reset();
                                update_layout_from_url(url); //if so import the data from the url
                                isPrepered = true;
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }

                        mediaPlayer.start();
                        pp_button.setText(R.string.str_pause); // Change the button text to "Pause"
                        Toast.makeText(MainActivity.this, R.string.media_playing, Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(MainActivity.this, R.string.wrong_url, Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    private void update_layout_from_url(String url) throws IOException {

        try {
            // Set data source using the URL
            retriever.setDataSource(url, new HashMap<String, String>());

            // Extract metadata
            String title = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);
            String artist = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);
            String album = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM);

            // Set the retrieved metadata to the UI
            title_text.setText(title != null ? title : "Unknown Title");
            artist_text.setText(artist != null ? artist : "Unknown Artist");
            album_text.setText(album != null ? album : "Unknown Album");

            // Optionally, you can also set the album art if it's available
            byte[] embedPic = retriever.getEmbeddedPicture();
            if (embedPic != null) {
                // Convert the byte array into a Bitmap and set it to the ImageView
                imageView.setImageBitmap(android.graphics.BitmapFactory.decodeByteArray(embedPic, 0, embedPic.length));
            } else {
                // If no album art is found, set a default image (optional)
//                imageView.setImageResource(R.drawable.default_album_cover);  // Replace with a default image if desired
                // If no album art is found, set the ImageView to null (no image)
                imageView.setImageDrawable(null);
            }


        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(MainActivity.this, R.string.metadata_error, Toast.LENGTH_SHORT).show();
        } finally {
            //
        }

        // Set the MediaPlayer data source
        try {
            mediaPlayer.setDataSource(url);
            mediaPlayer.prepare();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(MainActivity.this, R.string.prepare_error, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Pause media if activity is paused
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        // Release the media player to avoid memory leaks
//        if (mediaPlayer != null) {
//            mediaPlayer.release();
//        }
//    }

}