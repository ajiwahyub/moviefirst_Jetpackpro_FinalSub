package com.ajijetpackpro.finalsub.ui.home.favorite.tvshow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ajijetpackpro.finalsub.R
import com.ajijetpackpro.finalsub.databinding.FragmentFavTvshowBinding
import com.ajijetpackpro.finalsub.ui.home.favorite.FavoriteViewModel
import com.ajijetpackpro.finalsub.ui.home.tvshow.TvshowAdapter
import com.ajijetpackpro.finalsub.viewmodel.ViewModelFactory

class FavTvshowFragment : Fragment() {

    private var _binding: FragmentFavTvshowBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFavTvshowBinding.inflate(layoutInflater, container,false)
        return binding?.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setRecyclerView()
        val factory = ViewModelFactory.getInstance(requireActivity())
        val viewModel = ViewModelProvider(this, factory)[FavoriteViewModel::class.java]

        viewModel.getFavoriteTvShow().observe(viewLifecycleOwner, {
            if (it != null){
                binding?.recycleViewFavTvshow?.adapter?.let {adapter ->
                    when (adapter) {
                        is TvshowAdapter -> {
                            if (it.isNullOrEmpty()){
                                binding?.recycleViewFavTvshow?.visibility = View.GONE
                                binding?.tvEmptyFavtvshow?.text = resources.getString(R.string.empty_favmovie)
                            } else {
                                binding?.recycleViewFavTvshow?.visibility = View.VISIBLE
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
        val adapter = TvshowAdapter()
        binding?.recycleViewFavTvshow?.layoutManager = LinearLayoutManager(activity)
        binding?.recycleViewFavTvshow?.setHasFixedSize(true)
        binding?.recycleViewFavTvshow?.adapter = adapter
    }

}