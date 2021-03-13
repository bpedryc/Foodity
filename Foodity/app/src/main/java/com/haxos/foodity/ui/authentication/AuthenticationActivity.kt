package com.haxos.foodity.ui.authentication

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.haxos.foodity.R
import com.haxos.foodity.retrofit.UserService
import com.haxos.foodity.data.model.KeycloakUser
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@AndroidEntryPoint
class AuthenticationActivity : AppCompatActivity() {

    @Inject lateinit var userService: UserService
    lateinit var viewPager : ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)

        val pagerAdapter = AuthenticationPagerAdapter(this)
                .addFragment(LoginFragment())
                .addFragment(RegisterFragment())

        viewPager = findViewById(R.id.view_pager)
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

    fun moveToLogin(view: View) {
        viewPager.setCurrentItem(0, true)
    }

    fun moveToRegister(view: View) {
        viewPager.setCurrentItem(1, true)
    }

    fun register(view: View) {
        val username: String = findViewById<EditText>(R.id.username).text.toString()
        val password: String = findViewById<EditText>(R.id.password).text.toString()

        userService.createUser(KeycloakUser(username, password))
                .enqueue(object : Callback<KeycloakUser> {
                    override fun onResponse(call: Call<KeycloakUser>, response: Response<KeycloakUser>) {

                    }

                    override fun onFailure(call: Call<KeycloakUser>, t: Throwable) {
                        TODO("Not yet implemented")
                    }

                })
    }
}