package com.example.submission_github.ui.main.api
import com.example.submission_github.ui.main.data.model.DetailUserResponse
import com.example.submission_github.ui.main.data.model.User
import com.example.submission_github.ui.main.data.model.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query
interface Api {
    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getSearcUsers(
        @Query("q") query: String
    ): Call<UserResponse>

    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun getUserDetail(
        @Path("username") username: String
    ): Call<DetailUserResponse>

    @GET("users/{username}/followers")
    @Headers("Authorization: token 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")
    fun getFollowers(
        @Path("username") username: String
    ): Call<ArrayList<User>>

    @GET("users/{username}/following")
    @Headers("Authorization: token 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")
    fun getFollowing(
        @Path("username") username: String
    ): Call<ArrayList<User>>

}