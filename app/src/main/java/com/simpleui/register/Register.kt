package com.simpleui.register

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
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
import com.simpleui.R
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "SuspiciousIndentation")
@Composable
fun RegisterUser(navController: NavController) {

    val context = LocalContext.current

    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val name = remember { mutableStateOf("") }
    val phone = remember { mutableStateOf("") }

    val passwordVisibility = remember { mutableStateOf(false) }

    val nestedScroll = rememberScrollState()


            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(nestedScroll)
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxHeight()
                ) {
                    Box(
                        contentAlignment = Alignment.TopCenter, modifier = Modifier.background(Color.White)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.login_and_register),
                            contentDescription = "Register Image",
                            modifier = Modifier
                                .width(300.sdp)
                                .height(250.sdp)
                        )
                    }

                    Spacer(modifier = Modifier.padding(10.sdp))

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = stringResource(id = R.string.create_account),
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.ssp,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.padding(10.sdp))

                        OutlinedTextField(value = email.value,
                            onValueChange = { email.value = it },
                            label = { Text(text = "Email Address", fontSize = 17.ssp, color = Color.Black) },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
                            modifier = Modifier.fillMaxWidth(0.8f),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                textColor = Color.Black, focusedBorderColor = colorResource(
                                    id = R.color.teal_700
                                )
                            ),
                            leadingIcon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_email),
                                    contentDescription = "Password Icon"
                                )
                            })

                        OutlinedTextField(value = password.value,
                            onValueChange = { password.value = it },
                            label = { Text(text = "Password", fontSize = 17.ssp, color = Color.Black) },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
                            modifier = Modifier.fillMaxWidth(0.8f),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                textColor = Color.Black, focusedBorderColor = colorResource(
                                    id = R.color.teal_700
                                )
                            ),
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
                            leadingIcon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_password),
                                    contentDescription = "Password Icon"
                                )
                            },
                            visualTransformation = if (passwordVisibility.value) {
                                VisualTransformation.None
                            } else {
                                PasswordVisualTransformation()
                            }
                        )

                        OutlinedTextField(value = name.value,
                            onValueChange = { name.value = it },
                            label = { Text(text = "User Name", fontSize = 17.ssp, color = Color.Black) },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                            modifier = Modifier.fillMaxWidth(0.8f),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                textColor = Color.Black, focusedBorderColor = colorResource(
                                    id = R.color.teal_700
                                )
                            ),
                            leadingIcon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_email),
                                    contentDescription = "Password Icon"
                                )
                            })

                        OutlinedTextField(value = phone.value,
                            onValueChange = { phone.value = it },
                            label = { Text(text = "Phone", fontSize = 17.ssp, color = Color.Black) },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone),
                            modifier = Modifier.fillMaxWidth(0.8f),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                textColor = Color.Black, focusedBorderColor = colorResource(
                                    id = R.color.teal_700
                                )
                            ),
                            leadingIcon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_phone),
                                    contentDescription = "Password Icon"
                                )
                            })
                    }

                    Spacer(modifier = Modifier.padding(10.sdp))

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth(0.7f)
                    ) {
                        Button(onClick = {
                            when {
                                email.value.isNullOrEmpty() -> {
                                    Toast.makeText(
                                        context, R.string.enter_email, Toast.LENGTH_SHORT
                                    ).show()
                                }
                                password.value.isNullOrEmpty() -> {
                                    Toast.makeText(
                                        context, R.string.enter_password, Toast.LENGTH_SHORT
                                    ).show()
                                }
                                name.value.isNullOrEmpty() -> {
                                    Toast.makeText(
                                        context, R.string.enter_name, Toast.LENGTH_SHORT
                                    ).show()
                                }
                                phone.value.isNullOrEmpty() -> {
                                    Toast.makeText(
                                        context, R.string.enter_phone, Toast.LENGTH_SHORT
                                    ).show()
                                }
                                else -> {
                                    Toast.makeText(
                                        context,
                                        "Create an account, done successfully",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        },
                            content = {
                                Text(
                                    text = stringResource(id = R.string.create_account),
                                    fontSize = 25.ssp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black
                                )
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(60.sdp),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                            elevation = ButtonDefaults.elevation(5.sdp)
                        )
                    }

                    Spacer(modifier = Modifier.padding(10.sdp))

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(text = "Already have an account? Login",
                            fontSize = 20.ssp,
                            fontStyle = FontStyle.Italic,
                            color = Color.Black,
                            modifier = Modifier.clickable {
                                navController.navigate("login_page")
                            })
                    }

                    Spacer(modifier = Modifier.padding(10.sdp))

                }
            }
}