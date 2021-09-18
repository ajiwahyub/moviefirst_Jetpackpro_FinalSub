package com.ajijetpackpro.finalsub.ui.home.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ajijetpackpro.finalsub.databinding.FragmentMovieBinding
import com.ajijetpackpro.finalsub.viewmodel.ViewModelFactory
import com.ajijetpackpro.finalsub.vo.Status


class MovieFragment : Fragment() {
    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding as FragmentMovieBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        val factory = ViewModelFactory.getInstance(requireActivity())
        val viewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]
            viewModel.getPopularMovies().observe(viewLifecycleOwner, { list ->
                if(list != null) {
                    when (list.status) {
                        Status.LOADING -> binding.progressBar2.visibility = View.VISIBLE
                        Status.SUCCESS -> {
                            binding.progressBar2.visibility = View.GONE
                            binding.recycleViewMovie.adapter?.let { adapter ->
                                when (adapter) {
                                    is MovieAdapter -> {
                                        adapter.submitList(list.data)
                                        adapter.notifyDataSetChanged()
                                    }
                                }
                            }
                        }
                        Status.ERROR -> {
                            binding.progressBar2.visibility = View.GONE
                            Toast.makeText(context, "Sorry, your internet connection is in trouble", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })
        }
    private fun setRecyclerView() {
        binding.recycleViewMovie.layoutManager = LinearLayoutManager(context)
        binding.recycleViewMovie.adapter = MovieAdapter()
    }

}