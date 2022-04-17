package com.example.j36


import android.content.Context
import androidx.lifecycle.LiveData



object Repository {

    var db: Database3? = null
    var questionDao: QuestionDao? = null


    fun initDB(context: Context) {
        db = Database3.getMyDataBase(context)
        questionDao = db?.questionDao()
    }


    private fun newRandomQuestion(): Question {
        val num1 = (1..100).random()
        val num2 = (1..20).random()
        val answer = num1 - num2
        val rand = (1..2).random()
        val question: Question

        if (rand == 1) {
            question = Question(0, "$num1 - $num2 = $answer", true)
        } else {

            var falseAnswer = (answer-3 until answer).random()
//            var flag = true
//            while (flag) {
//                if (falseAnswer == answer) {
//                    falseAnswer = (1..99).random()
//                } else
//                    flag = false
//            }
            question = Question(0, "$num1 - $num2 = $falseAnswer", false)
        }
        return question
    }

    fun setQuestion() {
        questionDao?.insert(newRandomQuestion())
    }

    fun getQuestion(id:Int): Question?{
        return questionDao?.getQuestion(id)
    }

    fun getCountQuestion():Int?{
        return questionDao?.getCountQuestion()
    }

    fun getCountQuestionLiveData():LiveData<Int>?{
        return questionDao?.getCountQuestionLiveData()
    }


}