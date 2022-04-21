package com.example.j36


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Question::class], version = 3)
abstract class Database3 : RoomDatabase() {

    abstract fun questionDao(): QuestionDao

    companion object {
        @Volatile
        private var INSTANCE: Database3? = null

        fun getMyDataBase(context: Context): Database3 {
            val tempInstance = INSTANCE
            if (tempInstance != null)
                return tempInstance

            synchronized(Database3::class) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    Database3::class.java, "MyDb"
                )
                    .allowMainThreadQueries()
                    //.fallbackToDestructiveMigration()
                    .build()
                INSTANCE=instance
                return  instance
            }
        }


    fun destroyDataBase() {
        INSTANCE = null
    }

}

}
