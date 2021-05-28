package org.hapley.shared

import org.junit.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.hapley.shared.network.KerisApi
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@RunWith(JUnit4::class)
class RemoteKerisApiTest {

    private val URL = "https://hacker-news.firebaseio.com"


    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val api = retrofit.create(KerisApi::class.java)

    private val storiesCount = 500
    private val twoHundred = 200

    @Test
    fun `get top stories`() = runBlocking {
        val topStories = api.getTopStories()
        assertEquals(storiesCount, topStories.size)
    }

    @Test
    fun `get new stories`() = runBlocking {
        val newStories = api.getNewStories()
        assertEquals(storiesCount, newStories.size)
    }

    @Test
    fun `get best stories`() = runBlocking {
        val bestStories = api.getBestStories()
        assertEquals(twoHundred, bestStories.size)
    }

    @Test
    fun `get ask stories`() = runBlocking {
        val askStories = api.getAskStories()
        assertTrue(askStories.isNotEmpty() && askStories.size < 200)
    }

    @Test
    fun `get show stories`() = runBlocking {
        val showStories = api.getShowStories()
        assertTrue(showStories.isNotEmpty() && showStories.size < 200)
    }

    @Test
    fun `get job stories`() = runBlocking {
        val jobStories = api.getJobStories()
        assertTrue(jobStories.isNotEmpty() && jobStories.size < 200)
    }

    @Test
    fun `get user test`() = runBlocking {
        val id = "jl"
        val getUser = api.getUser(id)
        assertEquals(id, getUser.id)
    }

    @Test
    fun `get item test`() = runBlocking {
        val id = 27273724
        val getItem = api.getItemDetail(id)
        assertEquals(id, getItem.id)
    }
}