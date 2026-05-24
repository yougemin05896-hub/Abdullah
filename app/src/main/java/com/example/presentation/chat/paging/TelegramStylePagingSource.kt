package com.example.presentation.chat.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.local.entities.MessageEntity

class TelegramStylePagingSource : PagingSource<Int, MessageEntity>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MessageEntity> {
        return LoadResult.Page(emptyList(), null, null)
    }
    
    override fun getRefreshKey(state: PagingState<Int, MessageEntity>): Int? = null
}
