package com.example.movieapp

import androidx.compose.ui.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movieapp.ui.theme.MovieAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieAppTheme {
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    MovieApp {
                        MovieContent {
                            MainMovieFetch(getMovies())
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MovieContent(content: @Composable () -> Unit){
    Surface(
        color = MaterialTheme.colors.background
    ) {
        content()
    }
}

@Composable
fun MovieApp(content: @Composable () -> Unit){
    var showMenu by remember{
        mutableStateOf(false)
    }
    MovieAppTheme() {
        Scaffold(
            topBar = {
                TopAppBar(title = {Text(text="Movies")},
                    actions = {
                        IconButton(onClick = { showMenu =! showMenu }) {
                            Icon(imageVector = Icons.Default.MoreVert, contentDescription = "more")
                        }
                        DropdownMenu(expanded = showMenu, onDismissRequest = { showMenu = false }) {
                            DropdownMenuItem(onClick = { /*TODO*/ }) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(imageVector = Icons.Default.Favorite, contentDescription = "fav icon",
                                        modifier = Modifier.padding(2.dp))
                                    Text(text ="Favourites", style= MaterialTheme.typography.h6,
                                        modifier = Modifier
                                            .padding(2.dp)
                                            .width(120.dp))
                                }
                            }
                        }
                    })
            }
        ) {
            content()
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MovieRow(movie: Movie){
    var showDetails by remember{
        mutableStateOf(false)
    }
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .fillMaxHeight(),
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = 6.dp

    ){
        Row(
            verticalAlignment = Alignment.CenterVertically
        ){
            Surface(
                modifier = Modifier
                    .size(120.dp)
                    .padding(12.dp),
                elevation = 6.dp
            ){
                Icon(imageVector = Icons.Default.AccountBox , contentDescription ="movie image" )

            }
            Column(
                modifier = Modifier.align(alignment = Alignment.CenterVertically)
            ) {
                Text(text =movie.title, style= MaterialTheme.typography.h6)
                Text(text ="Director: ${movie.director}", style= MaterialTheme.typography.body2)
                Text(text ="Released: ${movie.year}", style= MaterialTheme.typography.body2)
                AnimatedVisibility(visible = showDetails,
                    enter = fadeIn(),
                    exit = slideOutHorizontally() + shrinkVertically() + fadeOut()
                ) {
                    MovieDetails(movie)
                }
                when(showDetails){
                    true ->Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = "arrow down", modifier = Modifier.clickable { showDetails =! showDetails  })
                    false-> Icon(imageVector = Icons.Default.KeyboardArrowUp, contentDescription = "arrow up", modifier = Modifier.clickable { showDetails =! showDetails  })
                }
            }
        }
    }
}

@Composable
fun MainMovieFetch(data: List<Movie>){
    LazyColumn(){
        items(data) { item ->
            MovieRow(item)
        }
    }
}

@Composable
fun MovieDetails(movie: Movie){
    Box(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .padding(4.dp)) {
        Column() {
            Text(text ="Plot: ${movie.plot}", style= MaterialTheme.typography.body2)
            Divider(color = Color.Gray, thickness = 1.dp, modifier = Modifier.padding(top =2.dp, bottom = 2.dp))
            Text(text ="Genre: ${movie.genre}", style= MaterialTheme.typography.body2)
            Text(text ="Actor: ${movie.actors}", style= MaterialTheme.typography.body2)
            Text(text ="Rating: ${movie.rating}", style= MaterialTheme.typography.body2)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MovieAppTheme {
    }
}