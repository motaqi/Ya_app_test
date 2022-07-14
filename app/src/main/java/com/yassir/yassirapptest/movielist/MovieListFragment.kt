package com.yassir.yassirapptest.movielist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar
import com.yassir.yassirapptest.R
import com.yassir.yassirapptest.data.entity.Movie
import com.yassir.yassirapptest.databinding.FragmentMovieListBinding
import com.yassir.yassirapptest.util.*
import org.koin.android.ext.android.inject

class MovieListFragment : Fragment(R.layout.fragment_movie_list),
    MovieListAdapter.OnMovieItemClickListener {

    private val movieListViewModel by inject<MovieListFragmentViewModel>()
    private lateinit var binding: FragmentMovieListBinding
    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        binding = FragmentMovieListBinding.bind(view)
        movieListViewModel.getMovies()
        observe(movieListViewModel.moviesLiveData, ::fetchMovies)
    }

    private fun fetchMovies(resource: Resource<List<Movie>>) {
        when (resource.status) {
            Status.LOADING -> {
                binding.progressBar.visible()
                binding.movieRecyclerView.gone()
            }
            Status.SUCCESS -> {
                binding.progressBar.gone()
                resource.data?.let {
                    binding.movieRecyclerView.setHasFixedSize(true)
                    val adapter = MovieListAdapter(it)
                    adapter.onMovieItemClickListener = this
                    binding.movieRecyclerView.adapter = adapter
                }
                binding.movieRecyclerView.visible()
            }
            Status.ERROR -> {
                binding.progressBar.gone()
                Snackbar.make(binding.root, resource.message.toString(), Snackbar.LENGTH_SHORT)
                    .show()
            }
        }
    }

    override fun onItemClick(movie: Movie) {
        navController.navigate(
            MovieListFragmentDirections.actionMovieListFragmentToMovieDetailFragment(
                movie.id
            )
        )
    }
}