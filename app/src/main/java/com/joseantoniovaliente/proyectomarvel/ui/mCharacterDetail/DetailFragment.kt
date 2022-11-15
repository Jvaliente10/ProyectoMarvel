package com.joseantoniovaliente.proyectomarvel.ui.mCharacterDetail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.joseantoniovaliente.proyectomarvel.R
import com.joseantoniovaliente.proyectomarvel.databinding.FragmentDetailBinding
import com.joseantoniovaliente.proyectomarvel.ui.detail.DetailViewModel
import com.joseantoniovaliente.proyectomarvel.ui.detail.DetailViewModelFactory
import com.joseantoniovaliente.proyectomarvel.ui.loadUrl

class DetailFragment : Fragment(R.layout.fragment_detail) {
    private val viewModel: DetailViewModel by viewModels {
       DetailViewModelFactory(arguments?.getParcelable<MCharacter>(EXTRA_CHARACTER)!!)
    }
    companion object{
        const val EXTRA_CHARACTER = "DetailActivity:Character"
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentDetailBinding.bind(view)


        viewModel.mCharacter.observe(viewLifecycleOwner){ mCharacter->
            (requireActivity()as AppCompatActivity).supportActionBar?.title = mCharacter.name
            binding.detailImage.loadUrl(mCharacter.url)

        }
    }
}