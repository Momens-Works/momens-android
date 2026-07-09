package com.momens.android.presentation.signin

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.momens.android.core.common.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class SignInViewModel @Inject constructor() : ViewModel() {
    private val _signInState = MutableStateFlow<UiState<Unit>>(UiState.Empty)
    val signInState: StateFlow<UiState<Unit>> = _signInState.asStateFlow()

    fun signInWithGoogle() {
        viewModelScope.launch {
            _signInState.value = UiState.Loading
            // TODO: Replace this mock success with repository.signInWithGoogle().
            _signInState.value = UiState.Success(Unit)
            Log.d("signin", "버튼 눌림") //이건 로직 확인 toast라 추후 api연동 디벨롭 때 고칠게용
        }
    }
}
