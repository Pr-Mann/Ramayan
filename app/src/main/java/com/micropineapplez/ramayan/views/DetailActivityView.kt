package com.micropineapplez.ramayan.views

import android.util.TypedValue
import android.widget.TextView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.micropineapplez.ramayan.R
import com.micropineapplez.ramayan.ui.theme.ColorPrimary
import com.micropineapplez.ramayan.viewmodel.DetailViewModel

@Composable
fun DetailActivityView(viewModel: DetailViewModel = viewModel()) {

    LaunchedEffect(viewModel) {
        viewModel.getDetails()
    }

    val tableDetail by viewModel.tableDetail.observeAsState()

    Column {
        HeaderItemView(text = viewModel.getChapName().toString())

        Column(
            modifier = Modifier
                .weight(1f)
                .background(color = Color.White)
                .verticalScroll(
                    rememberScrollState()
                )
        ) {
            HtmlText(
                modifier = Modifier.padding(8.dp),
                text = "<h4>${viewModel.getTitle()}</h4> $tableDetail"
            )
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

@Composable
private fun HtmlText(modifier: Modifier = Modifier, text: String) {
    AndroidView(factory = { context ->
        val textView = TextView(context)
        textView.setTextColor(context.resources.getColor(R.color.colorText))
        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 24f)
        textView
    }, update = { textView ->
        textView.text = HtmlCompat.fromHtml(text, HtmlCompat.FROM_HTML_MODE_LEGACY)
    }, modifier = modifier.fillMaxSize())
}

@Preview
@Composable
fun PreviewDetailActivityView() {
    DetailActivityView()
}