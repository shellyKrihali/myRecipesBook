package com.example.myrecipes.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.myrecipes.R
import com.example.myrecipes.util.Constants
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_details.*

class UploadedDetailsActivity : AppCompatActivity() {

    lateinit var name: TextView
    lateinit var description: TextView
    lateinit var ingredients: TextView
    lateinit var instructions: TextView
    lateinit var category: TextView
    lateinit var saveEditBtn: Button
    lateinit var cancelEditBtn: Button

    lateinit var editTxtDes: EditText
    lateinit var editTxtIngredients: EditText
    lateinit var editTxtInstructions: EditText


    var idIntent: String = ""
    var nameIntent: String? = ""
    var descriptionIntent: String? = ""
    var ingredientsIntent: String? = ""
    var instructionsIntent: String? = ""
    var categoryIntent: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_uploaded_details)

        setSupportActionBar(toolbar)
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white))
        toolbar.title = "My Recipe Details"

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        init()

        getAndSetIntents()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.details_edit_menu, menu)
        val menuItem = menu?.findItem(R.id.menu_edit)
        // checkSavedRecipes(menuItem!!)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        } else if (item.itemId == R.id.menu_edit) {
            editRecipeMenuShow();
        }
        return super.onOptionsItemSelected(item)
    }

    private fun editRecipeMenuShow() {

        saveEditBtn.visibility = View.VISIBLE
        cancelEditBtn.visibility = View.VISIBLE
        editTxtDes.visibility = View.VISIBLE
        editTxtInstructions.visibility = View.VISIBLE
        editTxtIngredients.visibility = View.VISIBLE
        description.visibility = View.GONE
        ingredients.visibility = View.GONE
        instructions.visibility = View.GONE

        editTxtDes.setText(descriptionIntent)
        editTxtInstructions.setText(instructionsIntent)
        editTxtIngredients.setText(ingredientsIntent)

        saveEditBtn.setOnClickListener {
            saveEditTofirebase()
        }
        cancelEditBtn.setOnClickListener {
            cancelEdit()
        }

    }

    private fun saveEditTofirebase() {
        var descriptionTxt: String = editTxtDes.text.toString()
        var instructionsTxt: String = editTxtInstructions.text.toString()
        var ingredientsTxt: String = editTxtIngredients.text.toString()
        if (descriptionTxt != descriptionIntent) {
            descriptionIntent = descriptionTxt
            saveToFirebase(descriptionTxt, "description", description)
        }
        if (instructionsTxt != instructionsIntent) {
            instructionsIntent = instructionsTxt
            saveToFirebase(instructionsTxt, "instructions", instructions)
        }
        if (ingredientsTxt != ingredientsIntent) {
            ingredientsIntent = ingredientsTxt
            saveToFirebase(ingredientsTxt, "ingredients", ingredients)
        }
    }

    private fun saveToFirebase(editTxtStr: String, entity: String, textViewToChange: TextView) {
        val ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_RECIPE)
        ref.child(idIntent).child(entity).setValue(editTxtStr);

        textViewToChange.setText(editTxtStr)

        cancelEdit()
    }

    private fun cancelEdit() {
        saveEditBtn.visibility = View.GONE
        cancelEditBtn.visibility = View.GONE
        editTxtDes.visibility = View.GONE
        editTxtInstructions.visibility = View.GONE
        editTxtIngredients.visibility = View.GONE
        description.visibility = View.VISIBLE
        ingredients.visibility = View.VISIBLE
        instructions.visibility = View.VISIBLE

    }

    private fun init() {

        name = findViewById<TextView>(R.id.name_txtView)
        description = findViewById<TextView>(R.id.description_txtView)
        ingredients = findViewById<TextView>(R.id.ingredients_txtView)
        instructions = findViewById<TextView>(R.id.instructions_txtView)
        category = findViewById<TextView>(R.id.category_txtView)
        saveEditBtn = findViewById(R.id.save_edit_recipe_btn)
        cancelEditBtn = findViewById(R.id.cancel_edit_recipe_btn)
        editTxtDes = findViewById(R.id.des_edit_recipe)
        editTxtIngredients = findViewById(R.id.ingredients_edit_recipe)
        editTxtInstructions = findViewById(R.id.instructions_edit_recipe)
    }

    private fun getAndSetIntents() {

        idIntent = intent.getStringExtra("id").toString()
        nameIntent = intent.getStringExtra("name")
        descriptionIntent = intent.getStringExtra("description")
        ingredientsIntent = intent.getStringExtra("ingredients")
        instructionsIntent = intent.getStringExtra("instructions")
        categoryIntent = intent.getStringExtra("category")


        name.setText(nameIntent)
        description.setText(descriptionIntent)
        ingredients.setText(ingredientsIntent)
        instructions.setText(instructionsIntent)
        category.setText(categoryIntent)
    }
}