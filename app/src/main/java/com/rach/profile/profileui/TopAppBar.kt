package com.rach.profile.profileui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    title: String,
    modifier: Modifier,
    onEditClick: () -> Unit,
    onBackClick: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(state = rememberTopAppBarState())

) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                fontWeight = FontWeight.SemiBold
            )
        },
        modifier = modifier,
        navigationIcon = {
            if (title == "Profile Ui") {
                IconButton(onClick = { onBackClick() }) {
                    Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu Icons")
                }
            } else {
                IconButton(onClick = {onBackClick()}) {
                    Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Menu Icons")
                }
            }
        },
        actions = {
            if (title == "Profile Ui") {
                IconButton(onClick = {
                    onEditClick()
                }) {
                    Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit Icons")
                }
            } else {
                null
            }
        },
        scrollBehavior = scrollBehavior
    )
}

