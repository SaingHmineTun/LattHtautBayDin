package it.saimao.latthtautbaydin.ui.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import it.saimao.latthtautbaydin.R
import it.saimao.latthtautbaydin.ui.Utility
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random


@Composable
fun ChooseNumberScreen(
    onSelectNumber: (Int) -> Unit,

    numberScreenTab: Int = 0,
    updateNumberScreenTab: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val tabs = listOf(stringResource(id = R.string.latt_htaut), stringResource(id = R.string.spin))
    Log.d("Kham", "Choose Number Screen")

    Column(modifier = modifier.fillMaxWidth()) {
        TabRow(selectedTabIndex = numberScreenTab) {
            tabs.forEachIndexed { index, title ->
                Tab(text = {
                    Text(
                        title, fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                    selected = numberScreenTab == index,
                    onClick = { updateNumberScreenTab(index) }
                )
            }
        }
        when (numberScreenTab) {
            0 -> NumberScreen(onSelectNumber = onSelectNumber)
            1 -> SpinScreen(onSelectNumber = onSelectNumber)
        }
    }
}

@Composable
fun SpinScreen(onSelectNumber: (Int) -> Unit, modifier: Modifier = Modifier) {

    val imageResources = arrayOf(
        R.drawable.one,
        R.drawable.two,
        R.drawable.three,
        R.drawable.four,
        R.drawable.five,
        R.drawable.six,
        R.drawable.seven,
        R.drawable.eight,
        R.drawable.nine,
        R.drawable.ten
    )

    var imageResource by rememberSaveable {
        mutableIntStateOf(imageResources[0])
    }

    var isSpinning by rememberSaveable {
        mutableStateOf(false)
    }

    var buttonText by rememberSaveable {
        mutableIntStateOf(R.string.start_spinning)
    }

    if (isSpinning) {
        LaunchedEffect(key1 = imageResource) {
            coroutineScope {
                launch {
                    while (true) {
                        delay(50)
                        imageResource = imageResources[Random.nextInt(0, 10)]
                    }
                }
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Click Start Button to Start Spinning",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(32.dp))
        Image(
            painter = painterResource(id = imageResource),
            modifier = Modifier.size(200.dp),
            contentDescription = "Number Image"
        )
        Spacer(modifier = Modifier.height(48.dp))
        Button(
            onClick = {
                isSpinning = !isSpinning
                buttonText = if (isSpinning) {
                    R.string.stop_spinning
                } else {
                    onSelectNumber(imageResources.indexOf(imageResource) + 1)
                    R.string.start_spinning
                }
            },

            shape = RoundedCornerShape(4.dp)
        ) {
            Text(
                text = stringResource(id = buttonText),

                fontSize = 16.sp, fontWeight = FontWeight.ExtraBold
            )
        }
    }
}

@Composable
fun NumberScreen(onSelectNumber: (Int) -> Unit, modifier: Modifier = Modifier) {
    val number = Utility.getJsonData(LocalContext.current).numberList
    var index = 0
    val no = 6
    LazyVerticalGrid(
        columns = GridCells.Fixed(no),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp)
            .wrapContentSize(Alignment.Center)
    ) {
        val start = Random.nextInt(0,81 - (no * no));
        items(number.subList(start, start + (no * no))) {

            TextButton(
                onClick = {
                    onSelectNumber.invoke(
                        Utility.convertToEngNum(it)
                    )
                },
                modifier = Modifier
                    .border(
                        2.dp, MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(8.dp),
                    )
                    .size(56.dp)
            ) {
                Text(
                    text = it,
                    fontSize = 10.sp
                )
            }
            index++
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LattHtautScreenPreview() {
    NumberScreen(onSelectNumber = {})
}