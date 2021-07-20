package com.example.myrecipes.models

class UploadedRecipe
    (
    val id: String, val name: String, val description: String,
    val ingredients: String, val instructions: String, val category: String, val image: String
) {
    constructor() : this(
        "",
        "",
        "", "", "", "", ""
    )
}
