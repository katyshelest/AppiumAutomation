package lib

import io.appium.java_client.AppiumDriver
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.ios.IOSDriver
import junit.framework.TestCase
import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.DesiredCapabilities
import java.net.URL

open class iOSTestCase: TestCase() {

    lateinit var driver: AppiumDriver<WebElement>
    val appiumURL = "http://127.0.0.1:4723/wd/hub"

    override fun setUp() {
        super.setUp()

        val capabilities = DesiredCapabilities()

        capabilities.setCapability("platformName", "iOS")
        capabilities.setCapability("deviceName", "iPhone 8")
        capabilities.setCapability("platformVersion", "13.7")
        capabilities.setCapability(
            "app",
            "/Users/okhlopkovaekaterina/Desktop/JavaAppiumAutomation/apks/wiki.app"
        )

        driver = IOSDriver(URL(appiumURL), capabilities)
    }

    override fun tearDown() {
        driver.quit()

        super.tearDown()
    }
}