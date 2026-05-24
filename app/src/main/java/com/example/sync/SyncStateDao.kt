package com.example.sync

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SyncStateDao {
    @Query("SELECT lastSyncTimestamp FROM sync_state WHERE tableId = :tableId")
    suspend fun getLastSync(tableId: String): Long?

    @Query("INSERT OR REPLACE INTO sync_state(tableId, lastSyncTimestamp) VALUES (:tableId, :timestamp)")
    suspend fun updateSyncTime(tableId: String, timestamp: Long)
}
