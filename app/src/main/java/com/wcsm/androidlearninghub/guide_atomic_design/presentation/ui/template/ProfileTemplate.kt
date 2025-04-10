package com.wcsm.androidlearninghub.guide_atomic_design.presentation.ui.template

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ProfileTemplate(
    header: @Composable () -> Unit,
    content: @Composable () -> Unit
) {
    Column {
        header()
        Divider()
        content()
    }
}

@Preview
@Composable
fun ProfileTemplatePreview() {
    ProfileTemplate(
        header = { Text("Header") },
        content = { Text("Main") }
    )
}