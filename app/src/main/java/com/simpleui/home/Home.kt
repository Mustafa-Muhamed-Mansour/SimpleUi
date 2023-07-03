package com.simpleui.home

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.simpleui.R
import ir.kaaveh.sdpcompose.sdp


@Composable
fun HomeUser() {

    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {

        Image(
            painter = painterResource(id = R.drawable.login_and_register),
            contentDescription = "Image Home",
            modifier = Modifier.fillMaxSize()
        )

        Spacer(modifier = Modifier.padding(10.sdp))

        Toast.makeText(context, "Welcome Home", Toast.LENGTH_SHORT).show()

    }


}