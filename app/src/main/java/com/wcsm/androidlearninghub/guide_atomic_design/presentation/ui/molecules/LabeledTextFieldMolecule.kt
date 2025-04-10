package com.wcsm.androidlearninghub.guide_atomic_design.presentation.ui.molecules

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun LabeledTextFieldMolecule(
    label: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    Column {
        Text(text = label)
        TextField(
            value = value,
            onValueChange = onValueChange
        )
    }
}

@Preview
@Composable
fun LabeledTextFieldMoleculePreview() {
    LabeledTextFieldMolecule(
        label = "NOME",
        value = "Wallace",
        onValueChange = {}
    )
}