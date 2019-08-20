package com.example.code

/**
 * Created by anonymous on 11/4/16.
 */

import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import java.util.ArrayList

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [HorizontalListViewFragment.OnListFragmentInteractionListener] interface.
 */
class HorizontalListViewFragment : Fragment() {

    internal var listitems = ArrayList<Fruit>()
    private var listener : OnListFragmentInteractionListener? = null
    internal var MyRecyclerView: RecyclerView? = null
    internal var Fruits = arrayOf("Mango", "Apple", "Grapes", "Papaya", "WaterMelon")
    internal var Images =
        intArrayOf(R.drawable.mango, R.drawable.apple, R.drawable.grapes, R.drawable.papaya, R.drawable.watermelon)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listitems.clear()
        for (i in Fruits.indices) {
            val item = Fruit()
            item.cardName = Fruits[i]
            item.imageResourceId = Images[i]
            item.isfav = 0
            item.isturned = 0
            listitems.add(item)
        }
        activity!!.title = "Fruit List"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_horizontal_list_view, container, false)
        MyRecyclerView = view.findViewById<View>(R.id.cardView) as RecyclerView
        MyRecyclerView!!.setHasFixedSize(true)
        val MyLayoutManager = LinearLayoutManager(activity)
        MyLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        if ((listitems.size > 0) and (MyRecyclerView != null)) {
            MyRecyclerView!!.adapter = MyAdapter(listitems, listener)
        }
        MyRecyclerView!!.layoutManager = MyLayoutManager
Log.e("h","waloo2")
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            listener = context
            Log.e("hi","hihi")
        } else {
            throw RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }


    interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onListFragmentInteraction(item:Fruit)
    }

    companion object {

        const val ARG_TITRE = "titre"
        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance( titre: String) =
            HorizontalListViewFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_TITRE, titre)
                }
            }
    }

    inner class MyAdapter(private val list: ArrayList<Fruit>,  private val mListener: OnListFragmentInteractionListener?) : RecyclerView.Adapter<MyViewHolder>() {

        private val mOnClickListener: View.OnClickListener

        init {
            mOnClickListener = View.OnClickListener { v ->
                val item = v.tag as Fruit

                // Notify the active callbacks interface (the activity, if the fragment is attached to
                // one) that an item has been selected.

                mListener?.onListFragmentInteraction(item)

            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            // create a new view
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.recycle_items, parent, false)
            return MyViewHolder(view)
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            val item = Fruit()
            holder.titleTextView.text = list[position].cardName
            holder.coverImageView.setImageResource(list[position].imageResourceId)
            holder.coverImageView.tag = list[position].imageResourceId
            holder.likeImageView.tag = R.drawable.ic_like

            with(holder.v) {
                tag = item
                setOnClickListener(mOnClickListener)
            }

        }

        override fun getItemCount(): Int {
            return list.size
        }
    }

    inner class MyViewHolder(val v: View) : RecyclerView.ViewHolder(v) {

        var titleTextView: TextView
        var coverImageView: ImageView
        var likeImageView: ImageView
        var shareImageView: ImageView

        init {
            titleTextView = v.findViewById<View>(R.id.titleTextView) as TextView
            coverImageView = v.findViewById<View>(R.id.coverImageView) as ImageView
            likeImageView = v.findViewById<View>(R.id.likeImageView) as ImageView
            shareImageView = v.findViewById<View>(R.id.shareImageView) as ImageView
            likeImageView.setOnClickListener {
                val id = likeImageView.tag as Int
                if (id == R.drawable.ic_like) {

                    likeImageView.tag = R.drawable.ic_liked
                    likeImageView.setImageResource(R.drawable.ic_liked)

                    Toast.makeText(activity, titleTextView.text.toString() + " added to favourites", Toast.LENGTH_SHORT)
                        .show()

                } else {

                    likeImageView.tag = R.drawable.ic_like
                    likeImageView.setImageResource(R.drawable.ic_like)
                    Toast.makeText(
                        activity,
                        titleTextView.text.toString() + " removed from favourites",
                        Toast.LENGTH_SHORT
                    ).show()


                }
            }



            shareImageView.setOnClickListener {
                val imageUri = Uri.parse(
                    ContentResolver.SCHEME_ANDROID_RESOURCE +
                            "://" + resources.getResourcePackageName(coverImageView.id)
                            + '/'.toString() + "drawable" + '/'.toString() + resources.getResourceEntryName(
                        coverImageView.tag as Int
                    )
                )


                val shareIntent = Intent()
                shareIntent.action = Intent.ACTION_SEND
                shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri)
                shareIntent.type = "image/jpeg"
                startActivity(Intent.createChooser(shareIntent, resources.getText(R.string.send_to)))
            }


        }
    }
}