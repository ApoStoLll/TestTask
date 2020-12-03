package com.example.test.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.test.App
import com.example.test.MainActivity
import com.example.test.R
import com.example.test.ui.main.MovieListPresenter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.movie_list_item.*

private const val ARG_PARAM1 = "param1"

class DetailFragment : Fragment() {

    private var param1: Int? = null
    lateinit var presenter: DetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getInt(ARG_PARAM1)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movieApi = ((activity as MainActivity).application as App).movieApi
        presenter = DetailPresenter(movieApi)
        param1?.let { presenter.getMovie(it){
            Picasso.with(context)
                .load("https://image.tmdb.org/t/p/w500" + it.poster)
                .into(poster_image_details)
            full_description_details.text = it.description
            release_date.text = it.release
            directors.text = it.directors
            genres.text = it.genres
            cast_detail.text = it.cast
        }
        }
    }

    override fun onDestroy() {
        presenter.onClear()
        super.onDestroy()

    }

    companion object{
        fun newInstance(param1: Int) =
            Bundle().apply {
                    putInt(ARG_PARAM1, param1)
                }
    }

}