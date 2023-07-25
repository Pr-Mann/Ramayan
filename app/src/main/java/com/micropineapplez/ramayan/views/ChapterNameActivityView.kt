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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.micropineapplez.ramayan.R
import com.micropineapplez.ramayan.activity.TitleActivity
import com.micropineapplez.ramayan.ui.theme.ColorPrimary
import com.micropineapplez.ramayan.viewmodel.ChapterNameViewModel

@Composable
fun ChapterNameActivityView(viewModel: ChapterNameViewModel = viewModel()) {

    LaunchedEffect(viewModel) {
        viewModel.getChapterNames()
    }

    val chapterNames by viewModel.chapterName.observeAsState(emptyList())
    val context = LocalContext.current

    Column() {
        HeaderItemView(text = stringResource(id = R.string.sampurn_ramayan))

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .background(color = Color.White)
        ) {
            items(chapterNames) { chapterName ->
                TitleTextView(
                    text = chapterName.ChapName.toString(),
                    textAlign = TextAlign.Center,
                    onClick = {
                        val intent = Intent(context, TitleActivity::class.java)
                        intent.putExtra("tableName", chapterName.EngChapName)
                        intent.putExtra("chapName", chapterName.ChapName)
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
fun PreviewChapterNameActivityView() {
    ChapterNameActivityView()
}