package com.lotto24.qacase.ui
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lotto24.qacase.domain.model.LottoDomain
import com.lotto24.qacase.ui.theme.LottoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LottoScreen(
    viewModel: LottoViewModel = hiltViewModel()
) {
    val lottoResults by viewModel.lottoResults.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.safeDrawing.asPaddingValues())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(vertical = 16.dp)
    ) {
        items(lottoResults) { lotto ->
            LottoResultItem(lotto = lotto)
        }
    }
}

@Composable
fun LottoResultItem(lotto: LottoDomain) {
    Card(
        modifier = Modifier.fillMaxWidth().testTag(LotteryComposeUITags.LOTTERY_ITEM_TAG),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = lotto.lottery.uppercase(),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = Color.Gray,
                modifier = Modifier.testTag(LotteryComposeUITags.LOTTERY_TAG)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Last Draw: ${DateFormatter.format(lotto.lastDrawDate)}",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.testTag(LotteryComposeUITags.LOTTERY_LAST_DRAW_TAG)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .testTag(LotteryComposeUITags.LOTTERY_NUMBERS_ROW_TAG),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                lotto.numbers.forEach { number ->
                    NumberCircle(
                        number = number.toString(),
                        modifier = Modifier.testTag(LotteryComposeUITags.LOTTERY_NUMBER_TAG)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                }

                lotto.superNumber.forEach { number ->
                    if (lotto.isEuroJackpot) {
                        NumberCircle(
                            number = number.toString(),
                            modifier = Modifier.testTag(LotteryComposeUITags.LOTTERY_NUMBER_TAG)
                        )
                    } else {
                        SuperNumberCircle(
                            number = number.toString(),
                            modifier = Modifier.testTag(LotteryComposeUITags.LOTTERY_SUPER_NUMBER_TAG)
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Next Draw: ${DateFormatter.format(lotto.nextDrawDate)}",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.align(Alignment.End).testTag(LotteryComposeUITags.LOTTERY_NEXT_DRAW_TAG)
            )
        }
    }
}

@Composable
fun NumberCircle(number: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(40.dp)
            .clip(CircleShape)
            .background(Color.DarkGray),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = number,
            color = MaterialTheme.colorScheme.onPrimary,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun SuperNumberCircle(number: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(40.dp)
            .clip(CircleShape)
            .background(Color.Red),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = number,
            color = MaterialTheme.colorScheme.onTertiary,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}