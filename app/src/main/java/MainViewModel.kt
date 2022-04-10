import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.j36.R

class MainViewModel : ViewModel() {

    val numberLiveData = MutableLiveData<Int>(0)
    val scoreLiveData = MutableLiveData(0)
    val colorTrueLiveData = MutableLiveData(R.color.purple_500)
    val colorFalseLiveData = MutableLiveData(R.color.purple_500)
    val isEnableButtonLLiveData = MutableLiveData<Boolean>(true)
    var nextEnabledLiveData = MutableLiveData<Boolean>(true)
    var backEnabledLiveData = MutableLiveData<Boolean>(false)
    val questionLiveData = MutableLiveData<String>(QuestionRepository.questionList[0].question)
    val questionCount = QuestionRepository.questionList.size

    val messageLiveData = Transformations.map(numberLiveData) {
        when {
            it < questionCount / 2 -> "خیلی مونده"
            it > questionCount / 2 -> "داری میرسی"
            else -> "ادامه بده"
        }

    }




    private fun setQuestion(i: Int) {
        when (i) {
            0 -> backEnabledLiveData.value = false
            questionCount - 1 -> nextEnabledLiveData.value = false
            else -> {
                nextEnabledLiveData.value = true
                backEnabledLiveData.value = true
            }
        }
        isEnableButtonLLiveData.value=true
        questionLiveData.value = QuestionRepository.questionList[i].question
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
            if (QuestionRepository.questionList[it].answer) {
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
            if (!QuestionRepository.questionList[it].answer) {
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