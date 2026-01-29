package com.lotto24.qacase

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertHasNoClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.lotto24.qacase.ui.DateFormatter
import com.lotto24.qacase.ui.LotteryComposeUITags
import com.lotto24.qacase.ui.LottoResultItem
import org.junit.Rule
import org.junit.Test

class Lottery6aus49DrawUITest {
    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun correctDataDisplay() {
        // composing
        val lottery = LotteryTestData.lottery6aus49()
        val lotteryLastDrawDate = "Last Draw: ${DateFormatter.format(dateString = lottery.lastDrawDate) }"
        val lotteryNextDrawDate = "Next Draw: ${DateFormatter.format(dateString = lottery.nextDrawDate) }"

        composeRule.setContent {
            LottoResultItem(lottery)
        }
        // Assertions
        composeRule.onNodeWithTag(LotteryComposeUITags.LOTTERY_ITEM_TAG).assertIsDisplayed()
        composeRule.onNodeWithText(lottery.lottery.uppercase()).assertIsDisplayed()
        composeRule.onNodeWithText(lotteryLastDrawDate).assertIsDisplayed()
        composeRule.onNodeWithText(lotteryNextDrawDate).assertIsDisplayed()
    }

    @Test
    fun checkNumberRendering() {
        // composing
        val lottery = LotteryTestData.lottery6aus49()

        composeRule.setContent {
            LottoResultItem(lottery)
        }
        // Assertions for 6 regular numbers
        composeRule.onAllNodesWithTag(LotteryComposeUITags.LOTTERY_NUMBER_TAG).assertCountEquals(6)
        composeRule.onNodeWithTag(LotteryComposeUITags.LOTTERY_NUMBERS_ROW_TAG).assertIsDisplayed()
        composeRule.onNodeWithTag(LotteryComposeUITags.LOTTERY_NUMBERS_ROW_TAG).assertHasNoClickAction()

        // Assertions for super number
        composeRule.onAllNodesWithTag(LotteryComposeUITags.LOTTERY_SUPER_NUMBER_TAG).assertCountEquals(1)
        composeRule.onNodeWithTag(LotteryComposeUITags.LOTTERY_SUPER_NUMBER_TAG).assertIsDisplayed()
        composeRule.onNodeWithTag(LotteryComposeUITags.LOTTERY_SUPER_NUMBER_TAG).assertHasNoClickAction()
    }
}