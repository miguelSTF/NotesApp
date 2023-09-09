package com.mikeSTF.notesapp.ui.screens.listNotes

import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mikeSTF.notesapp.R
import com.mikeSTF.notesapp.components.CustomText
import com.mikeSTF.notesapp.utils.AppBarState

@Composable
fun ListAppBar(
    searchAppBarState: AppBarState,
) {

    when (searchAppBarState) {
        AppBarState.CLOSED -> {
            DefaultListAppBar( )
        }
    }
}

@Composable
fun DefaultListAppBar( ) {
    TopAppBar(elevation = 0.dp, title = {
        CustomText(
            text = stringResource(id = R.string.app_name),
            color = MaterialTheme.colors.secondary,
            fontSize = 28.sp,
            fontWeight = FontWeight.W600,

            )
    },
        backgroundColor = MaterialTheme.colors.primary)
}

@Composable
@Preview
private fun DefaultListAppBarPreview() {
    DefaultListAppBar()
}