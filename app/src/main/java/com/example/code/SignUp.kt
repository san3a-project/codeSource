package com.example.code


import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
 import kotlinx.android.synthetic.main.fragment_sign_up.*


class SignUp : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        textView15.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_signUp_to_signIn)


        }
        button3.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_signUp_to_signIn)

        }

    }

}
