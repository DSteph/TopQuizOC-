package com.example.topquiz.controleur;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.topquiz.R;
import com.example.topquiz.modele.User;


public class MainActivity extends AppCompatActivity {

    private TextView mGreetingText;
    private EditText mNameInput;
    private Button mPlayButton;
    private User mUser;
    private int score;

    private static final int GAME_ACTIVITY_REQUEST_CODE = 777;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mGreetingText = (TextView)findViewById(R.id.activity_main_welcome_title_txt);
        mNameInput = (EditText)findViewById(R.id.activity_main_username_input);
        mPlayButton = (Button)findViewById(R.id.activity_main_button_go_btn);

        mUser = new User();

        //bouton grisé au lancement tant qu'on a pas de username
        mPlayButton.setEnabled(false);


        mNameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mPlayButton.setEnabled(s.toString().length() != 0);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mUser.setFirstName(mNameInput.getText().toString());

                Intent gameActivity = new Intent( MainActivity.this, GameActivity.class);
                startActivityForResult(gameActivity, GAME_ACTIVITY_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if ( requestCode == GAME_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK )
            score = data.getIntExtra(GameActivity.BUNDLE_EXTRA_SCORE, 0);

        super.onActivityResult(requestCode, resultCode, data);
    }
}