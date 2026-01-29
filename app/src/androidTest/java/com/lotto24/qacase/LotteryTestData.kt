package com.lotto24.qacase

import com.lotto24.qacase.domain.model.LottoDomain

object LotteryTestData {
    fun lottery6aus49(): LottoDomain = LottoDomain(
        lottery = "6aus49",
        lastDrawDate = "2026-01-26",
        nextDrawDate = "2026-01-31",
        numbers = listOf(2, 14, 16, 23, 11, 49),
        superNumber = listOf(5),
        isEuroJackpot = false
    )

    fun euroJackpot(): LottoDomain = LottoDomain(
        lottery = "EuroJackpot",
        lastDrawDate = "2026-01-26",
        nextDrawDate = "2026-01-31",
        numbers = listOf(11, 22, 33, 44, 49),
        superNumber = listOf(3, 12),
        isEuroJackpot = true
    )

}