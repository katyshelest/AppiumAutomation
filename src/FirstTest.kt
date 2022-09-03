import io.appium.java_client.AppiumDriver
import io.appium.java_client.android.AndroidDriver
import junit.framework.Assert.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.net.URL

class FirstTest {

    private lateinit var driver: AppiumDriver<WebElement>

    @Before
    fun setUp() {
        val capabilities = DesiredCapabilities()

        capabilities.setCapability("platformName", "Android")
        capabilities.setCapability("deviceName", "Nexus_5")
        capabilities.setCapability("platformVersion", "10.0")
        capabilities.setCapability("automationName", "Appium")
        capabilities.setCapability("appPackage", "org.wikipedia")
        capabilities.setCapability("appActivity", ".main.MainActivity")
        capabilities.setCapability("app", "/Users/okhlopkovaekaterina/Desktop/JavaAppiumAutomation/apks/org.wikipedia.apk")

        driver = AndroidDriver(URL("http://127.0.0.1:4723/wd/hub"), capabilities)
    }

    @After
    fun tearDown() {
        driver.quit()
    }

    @Test
    fun checkTextSearch() {
        waitForElementAndClick(
            By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
            "Cannot find button 'Skip'",
            5
        )

        assertElementHasText(
            By.xpath("//*[contains(@text,'Search Wikipedia')]"),
            "Search Wikipedia",
            "Cannot find main search line",
            5
        )
    }

    private fun waitForElementPresent(by: By, errorMessage: String, timeout: Long): WebElement {
        val wait = WebDriverWait(driver, timeout)
        wait.withMessage(errorMessage + "\n")
        return wait.until(ExpectedConditions.presenceOfElementLocated(by))
    }

    private fun waitForElementPresent(by: By, errorMessage: String): WebElement {
        return waitForElementPresent(by, errorMessage, 5)
    }

    private fun waitForElementAndClick(by: By, errorMessage: String, timeout: Long): WebElement {
        val element: WebElement = waitForElementPresent(by, errorMessage, timeout)
        element.click()
        return element
    }

    private fun assertElementHasText(by: By, text: String, errorMessage: String, timeout: Long) {
        val element: WebElement = waitForElementPresent(by, errorMessage, timeout)
        val textElement = element.text
        assertEquals(
            "We see unexpected text",
            text,
            textElement
        )
    }
}
