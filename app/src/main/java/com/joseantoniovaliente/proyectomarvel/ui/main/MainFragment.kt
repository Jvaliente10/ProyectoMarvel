package com.joseantoniovaliente.proyectomarvel.ui.main
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.joseantoniovaliente.proyectomarvel.R
import com.joseantoniovaliente.proyectomarvel.databinding.FragmentMainBinding
import com.joseantoniovaliente.proyectomarvel.ui.detail.DetailFragment


class MainFragment:Fragment(R.layout.fragment_main) {
    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: FragmentMainBinding
    private val adapter = CharacterAdapter(){ character -> viewModel.navigateTo(character) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view).apply {
            recycler.adapter = adapter
        }
        (requireActivity() as AppCompatActivity).supportActionBar?.title = getString(R.string.app_name)

        viewModel.state.observe(viewLifecycleOwner){state ->
            binding.progress.visibility =  if (state.loading) VISIBLE else GONE
            state.characters?.let{
                adapter.characters = state.characters
                adapter.notifyDataSetChanged()
            }
            state.navigateTo?.let{
                findNavController().navigate(
                    R.id.action_mainFragment_to_detailFragment,
                    bundleOf(DetailFragment.EXTRA_CHARACTER to it)
                )
                viewModel.onNavigateDone()
            }

        }
    }

}