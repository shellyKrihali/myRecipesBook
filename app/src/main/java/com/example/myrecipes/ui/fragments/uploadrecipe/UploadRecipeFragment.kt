package com.example.myrecipes.ui.fragments.uploadrecipe

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myrecipes.R
import com.example.myrecipes.adapters.UploadedRecipeAdapter
import com.example.myrecipes.databinding.FragmentUploadRecipesBinding
import com.example.myrecipes.models.UploadedRecipe
import com.example.myrecipes.util.Constants.Companion.FIREBASE_RECIPE
import com.example.myrecipes.viewmodels.MainViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UploadRecipeFragment : Fragment() {

    private val mainViewModel by viewModels<MainViewModel>()
    val recipes = arrayListOf<UploadedRecipe>()

    private var _binding: FragmentUploadRecipesBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        recipes.clear()
        _binding = FragmentUploadRecipesBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.mainViewModel = mainViewModel

        fetchDataFromFirebase()
        binding.recipesUploadFab1?.setOnClickListener {
            findNavController().navigate(R.id.action_uploadRecipeFragment_to_createRecipesBottomSheet)
        }


        return binding.root
    }

    private fun setUpRecycleView() {

        val mAdapter by lazy { UploadedRecipeAdapter(recipes) }

        binding.recyclerviewRecipesUpload.adapter = mAdapter
        binding.recyclerviewRecipesUpload.layoutManager = LinearLayoutManager(requireContext())

    }

    fun fetchDataFromFirebase() {
        val ref = FirebaseDatabase.getInstance().getReference(FIREBASE_RECIPE)
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                recipes.clear()
                for (recipeSnapshot in dataSnapshot.children) {
                    val recipe = recipeSnapshot.getValue(UploadedRecipe::class.java)
                    recipes.add(recipe!!)

                }
                if (recipes.size > 0) {
                    setUpRecycleView()
                }else{
                    setUpRecycleView()
                    binding.noRecipesTextView.visibility = View.VISIBLE
                }
                Log.d("pttt", "size: " + recipes.size);
            }

            override fun onCancelled(databaseError: DatabaseError) {
                throw databaseError.toException()
            }
        })
    }


}