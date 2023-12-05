package com.example.newsappproject.ui.details

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.newsappproject.R
import com.example.newsappproject.databinding.FragmentDetailsBinding


class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding
    private val args: DetailsFragmentArgs by navArgs()
    private var url = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        url = args.argUrl
        binding.webwiew.webViewClient = WebViewClient()
        binding.webwiew.loadUrl(url)
        binding.webwiew.settings.javaScriptEnabled = true


        binding.backIcon.setOnClickListener {
            val aciton = DetailsFragmentDirections.actionDetailsFragmentToBottomHome()
            findNavController().navigate(aciton)
        }

        binding.shareIcon.setOnClickListener {
            Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, url)
                startActivity(Intent.createChooser(this, getString(R.string.share)))
            }

        }

    }
}
