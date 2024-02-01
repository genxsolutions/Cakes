package com.genxsol.cakesapp

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.hasScrollToNodeAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performScrollToNode
import com.genxsol.cakesapp.data.model.CakesItem
import com.genxsol.cakesapp.ui.base.ShowError
import com.genxsol.cakesapp.ui.base.ShowLoading
import com.genxsol.cakesapp.ui.components.CakesLayout
import org.junit.Rule
import org.junit.Test


class CakesScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun loading_whenShowLoading_isShown() {
        composeTestRule.setContent {
            ShowLoading()
        }

        composeTestRule
            .onNodeWithContentDescription(composeTestRule.activity.resources.getString(R.string.loading))
            .assertExists()
    }

    @Test
    fun articles_whenCakesLayout_isShown() {
        composeTestRule.setContent {
            CakesLayout(
                cakesList = testCakeList,
                cakeClicked = {}
            )
        }

        composeTestRule
            .onNodeWithText(
                testCakeList[0].title,
                substring = true
            )
            .assertExists()
            .assertHasClickAction()

        composeTestRule.onNode(hasScrollToNodeAction())
            .performScrollToNode(
                hasText(
                    testCakeList[1].title,
                    substring = true
                )
            )

        composeTestRule
            .onNodeWithText(
                testCakeList[2].title,
                substring = true
            )
            .assertExists()
            .assertHasClickAction()
    }

    @Test
    fun error_whenShowError_isShown() {
        val errorMessage = "Error Message For You"

        composeTestRule.setContent {
            ShowError(
                text = errorMessage
            )
        }

        composeTestRule
            .onNodeWithText(errorMessage)
            .assertExists()
    }

}

private val testCakeList = listOf(
    CakesItem(
        title = "title1",
        desc = "description1",
        image = "url1"
    ),
    CakesItem(
        title = "title2",
        desc = "description2",
        image = "url2"
    ),
    CakesItem(
        title = "title3",
        desc = "description3",
        image = "url3"
    ),
    CakesItem(
        title = "title4",
        desc = "description4",
        image = "url4"
    ),
)