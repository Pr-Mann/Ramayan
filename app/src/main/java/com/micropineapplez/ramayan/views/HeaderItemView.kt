package com.micropineapplez.ramayan.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.micropineapplez.ramayan.R
import com.micropineapplez.ramayan.ui.theme.ColorPrimary

@Composable
fun HeaderItemView(text: String) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = ColorPrimary)
            .padding(10.dp),
        text = text,
        fontSize = 25.sp,
        textAlign = TextAlign.Center,
        color = Color.White,
        fontWeight = FontWeight.SemiBold
    )
}

@Preview
@Composable
fun PreviewHeaderItemView() {
    HeaderItemView(stringResource(id = R.string.app_name))
}