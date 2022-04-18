package com.example.movieapp.module

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.movieapp.data.Movie
import java.nio.file.Files.exists

class FavouritesViewModel : ViewModel() {

    private val _favMovies = mutableStateListOf<Movie>()
    val favMovies: List<Movie>
        get() = _favMovies

    fun movieExists(movie: Movie): Boolean {
        return _favMovies.any { item -> item.id == movie.id }
    }

    fun addMovie(movie: Movie) {
        if (!movieExists(movie)) _favMovies.add(movie)
        else deleteMovie(movie)
    }

    fun deleteMovie(movie: Movie) {
        _favMovies.remove(movie)
    }
}
