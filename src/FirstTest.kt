import io.appium.java_client.AppiumDriver
import io.appium.java_client.android.AndroidDriver
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.DesiredCapabilities
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
        capabilities.setCapability("appPackage", "org.Wikipedia")
        capabilities.setCapability("appActivity", ".main.MainActivity")
        capabilities.setCapability("app", "/Users/okhlopkovaekaterina/Desktop/JavaAppiumAutomation/apks/wiki.apk")

        driver = AndroidDriver(URL("http://127.0.0.1:4723/ws/hub"), capabilities)
        }

    @After
    fun tearDown() {
        driver.quit()
    }

    @Test
    fun firstTest() {
        println("First Test Run")
    }
}
