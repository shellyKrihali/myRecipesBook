package com.example.myrecipes.ui.fragments.uploadrecipe.bottomsheet

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.myrecipes.R
import com.example.myrecipes.models.UploadedRecipe
import com.example.myrecipes.util.Constants.Companion.DEFAULT_MEAL_CATEGORY
import com.example.myrecipes.util.Constants.Companion.FIREBASE_RECIPE
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.fragment_create_recipe.*
import java.net.URI
import java.text.SimpleDateFormat
import java.util.*


class CreateRecipeBottomSheet : Fragment(), AdapterView.OnItemSelectedListener {
    lateinit var editTextRecipeName: EditText
    lateinit var editTextRecipeDescription: EditText
    lateinit var editTextRecipeIngredients: EditText
    lateinit var editTextRecipeInstructions: EditText
    lateinit var stringCategory: String
    lateinit var buttonCreateNewRecipe: Button
    lateinit var categorySpinner: Spinner


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView = inflater.inflate(R.layout.fragment_create_recipe, container, false)

        init(rootView)
        initSpinner()

        return rootView
    }

    private fun initSpinner() {
        getActivity()?.let {
            ArrayAdapter.createFromResource(
                it,
                R.array.category_spinner,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                // Apply the adapter to the spinner
                categorySpinner.adapter = adapter
            }


        }
    }

    fun init(rootView: View) {
        editTextRecipeName = rootView.findViewById<EditText>(R.id.editTextRecipeName)
        editTextRecipeDescription = rootView.findViewById<EditText>(R.id.editTextRecipeDescription)
        editTextRecipeIngredients = rootView.findViewById<EditText>(R.id.editTextRecipeIngredients)
        editTextRecipeInstructions =
            rootView.findViewById<EditText>(R.id.editTextRecipeInstructions)
        buttonCreateNewRecipe = rootView.findViewById<Button>(R.id.buttonCreateNewRecipe)
        categorySpinner = rootView.findViewById(R.id.category_spinner)
        categorySpinner.onItemSelectedListener = this


        buttonCreateNewRecipe.setOnClickListener {
            Log.d("pttt", "button click listener")
            addRecipeToFirebase()
            editTextRecipeName.text.clear()
            editTextRecipeDescription.text.clear()
            editTextRecipeIngredients.text.clear()
            editTextRecipeInstructions.text.clear()

        }


    }

    private fun addRecipeToFirebase() {
        val name = editTextRecipeName.text.toString().trim()
        val description = editTextRecipeDescription.text.toString().trim()
        val ingredients = editTextRecipeIngredients.text.toString().trim()
        val instructions = editTextRecipeInstructions.text.toString().trim()

        if (name?.isEmpty()) {
            Log.d("pttt", "null 1 ")
            editTextRecipeName.error = "Please enter a recipe name"
            return
        }
        if (description.isEmpty()) {
            Log.d("pttt", "null 2")
            editTextRecipeDescription.error = "Please enter a recipe description"
            return
        }
        if (ingredients.isEmpty()) {
            Log.d("pttt", "null 3")
            editTextRecipeIngredients.error = "Please enter a recipe ingredients"
            return
        }
        if (instructions.isEmpty()) {
            Log.d("pttt", "null 4")
            editTextRecipeInstructions.error = "Please enter a recipe instructions"
            return
        }

        val ref = FirebaseDatabase.getInstance().getReference(FIREBASE_RECIPE)

        val recipeId = ref.push().key.toString()
        var recipe =
            UploadedRecipe(
                recipeId,
                name,
                description,
                ingredients,
                instructions,
                stringCategory,
                ""
            )
        Log.d("pttt", "cat " + stringCategory)
        ref.child(recipeId).setValue(recipe).addOnCompleteListener {
            Log.d("pttt", "recipe added")
            Toast.makeText(context, "Recipe added successfully", Toast.LENGTH_LONG).show()
        }
    }


    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
        // An item was selected. You can retrieve the selected item using
        Log.d("pttt", "item clicked" + parent.getItemAtPosition(pos).toString())
        stringCategory = parent.getItemAtPosition(pos).toString()
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
        stringCategory = DEFAULT_MEAL_CATEGORY
    }
}