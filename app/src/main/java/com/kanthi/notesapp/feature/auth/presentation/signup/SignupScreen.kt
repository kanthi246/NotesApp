package com.kanthi.notesapp.feature.auth.presentation.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kanthi.notesapp.core.presentation.components.TextFieldComponent

@Composable
fun SignupScreen(
    onSignupSuccess: () -> Unit,
    onGoToLogin: () -> Unit,
    viewModel: SignupViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState.signupSucceeded) {
        if (uiState.signupSucceeded) onSignupSuccess()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 28.dp)
            .padding(top = 60.dp),
        verticalArrangement = Arrangement.spacedBy(22.dp)
    ) {
        Column {
            Text(text = "Create your account", style = MaterialTheme.typography.headlineMedium)
            Text(
                text = "A few details and you're in.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
            )
        }

        TextFieldComponent(
            modifier = Modifier.fillMaxWidth(),
            value = uiState.name,
            labelValue = "Name",
            onValueChange = viewModel::onNameChange
        )

        TextFieldComponent(
            modifier = Modifier.fillMaxWidth(),
            value = uiState.email,
            labelValue = "Email",
            onValueChange = viewModel::onEmailChange
        )

        TextFieldComponent(
            modifier = Modifier.fillMaxWidth(),
            value = uiState.password,
            labelValue = "Password",
            isPassword = true,
            onValueChange = viewModel::onPasswordChange
        )

        uiState.errorMessage?.let { message ->
            Text(text = message, style = MaterialTheme.typography.bodyMedium, color = Color.Red)
        }

        Button(
            onClick = viewModel::submit,
            enabled = !uiState.isLoading,
            modifier = Modifier.fillMaxWidth()
        ) {
            if (uiState.isLoading) {
                CircularProgressIndicator(modifier = Modifier.height(20.dp), color = MaterialTheme.colorScheme.onPrimary)
            } else {
                Text("Create account")
            }
        }

        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            TextButton(onClick = onGoToLogin) {
                Text("Already have an account? Log in")
            }
        }
    }
}
