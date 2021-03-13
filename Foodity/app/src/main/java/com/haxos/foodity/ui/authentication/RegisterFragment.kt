package com.haxos.foodity.ui.authentication

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.lifecycle.Observer
import com.haxos.foodity.data.GeneralCallback
import com.haxos.foodity.databinding.FragmentRegisterBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    @Inject lateinit var registerViewModel: RegisterViewModel
    private lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentRegisterBinding.inflate(inflater)

        val username = binding.username
        val password = binding.password

        registerViewModel.registerResult.observe(viewLifecycleOwner, Observer {
            val registerResult = it ?: return@Observer

            binding.loading.visibility = View.GONE

            if (registerResult.error != null) {
                showMessage(registerResult.error)
            }
            if (registerResult.success != null) {
                showMessage(registerResult.success)
                binding.backButton.callOnClick()
            }
        })

        binding.registerButton.setOnClickListener {
            binding.loading.visibility = View.VISIBLE
            registerViewModel.register(username.text.toString(), password.text.toString())
        }

        return binding.root
    }

    private fun showMessage(@StringRes errorString: Int) {
        Toast.makeText(activity?.applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }
}