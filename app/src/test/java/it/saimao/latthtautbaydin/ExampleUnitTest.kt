package it.saimao.latthtautbaydin

import it.saimao.latthtautbaydin.ui.Utility
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
    fun test_myanmarToEng() {
        val engNumber = Utility.convertToEngNum("၁")
        assertEquals(engNumber, 1)
        assertEquals(Utility.convertToEngNum("၈၄"), 84)
    }

}