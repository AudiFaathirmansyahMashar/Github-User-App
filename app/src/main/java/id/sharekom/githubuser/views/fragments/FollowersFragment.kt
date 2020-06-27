package id.sharekom.githubuser.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import id.sharekom.githubuser.R
import id.sharekom.githubuser.adapters.FollowersAdapter
import id.sharekom.githubuser.helpers.Values
import id.sharekom.githubuser.viewmodels.FollowersViewModel
import kotlinx.android.synthetic.main.fragment_followers.*

class FollowersFragment : Fragment() {
    private lateinit var viewModel:FollowersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val username = activity?.intent?.getStringExtra(Values.DATA_MAIN).toString()

        viewModel = ViewModelProvider(this).get(FollowersViewModel::class.java)
        viewModel.setFollowers(username)
        getData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_followers, container, false)
    }

    override fun onResume() {
        super.onResume()

        getData()
        recycler_followers.setHasFixedSize(true)
    }

    private fun getData(){
        viewModel.getFollowers().observe(this, Observer {
            if (it.isNotEmpty()) {
                progress_followers.visibility = View.INVISIBLE
                message_followers.visibility = View.INVISIBLE
                recycler_followers.adapter = FollowersAdapter(it)
            }else{
                recycler_followers.adapter = FollowersAdapter(it)
                message_followers.visibility = View.VISIBLE
                progress_followers.visibility = View.INVISIBLE
            }
        })
    }
}