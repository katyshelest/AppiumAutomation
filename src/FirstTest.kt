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

    @Test
    fun checkCancelSearch() {
        waitForElementAndClick(
            By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
            "Cannot find button 'Skip'",
            5
        )

        waitForElementAndClick(
            By.xpath("//*[contains(@text,'Search Wikipedia')]"),
            "Cannot find main search line",
            5
        )

        waitForElementAndSendKeys(
            By.id("org.wikipedia:id/search_src_text"),
            "forest",
            "Cannot find inside search line",
            5
        )

        waitForElementPresent(
            By.xpath("(//*[@class='android.view.ViewGroup'])[1]"),
            "Cannot find first result of searching",
            15
        )

        waitForElementPresent(
            By.xpath("(//*[@class='android.view.ViewGroup'])[2]"),
            "Cannot find second result of searching",
            15
        )

        waitForElementAndClear(
            By.id("org.wikipedia:id/search_src_text"),
            "Cannot find inside search line",
            5
        )

        waitForElementPresent(
            By.id("org.wikipedia:id/search_empty_message"),
            "Search results is not empty",
            15
        )
    }

    @Test
    fun checkSearchResults() {
        waitForElementAndClick(
            By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
            "Cannot find button 'Skip'",
            5
        )

        waitForElementAndClick(
            By.xpath("//*[contains(@text,'Search Wikipedia')]"),
            "Cannot find main search line",
            5
        )

        waitForElementAndSendKeys(
            By.id("org.wikipedia:id/search_src_text"),
            "air",
            "Cannot find inside search line",
            5
        )

        waitForElementAndCheckContainsText(
            By.xpath("//*[@class='android.view.ViewGroup'][@index='1']//*[@resource-id='org.wikipedia:id/page_list_item_title']"),
            "[Aa]ir".toRegex(),
            "Cannot find first result of searching",
            15
        )

        waitForElementAndCheckContainsText(
            By.xpath("//*[@class='android.view.ViewGroup'][@index='2']//*[@resource-id='org.wikipedia:id/page_list_item_title']"),
            "[Aa]ir".toRegex(),
            "Cannot find second result of searching",
            15
        )

        waitForElementAndCheckContainsText(
            By.xpath("//*[@class='android.view.ViewGroup'][@index='3']//*[@resource-id='org.wikipedia:id/page_list_item_title']"),
            "[Aa]ir".toRegex(),
            "Cannot find third result of searching",
            15
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

    private fun waitForElementAndSendKeys(by: By, text: String, errorMessage: String, timeout: Long): WebElement {
        val element: WebElement = waitForElementPresent(by, errorMessage, timeout)
        element.sendKeys(text)
        return element
    }

    private fun waitForElementNotPresent(by: By, errorMessage: String, timeout: Long): Boolean {
        val wait = WebDriverWait(driver, timeout)
        wait.withMessage(errorMessage + "\n")
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by))
    }

    private fun waitForElementAndClear(by: By, errorMessage: String, timeout: Long): WebElement {
        val element: WebElement = waitForElementPresent(by, errorMessage, timeout)
        element.clear()
        return element
    }

    private fun waitForElementAndCheckContainsText(by: By, text: Regex, errorMessage: String, timeout: Long) {
        val element: WebElement = waitForElementPresent(by, errorMessage, timeout)
        val textElement = element.text
        assertTrue("'${textElement}' not contains '${text}'", textElement.contains(text))
    }
}
