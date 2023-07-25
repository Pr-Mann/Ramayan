package com.micropineapplez.ramayan.views

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.micropineapplez.ramayan.R
import com.micropineapplez.ramayan.model.AppData
import com.micropineapplez.ramayan.ui.theme.AdColor1
import com.micropineapplez.ramayan.ui.theme.AdColor2
import com.micropineapplez.ramayan.ui.theme.AdColor3
import com.micropineapplez.ramayan.ui.theme.AdColor4
import com.micropineapplez.ramayan.ui.theme.AdColor5
import com.micropineapplez.ramayan.ui.theme.AdColorBlack
import com.micropineapplez.ramayan.ui.theme.AdColorPrimary

@Composable
fun AdSecondActivityView(onStartClick: (() -> Unit)?) {

    val context = LocalContext.current
    val databaseReference = FirebaseDatabase.getInstance().getReference("appData")
    val appDataList = mutableListOf<AppData>()
    var isAdOn = true

    databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            for (di in dataSnapshot.children) {
                val appData = AppData()
                appData.appId = di.child("appId").value.toString()
                appData.image = di.child("image").value.toString()
                appData.isAdOn = di.child("isAdOn").value.toString()
                appData.name = di.child("name").value.toString()
                if (appData.appId != context.packageName) {
                    appDataList.add(appData)
                } else if (appData.appId == context.packageName && appData.isAdOn == "0") {
                    isAdOn = false
                }
            }
        }

        override fun onCancelled(error: DatabaseError) {
        }

    })

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = AdColorBlack)
            .padding(start = 6.dp, top = 6.dp, end = 6.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp)
        ) {
            Text(
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp),
                text = stringResource(id = R.string.app_name),
                color = Color.White,
                fontSize = 23.sp
            )
            Row(
                modifier = Modifier
                    .height(50.dp)
                    .clip(shape = RoundedCornerShape(8.dp))
                    .background(color = AdColorPrimary)
                    .padding(horizontal = 8.dp)
                    .clickable(
                        interactionSource = MutableInteractionSource(),
                        indication = null
                    ) { onStartClick?.invoke() },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.padding(start = 6.dp, end = 12.dp),
                    text = stringResource(R.string.start),
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Image(
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(8.dp))
                        .background(color = Color.White)
                        .size(35.dp)
                        .padding(6.dp),
                    painter = painterResource(id = R.drawable.ad_right),
                    contentDescription = stringResource(R.string.start)
                )
            }
        }
        Log.e("logs","isAdOn: $isAdOn")
        Log.e("logs","size: ${appDataList.size}")
        if (isAdOn) {
            LazyVerticalGrid(modifier = Modifier.weight(1f),
                columns = GridCells.Adaptive(90.dp),
                content = {
                    itemsIndexed(appDataList) { index, appData ->
                        val downloadBgColor = when (index % 5) {
                            0 -> AdColor3
                            1 -> AdColor4
                            2 -> AdColor5
                            3 -> AdColor1
                            else -> AdColor2
                        }
                        AdAppView(downloadBgColor = downloadBgColor, appData = appData)
                    }
                }
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
                .padding(top = 5.dp)
                .clip(shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
                .background(color = AdColorPrimary)
                .padding(top = 10.dp)
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.Bottom
        ) {
            AdBottomButton(
                modifier = Modifier
                    .weight(1f)
                    .clickable(
                        interactionSource = MutableInteractionSource(),
                        indication = null
                    ) { rateUs(context = context) },
                icon = R.drawable.ad_rate,
                title = stringResource(R.string.rate_us),
                iconColor = AdColor2
            )
            AdBottomButton(
                modifier = Modifier
                    .weight(1f)
                    .clickable(
                        interactionSource = MutableInteractionSource(),
                        indication = null
                    ) { share(context = context) },
                icon = R.drawable.ad_share,
                title = stringResource(R.string.share),
                iconColor = AdColor3
            )
            AdBottomButton(
                modifier = Modifier
                    .weight(1f)
                    .clickable(
                        interactionSource = MutableInteractionSource(),
                        indication = null
                    ) { more(context = context) },
                icon = R.drawable.ad_more,
                title = stringResource(R.string.more_apps),
                iconColor = AdColor4
            )
        }
    }
}

@Composable
private fun AdBottomButton(modifier: Modifier, icon: Int, title: String, iconColor: Color) {
    Column(
        modifier = modifier
            .padding(horizontal = 8.dp)
            .clip(shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
            .background(color = Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Icon(
            modifier = Modifier
                .size(37.dp)
                .padding(start = 4.dp, top = 8.dp, end = 4.dp),
            painter = painterResource(id = icon),
            contentDescription = title,
            tint = iconColor
        )
        Text(
            text = title, color = Color.Black, fontSize = 15.sp, textAlign = TextAlign.Center
        )
    }
}

private fun rateUs(context: Context) {
    try {
        context.startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://play.google.com/store/apps/details?id=${context.packageName}")
            )
        )
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

private fun share(context: Context) {
    val shareIntent = Intent(Intent.ACTION_SEND)
    shareIntent.type = "text/plain"
    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Awesome Application!")
    shareIntent.putExtra(
        Intent.EXTRA_TEXT,
        "Have a look at,\n\nhttps://play.google.com/store/apps/details?id=${context.packageName}"
    )
    context.startActivity(Intent.createChooser(shareIntent, "Share via"))
}

private fun more(context: Context) {
    try {
        context.startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://play.google.com/store/apps/developer?id=${context.getString(R.string.account_name)}")
            )
        )
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

@Preview
@Composable
fun PreviewAdSecondActivityView() {
    AdSecondActivityView(onStartClick = null)
}