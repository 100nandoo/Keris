package org.hapley.shared.data

import kotlinx.coroutines.*
import org.hapley.shared.network.KerisApi
import org.hapley.shared.network.response.Item
import javax.inject.Inject

class RemoteItemsDataSource @Inject constructor(
    private val kerisApi: KerisApi,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ItemsDataSource {

    override suspend fun getItemDetail(id: Int): Result<Item> = withContext(ioDispatcher) {
        return@withContext try {
            Result.Success(kerisApi.getItemDetail(id))
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun getUser(id: String) = withContext(ioDispatcher) {
        return@withContext try {
            Result.Success(kerisApi.getUser(id))
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun getItems(itemType: ItemType): Result<List<Item>> = withContext(ioDispatcher) {
        val suspendFunction = when (itemType) {
            ItemType.Top -> kerisApi::getTopStories
            ItemType.New -> kerisApi::getNewStories
            ItemType.Best -> kerisApi::getBestStories
            ItemType.Ask -> kerisApi::getAskStories
            ItemType.Show -> kerisApi::getShowStories
            ItemType.Job -> kerisApi::getJobStories
        }

        val result = try {
            Result.Success(suspendFunction.invoke())
        } catch (e: Exception) {
            Result.Error(e)
        }

        if (result is Result.Success) {
            val itemList = result.data.map {
                async { kerisApi.getItemDetail(it) }
            }.awaitAll()
            return@withContext Result.Success(itemList)
        } else {
            return@withContext Result.Error(Exception())
        }
    }
}