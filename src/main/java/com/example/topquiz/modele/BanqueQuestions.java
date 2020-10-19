package com.example.topquiz.modele;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class BanqueQuestions {

   private List<Question> mQuestionList;
   private int mNextQuestionIndex;

   public BanqueQuestions(List<Question> questionList) {

      mNextQuestionIndex = 0;

      mQuestionList = questionList;

      Collections.shuffle(mQuestionList);
   }

   public Question getQuestion() {

      if ( mNextQuestionIndex == mQuestionList.size() )
         mNextQuestionIndex = 0;

      return mQuestionList.get(mNextQuestionIndex++);
   }
}
