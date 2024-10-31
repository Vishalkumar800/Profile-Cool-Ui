package com.rach.profile.profileui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CustomOutLinedTextField(
    modifier: Modifier,
    label:String,
    value:String,
    onValueChanged:(String) -> Unit,
    singLineTrue:Boolean,
    showError:Boolean
){

    val isError  = value.isEmpty() && showError


    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value = value,
        label = {Text(text = label)},
        onValueChange = onValueChanged,
        isError = isError,
        singleLine = singLineTrue,
        colors = OutlinedTextFieldDefaults.colors(

            focusedTextColor = MaterialTheme.colorScheme.onBackground,
            unfocusedTextColor = MaterialTheme.colorScheme.onBackground

        )
    )

    if (isError){
        Text(
            text = "Please  $label",
            color = MaterialTheme.colorScheme.error
        )
    }



}