import org.junit.Test
import kotlin.test.*

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
        val word = "[Hh]ello".toRegex()

        assertContains(
            getClassString(),
            word,
            message = "Error. '${getClassString()}' is not contains 'Hello' or 'hello'"
        )
    }
}
