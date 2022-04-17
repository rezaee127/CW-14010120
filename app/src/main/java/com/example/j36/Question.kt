package com.example.j36


import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity
data class Question(@PrimaryKey(autoGenerate=true)  val id:Int, val question:String, val answer:Boolean)