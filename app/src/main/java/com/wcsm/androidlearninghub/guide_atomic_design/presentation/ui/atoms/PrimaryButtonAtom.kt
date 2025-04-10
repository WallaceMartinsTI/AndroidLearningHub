package com.wcsm.androidlearninghub.guide_atomic_design.presentation.ui.atoms

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun PrimaryButtonAtom(
    text: String,
    onClick: () -> Unit
) {
    Button(onClick = onClick) {
        Text(text = text)
    }
}

@Preview
@Composable
fun PrimaryButtonAtomPreview() {
    PrimaryButtonAtom(
        text = "CLICK HERE"
    ) { }
}