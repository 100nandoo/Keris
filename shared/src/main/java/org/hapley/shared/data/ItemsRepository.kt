package org.hapley.shared.data

import javax.inject.Inject

class ItemsRepository @Inject constructor(
    private val remoteItemsDataSource: RemoteItemsDataSource
) {
}