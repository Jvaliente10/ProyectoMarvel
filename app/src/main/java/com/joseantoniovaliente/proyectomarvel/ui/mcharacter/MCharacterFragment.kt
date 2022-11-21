package com.joseantoniovaliente.proyectomarvel.ui.mcharacter

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.joseantoniovaliente.proyectomarvel.R
import com.joseantoniovaliente.proyectomarvel.ui.adapter.MCharacterAdapter
import com.joseantoniovaliente.proyectomarvel.ui.viewmodel.MainViewModel


class MCharacterFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var mCharacterAdapter: MCharacterAdapter


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



        viewModel = (activity as MainActivity).viewModel
        recyclerView()

        mCharacterAdapter.setOnItemClicklistener {
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