package com.gharieb.yomicepatask.presentation.fragments.login.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.gharieb.yomicepatask.R
import com.gharieb.yomicepatask.core.common.dialogs.alertDialogProgressBar
import com.gharieb.yomicepatask.core.common.dialogs.okDialog
import com.gharieb.yomicepatask.core.handles.HandleStates.handleErrorResult
import com.gharieb.yomicepatask.core.handles.HandleStates.handleLoadingState
import com.gharieb.yomicepatask.core.handles.TaskResult
import com.gharieb.yomicepatask.databinding.FragmentLoginBinding
import com.gharieb.yomicepatask.presentation.fragments.login.viewModel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LoginViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observerLogin()
        actions()
    }

    private fun actions(){
        binding.apply {
            loginBtn.setOnClickListener {
                performLogin()
            }
        }
    }

    private fun observerLogin(){
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.loginState.collect { result ->
                result?.let {
                    when (result) {
                        is TaskResult.Success -> handleLoginSuccessResult()
                        is TaskResult.Error -> handleErrorResult(errorMessage = result.exception.message, context = requireContext())
                        is TaskResult.Loading -> handleLoadingState(context = requireContext())
                    }
                }
            }
        }
    }

    private fun handleLoginSuccessResult() {
        alertDialogProgressBar?.dismiss()
        findNavController().navigate(R.id.action_loginFragment_to_pharmaciesFragment)
    }

    private fun performLogin() {
        binding.apply {
            val userName = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()
            if (userName.isEmpty() || password.isEmpty()){
                okDialog(getString(R.string.please_fill_all_fields), context = requireContext())
            }else viewModel.login(userNane = userName, password = password)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}