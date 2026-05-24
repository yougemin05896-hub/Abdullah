package com.example.sync

data class DeltaSyncPayload(
    val entityId: String,
    val changes: Map<String, Any>,
    val versionStamp: Long
)
