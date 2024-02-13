package it.saimao.latthtautbaydin.ui.screen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import it.saimao.latthtautbaydin.ui.Utility

@Composable
fun NumberScreen(onSelectNumber: (String) -> Unit, modifier: Modifier = Modifier) {
    val number = Utility.getJsonData(LocalContext.current).numberList
    var index = 0
    val no = 9
    LazyVerticalGrid(
        columns = GridCells.Fixed(no),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .wrapContentSize(Alignment.Center)
    ) {
        items(number) {

            TextButton(
                onClick = { onSelectNumber.invoke(it) }, modifier = Modifier
                    .border(1.dp, MaterialTheme.colorScheme.primary)
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
    NumberScreen({})

}