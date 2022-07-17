package io.jetpack.compose.cases.ui.casesList

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import io.jetpack.compose.extensions.navigateNextScreen

@Composable
fun CasesListScreen(navController: NavHostController, cases: List<CaseItem>) {
    LazyColumn(contentPadding = PaddingValues(32.dp)) {
        items(cases) { item ->
            Column {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { navController.navigateNextScreen(item.navigation) }) {
                    Text(text = item.title)
                }
                Row(modifier = Modifier.padding(bottom = 12.dp)) {
                   Text(text = "By: ", fontWeight = FontWeight.Bold )
                   Spacer(modifier = Modifier.padding(start = 4.dp))
                    Text(text = item.author)
                }
            }
            Divider(color = Color.Gray)
        }
    }
}