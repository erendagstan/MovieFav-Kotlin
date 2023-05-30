package com.erendagistan.moviefav.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.erendagistan.moviefav.R
import com.erendagistan.moviefav.databinding.FragmentSignInBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class sign_in : Fragment() {
    private lateinit var auth : FirebaseAuth
    private lateinit var navController: NavController
   private lateinit var binding : FragmentSignInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        val currentUser = auth.currentUser
//        if(currentUser!=null){
//            val intent = Intent(activity, LibraryActivity::class.java)
//            startActivity(intent)
//        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentSignInBinding.inflate(layoutInflater,container,false)
        val view = binding.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.btnSignIn.setOnClickListener {result->
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            if(email.equals("") || password.equals("")){
                Toast.makeText(activity,"Enter email and password correctly!",Toast.LENGTH_LONG).show()
            }
            else{
                auth.signInWithEmailAndPassword(email,password).addOnSuccessListener {
                    result.findNavController().navigate(R.id.action_sign_in_to_libraryActivity)
//                    val intent = Intent(activity,LibraryActivity::class.java)
//                    startActivity(intent)
                }.addOnFailureListener {
                    Toast.makeText(activity,it.localizedMessage,Toast.LENGTH_LONG).show()
                }
            }
        }

        binding.btnSignUp.setOnClickListener {
            it.findNavController().navigate(R.id.action_sign_in_to_sign_up)
        }
    }
}