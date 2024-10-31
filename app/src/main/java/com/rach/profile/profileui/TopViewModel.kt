package com.rach.profile.profileui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class FieldState(
    val label: String,
    val value: String = "",
    val showError: Boolean = false
)

class TopViewModel : ViewModel() {

    private val _isDarkMode = MutableStateFlow(false)
    val isDarkMode: StateFlow<Boolean> = _isDarkMode

    fun onChangeDarkMode(newMode: Boolean) {
        _isDarkMode.value = newMode
    }

    private val _showBottomSheet = MutableStateFlow(false)
    val showBottomSheet:StateFlow<Boolean> = _showBottomSheet

    fun onChangeBottomSheet(newState:Boolean){
        _showBottomSheet.value = newState
    }

    private val _allowHalfExpanded = MutableStateFlow(false)
    val allowHalfExpanded :StateFlow<Boolean> =_allowHalfExpanded

    fun onAllowedHalfExpanded (newState: Boolean){
        _allowHalfExpanded.value = newState
    }
    // Edit Panel
    private val _fields = MutableStateFlow(
        listOf(
            FieldState(label = "Enter Name"),
            FieldState(label = "Enter Phone Number"),
            FieldState(label = "Enter Email"),
            FieldState(label = "Enter Hobby")
        )
    )
    val fields: StateFlow<List<FieldState>> = _fields

    fun updateField(index: Int, newValue: String) {
        _fields.value = _fields.value.toMutableList().apply {
            this[index] = this[index].copy(
                value = newValue,
                showError = newValue.isEmpty()
            )
        }
    }

    fun validateFields(): Boolean {
        var isValid = true
        _fields.value = _fields.value.map { field ->
            if (field.value.isEmpty()) {
                isValid = false
                field.copy(showError = true)
            } else {
                field
            }
        }
        return isValid
    }




}
