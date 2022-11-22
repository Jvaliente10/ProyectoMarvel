package com.joseantoniovaliente.proyectomarvel.ui.mcharacter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.joseantoniovaliente.proyectomarvel.R
import com.joseantoniovaliente.proyectomarvel.databinding.FragmentMainBinding
import com.joseantoniovaliente.proyectomarvel.ui.adapter.MCharacterAdapter
import com.joseantoniovaliente.proyectomarvel.ui.mcharacterdetail.DetailFragment
import com.joseantoniovaliente.proyectomarvel.ui.viewmodel.MCharacterViewModel
import com.joseantoniovaliente.proyectomarvel.ui.viewmodel.MainViewModel
import com.joseantoniovaliente.proyectomarvel.ui.viewmodel.MainViewModelFactory


class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var comicsAdapter: MCharacterAdapter


    val TAG = "ComicFrag"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        viewModel = (fragment as MainActivity).viewModel
        recyclerView()

        comicsAdapter.setOnItemClicklistener {
            val bundle = Bundle().apply {
                putSerializable("comic",it)
            }
            findNavController().navigate(
                R.id.action_comicsFragment_to_comicDetailFragment,
                bundle
            )
        }

        viewModel.comics.observe(viewLifecycleOwner, Observer { response ->
            when(response) {
                is Resource.Success ->{
                    hideProgressBar()
                    response.data?.let {  comicsResponse ->
                        comicsAdapter.differ.submitList(comicsResponse.data?.results)
                        comicsAdapter.notifyDataSetChanged()
                        Log.i(TAG, "Error is: $comicsResponse")

                    }
                }
                is Resource.Error ->{
                    hideProgressBar()
                    response.message?.let { message ->
                        Log.e(TAG, "Error is: $message")
                    }
                }
                is Resource.Loading ->{
                    showProgressBar()
                }
            }
        })
    }

    private fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }

    private fun recyclerView(){
        comicsAdapter = ComicsAdapter()
        rvComics.apply {
            adapter = comicsAdapter
            comicsAdapter.notifyDataSetChanged()
            layoutManager = GridLayoutManager(activity, 4, GridLayoutManager.HORIZONTAL,false)
        }
    }




}