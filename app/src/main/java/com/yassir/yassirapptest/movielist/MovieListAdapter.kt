package com.yassir.yassirapptest.movielist

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.yassir.yassirapptest.R
import com.yassir.yassirapptest.data.entity.Movie
import com.yassir.yassirapptest.util.asImageUrl
import com.yassir.yassirapptest.util.inflate
import com.yassir.yassirapptest.util.listen

class MovieListAdapter(movieList: List<Movie>) :
    RecyclerView.Adapter<MovieListAdapter.MovieViewHolder>() {

    var onMovieItemClickListener: OnMovieItemClickListener? = null
    private val movies = movieList

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = parent.inflate(R.layout.view_item_movie_recycler_view)
        return MovieViewHolder(view).listen { position, type ->
            onMovieItemClickListener?.onItemClick(movies[position])
        }
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        Picasso
            .get()
            .load(movies[position].poster_path.asImageUrl())
            .placeholder(R.drawable.ic_movie_place_holder)
            .into(holder.itemView.findViewById<ImageView>(R.id.movie_image))
        holder.itemView.findViewById<TextView>(R.id.movie_title).text =
            movies[position].original_title
        holder.itemView.findViewById<TextView>(R.id.movie_release_date).text =
            movies[position].release_date
    }

    override fun getItemCount() = movies.size

    interface OnMovieItemClickListener {
        fun onItemClick(movie: Movie)
    }

}