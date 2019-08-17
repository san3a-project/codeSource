package com.example.code


import android.app.AlertDialog
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.widget.ArrayAdapter
import android.widget.Toast
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner
import kotlinx.android.synthetic.main.fragment_formulaire_listing.*
import java.io.IOException


class FormulaireListing : Fragment() {
    private val GALLERY = 1
    private val CAMERA = 2
    private var bitmap :Bitmap? = null

    var SPINNERLIST = arrayOf("Women's clothing & Shoes", "Jewerly & Accessories", "Arts and Crafts", "Dishes & Cakes","Health & Beauty")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_formulaire_listing, container, false)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        imageView.setOnClickListener { showPictureDialog() }

        val arrayAdapter = ArrayAdapter<String>(activity!!,
                android.R.layout.simple_dropdown_item_1line, SPINNERLIST)
        val materialDesignSpinner = view?.findViewById(R.id.android_material_design_spinner) as MaterialBetterSpinner
        materialDesignSpinner.setAdapter(arrayAdapter)
    }
    private fun showPictureDialog() {
        val pictureDialog = AlertDialog.Builder(activity!!)
        pictureDialog.setTitle("Choose an action")
        val pictureDialogItems = arrayOf("From Galerie", "Take a photo")
        pictureDialog.setItems(pictureDialogItems
        ) {_, which ->
            when (which) {
                0 -> choosePhotoFromGallary()
                1 -> takePhotoFromCamera()
            }
        }
        pictureDialog.show()
    }

    fun choosePhotoFromGallary() {
        val galleryIntent = Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI)

        startActivityForResult(galleryIntent, GALLERY)
    }

    private fun takePhotoFromCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, CAMERA)
    }

    override fun onActivityResult(requestCode:Int, resultCode:Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)
        /* if (resultCode == this.RESULT_CANCELED)
         {
         return
         }*/
        if (requestCode == GALLERY)
        {
            if (data != null)
            {
                val contentURI = data.data
                try
                {
                    bitmap = MediaStore.Images.Media.getBitmap(activity!!.contentResolver, contentURI)

                      imageView.setImageBitmap(bitmap)



                }
                catch (e: IOException) {
                    e.printStackTrace()
                    Toast.makeText(activity!!, "Failed!", Toast.LENGTH_SHORT).show()
                }

            }

        }
        else if (requestCode == CAMERA)
        {
            bitmap = data!!.extras!!.get("data") as Bitmap
               imageView.setImageBitmap(bitmap)
        }
    }

}
