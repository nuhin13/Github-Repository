package com.nuhin13.githubreposearch.repo_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nuhin13.githubreposearch.api.ValidationUtil
import com.nuhin13.githubreposearch.data.RepositoryItem
import com.nuhin13.githubreposearch.databinding.AdapterRepositoryBinding
import kotlin.math.ln
import kotlin.math.pow

class RepositoryAdapter : RecyclerView.Adapter<MainViewHolder>() {

    var repoList = ArrayList<RepositoryItem>()
    private lateinit var listener: OnItemClickListener

    fun setMovies(movies: ArrayList<RepositoryItem>, listener: OnItemClickListener) {
        this.repoList = movies
        this.listener = listener
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterRepositoryBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val item = repoList[position]
        if (ValidationUtil.validateRepoItem(item)) {
            holder.binding.tvName.text = item.name
            holder.binding.tvStar.text = getFormatNumber(item.stargazers_count.toLong())

            holder.binding.root.setOnClickListener {
                listener.onItemClick(item)
            }
        }
    }

    override fun getItemCount(): Int {
        return repoList.size
    }

    private fun getFormatNumber(count: Long): String {
        if (count < 1000) return "" + count
        val exp = (ln(count.toDouble()) / ln(1000.0)).toInt()
        return String.format("%.1f %c", count / 1000.0.pow(exp.toDouble()), "kMGTPE"[exp - 1])
    }

}

class MainViewHolder(val binding: AdapterRepositoryBinding) :
    RecyclerView.ViewHolder(binding.root)

interface OnItemClickListener {
    fun onItemClick(repo: RepositoryItem)
}