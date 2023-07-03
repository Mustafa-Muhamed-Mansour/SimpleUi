package com.simpleui.login

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.simpleui.R
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LoginUser(navController: NavController) {

    val context = LocalContext.current

    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    val passwordVisibility = remember { mutableStateOf(false) }

    val auth = FirebaseAuth.getInstance()

    val error = remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White), contentAlignment = Alignment.Center
    )
    {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        )
        {
            Box(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxWidth()
                    .height(200.sdp), contentAlignment = Alignment.TopCenter
            )
            {
                Image(
                    modifier = Modifier
                        .width(400.sdp)
                        .height(300.sdp), painter = painterResource(R.drawable.login_and_register),
                    contentDescription = "Login Image"
                )
            }

            Spacer(modifier = Modifier.padding(15.sdp))

            Scaffold(modifier = Modifier.fillMaxSize()) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(10.sdp)
                        .background(Color.White)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = stringResource(id = R.string.log_in_account),
                            fontSize = 25.ssp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    }

                    Spacer(modifier = Modifier.padding(15.sdp))

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {

                        OutlinedTextField(
                            value = email.value,
                            onValueChange = { email.value = it },
                            label = {
                                Text(
                                    text = stringResource(id = R.string.email_address),
                                    color = colorResource(
                                        id = R.color.black
                                    )
                                )
                            },
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = colorResource(id = R.color.teal_700)
                            ), leadingIcon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_email),
                                    contentDescription = "Password Icon"
                                )
                            },
                            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(1f)
                        )

                        OutlinedTextField(
                            value = password.value,
                            onValueChange = { password.value = it },
                            label = {
                                Text(
                                    text = stringResource(id = R.string.password),
                                    color = colorResource(id = R.color.black)
                                )
                            },
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = colorResource(id = R.color.teal_700)
                            ),
                            leadingIcon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_password),
                                    contentDescription = "Password Icon"
                                )
                            },
                            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
                            trailingIcon = {
                                IconButton(onClick = {
                                    passwordVisibility.value = passwordVisibility.value.not()
                                }) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.eye),
                                        contentDescription = "Password",
                                        tint = if (passwordVisibility.value) {
                                            Color.Red
                                        } else {
                                            colorResource(id = R.color.teal_700)
                                        }
                                    )
                                }
                            },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(1f),
                            visualTransformation = if (passwordVisibility.value) {
                                VisualTransformation.None
                            } else {
                                PasswordVisualTransformation()
                            }
                        )
                    }

                    Spacer(modifier = Modifier.padding(15.sdp))

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Button(
                            onClick = {
                                validationOfWidgetAndLoginAccount(
                                    context,
                                    email.value,
                                    password.value,
                                    auth,
                                    navController,
                                    error
                                )
                            },
                            modifier = Modifier
                                .fillMaxWidth(0.7f)
                                .height(60.sdp),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                            content = {
                                Text(
                                    text = stringResource(id = R.string.log_in),
                                    fontSize = 30.ssp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black
                                )
                            },
                            elevation = ButtonDefaults.elevation(5.sdp)
                        )
                    }

                    Spacer(modifier = Modifier.padding(10.sdp))

                    Text(
                        text = "Create an Account",
                        fontSize = 20.ssp,
                        color = Color.Black,
                        fontStyle = FontStyle.Italic,
                        modifier = Modifier.clickable {
                            navController.navigate("register_page")
                        })

                    Spacer(modifier = Modifier.padding(10.sdp))
                }
            }
        }
    }
}

fun validationOfWidgetAndLoginAccount(
    context: Context,
    email: String,
    password: String,
    auth: FirebaseAuth,
    navController: NavController,
    error: MutableState<String>
) {
    when {
        email.isNullOrEmpty() -> {
            Toast.makeText(
                context,
                R.string.enter_email,
                Toast.LENGTH_LONG
            ).show()
        }
        password.isNullOrEmpty() -> {
            Toast.makeText(
                context,
                R.string.enter_password,
                Toast.LENGTH_SHORT
            ).show()
        }
        else -> {
            auth
                .signInWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    Toast.makeText(
                        context,
                        "Done, login successfully",
                        Toast.LENGTH_SHORT
                    ).show()
                    navController.navigate("home_page")
                }.addOnFailureListener {
                    error.value = it.message.toString()
                }
        }
    }
}
