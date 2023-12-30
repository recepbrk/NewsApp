package com.example.newsappproject.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.newsappproject.databinding.FragmentSignUpBinding
import com.google.firebase.auth.FirebaseAuth


class SignUpFragment : Fragment() {
    private lateinit var binding: FragmentSignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        signUpOperation()
    }

    private fun signUpOperation() {
        firebaseAuth = FirebaseAuth.getInstance()
        binding.sigUpButton.setOnClickListener {
            val email = binding.textInputEdittextEmail.text.toString()
            val password = binding.textInputEdittextPasword.text.toString()
            val confirmPassword = binding.textInputEdittextPaswordConfirm.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()) {
                if (password == confirmPassword) {
                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                val action =
                                    SignUpFragmentDirections.actionSignUpFragmentToLoginFragment()
                                findNavController().navigate(action)
                            } else
                                Toast.makeText(context, it.exception.toString(), Toast.LENGTH_SHORT)
                                    .show()

                        }
                } else {
                    Toast.makeText(context, "Passwoord is not matching", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(context, "Empty Fields Are not Allowed .", Toast.LENGTH_SHORT).show()

            }
        }
    }

}