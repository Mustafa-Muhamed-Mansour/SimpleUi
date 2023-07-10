package com.simpleui.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.simpleui.home.HomeUser
import com.simpleui.login.LoginUser
import com.simpleui.register.RegisterUser
import com.simpleui.route.RoutePages
import com.simpleui.splash.splashScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            navigatePage()
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {

    val navController = rememberNavController()
    LoginUser(navController = navController)
}

@Composable
fun navigatePage() {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = RoutePages.SPLASH_PAGE, builder = {
        composable(RoutePages.SPLASH_PAGE) {
            splashScreen(navController = navController)
        }
        composable(RoutePages.LOGIN_PAGE) {
            LoginUser(navController = navController)
        }
        composable(RoutePages.REGISTER_PAGE) {
            RegisterUser(navController = navController)
        }
        composable(RoutePages.HOME_PAGE) {
            HomeUser()
        }
    })
}
