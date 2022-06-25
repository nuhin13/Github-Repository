package com.nuhin13.githubreposearch.repo_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.nuhin13.githubreposearch.R
import com.nuhin13.githubreposearch.data.RepositoryItem
import com.nuhin13.githubreposearch.databinding.RepositoryDetailsBinding
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class RepoDetailsFragment : Fragment() {

    private var _binding: RepositoryDetailsBinding? = null
    private val binding get() = _binding!!

    private lateinit var repo: RepositoryItem

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = RepositoryDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        repo = arguments?.getSerializable("repo") as RepositoryItem

        photoLoad()

        binding.tvDescription.text = repo.description
        binding.tvLastUpdated.text =
            getFormatDate(repo.updated_at, "yyyy-MM-dd'T'hh:mm:ss'Z'", "yyyy-MM-dd HH:mm:ss")
        binding.tvUserName.text = repo.owner.login
    }

    private fun photoLoad() {
        Glide.with(context!!)
            .load(repo.owner.avatar_url)
            .transition(DrawableTransitionOptions.withCrossFade())
            .error(R.drawable.ic_baseline_supervisor_account_24)
            .skipMemoryCache(true)
            .into(binding.ivImage)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getFormatDate(strDate: String, source: String, destination: String): String? {
        var df = SimpleDateFormat(source, Locale.ENGLISH)
        var date: Date? = null
        try {
            date = df.parse(strDate)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        df = SimpleDateFormat(destination)
        return date?.let { df.format(it) }
    }
}