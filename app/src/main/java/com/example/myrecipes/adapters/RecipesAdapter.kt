package com.example.myrecipes.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myrecipes.databinding.RecipesRowLayoutBinding
import com.example.myrecipes.models.FoodRecipe
import com.example.myrecipes.models.Result
import com.example.myrecipes.util.RecipesDiffUtil

class RecipesAdapter : RecyclerView.Adapter<RecipesAdapter.MyViewHolder>() {

    // array of recipes in the recycle view
    private var recipes = emptyList<Result>()

    // RecipesRowLayoutBinding: auto generated class - use binding to bind the api data with the xml file
    class MyViewHolder(private val binding: RecipesRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        // bind class with result
        fun bind(result: Result) {
            binding.result = result
            //update layout whenever there is a change
            binding.executePendingBindings()
        }

        // in order to access this function else where we call it companion object
        companion object {
            // return the myviewholder class we created -
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                // inflate the layout inflater
                val binding = RecipesRowLayoutBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(binding)
            }
        }

    }

    // return parent
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        // from function that we created
        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // return current item from recycle view
        val currentRecipe = recipes[position]
        // to bind the varible row layout
        holder.bind(currentRecipe)
    }

    override fun getItemCount(): Int {
        return recipes.size
    }

    // set the data of recipe function, we will call this function from the recipe fragment and pass
    // new data everytime from this function to update food automatically whe fetch data from API
    // for updating data everytime recieving a new data
    fun setData(newData: FoodRecipe) {
        val recipesDiffUtil =
            RecipesDiffUtil(recipes, newData.results)
        val diffUtilResult = DiffUtil.calculateDiff(recipesDiffUtil)
        recipes = newData.results
        diffUtilResult.dispatchUpdatesTo(this)
        // notify recyler view adapter about new data and update the views   ---> notifyDataSetChanged() (change all old to new)
        //diffutil - check and compare old list with new list and update only new data
    }
}