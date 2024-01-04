package com.gochoa.componentcatalog

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCompositionContext
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun MyDialog(
    show: Boolean,
    onDisMiss: () -> Unit,
    onConfirm: () -> Unit
) {
    if (show) {
        val mContext = LocalContext.current
        AlertDialog(
            //Se usa para cuando el usuario pica fuera del diálogo
            onDismissRequest = {
                onDisMiss()
            },
            confirmButton = {
                TextButton(onClick = { onConfirm() }) {
                    Text(text = "Aceptar")
                    Log.d("ggg", "MyDialog: clicke")
                }
            },
            dismissButton = {
                TextButton(onClick = { onDisMiss() }) {
                    Text(text = "Cancelar")
                }
            },
            title = { Text(text = "Título way") },
            text = { Text(text = "Hola, soy una descripción") },
        )
    }
}

@Composable
fun MyConfirmationDialog(
    show: Boolean,
    onDisMiss: () -> Unit,
) {
    if (show) {
        Dialog(onDismissRequest = { onDisMiss() }) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
            ) {
                MyTitleDialog(text = "Phone ringtone")
                Divider(modifier = Modifier.fillMaxWidth(), color = Color.Gray)

            }
        }

    }
}

@Composable
fun MyCustomDialog(
    show: Boolean,
    onDisMiss: () -> Unit,
) {
    if (show) {
        Dialog(onDismissRequest = { onDisMiss() }) {
            Column(
                Modifier
                    .background(Color.White)
                    .padding(24.dp)
                    .fillMaxWidth()
            ) {
                MyTitleDialog(text = "Set backup account")
                AccountItem(email = "ggg@gmail.com", drawerState = R.drawable.ic_avatar)
                AccountItem(email = "ttt@gmail.com", drawerState = R.drawable.ic_avatar)
                AccountItem(email = "Añadir nueva cuenta", drawerState = R.drawable.ic_add)
            }
        }
    }
}

@Composable
fun AccountItem(email: String, @DrawableRes drawerState: Int) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(id = drawerState),
            contentDescription = "avatar",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(8.dp)
                .size(40.dp)
                .clip(CircleShape)
        )
//        Text(text = email, fontSize = 14.sp, color = Color.Gray, modifiers = Modifier.padding(8.dp))
        Text(
            text = email,
            fontSize = (14.sp),
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
fun MyTitleDialog(text: String) {
    Text(
        text = text,
        fontSize = (24.sp),
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier.padding(12.dp)
    )
}

@Composable
fun MySimpleCustomDialog(
    show: Boolean,
    onDisMiss: () -> Unit,
) {
    if (show) {

        Dialog(
            onDismissRequest = { onDisMiss() },
            properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
        ) {
            Column(
                Modifier
                    .background(Color.White)
                    .padding(24.dp)
                    .fillMaxWidth()
            ) {
                Text(text = "Esto es un ejemplo")
                Text(text = "Esto es un ejemplo")
                Text(text = "Esto es un ejemplo")
            }
        }
    }

}

private fun messageToast(context: Context) {
    Toast.makeText(context, "Saliendo", Toast.LENGTH_SHORT).show()
}