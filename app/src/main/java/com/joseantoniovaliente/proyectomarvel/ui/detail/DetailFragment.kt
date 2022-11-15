package com.joseantoniovaliente.proyectomarvel.ui.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.joseantoniovaliente.proyectomarvel.R

class DetailFragment : Fragment(R.layout.fragment_detail) {
    private val viewModel: DetailViewModel by viewModels {
        DetailViewModelFactory(arguments?.getParcelable<Character>(EXTRA_CHARACTER)!!)
    }
}