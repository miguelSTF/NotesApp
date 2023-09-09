package com.mikeSTF.notesapp.ui.screens.noteDetail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
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
import com.mikeSTF.notesapp.data.models.Note
import com.mikeSTF.notesapp.utils.Action
import com.mikeSTF.notesapp.R

@Composable
fun NoteAppBar(navigateToListScreen: (Action) -> Unit, selectedNote: Note?) {
    if (selectedNote == null) {
        NewNoteAppBar(navigateToListScreen = navigateToListScreen)
    } else {
        DetailNoteAppBar(note = selectedNote, navigateToListScreen = navigateToListScreen)
    }
}

@Composable
fun NewNoteAppBar(navigateToListScreen: (Action) -> Unit) {
    TopAppBar(
        elevation = 0.dp,
        navigationIcon = {
            Divider(modifier = Modifier.width(12.dp), color = MaterialTheme.colors.primary)
            BackButton(backButtonPressed = navigateToListScreen)
        },
        title = {
            CustomText(
                text = stringResource(id = R.string.add_note),
                color = MaterialTheme.colors.secondary,
                fontSize = 20.sp,
                fontWeight = FontWeight.W600
            )
        },
        backgroundColor = MaterialTheme.colors.primary,
        actions = {
            AddNoteButton(addNoteButtonPressed = navigateToListScreen)
            Divider(modifier = Modifier.width(12.dp), color = MaterialTheme.colors.primary)
        }
    )
}

@Composable
fun BackButton(backButtonPressed: (Action) -> Unit) {
    Box(
        modifier = Modifier
            .width(40.dp)
            .height(40.dp)
            .clickable { backButtonPressed(Action.NO_ACTION) },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_arrow_back),
            contentDescription = stringResource(id = R.string.back_arrow),
            tint = MaterialTheme.colors.secondary
        )
    }
}

@Composable
fun AddNoteButton(addNoteButtonPressed: (Action) -> Unit) {
    Box(
        modifier = Modifier
            .width(40.dp)
            .height(40.dp)
            .clickable { addNoteButtonPressed(Action.ADD) },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_save),
            contentDescription = stringResource(id = R.string.save_note_action),
            tint = MaterialTheme.colors.secondary
        )
    }
}

@Composable
fun DetailNoteAppBar(note: Note, navigateToListScreen: (Action) -> Unit) {
    TopAppBar(
        elevation = 0.dp,
        navigationIcon = {
            Divider(modifier = Modifier.width(12.dp), color = MaterialTheme.colors.primary)
            BackButton(backButtonPressed = navigateToListScreen)
        },
        title = {
            CustomText(
                text = stringResource(id = R.string.detail_note),
                color = MaterialTheme.colors.secondary,
                fontSize = 20.sp,
                fontWeight = FontWeight.W600
            )
        },
        backgroundColor = MaterialTheme.colors.primary,
        actions = { }
    )
}

@Composable
@Preview
fun NoteAppBarPreview() {
    NoteAppBar(navigateToListScreen = {}, selectedNote = null)
}

@Composable
@Preview
fun DetailNoteAppBarPreview() {
    DetailNoteAppBar(
        note = Note(
            id = 0,
            title = "UI concepts worth existing",
            description = "UI concepts worth existing",
        ), navigateToListScreen = {})
}