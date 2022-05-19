package com.mhl.mvp_firebase.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.mhl.mvp_firebase.R
import com.mhl.mvp_firebase.databinding.FragmentSignedBinding
import com.mhl.mvp_firebase.dataclasses.Professions
import com.mhl.mvp_firebase.presenter.SignedPresenter
import com.mhl.mvp_firebase.utils.RecyclerAdapter
import com.mhl.mvp_firebase.view.SignedView
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class SignedFragment : MvpAppCompatFragment(), SignedView {

    private var _binding: FragmentSignedBinding? = null
    private val binding get() = _binding!!
    private val presenter by moxyPresenter { SignedPresenter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.getData()
        binding.signedButton.setOnClickListener {
            presenter.signOut()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun recyclerFill(data: ArrayList<Any>) {
        Log.d("Updated", "FIIIIILLL")
        val adapter = RecyclerAdapter(data as ArrayList<Professions>, requireContext())
        adapter.setOnItemClick(object : RecyclerAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {
                Toast.makeText(requireContext(), position.toString(), Toast.LENGTH_SHORT).show()
            }
        })
        binding.signedRecycler.adapter = adapter

    }

    override fun dataIsEmpty() {
        binding.signedRecycler.adapter = null
    }

    override fun signOut() {
        findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
    }
}