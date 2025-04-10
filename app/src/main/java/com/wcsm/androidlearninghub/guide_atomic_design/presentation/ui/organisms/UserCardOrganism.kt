package com.wcsm.androidlearninghub.guide_atomic_design.presentation.ui.organisms

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wcsm.androidlearninghub.guide_atomic_design.presentation.ui.atoms.PrimaryButtonAtom
import com.wcsm.androidlearninghub.guide_atomic_design.presentation.ui.atoms.TitleTextAtom

@Composable
fun UserCardOrganism(
    name: String,
    email: String,
    onEditClick: () -> Unit
) {
    Card {
        Column(modifier = Modifier.padding(16.dp)) {
            TitleTextAtom(text = name)
            Text(text = email)
            Spacer(modifier = Modifier.height(8.dp))
            PrimaryButtonAtom(text = "Editar", onClick = onEditClick)
        }
    }
}

@Preview
@Composable
fun UserCardOrganismPreview() {
    UserCardOrganism(
        name = "Wallace",
        email = "teste@teste.com",
        onEditClick = {}
    )
}