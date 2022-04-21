package com.example.j36

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }


    @Test
    fun testlongTask_WithThread(){
        var before=System.currentTimeMillis()
        Thread(Runnable{
            longTask()
        }).start()
        var after=System.currentTimeMillis()
        assertTrue(after-before<=40)
    }

    @Test
    fun testlongTask_WithOutThread(){
        var before=System.currentTimeMillis()
        longTask()
        var after=System.currentTimeMillis()
        assertTrue(after-before>=4000)
    }


    fun longTask(){
        Thread.sleep(4000)
    }


    @Test
    fun multiple_isCorrect(){
        assertEquals(6,2*3)
    }

    @Test
    fun testValidateName_isCorrect(){
        val str="Wererer"
        assertEquals(true,validateName(str))
    }

    @Test
    fun testValidateUpperCaseName_isInCorrect(){
        val str="wer"
        assertEquals(false,validateName(str))
    }

    @Test
    fun testValidateLengthName_isInCorrect(){
        val str="werfghgfhgdfg"
        assertEquals(false,validateName(str))
    }


    fun validateName(name:String):Boolean{
        return name.length<10 && name[0].isUpperCase()
    }
}