package com.example.artspaceapp

import androidx.annotation.DrawableRes

data class ArtPiece(
    @DrawableRes val image: Int,
    val title: String,
    val author: String,
    val year: String
)
