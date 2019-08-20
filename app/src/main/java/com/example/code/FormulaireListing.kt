package com.example.code


import android.app.AlertDialog
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
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
import androidx.appcompat.app.AppCompatActivity


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class FormulaireListing : Fragment() {
    private val GALLERY = 1
    private val CAMERA = 2
    private var bitmap :Bitmap? = null
    private var param1: String? = null
    private var param2: String? = null
    private var listener: FormulaireListing.OnFragmentInteractionListener? = null
    var SPINNERLIST = arrayOf("Women's clothing & Shoes", "Jewerly & Accessories", "Arts and Crafts", "Dishes & Cakes","Health & Beauty")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_formulaire_listing, container, false)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        imageView.setOnClickListener { showPictureDialog() }
        (activity as AppCompatActivity).supportActionBar!!.hide()
        val arrayAdapter = ArrayAdapter<String>(activity!!,
                android.R.layout.simple_dropdown_item_1line, SPINNERLIST)
        val materialDesignSpinner = view?.findViewById(R.id.android_material_design_spinner) as MaterialBetterSpinner
        materialDesignSpinner.setAdapter(arrayAdapter)
        button4.setOnClickListener {
            (activity as AppCompatActivity).supportActionBar!!.show()

            var frag = homeFragment()
                val fragmentManager = activity!!.supportFragmentManager
                val fragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.frame_container, frag)
                fragmentTransaction.addToBackStack(null)
                fragmentTransaction.commit()

        }
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
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProfileDetailFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                FormulaireListing().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }

}
