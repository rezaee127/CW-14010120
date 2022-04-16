package com.example.j36

import Question
import QuestionDao
import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Database

@Database(entities = [Question::class],version=2)
abstract class Database3 : RoomDatabase() {

    abstract fun questionDao(): QuestionDao

    companion object {
        private var INSTANCE: Database3? = null

        fun getMyDataBase(context: Context): Database3? {
            if (INSTANCE == null){
                synchronized(Database3::class){
                    INSTANCE =
                        Room.databaseBuilder(context.applicationContext,
                            Database3::class.java, "DB")
                            .allowMainThreadQueries()
                            .build()
                }
            }
            return INSTANCE
        }



        fun destroyDataBase(){
            INSTANCE = null
        }

    }

}
