package com.lotto24.qacase

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.test.assertAll
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import com.lotto24.qacase.ui.LotteryComposeUITags
import com.lotto24.qacase.ui.LottoResultItem
import org.junit.Rule
import org.junit.Test

class LotteryBonusTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun checkIfAllLotteriesDisplayed() {
        // composing lottery cards

        val lotteryList = listOf(
            LotteryTestData.lottery6aus49(),
            LotteryTestData.euroJackpot()
            )

        composeRule.setContent {
            LazyColumn() {
                items(lotteryList) { lottery ->
                    LottoResultItem(lottery)
                }
            }
        }
        // Assertions for two lotteries
        val nodes = composeRule.onAllNodes(hasTestTag(LotteryComposeUITags.LOTTERY_ITEM_TAG))
        nodes.assertCountEquals(lotteryList.size)
    }
}