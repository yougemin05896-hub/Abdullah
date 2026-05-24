package com.example.presentation.search

import kotlinx.coroutines.flow.MutableStateFlow

class GlobalSearchBarState {
    val query = MutableStateFlow("")
}
