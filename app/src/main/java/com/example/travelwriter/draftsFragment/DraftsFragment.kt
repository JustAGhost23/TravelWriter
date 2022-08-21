package com.example.travelwriter.draftsFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.travelwriter.R
import com.example.travelwriter.database.ArticleDatabase
import com.example.travelwriter.databinding.DraftsFragmentBinding

class DraftsFragment : Fragment() {
    private lateinit var viewModel: DraftsFragmentViewModel
    private lateinit var binding: DraftsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val database = ArticleDatabase.getDatabase(this.requireActivity().application).articleDao
        val adapter = DraftsAdapter(ArticleClickListener { articleId ->
            viewModel.deleteArticleWithId(articleId)
        })

        viewModel = ViewModelProvider(this, DraftsFragmentViewModelFactory(database))[DraftsFragmentViewModel::class.java]
        binding = DataBindingUtil.inflate(inflater, R.layout.drafts_fragment, container,
            false)

        viewModel.articleList.observe(viewLifecycleOwner){ list ->
            list?.let{
                adapter.submitList(it)
            }
        }

        binding.draftsFragmentList.adapter = adapter
        binding.lifecycleOwner = this

        return binding.root
    }
}