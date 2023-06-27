package com.simpleui.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.simpleui.login.LoginUser
import com.simpleui.register.RegisterUser

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
    NavHost(navController = navController, startDestination = "login_page", builder = {
        composable("login_page", content = { LoginUser(navController = navController) })
        composable("register_page", content = { RegisterUser(navController = navController) })
    })
}
