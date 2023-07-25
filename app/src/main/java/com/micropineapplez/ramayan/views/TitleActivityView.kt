package com.micropineapplez.ramayan.views

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.micropineapplez.ramayan.activity.DetailActivity
import com.micropineapplez.ramayan.ui.theme.ColorPrimary
import com.micropineapplez.ramayan.viewmodel.TitleViewModel

@Composable
fun TitleActivityView(viewModel: TitleViewModel = viewModel()) {

    LaunchedEffect(viewModel) {
        viewModel.getTitles()
    }

    val tableData by viewModel.tableData.observeAsState(emptyList())
    val context = LocalContext.current

    Column() {
        HeaderItemView(text = viewModel.getChapName().toString())

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .background(color = Color.White)
        ) {
            items(tableData) { data ->
                TitleTextView(
                    text = data.Title.toString(),
                    textAlign = TextAlign.Justify,
                    onClick = {
                        val intent = Intent(context, DetailActivity::class.java)
                        intent.putExtra("tableName", viewModel.getTableName().toString())
                        intent.putExtra("chapName", viewModel.getChapName().toString())
                        intent.putExtra("title", data.Title.toString())
                        intent.putExtra("id", data._id.toString())
                        context.startActivity(intent)
                    })
                Divider()
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(color = ColorPrimary)
        ) {

        }
    }
}

@Preview
@Composable
fun PreviewTitleActivityView() {
    TitleActivityView()
}