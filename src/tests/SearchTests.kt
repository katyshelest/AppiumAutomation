package tests

import lib.CoreTestCase
import lib.ui.ArticlePageObject
import lib.ui.NavigationUI
import lib.ui.SavedListsObject
import lib.ui.SearchPageObject
import org.junit.Test

class SearchTests: CoreTestCase() {

    @Test
    fun testSearch() {
        val SearchPageObject: SearchPageObject = SearchPageObject(driver)

        SearchPageObject.clickButtonSkip()
        SearchPageObject.initSearchInput()
        SearchPageObject.typeSearchLine("Java")
        SearchPageObject.waitForSearchResult("Object-oriented programming language")
    }

    @Test
    fun testCancelSearch() {
        val SearchPageObject: SearchPageObject = SearchPageObject(driver)

        SearchPageObject.clickButtonSkip()
        SearchPageObject.initSearchInput()
        SearchPageObject.typeSearchLine("forest")
        SearchPageObject.waitForCancelButtonToAppear()
        SearchPageObject.clickButtonCancelSearch()
        SearchPageObject.waitForCancelButtonToDisappear()
        SearchPageObject.waitForSearchResultEmptyMessage()
    }
}