package com.example.myrecipes.ui.fragments.uploadimagerecipe

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.denzcoskun.imageslider.models.SlideModel
import com.example.myrecipes.databinding.FragmentUploadImageRecipeBinding
import com.example.myrecipes.viewmodels.MainViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ListResult
import com.google.firebase.storage.StorageReference
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class UploadImageRecipeFragment : Fragment() {
    private val mainViewModel by viewModels<MainViewModel>()

    private var _binding: FragmentUploadImageRecipeBinding? = null
    private val binding get() = _binding!!
    val imageArrayList: ArrayList<SlideModel> = ArrayList()


    //  lateinit var uploadedRecipeImg: ImageView
    lateinit var imgURI: Uri //img uri uploaded from gallery
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUploadImageRecipeBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        binding.recipesUploadImageFab?.setOnClickListener {
            selectImgFromGallery()

        }

        if (imageArrayList.size == 0) {
            binding.noRecipesImageTextView.visibility = View.VISIBLE
        }else{
            binding.noRecipesImageTextView.visibility = View.INVISIBLE
        }

        uploadToSliderImage()
        return binding.root

    }

    private fun uploadToSliderImage() {
        val storage = FirebaseStorage.getInstance()
        val storageRef = storage.reference.child("images")


        val listAllTask: Task<ListResult> = storageRef.listAll()
        listAllTask.addOnCompleteListener { result ->
            val items: List<StorageReference> = result.result!!.items
            items.forEachIndexed { index, item ->
                item.downloadUrl.addOnSuccessListener {
                    imageArrayList.add(SlideModel(it.toString()))
                }.addOnCompleteListener {
                    if (imageArrayList.size == 0) {
                        binding.noRecipesImageTextView.visibility = View.VISIBLE
                    } else {
                        binding.noRecipesImageTextView.visibility = View.INVISIBLE
                        binding.imageSlider.stopSliding()
                        binding.imageSlider.setImageList(imageArrayList)
                    }

                }
            }
        }
    }

    private fun uploadImgToFirebase() {

        val formatter = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault())
        val now = Date()
        val fileName = formatter.format(now)
        val storageReference = FirebaseStorage.getInstance().getReference("images/$fileName")

        storageReference.putFile(imgURI).addOnSuccessListener {

            Toast.makeText(context, "image added", Toast.LENGTH_SHORT).show()

        }.addOnFailureListener {
            Toast.makeText(context, "error uploading image", Toast.LENGTH_SHORT).show()
        }


    }

    private fun selectImgFromGallery() {

        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT

        startActivityForResult(intent, 100)
    }

    // this method will be called when the user selected an image from the gallery
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && resultCode == Activity.RESULT_OK) {
            imgURI = data?.data!!
            uploadImgToFirebase()
            imageArrayList.add(SlideModel(imgURI.toString()))
            binding.imageSlider.setImageList(imageArrayList)
            binding.noRecipesImageTextView.visibility = View.INVISIBLE
        }
    }

}