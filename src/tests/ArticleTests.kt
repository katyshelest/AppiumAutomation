package tests

import lib.CoreTestCase
import lib.ui.ArticlePageObject
import lib.ui.NavigationUI
import lib.ui.SavedListsObject
import lib.ui.SearchPageObject
import org.junit.Test

class ArticleTests: CoreTestCase()  {

    @Test
    fun testCheckArticleTitle() {
        val SearchPageObject: SearchPageObject = SearchPageObject(driver)
        val ArticlePageObject: ArticlePageObject = ArticlePageObject(driver)

        SearchPageObject.clickButtonSkip()
        SearchPageObject.initSearchInput()
        SearchPageObject.typeSearchLine("air")
        SearchPageObject.clickAtSearchResult()
        val article_title = ArticlePageObject.getArticleTitle()
        assertEquals(
            "We see unexpected title",
            "Atmosphere of Earth",
            article_title
        )
    }

    @Test
    fun testSaveTwoArticlesIntoFolder() {
        val SearchPageObject: SearchPageObject = SearchPageObject(driver)
        val ArticlePageObject: ArticlePageObject = ArticlePageObject(driver)
        val NavigationUI: NavigationUI = NavigationUI(driver)
        val SavedListsObject: SavedListsObject = SavedListsObject(driver)

        val searchFirst = "Snowboarding"
        val searchSecond = "Ski"

        SearchPageObject.clickButtonSkip()
        SearchPageObject.initSearchInput()
        SearchPageObject.typeSearchLine(searchFirst)
        SearchPageObject.clickAtSearchResult()
        ArticlePageObject.clickButtonSave()

        //2 статья
        SearchPageObject.initSearchInput()
        SearchPageObject.typeSearchLine(searchSecond)
        SearchPageObject.clickAtSearchResult()
        ArticlePageObject.clickButtonSave()

        //Удаление статьи
        NavigationUI.clickButtonBackArticle()
        NavigationUI.clickButtonBackArticle()
        NavigationUI.clickButtonBackMain()
        NavigationUI.clickButtonSaved()
        SavedListsObject.clickOnSavedFolder()
        SavedListsObject.swipeArticle(searchSecond)

        //Проверка статьи
        SavedListsObject.waitForArticleAndClick(searchFirst)
        val article_title = ArticlePageObject.getArticleTitle()
        assertEquals(
            "We see unexpected title",
            searchFirst,
            article_title
        )
    }
}