package com.gochoa.componentcatalog

import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.FloatingActionButton
import android.widget.Toast
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.launch

/**
 * El scaffold debe guardar estados para algunos componentes como el snack
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldExample() {
    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            MyTopAppBar() {
                scope.launch {
                    snackBarHostState.showSnackbar(
                        message = "Has pulsado $it",
                        actionLabel = "Click me",
                        duration = SnackbarDuration.Short,
                    )
                }
            }
        },
        bottomBar = { MyBottomAppBar()},
        floatingActionButton = {},
        snackbarHost = { SnackbarHost(snackBarHostState) }
    ) {

    }

}

@Composable
fun MyBottomAppBar() {
    BottomAppBar(
        actions = {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Filled.Home,
                    contentDescription = "Home"
                )
            }
            IconButton(onClick = { }) {
                Icon(imageVector = Icons.Filled.Star, contentDescription = "favoritos")
            }
            IconButton(onClick = { }) {
                Icon(imageVector = Icons.Filled.Call, contentDescription = "llamada")
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(onClickIcon: (String) -> Unit) {
    val context = LocalContext.current
    TopAppBar(
        title = { Text(text = "Mi primera toolbar") },
        navigationIcon = {
            IconButton(onClick = { onClickIcon("Atrás") }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "back")
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.Red,
            titleContentColor = Color.White,
            navigationIconContentColor = Color.Green,
            actionIconContentColor = Color.Magenta
        ),
        actions = {
            IconButton(onClick = { onClickIcon("Buscar") }) {
                Icon(imageVector = Icons.Filled.Search, contentDescription = "search")
            }
            IconButton(onClick = { onClickIcon("Agregar") }) {
                Icon(imageVector = Icons.Filled.AddCircle, contentDescription = "add")
            }

        }
    )
}



