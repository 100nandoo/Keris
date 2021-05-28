package org.hapley.shared.data

import org.hapley.shared.network.response.Item
import org.hapley.shared.network.response.User

interface ItemsDataSource {
    suspend fun getItemDetail(id: Int): Result<Item>

    suspend fun getUser(id: String): Result<User>

    suspend fun getItems(itemType: ItemType): Result<List<Item>>
}

sealed class ItemType {
    object Top: ItemType()
    object New: ItemType()
    object Best: ItemType()
    object Ask: ItemType()
    object Show: ItemType()
    object Job: ItemType()
}