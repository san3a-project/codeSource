package com.example.code

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_home.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [homeFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [homeFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class homeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnHomeFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.e("hi","hoome")
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onHomeBackInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnHomeFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val fm = activity!!.supportFragmentManager
        icon.setOnClickListener {
            var frag = FormulaireListing()
            val fragmentManager = activity!!.supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.frame_container, frag)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

        var fragment = fm.findFragmentById(R.id.todaysContainer)
        Log.e("h","waloo")

            fragment = HorizontalListViewFragment()
            fm.beginTransaction()
                .add(R.id.todaysContainer, fragment)
                .commit()


        var fragment1 = fm.findFragmentById(R.id.clothingContainer)


            fragment1 = HorizontalListViewFragment()
            fm.beginTransaction()
                .add(R.id.clothingContainer, fragment1)
                .commit()


        var fragment2 = fm.findFragmentById(R.id.jewerlyContainer)


            fragment2 = HorizontalListViewFragment()
            fm.beginTransaction()
                .add(R.id.jewerlyContainer, fragment2)
                .commit()


        var fragment3 = fm.findFragmentById(R.id.fragmentContainer3)


            fragment3 = HorizontalListViewFragment()
            fm.beginTransaction()
                .add(R.id.fragmentContainer3, fragment3)
                .addToBackStack(null)
                .commit()


    /*    var fragment4 = fm.findFragmentById(R.id.fragmentContainer4)

        if (fragment4 == null) {
            fragment4 = ImageHorizontalListFragment()
            fm.beginTransaction()
                .add(R.id.fragmentContainer4, fragment4)
                .commit()
        }*/
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnHomeFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onHomeBackInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment homeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            homeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
