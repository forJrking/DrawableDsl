package com.github.forjrking.drawable

import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.math.roundToInt

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)

        val f = 5.5f
        val f1 = 5.6f
        val f2 = 5.4f
        val f3 = 5.8f
        val f4 = 5.1f

        println("1: ${toInt(f)} \t ${f.roundToInt()}")
        println("2: ${toInt(f1)} \t ${f1.roundToInt()}")
        println("3: ${toInt(f2)} \t ${f2.roundToInt()}")
        println("4: ${toInt(f3)} \t ${f3.roundToInt()}")
        println("5: ${toInt(f4)} \t ${f4.roundToInt()}")
    }


    fun toInt(f: Float): Int {
        return (f + .5f).toInt()
    }
}