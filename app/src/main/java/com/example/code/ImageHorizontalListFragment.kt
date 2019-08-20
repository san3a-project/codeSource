package com.example.code

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.content.ContentResolver
import android.content.Intent
import android.media.Image
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_formulaire_listing.*
import kotlinx.android.synthetic.main.fragment_listing_detail.*
import java.util.ArrayList


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [ImageHorizontalListFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [ImageHorizontalListFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class ImageHorizontalListFragment : Fragment(), OnMapReadyCallback {
    // TODO: Rename and change types of parameters
  //  internal var listitems = ArrayList<ImageView>
    private lateinit var mMap: GoogleMap
    internal var MyRecyclerView: RecyclerView? = null
   // internal var Fruits = arrayOf("Mango", "Apple", "Grapes", "Papaya", "WaterMelon")
    internal var images =
        intArrayOf(R.drawable.mango, R.drawable.apple, R.drawable.grapes, R.drawable.papaya, R.drawable.watermelon).toCollection(ArrayList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("huui","ouiiii")
      /*  listitems.clear()
        for (i in images.indices) {
                val item = ImageView(activity!!.applicationContext) as ImageView
            item.setImageResource(Images[i])

            listitems.add(item)
        }*/
        activity!!.title = "Fruit List"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.e("huui","oui")
        val view = inflater.inflate(R.layout.fragment_listing_detail, container, false)
        MyRecyclerView = view.findViewById<View>(R.id.cardViewDetail) as RecyclerView
        MyRecyclerView!!.setHasFixedSize(true)
        val MyLayoutManager = LinearLayoutManager(activity)
        MyLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        if ((images.size > 0) and (MyRecyclerView != null)) {
            MyRecyclerView!!.adapter = MyAdapter(images)
        }
        MyRecyclerView!!.layoutManager = MyLayoutManager

        return view
    }

 /*  override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }*/
 override fun onActivityCreated(savedInstanceState: Bundle?) {
     super.onActivityCreated(savedInstanceState)
     (activity as AppCompatActivity).supportActionBar!!.hide()

     seeprofile.setOnClickListener {
         (activity as AppCompatActivity).supportActionBar!!.show()

         var frag =ProfileDetailFragment()
         val fragmentManager = activity!!.supportFragmentManager
         val fragmentTransaction = fragmentManager.beginTransaction()
         fragmentTransaction.add(R.id.frame_container, frag)
         fragmentTransaction.addToBackStack(null)
         fragmentTransaction.commit()

     }
     val fm = activity!!.supportFragmentManager/// getChildFragmentManager();
     // var supportMapFragment = fm.findFragmentById(R.id.map) as SupportMapFragment
     //  if (supportMapFragment == null) {
     var  supportMapFragment = SupportMapFragment.newInstance()
     fm.beginTransaction().replace(R.id.mapDetail_container, supportMapFragment).commit()
     supportMapFragment.getMapAsync(this)
 }


    inner class MyAdapter(private val list: ArrayList<Int>) : RecyclerView.Adapter<MyViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            // create a new view
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.recycle_image_item, parent, false)
            return MyViewHolder(view)
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {


            holder.coverImageView.setImageResource(list[position])


        }

        override fun getItemCount(): Int {
            return list.size
        }
    }

    inner class MyViewHolder(v: View) : RecyclerView.ViewHolder(v) {


        var coverImageView: ImageView


        init {

            coverImageView = v.findViewById<View>(R.id.imageView) as ImageView


        }
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment listingDetail.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ImageHorizontalListFragment().apply {
                Log.e("hi","emmHori")

                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }
}
