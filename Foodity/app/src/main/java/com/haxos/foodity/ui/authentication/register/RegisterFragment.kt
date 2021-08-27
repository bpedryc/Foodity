package com.haxos.foodity.ui.authentication.register

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.lifecycle.Observer
import com.haxos.foodity.R
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

        binding.apply {

            registerButton.setOnClickListener {
                AlertDialog.Builder(requireActivity())
                    .setTitle(getString(R.string.dialog_title_register))
                    .setMessage(getString(R.string.dialog_message_register))
                    .setPositiveButton(android.R.string.yes) {_, _ ->
                        loading.visibility = View.VISIBLE
                        registerViewModel.register(
                            username.text.toString(), email.text.toString(), password.text.toString())
                    }
                    .setNegativeButton(android.R.string.no) {_, _ -> }
                    .show()
            }
        }

        return binding.root
    }

    private fun showMessage(@StringRes errorString: Int) {
        Toast.makeText(activity?.applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }
}