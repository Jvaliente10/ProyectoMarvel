package com.joseantoniovaliente.proyectomarvel.ui.mCharacter
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.joseantoniovaliente.proyectomarvel.R
import com.joseantoniovaliente.proyectomarvel.databinding.FragmentMainBinding
import com.joseantoniovaliente.proyectomarvel.ui.adapter.MCharacterAdapter
import com.joseantoniovaliente.proyectomarvel.ui.mCharacterDetail.DetailFragment
import com.joseantoniovaliente.proyectomarvel.ui.viewModel.MCharacterViewModel


class MainFragment:Fragment(R.layout.fragment_main) {
    private val viewModel: MCharacterViewModel by viewModels()
    private lateinit var binding: FragmentMainBinding
    private val adapter = MCharacterAdapter(){ mCharacter -> viewModel.navigateTo(mCharacter) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view).apply {
            recycler.adapter = adapter
        }
        (requireActivity() as AppCompatActivity).supportActionBar?.title = getString(R.string.app_name)

        viewModel.state.observe(viewLifecycleOwner){state ->
            binding.progress.visibility =  if (state.loading) VISIBLE else GONE
            state.MCharacters?.let{
                adapter.mCharacters = state.MCharacters
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