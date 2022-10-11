package lib.ui

import io.appium.java_client.AppiumDriver
import org.openqa.selenium.By
import org.openqa.selenium.WebElement

class WelcomePageObject(driver: AppiumDriver<WebElement>): MainPageObject(driver) {

    private val STEP_LEARN_MORE_LINK = "id:Learn more about Wikipedia"
    private val STEP_NEW_WAYS_TO_EXPLORE_TEXT = "id:New ways to explore"
    private val STEP_ADD_OR_EDIT_PREFERRED_LANG_LINK = "id:Add or edit preferred lang text"
    private val STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK = "id:Learn more about data collected text"
    private val NEXT_LINK = "id:Next"
    private val BUTTON_GET_STARTED = "id:Get started"


    fun waitForLearnMoreLink() {
        this.waitForElementPresent(STEP_LEARN_MORE_LINK, "Cannot find 'Learn more about Wikipedia' link", 10)
    }

    fun clickButtonNext() {
        this.waitForElementAndClick(NEXT_LINK, "Cannot find and click button Next", 10)
    }

    fun waitForNewWayToExploreText() {
        this.waitForElementPresent(STEP_NEW_WAYS_TO_EXPLORE_TEXT, "Cannot find 'New ways to explore' link", 10)
    }

    fun waitForAddOrEditPreferredLangText() {
        this.waitForElementPresent(STEP_ADD_OR_EDIT_PREFERRED_LANG_LINK, "Cannot find 'Add or edit preferred lang text' link", 10)
    }

    fun waitForLearnMoreAboutDataCollectedText() {
        this.waitForElementPresent(STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK, "Cannot find 'Learn more about data collected text' link", 10)
    }

    fun clickButtonGetStarted() {
        this.waitForElementAndClick(BUTTON_GET_STARTED, "Cannot find and click button Get started", 10)
    }
}