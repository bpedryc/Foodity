package com.haxos.foodity.ui.authentication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.haxos.foodity.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthenticationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)

        val pagerAdapter = AuthenticationPagerAdapter(this)
                .addFragment(LoginFragment())
                .addFragment(RegisterFragment())

        val viewPager = findViewById<ViewPager2>(R.id.view_pager)
        viewPager.adapter = pagerAdapter
    }

    private inner class AuthenticationPagerAdapter (
            activity: FragmentActivity
    ) : FragmentStateAdapter(activity) {
        private val fragmentList = ArrayList<Fragment>()
        override fun createFragment(i: Int) : Fragment {
            return fragmentList[i]
        }
        override fun getItemCount(): Int {
            return fragmentList.size
        }
        fun addFragment(fragment: Fragment) : AuthenticationPagerAdapter {
            fragmentList.add(fragment)
            return this
        }
    }
}
