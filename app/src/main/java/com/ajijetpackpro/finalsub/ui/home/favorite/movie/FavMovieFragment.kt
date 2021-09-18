package com.ajijetpackpro.finalsub.ui.home.favorite.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ajijetpackpro.finalsub.R
import com.ajijetpackpro.finalsub.databinding.FragmentFavMovieBinding
import com.ajijetpackpro.finalsub.ui.home.favorite.FavoriteViewModel
import com.ajijetpackpro.finalsub.ui.home.movie.MovieAdapter
import com.ajijetpackpro.finalsub.viewmodel.ViewModelFactory


class FavMovieFragment : Fragment() {
    private var _binding: FragmentFavMovieBinding ? = null
    private val binding get() = _binding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavMovieBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setRecyclerView()
        val factory = ViewModelFactory.getInstance(requireActivity())
        val viewModel = ViewModelProvider(this, factory)[FavoriteViewModel::class.java]

        viewModel.getFavoriteMovie().observe(viewLifecycleOwner,  {
            if (it != null){
                binding?.rvFavMovie?.adapter?.let {adapter ->
                    when (adapter) {
                        is MovieAdapter -> {
                            if (it.isNullOrEmpty()){
                                binding?.rvFavMovie?.visibility = View.GONE
                                binding?.tvEmptyFavmovie?.text = resources.getString(R.string.empty_favmovie)
                            } else {
                                binding?.rvFavMovie?.visibility = View.VISIBLE
                                adapter.submitList(it)
                                adapter.notifyDataSetChanged()
                            }
                        }
                    }
                }
            }
        })
    }


    private fun setRecyclerView() {
        val adapter = MovieAdapter()
       binding?.rvFavMovie?.layoutManager = LinearLayoutManager(activity)
        binding?.rvFavMovie?.setHasFixedSize(true)
        binding?.rvFavMovie?.adapter = adapter
    }

}