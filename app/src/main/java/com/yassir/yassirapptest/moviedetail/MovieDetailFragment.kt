package com.yassir.yassirapptest.moviedetail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import com.yassir.yassirapptest.R
import com.yassir.yassirapptest.data.entity.MovieDetailResponse
import com.yassir.yassirapptest.databinding.FragmentMovieDetailBinding
import com.yassir.yassirapptest.util.*
import org.koin.android.ext.android.inject

class MovieDetailFragment : Fragment(R.layout.fragment_movie_detail) {

    private val movieDetailViewModule by inject<MovieDetailFragmentViewModel>()
    private lateinit var binding: FragmentMovieDetailBinding
    private lateinit var navController: NavController
    private val args: MovieDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        binding = FragmentMovieDetailBinding.bind(view)
        movieDetailViewModule.getMovieById(args.movieId)
        observe(movieDetailViewModule.movieDetail, ::movieById)
    }

    private fun movieById(resource: Resource<MovieDetailResponse>) {
        when (resource.status) {
            Status.LOADING -> {
                binding.progressBar.visible()
                binding.movieAvatar.gone()
                binding.movieOriginalTitle.gone()
                binding.movieOverview.gone()
            }
            Status.SUCCESS -> {
                resource.data?.let {
                    Picasso.get()
                        .load(it.backdrop_path.asImageUrl())
                        .placeholder(R.drawable.ic_movie_place_holder)
                        .into(binding.movieAvatar)
                    binding.movieOriginalTitle.text = it.title
                    binding.movieOverview.text = it.overview

                }
                binding.progressBar.gone()
                binding.movieAvatar.visible()
                binding.movieOriginalTitle.visible()
                binding.movieOverview.visible()
            }
            Status.ERROR -> {
                binding.progressBar.gone()
                binding.movieAvatar.gone()
                binding.movieOriginalTitle.gone()
                binding.movieOverview.gone()
                Snackbar.make(
                    binding.progressBar,
                    resource.message.toString(),
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
    }

}