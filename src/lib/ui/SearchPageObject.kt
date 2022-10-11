package lib.ui

import io.appium.java_client.AppiumDriver
import org.openqa.selenium.By
import org.openqa.selenium.WebElement

class SearchPageObject(driver: AppiumDriver<WebElement>): MainPageObject(driver) {
    private val BUTTON_SKIP = "org.wikipedia:id/fragment_onboarding_skip_button"
    private val SEARCH_INIT_ELEMENT = "//*[contains(@text,'Search Wikipedia')]"
    private val SEARCH_INPUT = "org.wikipedia:id/search_src_text"
    private val SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/search_results_container']//*[@text='{SUBSTRING}']"
    private val SEARCH_RESULT = "//*[@class='android.view.ViewGroup'][@index='0']//*[@resource-id='org.wikipedia:id/page_list_item_title']"
    private val SEARCH_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn"
    private val SEARCH_RESULT_EMPTY_MESSAGE = "org.wikipedia:id/search_empty_message"

    /* TEMPLATES METHODS */
    fun getResultSearchElement(substring: String): String {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring)
    }
    /* TEMPLATES METHODS */

    fun clickButtonSkip() {
        this.waitForElementAndClick(By.id(BUTTON_SKIP), "Cannot find and click button Skip", 5)
    }

    fun initSearchInput() {
        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find and click search init element", 5)
        this.waitForElementPresent(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find search input after clicking search init element", 5)
    }

    fun waitForCancelButtonToAppear() {
        this.waitForElementPresent(By.id(SEARCH_CANCEL_BUTTON), "Cannot find search cancel button", 5)
    }

    fun waitForCancelButtonToDisappear() {
        this.waitForElementNotPresent(By.id(SEARCH_CANCEL_BUTTON), "Search line button is still present", 5)
    }

    fun clickButtonCancelSearch() {
        this.waitForElementAndClick(By.id(SEARCH_CANCEL_BUTTON), "Cannot find and click cancel button", 5)
    }

    fun waitForSearchResultEmptyMessage() {
        this.waitForElementPresent(By.id(SEARCH_RESULT_EMPTY_MESSAGE), "Cannot find empty message at search result", 5)
    }

    fun typeSearchLine(search_line: String) {
        this.waitForElementAndSendKeys(By.id(SEARCH_INPUT), search_line, "Cannot find and type into search line", 5)
    }

    fun waitForSearchResult(substring: String) {
        val search_result_substring = getResultSearchElement(substring)
        this.waitForElementPresent(By.xpath(search_result_substring), "Cannot find search result with substring + $substring", 5)
    }

    fun clickByArticleWithSubstring(substring: String) {
        val search_result_substring = getResultSearchElement(substring)
        this.waitForElementAndClick(By.xpath(search_result_substring), "Cannot find and click search result with substring + $substring", 10)
    }

    fun clickAtSearchResult() {
        this.waitForElementAndClick(By.xpath(SEARCH_RESULT), "Cannot find and click search result", 10)
    }
}