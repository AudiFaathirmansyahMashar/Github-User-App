package id.sharekom.githubuser.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import id.sharekom.githubuser.R
import id.sharekom.githubuser.adapters.FollowingAdapter
import id.sharekom.githubuser.helpers.Values
import id.sharekom.githubuser.viewmodels.FollowingViewModel
import kotlinx.android.synthetic.main.fragment_following.*

class FollowingFragment : Fragment() {
    private lateinit var viewModel: FollowingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val username = activity?.intent?.getStringExtra(Values.DATA_MAIN).toString()

        viewModel = ViewModelProvider(this).get(FollowingViewModel::class.java)
        viewModel.setFollowing(username)
        getData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_following, container, false)
    }

    override fun onResume() {
        super.onResume()

        getData()
        recycler_following.setHasFixedSize(true)
    }

    private fun getData(){
        viewModel.getFollowing().observe(this, Observer {
            if (it.isNotEmpty()){
                progress_following.visibility = View.INVISIBLE
                message_following.visibility = View.INVISIBLE
                recycler_following.adapter = FollowingAdapter(it)
            }else{
                progress_following.visibility = View.INVISIBLE
                message_following.visibility = View.VISIBLE
                recycler_following.adapter = FollowingAdapter(it)
            }
        })
    }
}