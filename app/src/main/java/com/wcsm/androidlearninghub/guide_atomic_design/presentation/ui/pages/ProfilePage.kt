package com.wcsm.androidlearninghub.guide_atomic_design.presentation.ui.pages

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.wcsm.androidlearninghub.guide_atomic_design.presentation.ui.atoms.TitleTextAtom
import com.wcsm.androidlearninghub.guide_atomic_design.presentation.ui.organisms.UserCardOrganism
import com.wcsm.androidlearninghub.guide_atomic_design.presentation.ui.template.ProfileTemplate

@Composable
fun ProfilePage(
    name: String,
    email: String,
    onEditClick: () -> Unit
) {
    ProfileTemplate(
        header = { TitleTextAtom(text = "Perfil do Usuário") },
        content = {
            UserCardOrganism(name = name, email = email, onEditClick = onEditClick)
        }
    )
}

@Preview
@Composable
fun ProfilePagePreview() {
    ProfilePage(
        name = "Wallace",
        email = "teste@teste.com",
        onEditClick = {}
    )
}