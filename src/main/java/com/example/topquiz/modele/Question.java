package com.example.topquiz.modele;

import java.util.List;

public class Question {

   private String mQuestion;
   private List<String> mChoix;
   private int mReponseIndex;


   public Question(String question, List<String> choix, int reponseIndex) {
      mQuestion = question;
      mChoix = choix;
      mReponseIndex = reponseIndex;
   }


   public String getQuestion() {
      return mQuestion;
   }

   public void setQuestion(String question) {
      mQuestion = question;
   }

   public List<String> getChoix() {
      return mChoix;
   }

   public void setChoix(List<String> choix) {
      if ( !choix.isEmpty() )
         mChoix = choix;
   }

   public int getReponseIndex() {
      return mReponseIndex;
   }

   public void setReponseIndex(int reponseIndex) {
      if ( reponseIndex > 0 && reponseIndex < mChoix.size() )
      mReponseIndex = reponseIndex;
   }
}
