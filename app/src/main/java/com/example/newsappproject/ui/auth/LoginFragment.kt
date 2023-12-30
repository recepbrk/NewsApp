package com.example.newsappproject.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.newsappproject.R
import com.example.newsappproject.databinding.FragmentLoginBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.auth.FirebaseAuth


class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var diolog: BottomSheetDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginOperation()
        binding.forgotPassword.setOnClickListener {
            forgotPassword()
        }


    }

    private fun forgotPassword() {
        diolog = BottomSheetDialog(requireContext())
        val view = LayoutInflater.from(context).inflate(R.layout.diolog_forgot_pass, null)
        diolog.setContentView(view)
        val emailEditText: EditText = view.findViewById(R.id.emailBox)
        val sendButton: Button = view.findViewById(R.id.btnReset)
        val cancelButton: Button = view.findViewById(R.id.btnCancel)
        cancelButton.setOnClickListener {
            diolog.dismiss()
        }
        sendButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            if (email.isNotEmpty()) {
                sendPasswordResetEmail(email)
            } else {
                Toast.makeText(context, "Lütfen e-posta adresinizi girin", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        diolog.show()


    }

    private fun sendPasswordResetEmail(email: String) {
        val auth = FirebaseAuth.getInstance()

        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(context, "Şifre sıfırlama isteği gönderildi", Toast.LENGTH_SHORT)
                        .show()
                    diolog.dismiss()
                } else {
                    Toast.makeText(context, "Hata: ${task.exception?.message}", Toast.LENGTH_SHORT)
                        .show()
                }
            }
    }

    private fun loginOperation() {
        firebaseAuth = FirebaseAuth.getInstance()
        binding.txtRegister.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToSignUpFragment()
            findNavController().navigate(action)
        }
        binding.loginButton.setOnClickListener {
            val email = binding.textInputEdittextEmail.text.toString()
            val password = binding.textInputEdittextPasword.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {

                firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            val action =
                                LoginFragmentDirections.actionLoginFragmentToBottomHome()
                            findNavController().navigate(action)
                        } else
                            Toast.makeText(context, it.exception.toString(), Toast.LENGTH_SHORT)
                                .show()

                    }
            } else {
                Toast.makeText(context, "Passwoord is not matching", Toast.LENGTH_SHORT).show()
            }

        }
    }

}



