package com.wcsm.androidlearninghub.guide_atomic_design.presentation.ui.atoms

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TitleTextAtom(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleLarge
    )
}

@Preview
@Composable
fun TitleTextAtomPreview() {
    TitleTextAtom("Hello World!")
}