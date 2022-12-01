package com.ulearning.ulearning_app.presentation.features.home.adapter

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ulearning.ulearning_app.domain.model.Subscription
import kotlinx.coroutines.delay
import kotlin.math.max

// The initial key used for loading.
// This is the article id of the first article that will be loaded
private const val STARTING_KEY = 0
private const val LOAD_DELAY_MILLIS = 3_000L

class CourseSubscriptionPagingSource : PagingSource<Int, Subscription>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Subscription> {
        TODO("Not yet implemented")
    }

    // The refresh key is used for the initial load of the next PagingSource, after invalidation
    override fun getRefreshKey(state: PagingState<Int, Subscription>): Int? {
        // In our case we grab the item closest to the anchor position
        // then return its id - (state.config.pageSize / 2) as a buffer
        val anchorPosition = state.anchorPosition ?: return null
        val subscription = state.closestItemToPosition(anchorPosition) ?: return null
        return ensureValidKey(key = subscription.id!! - (state.config.pageSize / 2))
    }

    /**
     * Makes sure the paging key is never less than [STARTING_KEY]
     */
    private fun ensureValidKey(key: Int) = max(STARTING_KEY, key)

}