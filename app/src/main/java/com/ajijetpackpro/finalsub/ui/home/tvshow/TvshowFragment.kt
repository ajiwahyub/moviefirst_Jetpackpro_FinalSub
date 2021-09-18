package com.ajijetpackpro.finalsub.ui.home.tvshow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ajijetpackpro.finalsub.databinding.FragmentTvshowBinding
import com.ajijetpackpro.finalsub.viewmodel.ViewModelFactory
import com.ajijetpackpro.finalsub.vo.Status


class TvshowFragment : Fragment() {
    private var _binding: FragmentTvshowBinding? = null
    private val binding get() = _binding as FragmentTvshowBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        _binding = FragmentTvshowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        val factory = ViewModelFactory.getInstance(requireActivity())
        val viewModel = ViewModelProvider(this, factory)[TvshowViewModel::class.java]

        viewModel.getPopularTvshow().observe(viewLifecycleOwner, { list ->
            if(list != null) {
                when (list.status) {
                    Status.LOADING -> binding.progressBar3.visibility = View.VISIBLE
                    Status.SUCCESS -> {
                        binding.progressBar3.visibility = View.GONE
                        binding.recycleViewTvshow.adapter?.let { adapter ->
                            when (adapter) {
                                is TvshowAdapter -> {
                                    adapter.submitList(list.data)
                                    adapter.notifyDataSetChanged()
                                }
                            }
                        }
                    }
                    Status.ERROR -> {
                        binding.progressBar3.visibility = View.GONE
                        Toast.makeText(context, "Sorry, your internet connection is in trouble", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }
    private fun setRecyclerView() {
        binding.recycleViewTvshow.layoutManager = LinearLayoutManager(activity)
        binding.recycleViewTvshow.adapter = TvshowAdapter()
    }

}