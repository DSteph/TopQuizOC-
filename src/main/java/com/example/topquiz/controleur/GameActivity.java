package com.example.topquiz.controleur;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.topquiz.R;
import com.example.topquiz.modele.BanqueQuestions;
import com.example.topquiz.modele.Question;

import org.w3c.dom.Text;

import java.util.Arrays;
import java.util.List;

public class GameActivity extends AppCompatActivity implements View.OnClickListener{

   private BanqueQuestions mBanqueQuestions;
   private Question mCurrentQuestion;
   private int mNumberOfQuestions, mNombrePoints, reponseIndex;

   public static final String BUNDLE_EXTRA_SCORE = "BUNDLE_EXTRA_SCORE";

   @Override
   public void onClick(View v) {

      reponseIndex = (int)v.getTag();

      if ( checkReponse(reponseIndex) ) {
         Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
         ++mNombrePoints;
      }
      else Toast.makeText(this, "Incorrect!", Toast.LENGTH_SHORT).show();

      --mNumberOfQuestions;

      if (mNumberOfQuestions == 0){
         AlertDialog.Builder builder = new AlertDialog.Builder(this);

         builder.setTitle("Bien joué!")
                 .setMessage("Ton score est " + mNombrePoints)
                 .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       Intent _intent = new Intent();
                       _intent.putExtra(BUNDLE_EXTRA_SCORE, mNombrePoints);
                       setResult(RESULT_OK, _intent);
                       finish();
                    }
                 })
                 .create()
                 .show();
      }

      mCurrentQuestion = mBanqueQuestions.getQuestion();
      displayQuestion(mCurrentQuestion);
   }

   private Button mReponse1, mReponse2, mReponse3, mReponse4;
   private TextView mQuestionTexte;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_game);

      //Wiring widgets
      mQuestionTexte = (TextView)findViewById(R.id.activity_game_question_text);
      mReponse1 = (Button)findViewById(R.id.activity_game_answer1_btn);
      mReponse2 = (Button)findViewById(R.id.activity_game_answer2_btn);
      mReponse3 = (Button)findViewById(R.id.activity_game_answer3_btn);
      mReponse4 = (Button)findViewById(R.id.activity_game_answer4_btn);

      //Setting listeners
      mReponse1.setOnClickListener(this);
      mReponse2.setOnClickListener(this);
      mReponse3.setOnClickListener(this);
      mReponse4.setOnClickListener(this);

      // Use the tag property to 'name' the buttons
      mReponse1.setTag(0);
      mReponse2.setTag(1);
      mReponse3.setTag(2);
      mReponse4.setTag(3);

      mNumberOfQuestions = 3;
      mNombrePoints = 0;

      mBanqueQuestions = createQuestions();

      mCurrentQuestion = mBanqueQuestions.getQuestion();

      displayQuestion( mCurrentQuestion );

   }

   private BanqueQuestions createQuestions(){
      Question question1 = new Question("Who is the creator of Android?",
              Arrays.asList("Andy Rubin",
                      "Steve Wozniak",
                      "Jake Wharton",
                      "Paul Smith"),
              0);

      Question question2 = new Question("When did the first man land on the moon?",
              Arrays.asList("1958",
                      "1962",
                      "1967",
                      "1969"),
              3);

      Question question3 = new Question("What is the house number of The Simpsons?",
              Arrays.asList("42",
                      "101",
                      "666",
                      "742"),
              3);

      return new BanqueQuestions(Arrays.asList(question1,
              question2,
              question3));
   }

   private void displayQuestion(final Question question) {

      List<String> _mChoix = question.getChoix();

      mQuestionTexte.setText(question.getQuestion());
      mReponse1.setText(_mChoix.get(0));
      mReponse2.setText(_mChoix.get(1));
      mReponse3.setText(_mChoix.get(2));
      mReponse4.setText(_mChoix.get(3));
   }

   private boolean checkReponse( int reponseIndex ){
      return ( reponseIndex == mCurrentQuestion.getReponseIndex() );
   }
}