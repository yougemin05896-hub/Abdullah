package com.example.presentation.inbox.components

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import com.example.ui.theme.NeonCyan
import com.example.ui.theme.TypographySecondary

@Composable
fun FolderTabs(
    tabs: List<String> = listOf("All Chats", "Personal", "Work", "Unread", "Bots"),
    selectedTabIndex: Int,
    onTabSelected: (Int) -> Unit
) {
    ScrollableTabRow(
        selectedTabIndex = selectedTabIndex,
        containerColor = Color.Transparent,
        contentColor = NeonCyan,
        edgePadding = 8.dp,
        divider = {},
        indicator = { tabPositions ->
            if (selectedTabIndex < tabPositions.size) {
                TabRowDefaults.PrimaryIndicator(
                    modifier = androidx.compose.ui.Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                    color = NeonCyan
                )
            }
        }
    ) {
        tabs.forEachIndexed { index, title ->
            Tab(
                selected = selectedTabIndex == index,
                onClick = { onTabSelected(index) },
                text = {
                    Text(
                        text = title,
                        color = if (selectedTabIndex == index) NeonCyan else TypographySecondary
                    )
                }
            )
        }
    }
}
