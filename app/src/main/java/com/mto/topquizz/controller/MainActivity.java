package com.mto.topquizz.controller;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mto.topquizz.R;
import com.mto.topquizz.model.User;

public class MainActivity extends AppCompatActivity {

    private TextView textWelcome;
    private EditText textInputName;
    private Button button;
    private User mUser;
    public static final int GAME_ACTIVITY_REQUEST_CODE = 59;
    private SharedPreferences mPreferences;
    public static final String PREF_KEY_SCORE = "PREF_KEY_SCORE";
    public static final String PREF_KEY_FIRSTNAME = "PREF_KEY_FIRSTNAME";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (GAME_ACTIVITY_REQUEST_CODE == requestCode && RESULT_OK == resultCode) {
            int score = data.getIntExtra(GameActivity.BUNDLE_EXTRA_SCORE, 0);
            mPreferences.edit().putInt(PREF_KEY_SCORE, score).apply();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // When activity is create, load the activity_main.xml
        setContentView(R.layout.activity_main); //Android syntax

        mUser = new User();
        //Reference graphics elements
        // MODE_PRIVATE : Access for our application only in Memory
        mPreferences = getPreferences(MODE_PRIVATE);
        textWelcome = findViewById(R.id.textWelcome);
        textInputName = findViewById(R.id.textInputName);
        button = findViewById(R.id.button);
        //System.out.println(mPreferences.getAll().get(PREF_KEY_FIRSTNAME));

        button.setEnabled(false);
        textInputName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //If character in textInputName is different than 0, setEnable Button true
                button.setEnabled(charSequence.toString().length() != 0);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            // this method is called everytime when user click on the button
            @Override
            public void onClick(View view) {
                // Get the name from main activity before click button
                String firstname = textInputName.getText().toString();
                mUser.setFirstname(firstname);
                if (mPreferences.getAll().get(PREF_KEY_FIRSTNAME).equals(firstname)) {
                    Toast.makeText(getApplicationContext(), "Welcome back ! Precedent Score : " + mPreferences.getAll().get(PREF_KEY_SCORE),
                            Toast.LENGTH_LONG).show();

                    System.out.println("Welcome back " + firstname + "Precedent Score " + mPreferences.getAll().get(PREF_KEY_SCORE));
                }
                mPreferences.edit().putString(PREF_KEY_FIRSTNAME, mUser.getFirstname()).apply();
                // user click button
                //Instantiation Intent and specification
                Intent gameActivityIntent = new Intent(MainActivity.this, GameActivity.class);
                //startActivity(gameActivityIntent);
                startActivityForResult(gameActivityIntent, GAME_ACTIVITY_REQUEST_CODE);
            }

        });


        MediaPlayer music = MediaPlayer.create(MainActivity.this, R.raw.intro);
        music.start();
    }

}