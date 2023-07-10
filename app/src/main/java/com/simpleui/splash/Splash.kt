package com.simpleui.splash

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.simpleui.R
import com.simpleui.route.RoutePages
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun splashScreen(navController: NavController) {

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.welcome))
    val scope = rememberCoroutineScope()


    Box(modifier = Modifier.fillMaxSize()) {

        LottieAnimation(
            composition = composition
        )

        scope.launch {
            delay(3000)
            navController.navigate(RoutePages.LOGIN_PAGE)
        }

    }

}