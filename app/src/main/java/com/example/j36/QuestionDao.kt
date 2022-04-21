package com.example.j36


import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface QuestionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(question: Question)

    @Query("SELECT COUNT(*) FROM Question" )
    fun getCountQuestion():Int

    @Query("SELECT COUNT(*) FROM Question" )
    fun getCountQuestionLiveData():LiveData<Int>


    @Query("SELECT * FROM Question WHERE id IN(:id)")
    fun getQuestion(id:Int): Question

    @Query("DELETE FROM Question")
    fun deleteAll()


}