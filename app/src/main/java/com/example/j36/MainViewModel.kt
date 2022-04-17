package com.example.j36

import android.app.Application
import androidx.lifecycle.*



class MainViewModel (app: Application): AndroidViewModel(app) {

    val numberLiveData = MutableLiveData<Int>(1)
    val scoreLiveData = MutableLiveData(0)
    val colorTrueLiveData = MutableLiveData(R.color.purple_500)
    val colorFalseLiveData = MutableLiveData(R.color.purple_500)
    val isEnableButtonLLiveData = MutableLiveData<Boolean>(true)
    var nextEnabledLiveData = MutableLiveData<Boolean>(true)
    var backEnabledLiveData = MutableLiveData<Boolean>(false)
    var questionLiveData:MutableLiveData<String>
    var questionCount: LiveData<Int>?
    var colorScoreTextLiveData:LiveData<Int>
    var messageLiveData:LiveData<String>

    init{
        Repository.initDB(app.applicationContext)
        addQuestion()
        addQuestion()
        addQuestion()
        questionLiveData = MutableLiveData<String>(Repository.getQuestion(1)?.question)
        questionCount = Repository.getCountQuestionLiveData()

        messageLiveData = Transformations.map(numberLiveData) {
            var x="خیلی مونده"
            if(questionCount?.value!=null){
            x = when {
                it < questionCount?.value!!/2 -> "خیلی مونده"
                it > questionCount?.value!!/2 -> "داری میرسی"
                else -> "ادامه بده"
            }}
             x
        }

        colorScoreTextLiveData = Transformations.map(scoreLiveData) {
            when {
                it in 10..20 -> R.color.yellow
                it<10 -> R.color.red
                else -> R.color.green
            }
        }

    }


    fun addQuestion(){
        Repository.setQuestion()

    }


    private fun setQuestion(i: Int) {
        when (i) {
            1 -> backEnabledLiveData.value = false
            questionCount?.value -> nextEnabledLiveData.value = false
            else -> {
                nextEnabledLiveData.value = true
                backEnabledLiveData.value = true
            }
        }
        isEnableButtonLLiveData.value=true
        questionLiveData.value =  Repository.getQuestion(i)?.question
    }

    fun click() {
        numberLiveData.value?.let {
            setQuestion(it)
        }
        colorTrueLiveData.value = R.color.purple_500
        colorFalseLiveData.value = R.color.purple_500
    }

    fun nextClicked() {
        numberLiveData.value = numberLiveData.value?.plus(1)
        click()
    }

    fun backClicked() {
        numberLiveData.value = numberLiveData.value?.minus(1)
        click()
    }

    fun trueButtonClicked() {
        numberLiveData.value?.let {
            if (Repository.getQuestion(it)?.answer == true) {
                scoreLiveData.value = scoreLiveData.value?.plus(5)
                colorTrueLiveData.value = R.color.green
            } else {
                scoreLiveData.value = scoreLiveData.value?.minus(5)
                colorTrueLiveData.value = R.color.red
            }
        }
        isEnableButtonLLiveData.value=false
    }

    fun falseButtonClicked() {
        numberLiveData.value?.let {
            if (Repository.getQuestion(it)?.answer==false) {
                scoreLiveData.value = scoreLiveData.value?.plus(5)
                colorFalseLiveData.value = R.color.green
            } else {
                scoreLiveData.value = scoreLiveData.value?.minus(5)
                colorFalseLiveData.value = R.color.red
            }
        }
        isEnableButtonLLiveData.value=false
    }

}