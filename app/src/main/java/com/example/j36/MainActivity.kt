package com.example.j36

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer

class MainActivity : AppCompatActivity() {
    private val vModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        val textView = findViewById<TextView>(R.id.tvNumber)
        val buttonNext = findViewById<Button>(R.id.nextButton)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val questionText = findViewById<TextView>(R.id.tvQuestion)
        val buttonBack = findViewById<Button>(R.id.backButton)
        val textViewMessage = findViewById<TextView>(R.id.tvMessage)
        val buttonTrue = findViewById<Button>(R.id.btn_true)
        val buttonFalse = findViewById<Button>(R.id.btn_false)
        val textViewScore = findViewById<TextView>(R.id.tv_score)
        val buttonAddQuestion=findViewById<Button>(R.id.buttonAddQuestion)
        val textViewCount=findViewById<TextView>(R.id.textViewCount)


        val messageObserver = Observer<String> {
            textViewMessage.text = it
        }

        val numberObserver = Observer<Int> { number ->
            textView.text = (number).toString()
            progressBar.progress = number
        }

        val buttonEnabledObserver = Observer<Boolean> { enabled ->
            buttonNext.isEnabled = enabled
        }

        val questionObserver = Observer<String> { question ->
            questionText.text = question
        }

        vModel.messageLiveData.observe(this, messageObserver)
        vModel.questionLiveData.observe(this, questionObserver)
        vModel.nextEnabledLiveData.observe(this, buttonEnabledObserver)
        vModel.numberLiveData.observe(this, numberObserver)

        vModel.backEnabledLiveData.observe(this) {
            buttonBack.isEnabled = it
        }

        vModel.colorFalseLiveData.observe(this) {
            buttonFalse.setBackgroundColor(ContextCompat.getColor(this, it))
        }

        vModel.colorTrueLiveData.observe(this) {
            buttonTrue.setBackgroundColor(ContextCompat.getColor(this, it))
        }

        vModel.colorScoreTextLiveData.observe(this){
            textViewScore.setTextColor(ContextCompat.getColor(this, it))
        }

        vModel.isEnableButtonLLiveData.observe(this) {
            buttonTrue.isEnabled = it
            buttonFalse.isEnabled = it
        }

        vModel.scoreLiveData.observe(this) {
            textViewScore.text = it.toString()
        }



        buttonNext.setOnClickListener {
            vModel.nextClicked()
        }

        buttonBack.setOnClickListener {
            vModel.backClicked()
        }

        buttonTrue.setOnClickListener {
            vModel.trueButtonClicked()
        }

        buttonFalse.setOnClickListener {
            vModel.falseButtonClicked()
        }

        buttonAddQuestion.setOnClickListener {
            vModel.addQuestion()
        }

        vModel.questionCount?.observe(this){
            progressBar.max=it
            textViewCount.text=it.toString()
        }

    }
}