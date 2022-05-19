package com.mhl.mvp_firebase.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.mhl.mvp_firebase.R
import com.mhl.mvp_firebase.databinding.FragmentSignInBinding
import com.mhl.mvp_firebase.presenter.SignInPresenter
import com.mhl.mvp_firebase.view.SignInView
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class SignInFragment : MvpAppCompatFragment(), SignInView {

    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!

    private val presenter by moxyPresenter {
        SignInPresenter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignInBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.signInButton.setOnClickListener {
            presenter.signIn(
                binding.signInEmail.text.toString(),
                binding.signInPassword.text.toString()
            )
        }
    }

    override fun showError(message: String) {
        AlertDialog.Builder(requireContext())
            .setMessage(message)
            .show()
    }

    override fun auth() {
        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}