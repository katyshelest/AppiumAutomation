package lib

import io.appium.java_client.AppiumDriver
import io.appium.java_client.android.AndroidDriver
import junit.framework.TestCase
import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.DesiredCapabilities
import java.net.URL

open class CoreTestCase: TestCase() {

    lateinit var driver: AppiumDriver<WebElement>
    val appiumURL = "http://127.0.0.1:4723/wd/hub"

    override fun setUp() {
        super.setUp()

        val capabilities = DesiredCapabilities()

        capabilities.setCapability("platformName", "Android")
        capabilities.setCapability("deviceName", "Nexus5")
        capabilities.setCapability("platformVersion", "11.0")
        capabilities.setCapability("automationName", "Appium")
        capabilities.setCapability("appPackage", "org.wikipedia")
        capabilities.setCapability("appActivity", ".main.MainActivity")
        capabilities.setCapability(
            "app",
            "/Users/okhlopkovaekaterina/Desktop/JavaAppiumAutomation/apks/org.wikipedia.apk"
        )

        driver = AndroidDriver(URL(appiumURL), capabilities)
    }

    override fun tearDown() {
        driver.quit()

        super.tearDown()
    }
}