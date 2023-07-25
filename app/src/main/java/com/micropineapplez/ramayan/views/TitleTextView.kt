package com.micropineapplez.ramayan.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.micropineapplez.ramayan.R

@Composable
fun TitleTextView(
    text: String,
    textAlign: TextAlign,
    onClick: (() -> Unit)?
) {
    Box(
        modifier = Modifier
            .clickable(
                interactionSource = MutableInteractionSource(), indication = rememberRipple(
                    color = Color.Black,
                    radius = Dp.Unspecified,
                    bounded = true
                )
            ) {
                onClick?.invoke()
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            text = text,
            fontSize = 25.sp,
            textAlign = textAlign,
            color = Color.Black
        )
    }
}

@Preview
@Composable
fun PreviewTitleTextView() {
    TitleTextView(
        text = stringResource(id = R.string.app_name),
        textAlign = TextAlign.Center,
        onClick = null
    )
}