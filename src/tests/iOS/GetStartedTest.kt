package tests.iOS

import lib.iOSTestCase
import lib.ui.WelcomePageObject
import org.junit.Test

class GetStartedTest: iOSTestCase() {

    @Test
    fun testWelcome() {
        val WelcomePageObject: WelcomePageObject = WelcomePageObject(driver)

        WelcomePageObject.waitForLearnMoreLink()
        WelcomePageObject.clickButtonNext()

        WelcomePageObject.waitForNewWayToExploreText()
        WelcomePageObject.clickButtonNext()

        WelcomePageObject.waitForAddOrEditPreferredLangText()
        WelcomePageObject.clickButtonNext()

        WelcomePageObject.waitForLearnMoreAboutDataCollectedText()
        WelcomePageObject.clickButtonGetStarted()
    }
}