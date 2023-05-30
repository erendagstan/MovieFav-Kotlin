package com.erendagistan.moviefav.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.erendagistan.moviefav.databinding.FragmentSignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class sign_up : Fragment() {
    private lateinit var auth : FirebaseAuth
    private lateinit var binding : FragmentSignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentSignUpBinding.inflate(layoutInflater,container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSignUp.setOnClickListener {
            signupclicked()
        }

    }

    private fun signupclicked() {
        val email = binding.editTextTextEmailAddress.text.toString()
        val name = binding.etNameSignup.text.toString()
        val password = binding.etPasswordSignup.text.toString()

        if(email.equals("") || password.equals("")){
            Toast.makeText(activity,"Enter email and password!", Toast.LENGTH_LONG).show()
        }
        else{
            auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
                val intent = Intent(activity, LibraryActivity::class.java)
                //val user = auth.currentUser
                startActivity(intent)
            }.addOnFailureListener {
                Toast.makeText(activity,it.localizedMessage,Toast.LENGTH_LONG).show()
            }
        }
    }


}