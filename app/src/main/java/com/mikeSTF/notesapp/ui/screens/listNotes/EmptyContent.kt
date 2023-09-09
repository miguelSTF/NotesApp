package com.mikeSTF.notesapp.ui.screens.listNotes

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mikeSTF.notesapp.components.CustomText
import com.mikeSTF.notesapp.R

@Composable
fun EmptyContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 56.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.size(300.dp),
            painter = painterResource(id = R.drawable.ic_add_note),
            contentDescription = stringResource(id = R.string.note_icon)
        )
        CustomText(
            text = stringResource(id = R.string.create_your_note),
            color = MaterialTheme.colors.secondary,
            fontSize = 18.sp,
            fontWeight = FontWeight.W300
        )
    }
}

@Composable
@Preview
fun EmptyContentPreview() {
    EmptyContent()
}