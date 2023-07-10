package com.simpleui.home

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.simpleui.R
import com.simpleui.model.UserModel
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp


@Composable
fun HomeUser() {

    val context = LocalContext.current

    val auth = FirebaseAuth.getInstance()
    val retrieve = FirebaseDatabase.getInstance().reference

    val name = remember {
        mutableStateOf("")
    }
    val phone = remember {
        mutableStateOf("")
    }
    val errorMessage = remember {
        mutableStateOf("")
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(id = R.drawable.login_and_register),
                contentDescription = "Image Home",
                modifier = Modifier
                    .width(250.sdp)
                    .height(250.sdp)
            )

            Toast.makeText(context, "Welcome Home", Toast.LENGTH_SHORT).show()

            retrieve
                .child("Users")
                .child(auth.uid.toString())
                .addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if (snapshot.exists()) {
                            val model = snapshot.getValue(UserModel::class.java)
                            name.value = model!!.name
                            phone.value = model.phone
                        } else {
                            errorMessage.value = snapshot.value.toString()
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        errorMessage.value = error.message
                    }
                })

            Spacer(modifier = Modifier.padding(20.sdp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {


                Text(
                    text = "Name: ${name.value}",
                    fontSize = 20.ssp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Text(
                    text = "Phone: ${phone.value}",
                    fontSize = 17.ssp,
                    color = Color.Black
                )

            }

        }

    }

}