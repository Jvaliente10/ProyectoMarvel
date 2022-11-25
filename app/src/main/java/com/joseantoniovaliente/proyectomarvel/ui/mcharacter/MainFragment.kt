package com.joseantoniovaliente.proyectomarvel.ui.mcharacter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.lifecycle.Observer
import com.joseantoniovaliente.proyectomarvel.R
import com.joseantoniovaliente.proyectomarvel.ui.adapter.MCharacterAdapter
import com.joseantoniovaliente.proyectomarvel.ui.viewmodel.MainViewModel
import com.joseantoniovaliente.proyectomarvel.utils.Resource


class MainFragment : Fragment() {

    lateinit var viewModel: MainViewModel
    private lateinit var characterAdapter: MCharacterAdapter


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

        characterAdapter.setOnItemClicklistener {
            val bundle = Bundle().apply {
                putSerializable("mCharacter",it)
            }
            findNavController().navigate(
                R.id.action_mainFragment_to_detailFragment,
                bundle
            )
        }

        viewModel.mcharacters.observe(viewLifecycleOwner, Observer { response ->
            when(response) {
                is Resource.Success ->{
                    hideProgressBar()
                    response.data?.let {  mCharacterResponse ->
                        characterAdapter.differ.submitList(mCharacterResponse.data?.results)
                        characterAdapter.notifyDataSetChanged()
                        Log.i(TAG, "Error is: $mCharacterResponse")

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
        characterAdapter = MCharacterAdapter()
        rvComics.apply {
            adapter = characterAdapter
            characterAdapter.notifyDataSetChanged()
            layoutManager = GridLayoutManager(activity, 4, GridLayoutManager.HORIZONTAL,false)
        }
    }




}