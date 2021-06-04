package org.hapley.shared.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.hapley.shared.network.KerisApi
import org.hapley.shared.network.response.Item
import org.hapley.shared.network.response.User
import javax.inject.Inject

class RemoteItemsDataSource @Inject constructor(
    private val kerisApi: KerisApi
) : ItemsDataSource {

    override suspend fun getItemDetail(id: Int): Result<Item> {
        return runCatching { Result.Success(kerisApi.getItemDetail(id)) }
            .getOrElse { e -> Result.Error(e) }
    }

    override fun getItemDetailFlow(id: Int): Flow<Result<Item>> = flow {
        val itemDetailResult = runCatching { Result.Success(kerisApi.getItemDetail(id)) }
            .getOrElse { e -> Result.Error(e) }

        emit(itemDetailResult)
    }

    override suspend fun getUser(id: String): Flow<Result<User>> = flow {
        val userResult = runCatching { Result.Success(kerisApi.getUser(id)) }
            .getOrElse { e -> Result.Error(e) }

        emit(userResult)
    }

    override fun getItemsFlow(itemType: ItemType): Flow<Result<List<Int>>> = flow {
        val suspendFunction = when (itemType) {
            ItemType.Top -> kerisApi::getTopStories
            ItemType.New -> kerisApi::getNewStories
            ItemType.Best -> kerisApi::getBestStories
            ItemType.Ask -> kerisApi::getAskStories
            ItemType.Show -> kerisApi::getShowStories
            ItemType.Job -> kerisApi::getJobStories
        }
        val getStories = runCatching { Result.Success(suspendFunction.invoke()) }
            .getOrElse { e -> Result.Error(e) }

        emit(getStories)
    }
}