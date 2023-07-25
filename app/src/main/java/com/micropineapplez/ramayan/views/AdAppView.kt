package com.micropineapplez.ramayan.views

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.micropineapplez.ramayan.R
import com.micropineapplez.ramayan.model.AppData
import com.micropineapplez.ramayan.ui.theme.AdColor3
import com.micropineapplez.ramayan.ui.theme.AdColorPrimary

@Composable
fun AdAppView(downloadBgColor: Color, appData: AppData) {
    Column(
        modifier = Modifier
            .padding(3.dp)
            .height(123.dp)
            .clip(shape = RoundedCornerShape(6.dp))
            .background(color = Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier.size(70.dp), contentAlignment = Alignment.BottomEnd) {
//            Image(
//                modifier = Modifier.padding(3.dp),
//                painter = painterResource(id = R.drawable.ic_launcher),
//                contentDescription = ""
//            )
            Log.e("logs","imgae: ${appData.image}")
            AsyncImage(
                modifier = Modifier.padding(3.dp),
                model = appData.image,
                contentDescription = "",
            )
            Icon(
                modifier = Modifier
                    .height(25.dp)
                    .width(15.dp),
                painter = painterResource(id = R.drawable.ad_ads),
                contentDescription = "Ad",
                tint = AdColorPrimary
            )
        }
        Text(
            modifier = Modifier.padding(horizontal = 3.dp),
            text = appData.name,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            textAlign = TextAlign.Center,
            maxLines = 1
        )
        Text(
            modifier = Modifier
                .padding(top = 2.dp)
                .clip(shape = RoundedCornerShape(4.dp))
                .background(color = downloadBgColor)
                .padding(4.dp),
            text = stringResource(R.string.download),
            color = Color.Black,
            fontSize = 10.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun PreviewAdAppView() {
    AdAppView(downloadBgColor = AdColor3, appData = AppData())
}