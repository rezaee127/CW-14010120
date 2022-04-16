import androidx.room.Entity

//object QuestionRepository {
//    val questionList = arrayListOf(
//        Question("  2 + 2 = 9",false) ,
//        Question("   5 - 1 = 4 " ,true),
//        Question( " 100 * 21 =2100",true))
//}

@Entity
data class Question(val id:Int,val question:String,val answer:Boolean)