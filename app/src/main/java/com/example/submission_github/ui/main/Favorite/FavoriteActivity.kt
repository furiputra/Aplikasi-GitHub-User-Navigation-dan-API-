package com.example.submission_github.ui.main.Favorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submission_github.databinding.ActivityFavoriteBinding
import com.example.submission_github.ui.main.UserAdapter
import com.example.submission_github.ui.main.data.model.User
import com.example.submission_github.ui.main.data.model.local.FavoriteUser
import com.example.submission_github.ui.main.detail.DetailUserActivity

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var adapter: UserAdapter
    private lateinit var viewModel: FavoriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = UserAdapter()
        adapter.notifyDataSetChanged()

        viewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)

        adapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback{
            override fun onItemClicked(data: User) {
                Intent(this@FavoriteActivity, DetailUserActivity::class.java).also {
                    it.putExtra(DetailUserActivity.EXTRA_USERNAME, data.login)
                    it.putExtra(DetailUserActivity.EXTRA_ID, data.id)
                    it.putExtra(DetailUserActivity.EXTRA_URL, data.avatar_url)
                    startActivity(it)
                }
            }
        })


        binding.apply {
            rvUser.setHasFixedSize(true)
            rvUser.layoutManager = LinearLayoutManager(this@FavoriteActivity)
            rvUser.adapter = adapter
        }

        viewModel.getFavoriteUser()?.observe(this, {
            if (it!= null) {
                val list = mapList(it)
                adapter.setListUser(list)
            }
        })
    }

    private fun mapList(users: List<FavoriteUser>): ArrayList<User>{
        val listUsers = ArrayList<User>()
        for (user in users){
            val userMapped = User (
                login = user.login,
                id = user.id,
                avatar_url = user.avatar_url
                )
            listUsers.add(userMapped)
        }
        return listUsers
    }
}