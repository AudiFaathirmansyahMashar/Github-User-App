package id.sharekom.githubuser.views

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bumptech.glide.Glide
import id.sharekom.githubuser.R
import id.sharekom.githubuser.helpers.Values
import id.sharekom.githubuser.viewmodels.ProfileViewModel
import id.sharekom.githubuser.views.fragments.FollowersFragment
import id.sharekom.githubuser.views.fragments.FollowingFragment
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    private lateinit var viewModel: ProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        profile_viewpager.adapter =
            ScreenSlidePagerAdapter(
                this
            )
        profile_viewpager.isUserInputEnabled = false
        supportActionBar?.title = "Profile"



        val username = intent.getStringExtra(Values.DATA_MAIN).toString()
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        viewModel.setProfile(username)
        viewModel.getProfile().observe(this, Observer {
            val locationUsername = "Location : ${it.location}   Username : ${it.login}"
            val count = "Followers : ${it.followers}   Following : ${it.following}   Repository : ${it.public_repos} \nCompany : ${it.company}"
            val idType = "User id : ${it.id}   User type : ${it.type}"

            Glide.with(this)
                .load(it.avatar_url)
                .into(profile_image)

            name.text = it.name
            location.text = locationUsername
            followers_following.text = count
            id_type.text = idType

            detail_data.visibility = View.VISIBLE
            profile_progress.visibility = View.INVISIBLE
        })

        detail_bottomnavbar.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.followers ->  {
                    profile_viewpager.currentItem = 0
                }
                R.id.following ->  {
                    profile_viewpager.currentItem = 1
                }
            }

            return@setOnNavigationItemSelectedListener true
        }
    }

    private class ScreenSlidePagerAdapter(fa: FragmentActivity?) :
        FragmentStateAdapter(fa!!) {
        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> FollowersFragment()
                1 -> FollowingFragment()
                else -> FollowersFragment()
            }
        }

        override fun getItemCount(): Int {
            return 2
        }
    }
}