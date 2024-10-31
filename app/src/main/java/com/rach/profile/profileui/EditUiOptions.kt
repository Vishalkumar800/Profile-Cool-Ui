package com.rach.profile.profileui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.rach.profile.ui.theme.ProfileTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnEditOptions(navController: NavController, viewModel: TopViewModel) {
    val fields by viewModel.fields.collectAsState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(state = rememberTopAppBarState())
    val scrollState = rememberScrollState()

    val focusRequester = remember { FocusRequester() }

    // LaunchEffect to request focus when the screen loads
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = "Edit Profile",
                modifier = Modifier,
                onEditClick ={} ,
                onBackClick ={viewModel.onChangeBottomSheet(false)} ,
                scrollBehavior = scrollBehavior
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            fields.forEachIndexed { index, field ->
                CustomOutLinedTextField(
                    modifier = Modifier.fillMaxWidth()
                        .focusRequester(if (index == 0) focusRequester else FocusRequester()),
                    label = field.label,
                    value = field.value,
                    onValueChanged = {
                        viewModel.updateField(index, it)
                    },
                    singLineTrue = true,
                    showError = field.showError
                )
                Spacer(modifier = Modifier.height(6.dp))
            }

            Button(
                onClick = {
                    if (viewModel.validateFields()) {
                        // Perform Save Action
                    }
                },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text(text = "SAVE", fontWeight = FontWeight.Medium)
            }
        }
    }
}

@ProfileDesignPreview
@Composable
private fun Preview() {
    ProfileTheme {
        OnEditOptions(
            navController = rememberNavController(),
            viewModel = TopViewModel()
        )
    }
}
