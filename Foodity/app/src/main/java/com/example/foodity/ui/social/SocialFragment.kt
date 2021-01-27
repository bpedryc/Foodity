package com.example.foodity.ui.social

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.foodity.R
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SocialFragment : Fragment() {

    @Inject lateinit var socialViewModel: SocialViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_social, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        socialViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

   /* override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        *//*menu?.add(Menu.NONE, R.id.my_toolbar, 10, R.string.app_name)
        return super.onCreateOptionsMenu(menu)*//*
    }*/
}
