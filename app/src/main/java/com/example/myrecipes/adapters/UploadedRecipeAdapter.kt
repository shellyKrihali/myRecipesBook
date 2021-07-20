package com.example.myrecipes.adapters

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.myrecipes.R
import com.example.myrecipes.models.UploadedRecipe
import com.example.myrecipes.ui.UploadedDetailsActivity
import com.example.myrecipes.util.Constants
import com.example.myrecipes.util.Constants.Companion.FIREBASE_RECIPE
import com.google.firebase.database.*


class UploadedRecipeAdapter(private val list: List<UploadedRecipe>) :
    RecyclerView.Adapter<UploadedRecipeViewHolder>() {
    private var listData: MutableList<UploadedRecipe> = list as MutableList<UploadedRecipe>


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UploadedRecipeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return UploadedRecipeViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: UploadedRecipeViewHolder, position: Int) {
        val uploadedRecipe: UploadedRecipe = list[position]
        holder.bind(list[position], position)
    }

    fun deleteItem(index: Int) {
        listData.removeAt(index)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = list.size

}

class UploadedRecipeViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.upload_recipe_row_layout, parent, false)) {
    private var mNameView: TextView? = null
    private var mDesView: TextView? = null
    private var mBtnDelete: Button

    private var id = ""
    private var name = ""
    private var description = ""
    private var ingredients = ""
    private var instructions = ""
    private var category = ""
    private var image = ""


    init {
        mNameView = itemView.findViewById(R.id.uploadedName_txtView)
        mDesView = itemView.findViewById(R.id.uplodedDes_txtView)
        mBtnDelete = itemView.findViewById(R.id.btn_delete_recipe)

        itemView.setOnClickListener { v ->
            val context: Context = v.context
            val intent = Intent(context, UploadedDetailsActivity::class.java)
            intent.putExtra("id", id)
            intent.putExtra("name", name)
            intent.putExtra("description", description)
            intent.putExtra("ingredients", ingredients)
            intent.putExtra("instructions", instructions)
            intent.putExtra("category", category)
            intent.putExtra("image", image)
            context.startActivity(intent)
        }


    }

    fun bind(uploadedRecipe: UploadedRecipe, index: Int) {
        mNameView?.text = uploadedRecipe.name
        mDesView?.text = uploadedRecipe.description
        id = uploadedRecipe.id


        name = uploadedRecipe.name
        description = uploadedRecipe.description
        ingredients = uploadedRecipe.ingredients
        instructions = uploadedRecipe.instructions
        category = uploadedRecipe.category
        image = uploadedRecipe.image


        mBtnDelete.setOnClickListener {
            val ref = FirebaseDatabase.getInstance().reference
            val applesQuery: Query =
                ref.child(FIREBASE_RECIPE).orderByChild("id").equalTo(id)

            applesQuery.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (appleSnapshot in dataSnapshot.children) {
                        appleSnapshot.ref.removeValue()
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    Log.e(TAG, "onCancelled", databaseError.toException())
                }
            })

            Toast.makeText(itemView.context, "Recipe deleted!", Toast.LENGTH_SHORT).show()

        }

    }


}