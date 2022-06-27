package com.nuhin13.githubreposearch.repo_list
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.nuhin13.githubreposearch.R
import com.nuhin13.githubreposearch.api.MainRepository
import com.nuhin13.githubreposearch.api.RetrofitService
import com.nuhin13.githubreposearch.data.FakeRepositoryItem
import com.nuhin13.githubreposearch.data.MyViewModelFactory
import com.nuhin13.githubreposearch.data.RepositoryItem
import com.nuhin13.githubreposearch.databinding.RepositoryListBinding

class RepoListFragment : Fragment(), OnItemClickListener{

    private var _binding: RepositoryListBinding? = null
    private val binding get() = _binding!!

    lateinit var viewModel: MainViewModel
    private val adapter = RepositoryAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = RepositoryListBinding.inflate(inflater, container, false)
        context.let {
            val retrofitService = RetrofitService.getInstance(context!!)
            val mainRepository = MainRepository(retrofitService)

            binding.recyclerview.adapter = adapter
            viewModel = ViewModelProvider(
                this,
                MyViewModelFactory(mainRepository)
            )[MainViewModel::class.java]

            viewModel.storeInit(context!!)
            viewModel.retrieveDate()

            generateList()
            generateSortItem()
        }
        return binding.root
    }

    private fun generateList(){

        /// For testing purpose
        //adapter.setMovies(FakeRepositoryItem.repoList.items, this)

        viewModel.repoList.observe(viewLifecycleOwner) {
            adapter.setMovies(it.items, this)
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) {
            Toast.makeText(context!!, it, Toast.LENGTH_SHORT).show()
        }

        viewModel.loading.observe(viewLifecycleOwner, Observer {
            if (it) {
                binding.progressDialog.visibility = View.VISIBLE
            } else {
                binding.progressDialog.visibility = View.GONE
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun generateSortItem() {
        val languages = resources.getStringArray(R.array.sort)

        val adapter = ArrayAdapter(context!!, R.layout.drop_down_item, languages)
        binding.spinnerSort.adapter = adapter

        binding.spinnerSort.setSelection(viewModel.position)

        binding.spinnerSort.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?,
                view: View?, position: Int, id: Long) {
                viewModel.saveData(position)
                viewModel.getTopRepository(languages[position])
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(repo: RepositoryItem) {
        val bundle = Bundle()
        bundle.putSerializable("repo", repo)

        findNavController().navigate(R.id.action_list_to_details, bundle)
    }
}