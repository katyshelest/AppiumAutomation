import org.junit.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class MainClassTest : MainClass() {
    @Test
    fun testGetLocalNumber() {
        val number = 14
        assertEquals(number, getLocalNumber(), "Wrong number")
    }

    @Test
    fun testGetClassNumber() {
        val number = 45
        assertTrue(getClassNumber() > number, "Error, '${getClassNumber()}' is less than '$number'")
    }

    @Test
    fun testGetClassString() {
        val word = "hello"
        assertContains(
            getClassString(),
            word,
            ignoreCase = true,
            message = "Error. '${getClassString()}' is not contains '$word'"
        )
    }
}
