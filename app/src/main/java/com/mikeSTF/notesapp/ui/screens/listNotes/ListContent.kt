package com.mikeSTF.notesapp.ui.screens.listNotes

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.mikeSTF.notesapp.components.CustomLoading
import com.mikeSTF.notesapp.components.CustomText
import com.mikeSTF.notesapp.data.models.Note
import com.mikeSTF.notesapp.ui.theme.BlackShade
import com.mikeSTF.notesapp.utils.noteItemColor

@Composable
fun ListContent(
    notes: LazyPagingItems<Note>,
    navigateToNoteScreen: (taskId: Int) -> Unit
) {
    Log.d("LazyPagingItems_LoadState_APPEND", notes.loadState.append.toString())
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 32.dp),
        content = {
            itemsIndexed(notes, key = { _, note -> note.id }) { index, note ->
                if (note != null) {
                    NoteItem(
                        note = note,
                        index = index,
                        navigateToNoteScreen = navigateToNoteScreen
                    )
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NoteItem(
    note: Note, index: Int, navigateToNoteScreen: (taskId: Int) -> Unit
) {
    Surface(modifier = Modifier.fillMaxWidth().padding(8.dp),
        color = noteItemColor(index = index),
        elevation = 10.dp,
        onClick = {
            navigateToNoteScreen(note.id)
        }) {

        Column(
            modifier = Modifier
                .padding(vertical = 16.dp, horizontal = 24.dp)
                .fillMaxWidth()
        ) {
            CustomText(
                text = note.title,
                color = BlackShade,
                fontWeight = FontWeight.W700,
                fontSize = 22.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            CustomText(
                modifier = Modifier.padding(top = 8.dp),
                text = note.description,
                color = BlackShade,
                fontWeight = FontWeight.W400,
                fontSize = 18.sp,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
@Preview
fun NoteItemPreview() {
    NoteItem(note = Note(
        id = 0,
        title = "Book Review : The Design of Everyday Things by Don Norman",
        description = "Book Review : The Design of Everyday Things by Don Norman",
    ),
        index = 0,
        navigateToNoteScreen = {})
}

@Composable
@Preview
fun CustomLoadingPreview() {
    CustomLoading()
}