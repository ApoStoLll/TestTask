package com.example.test.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.test.R
import com.example.test.databinding.FragmentDetailBinding
import com.example.test.ui.main.MovieListViewModel
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val ARG_PARAM1 = "param1"

class DetailFragment : Fragment(R.layout.fragment_detail) {

    private var param1: Int? = null
    private val viewModel : DetailViewModel by viewModel()
    private val binding by viewBinding(FragmentDetailBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getInt(ARG_PARAM1)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        param1?.let {
            viewModel.getMovie(it){
                binding.apply {
                    Picasso.with(context)
                        .load("https://image.tmdb.org/t/p/w500" + it.poster)
                        .into(posterImageDetails)
                    fullDescriptionDetails.text = it.description
                    releaseDate.text = it.release
                    directors.text = it.directors
                    genres.text = it.genres
                    castDetail.text = it.cast
                }

            }
        }
    }


    companion object{
        fun newInstance(param1: Int) =
            Bundle().apply {
                putInt(ARG_PARAM1, param1)
            }
    }

}