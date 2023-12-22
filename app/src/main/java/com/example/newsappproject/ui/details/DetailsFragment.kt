package com.example.newsappproject.ui.details

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.newsappproject.R
import com.example.newsappproject.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding
    private val webViewModel: DetailsViewModel by viewModels()
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
        binding.fabSave.setOnClickListener {
            webViewModel.addFavoriteArticle(args.article)
        }
        webView()
        backButton()
        shareArticle()


    }

    private fun webView() {
        url = args.article.url
        binding.webwiew.webViewClient = WebViewClient()
        binding.webwiew.loadUrl(url)
        binding.webwiew.settings.javaScriptEnabled = true
    }

    private fun backButton() {

        binding.backIcon.setOnClickListener {
            val aciton = DetailsFragmentDirections.actionDetailsFragmentToBottomHome()
            findNavController().navigate(aciton)
        }
    }

    private fun shareArticle() {
        binding.shareIcon.setOnClickListener {
            Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, url)
                startActivity(Intent.createChooser(this, getString(R.string.share)))
            }

        }
    }

}
