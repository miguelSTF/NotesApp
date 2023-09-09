package com.mikeSTF.notesapp.ui.screens.noteDetail

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.mikeSTF.notesapp.R
import com.mikeSTF.notesapp.data.models.Note
import com.mikeSTF.notesapp.utils.Action

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun NoteScreen(
    navigateToListScreen: (Action) -> Unit, selectedNote: Note?, sharedViewModel: SharedViewModel
) {
    val title: String by sharedViewModel.title
    val description: String by sharedViewModel.description

    val context = LocalContext.current
    val validationErrorMessage = stringResource(id = R.string.note_validation_error)

    BackHandler {
        navigateToListScreen(Action.NO_ACTION)
    }

    Scaffold(backgroundColor = MaterialTheme.colors.primary, topBar = {
        NoteAppBar(
            navigateToListScreen = { action ->
                if (action == Action.NO_ACTION || sharedViewModel.validateNoteFields()) {
                    navigateToListScreen(action)
                } else {
                    displayToast(
                        context = context, message = validationErrorMessage
                    )
                }
            }, selectedNote = selectedNote
        )
    }, content = {
        NoteContent(
            title = title,
            onTitleChange = { title ->
                sharedViewModel.title.value = title
            },
            description = description,
            onDescriptionChange = { description ->
                sharedViewModel.description.value = description
            }
        )
    })
}

fun displayToast(context: Context, message: String) {
    val toast: Toast = Toast.makeText(context, message, Toast.LENGTH_SHORT)

    val view: View? = toast.view

    view?.background?.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN)

    val text: TextView? = view?.findViewById(android.R.id.message)

    text?.setTextColor(Color.BLACK)
    text?.setBackgroundColor(Color.TRANSPARENT)

    toast.show()
}