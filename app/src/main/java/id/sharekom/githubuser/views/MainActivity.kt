package id.sharekom.githubuser.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import id.sharekom.githubuser.R
import id.sharekom.githubuser.adapters.MainAdapter
import id.sharekom.githubuser.viewmodels.SearchDataViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var searchDataViewModel: SearchDataViewModel
    private lateinit var adapter:MainAdapter
    private val editTextData:String = "Data"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = MainAdapter()
        adapter.notifyDataSetChanged()

        if (savedInstanceState != null){
            val data = savedInstanceState.getString(editTextData)

            search.editText?.setText(data)
        }

        searchDataViewModel = ViewModelProvider(this).get(SearchDataViewModel::class.java)
        btn_search.setOnClickListener {
            getActionData()
        }

        search.editText?.setOnEditorActionListener(object : TextView.OnEditorActionListener{
            override fun onEditorAction(p0: TextView?, p1: Int, p2: KeyEvent?): Boolean {
                if (p1 == EditorInfo.IME_ACTION_SEND){
                    getActionData()

                    return true
                }

                return false
            }

        })

        recycler_main.setHasFixedSize(true)

        getData()
    }

    private fun getActionData(){
        val search = search.editText?.text.toString()

        if (search.isEmpty()){
            Toast.makeText(this, resources.getString(R.string.search_message), Toast.LENGTH_SHORT).show()
        }else{
            setProgress(true)
            setMessage(false)
            searchDataViewModel.setSearchData(search)
            getData()
        }
    }

    private fun getData(){
        searchDataViewModel.getSearchData().observe(this, Observer {
            if (it.total_count > 0) {
                adapter.setData(it.items)
                recycler_main.adapter = adapter
                setProgress(false)
                setMessage(false)
            }else{
                setProgress(false)
                setMessage(true)
            }
        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putString(editTextData, search.editText?.text.toString())
    }

    private fun setProgress(condition: Boolean){
        if (condition) {
            progress_main.visibility = View.VISIBLE
        }else{
            progress_main.visibility = View.INVISIBLE
        }
    }

    private fun setMessage(condition:Boolean){
        if (condition){
            data_message_main.visibility = View.VISIBLE
        }else{
            data_message_main.visibility = View.INVISIBLE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.change_language) {
            val mIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
            startActivity(mIntent)
        }
        return super.onOptionsItemSelected(item)
    }
}