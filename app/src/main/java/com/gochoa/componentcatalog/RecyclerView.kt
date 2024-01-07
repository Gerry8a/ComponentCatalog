package com.gochoa.componentcatalog

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gochoa.componentcatalog.model.SuperHero
import kotlinx.coroutines.launch

@Composable
fun SimpleRecyclerView() {
    val myList = listOf("Gerardo", "Angie", "Pikachu")
    LazyColumn {
        item { Text(text = "Hola") }
        items(7) {
            Text(text = "Este es el item $it")
        }
        items(myList) {
            Text(text = "Hola, me llamo $it")
        }
    }
}

/**
 * Clase 70
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SuperHeroStickyView() {
    val context = LocalContext.current
    val superhero: Map<String, List<SuperHero>> = getsuperHeros().groupBy { it.publisher }
    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        superhero.forEach { (publisher, mySuperHero) ->
            stickyHeader {
                Text(
                    text = publisher,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Green),
                    fontSize = 16.sp,
                    color = Color.White
                )
            }
            items(mySuperHero) { superhero ->
                ItemHero(superHero = superhero) {
                    Toast.makeText(context, it.superheroName, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

/**
 * Clase 69 minuto 5
 */
@Composable
fun SuperHeroWithSpecialControl() {
    val context = LocalContext.current
    val rvState = rememberLazyListState()
    val coroutinesScope = rememberCoroutineScope()
    Column {
        LazyColumn(
            state = rvState,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(getsuperHeros()) { superhero ->
                ItemHero(superHero = superhero) {
                    Toast.makeText(context, it.superheroName, Toast.LENGTH_SHORT).show()
                }
            }
        }

        val showButton by remember {
            derivedStateOf {
                rvState.firstVisibleItemIndex > 0
            }
        }

        if (showButton) {
            Button(
                onClick = {
                    coroutinesScope.launch {
                        rvState.animateScrollToItem(0)
                    }
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp)
            ) {
                Text(text = "Soy un botón")
            }
        }

    }
}


@Composable
fun SuperHeroGridView() {
    val context = LocalContext.current
    //GridCells.Adaptative(x.dp) en X se pone el mínimo de dp que pueda ajustar en pantalla
    LazyVerticalGrid(columns = GridCells.Fixed(2), content = {
        items(getsuperHeros()) { superhero ->
            ItemHero(superHero = superhero, onItemSelected = {
                Toast.makeText(
                    context,
                    superhero.superheroName,
                    Toast.LENGTH_SHORT
                ).show()
            })
        }
    })

}

@Composable
fun SuperHeroView() {
    val context = LocalContext.current
    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        items(getsuperHeros()) { superhero ->
            ItemHero(superHero = superhero) {
                Toast.makeText(context, it.superheroName, Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@Composable
fun ItemHero(superHero: SuperHero, onItemSelected: (SuperHero) -> Unit) {
    Card(
        border = BorderStroke(2.dp, color = Color.Red),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemSelected(superHero) }
            .padding(top = 8.dp, bottom = 8.dp, end = 16.dp, start = 16.dp)) {
        Column {
            Image(
                painterResource(id = superHero.photo),
                contentDescription = "avatar",
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Text(
                text = superHero.superheroName,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = superHero.realName,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                fontSize = 12.sp
            )
            Text(
                text = superHero.publisher,
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(8.dp),
                fontSize = 10.sp
            )
        }
    }
}

fun getsuperHeros(): List<SuperHero> {
    return listOf(
        SuperHero("Spiderman", "Peter Parker", "Marvel", R.drawable.spiderman),
        SuperHero("Wolverine", "Jonh howsw", "Marvel", R.drawable.logan),
        SuperHero("Batman", "Bruce Wayne", "DC", R.drawable.batman),
        SuperHero("Thor", "Thor Odison", "Marvel", R.drawable.thor),
        SuperHero("Flash", "Jay Garrick", "DC", R.drawable.flash),
        SuperHero("Green Lantern", "Alan Scott", "DC", R.drawable.green_lantern),
        SuperHero("Wonder Woman", "Diana", "DC", R.drawable.wonder_woman)
    )
}