package com.example.myapplication.ui

import android.provider.ContactsContract.Directory
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
@Composable
fun DirectoryScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = "Banana!",
            style = MaterialTheme.typography.headlineSmall,
        )
    }
}