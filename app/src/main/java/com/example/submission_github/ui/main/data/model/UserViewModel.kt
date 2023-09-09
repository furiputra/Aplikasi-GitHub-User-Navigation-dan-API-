package com.example.submission_github.ui.main.data.model

import androidx.lifecycle.*
import com.example.submission_github.ui.main.api.RetrofitClient
import com.example.submission_github.ui.main.data.model.local.SettingPreferences
import retrofit2.Callback

class UserViewModel(private val preferences: SettingPreferences) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    var listUser = MutableLiveData<ArrayList<User>>()

    fun getTheme() = preferences.getThemeSetting().asLiveData()

    fun setSearchUsers(query: String) {
        _isLoading.value = true
        RetrofitClient.apiInstant
            .getSearcUsers(query)
            .enqueue(object : Callback<UserResponse> {
                override fun onResponse(
                    call: retrofit2.Call<UserResponse>,
                    response: retrofit2.Response<UserResponse>
                ) {
                    _isLoading.value = false
                    if (response.isSuccessful) {
                        listUser.postValue(response.body()?.items)
                    }
                }

                override fun onFailure(call: retrofit2.Call<UserResponse>, t: Throwable) {
                    _isLoading.value = false
                    t.message?.let { println(it) }
                }
            })
    }

    fun getSearchUsers(): LiveData<ArrayList<User>> {
        return listUser
    }

    class Factory(private val preferences: SettingPreferences) :
        ViewModelProvider.NewInstanceFactory() {

        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            UserViewModel(preferences) as T

    }
}
