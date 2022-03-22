package com.example.movieapp

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.movieapp.data.Movie

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