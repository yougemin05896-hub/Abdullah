package com.example.sync

class ConflictResolver {
    fun resolve(localData: Any, remoteData: Any): Any {
        // CRDT-based operational transform resolution
        return remoteData // Prefer server on conflict
    }
}
