import io.appium.java_client.AppiumDriver
import io.appium.java_client.TouchAction
import io.appium.java_client.android.AndroidDriver
import junit.framework.Assert.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.openqa.selenium.By
import org.openqa.selenium.Dimension
import org.openqa.selenium.TimeoutException
import org.openqa.selenium.WebDriverException
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
        capabilities.setCapability(
            "app",
            "/Users/okhlopkovaekaterina/Desktop/JavaAppiumAutomation/apks/org.wikipedia.apk"
        )

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

    @Test
    fun saveTwoArticlesIntoFolder() {
        val searchFirst = "Snowboarding"
        val searchSecond = "Ski"

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
            searchFirst,
            "Cannot find inside search line",
            5
        )

        waitForElementAndClick(
            By.xpath("//*[@class='android.view.ViewGroup'][@index='0']//*[@resource-id='org.wikipedia:id/page_list_item_title']"),
            "Cannot find first result of searching",
            15
        )

        waitForElementAndClick(
            By.xpath("//*[contains(@text, 'Save')]"),
            "Cannot find button Save",
            5
        )

        //2 поиск и сохранение статьи

        waitForElementAndClick(
            By.xpath("//*[contains(@text,'Search Wikipedia')]"),
            "Cannot find main search line",
            5
        )

        waitForElementAndSendKeys(
            By.id("org.wikipedia:id/search_src_text"),
            searchSecond,
            "Cannot find inside search line",
            5
        )

        waitForElementAndClick(
            By.xpath("//*[@class='android.view.ViewGroup'][@index='0']//*[@resource-id='org.wikipedia:id/page_list_item_title']"),
            "Cannot find first result of searching",
            15
        )

        waitForElementAndClick(
            By.xpath("//*[contains(@text, 'Save')]"),
            "Cannot find button Save",
            5
        )

        waitForElementAndClick(
            By.xpath("//*[@class='android.widget.ImageButton'][@content-desc='Navigate up']"),
            "Cannot find button back",
            5
        )

        waitForElementAndClick(
            By.xpath("//*[@class='android.widget.ImageButton'][@content-desc='Navigate up']"),
            "Cannot find button back",
            5
        )

        waitForElementAndClick(
            By.xpath("//*[@class='android.widget.ImageButton'][@index='0']"),
            "Cannot find button back",
            5
        )

        waitForElementAndClick(
            By.xpath("//*[@class='android.widget.FrameLayout'][@content-desc='Saved']"),
            "Cannot find button Saved",
            5
        )

        waitForElementAndClick(
            By.xpath("//*[@resource-id='org.wikipedia:id/recycler_view']//*[@resource-id='org.wikipedia:id/item_title']"),
            "Cannot find folder with saved articles",
            5
        )

        swipeElementToLeft(
            By.xpath("//*[contains(@text, '$searchSecond')]"),
            "Cannot find saved article"
        )

        waitForElementNotPresent(
            By.xpath("//*[contains(@text, '$searchSecond')]"),
            "Cannot delete saved article",
            5
        )

        waitForElementAndClick(
            By.xpath("//*[contains(@text, '$searchFirst')]"),
            "Cannot find article '$searchFirst'",
            5
        )

        waitForElementAndCheckContainsText(
            By.id("org.wikipedia:id/view_page_title_text"),
            searchFirst.toRegex(),
            "Cannot find title of article",
            15
        )
    }

    @Test
    fun checkArticleTitle() {
        val textSearch = "air"
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
            textSearch,
            "Cannot find inside search line",
            5
        )

        waitForElementAndClick(
            By.xpath("//*[@class='android.view.ViewGroup'][@index='0']//*[@resource-id='org.wikipedia:id/page_list_item_title']"),
            "Cannot find first result of searching",
            15
            )

        assertElementPresent(
            By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']/android.widget.LinearLayout[1]"),
            "Cannot find title of article"
        )
    }

    private fun waitForElementPresent(by: By, errorMessage: String, timeout: Long): WebElement {
        val wait = WebDriverWait(driver, timeout)
        wait.withMessage(errorMessage + "\n")
        return wait.until(ExpectedConditions.presenceOfElementLocated(by))
    }

    private fun elementPresent(by: By, errorMessage: String): WebElement {
        val wait = WebDriverWait(driver, 0)
        wait.withMessage(errorMessage + "\n")
        return wait.until(ExpectedConditions.presenceOfElementLocated(by))
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

    private fun swipeUp(timeOfSwipe: Int) {
        val action: TouchAction = TouchAction(driver)
        val size: Dimension = driver.manage().window().size
        val x = size.width / 2
        val startY  = (size.height * 0.8).toInt()
        val endY  = (size.height * 0.2).toInt()

        action
            .press(x, startY)
            .waitAction(timeOfSwipe)
            .moveTo(x, endY)
            .release()
            .perform()
    }

    private fun swipeUpQuick() {
        swipeUp(200)
    }

    private fun swipeUpToWaitElement(by: By, errorMessage: String, maxSwipes: Int, ) {
        var doneSwipes = 0

        while (driver.findElements(by).size == 0) {
            if (doneSwipes > maxSwipes) {
                waitForElementPresent(by, "Cannot find element by swiping up. \n" + errorMessage, 0)
                return
            }

            swipeUpQuick()
            ++doneSwipes
        }
    }

    private fun swipeElementToLeft(by: By, errorMessage: String) {
        val element: WebElement = waitForElementPresent(by, errorMessage, 10)
        val leftX = element.location.getX()
        val rightX = leftX + element.size.width
        val upperY = element.location.getY()
        val lowerY = upperY + element.size.height
        val middleY = (upperY + lowerY) / 2

        val action: TouchAction = TouchAction(driver)

        action
            .press(rightX, middleY)
            .waitAction(300)
            .moveTo(leftX, middleY)
            .release()
            .perform()
    }

    private fun getAmountOfElements(by: By): Int {
        val elements = driver.findElements(by)
        return elements.size
    }

    private fun assertElementNotPresent(by: By, errorMessage: String) {
        val amountOfElements = getAmountOfElements(by)

        if (amountOfElements > 0) {
            val defaultMessage = "An element '${by.toString()}' supposed to be not present"
            throw AssertionError(defaultMessage + "" + errorMessage)
        }
    }

    private fun assertElementPresent(by: By, errorMessage: String): WebElement {
        return elementPresent(by, errorMessage)
    }
}
