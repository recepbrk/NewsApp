package com.example.newsappproject.ui.auth.login

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.newsappproject.R
import com.example.newsappproject.databinding.FragmentLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider


class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient
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
        googleAuth()

    }

    private fun googleAuth() {
        firebaseAuth = FirebaseAuth.getInstance()
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(requireContext(), gso)

        binding.googleButton.setOnClickListener {
            signInGoogle()
        }
    }

    private fun signInGoogle() {
        val signInIntent = googleSignInClient.signInIntent
        launcher.launch(signInIntent)
    }

    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                handleResult(task)
            }

        }

    @SuppressLint("SuspiciousIndentation")
    private fun handleResult(task: Task<GoogleSignInAccount>) {
        if (task.isSuccessful) {
            val account: GoogleSignInAccount? = task.result
            if (account != null) {
                updateUI(account)
            }
        } else {
            Toast.makeText(context, task.exception.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateUI(account: GoogleSignInAccount) {
        val credentinal = GoogleAuthProvider.getCredential(account.idToken, null)
        firebaseAuth.signInWithCredential(credentinal).addOnCompleteListener {
            if (it.isSuccessful) {
                findNavController().navigate(R.id.action_loginFragment_to_bottom_home)

            } else {
                Toast.makeText(context, it.exception.toString(), Toast.LENGTH_SHORT).show()

            }
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



