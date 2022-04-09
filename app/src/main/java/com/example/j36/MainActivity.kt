package com.example.j36

import MainViewModel
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer

class MainActivity : AppCompatActivity() {
    val vmodel: MainViewModel by viewModels()
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


        progressBar.max = vmodel.questionCount

        val messageObserver = Observer<String> {
            textViewMessage.text = it
        }

        val numberObserver = Observer<Int> { number ->
            textView.text = (number + 1).toString()
            progressBar.progress = number + 1
        }

        val buttonEnabledObserver = Observer<Boolean> { enabled ->
            buttonNext.isEnabled = enabled
        }

        val questionObserver = Observer<String> { question ->
            questionText.text = question
        }

        vmodel.messageLiveData.observe(this, messageObserver)
        vmodel.questionLiveData.observe(this, questionObserver)
        vmodel.nextEnabledLiveData.observe(this, buttonEnabledObserver)
        vmodel.numberLiveData.observe(this, numberObserver)

        vmodel.backEnabledLiveData.observe(this) {
            buttonBack.isEnabled = it
        }

        vmodel.colorFalseLiveData.observe(this) {
            buttonFalse.setBackgroundColor(ContextCompat.getColor(this, it))
        }

        vmodel.colorTrueLiveData.observe(this) {
            buttonTrue.setBackgroundColor(ContextCompat.getColor(this, it))
        }

        vmodel.statusButtonLLiveData.observe(this) {
            buttonTrue.isEnabled = it
            buttonFalse.isEnabled = it
        }

        vmodel.scoreLiveData.observe(this) {
            textViewScore.text = it.toString()
        }



        buttonNext.setOnClickListener {
            vmodel.nextClicked()
        }

        buttonBack.setOnClickListener {
            vmodel.backClicked()
        }

        buttonTrue.setOnClickListener {
            vmodel.trueButtonClicked()
        }

        buttonFalse.setOnClickListener {
            vmodel.falseButtonClicked()
        }
    }
}