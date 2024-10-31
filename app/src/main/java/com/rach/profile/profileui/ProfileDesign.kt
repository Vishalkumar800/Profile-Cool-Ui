package com.rach.profile.profileui

import android.content.Context
import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.rach.profile.ui.theme.ProfileTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileDesign(
    viewModel: TopViewModel,
    navController: NavController
) {

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(state = rememberTopAppBarState())

    val isDarkMode by viewModel.isDarkMode.collectAsState()

    val scope = rememberCoroutineScope()

    val showBottomSheet by viewModel.showBottomSheet.collectAsState()

    val allowHalfExpanded by viewModel.allowHalfExpanded.collectAsState()

    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = "Profile Ui",
                modifier = Modifier,
                onEditClick = { viewModel.onChangeBottomSheet(true) },
                onBackClick = {},
                scrollBehavior = scrollBehavior
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier.size(120.dp),
                shape = CircleShape
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Gray),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "A",
                        color = Color.White,
                        fontSize = 48.sp
                    )
                }

            }
            Spacer(modifier = Modifier.height(16.dp))

            ProfileTextField(
                label = "Name",
                value = "Vishal"
            )

            Spacer(modifier = Modifier.height(18.dp))

            ProfileTextField(
                label = "Phone Number",
                value = "67342673426"
            )

            Spacer(modifier = Modifier.height(18.dp))

            ProfileTextField(
                label = "Email",
                value = "statusb130@gmail.com"
            )

            Spacer(modifier = Modifier.height(18.dp))

            ProfileTextField(
                label = "Hooby",
                value = "Cricket"
            )

            Spacer(modifier = Modifier.height(18.dp))

            EnableSwitch(
                isChecked = isDarkMode, onValueChanged = {
                    viewModel.onChangeDarkMode(it)
                },
                modifier = Modifier.fillMaxWidth()
            )


        }

        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = {
                    viewModel.onChangeBottomSheet(false)
                },
                sheetState = sheetState,
                modifier = Modifier
                    .fillMaxSize()
                    .windowInsetsPadding(WindowInsets.systemBars)
                    .windowInsetsPadding(WindowInsets.ime)
            ) {
                OnEditOptions(navController = navController, viewModel = viewModel)


            }
        }
    }


}

@Composable
fun EnableSwitch(
    isChecked: Boolean,
    onValueChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        Column {
            Text(
                text = "Mode",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.primary
            )

            Text(
                text = if (isChecked) "Dark Mode" else "Light Mode",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onBackground
            )

        }


        Switch(
            checked = isChecked,
            onCheckedChange = onValueChanged,
            thumbContent = {
                if (isChecked) {
                    Icon(imageVector = Icons.Default.Check, contentDescription = "Checked")
                } else {
                    null
                }
            },
            colors = SwitchDefaults.colors(
                checkedIconColor = Color.White,
                checkedBorderColor = Color.White
            )
        )
    }

}

@Composable
fun ProfileTextField(
    label: String,
    value: String
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = label,
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.SemiBold
        )

        Text(
            text = value,
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Medium
        )
    }
}

@Preview(name = "Light", uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true)
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
annotation class ProfileDesignPreview

@ProfileDesignPreview
@Composable
private fun Preview() {
    ProfileTheme {
        ProfileDesign(viewModel = TopViewModel(), navController = rememberNavController())
    }
}


