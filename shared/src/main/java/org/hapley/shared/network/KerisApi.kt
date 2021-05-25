package org.hapley.shared.network

import org.hapley.shared.network.response.Item
import org.hapley.shared.network.response.User
import retrofit2.http.GET
import retrofit2.http.Path


interface KerisApi {
    @GET("/v0/topstories.json")
    suspend fun getTopStories(): List<Int>

    @GET("/v0/newstories.json")
    suspend fun getNewStories(): List<Int>

    @GET("/v0/beststories.json")
    suspend fun getBestStories(): List<Int>

    @GET("/v0/askstories.json")
    suspend fun getAskStories(): List<Int>

    @GET("/v0/showstories.json")
    suspend fun getShowStories(): List<Int>

    @GET("/v0/jobstories.json")
    suspend fun getJobStories(): List<Int>

    @GET("/v0/item/{id}.json")
    suspend fun getItem(
        @Path("id") id: Int
    ): Item

    @GET("/v0/user/{id}.json")
    suspend fun getUser(
        @Path("id") id: String
    ): User
}