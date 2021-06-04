package org.hapley.shared.data

import kotlinx.coroutines.flow.Flow
import org.hapley.shared.network.response.Item
import org.hapley.shared.network.response.User

interface ItemsDataSource {
    suspend fun getItemDetail(id: Int): Result<Item>

    fun getItemDetailFlow(id: Int): Flow<Result<Item>>

    suspend fun getUser(id: String): Flow<Result<User>>

    fun getItemsFlow(itemType: ItemType): Flow<Result<List<Int>>>

}

sealed class ItemType {
    object Top : ItemType()
    object New : ItemType()
    object Best : ItemType()
    object Ask : ItemType()
    object Show : ItemType()
    object Job : ItemType()
}