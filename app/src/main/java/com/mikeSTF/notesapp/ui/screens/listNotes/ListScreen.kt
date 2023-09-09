package com.mikeSTF.notesapp.ui.screens.listNotes

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.mikeSTF.notesapp.R
import com.mikeSTF.notesapp.components.CustomLoading
import com.mikeSTF.notesapp.data.models.Note
import com.mikeSTF.notesapp.ui.theme.RedButton
import com.mikeSTF.notesapp.ui.viewmodels.SharedViewModel
import com.mikeSTF.notesapp.utils.Action
import com.mikeSTF.notesapp.utils.AppBarState

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ListScreen(
    navigateToNoteScreen: (taskId: Int) -> Unit,
    sharedViewModel: SharedViewModel
) {
    val action by sharedViewModel.action
    val allNotes = sharedViewModel.allNotes.collectAsLazyPagingItems()
    val searchAppBarState: AppBarState by sharedViewModel.searchAppBarState

    LaunchedEffect(key1 = action) {
        sharedViewModel.handleDatabaseAction(action = action)
    }

    val scaffoldState = rememberScaffoldState()

    CompleteResult(
        onComplete = { sharedViewModel.action.value = it },
        action = action
    )

    Scaffold(
        scaffoldState = scaffoldState,
        snackbarHost = {
            SnackbarHost(it) { data ->
                Snackbar(
                    actionColor = MaterialTheme.colors.primary,
                    snackbarData = data,
                    contentColor = MaterialTheme.colors.primary,
                    backgroundColor = MaterialTheme.colors.secondary
                )
            }
        },
        backgroundColor = MaterialTheme.colors.primary,
        topBar = {
            ListAppBar(
                searchAppBarState = searchAppBarState
            )
        },
        content = {
            HandleListContent(
                notes = allNotes,
                navigateToNoteScreen = navigateToNoteScreen
            )
        },
        floatingActionButton = {
            FloatingButton(onFloatingActionButtonPressed = navigateToNoteScreen)
        })
}

@Composable
fun HandleListContent(
    notes: LazyPagingItems<Note>,
    navigateToNoteScreen: (taskId: Int) -> Unit
) {
    if (notes.loadState.refresh == LoadState.Loading) {
        CustomLoading()
    } else {
        if (notes.itemCount == 0) {
            EmptyContent()
        } else {
            ListContent(
                notes = notes,
                navigateToNoteScreen = navigateToNoteScreen
            )
        }
    }
}

@Composable
fun FloatingButton(onFloatingActionButtonPressed: (taskId: Int) -> Unit) {
    ExtendedFloatingActionButton(
        modifier = Modifier.padding(end = 10.dp, bottom = 10.dp),
        elevation = FloatingActionButtonDefaults.elevation(20.dp),
        backgroundColor = RedButton,
        text = { Text(stringResource(R.string.add_note), color = MaterialTheme.colors.secondary) },
        onClick = { onFloatingActionButtonPressed(-1) },
        icon = {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = stringResource(id = R.string.add_note_action),
                tint = MaterialTheme.colors.secondary
            )
        }
    )
}

@Composable
fun CompleteResult(
    onComplete: (Action) -> Unit,
    action: Action
) {
    LaunchedEffect(key1 = action) {
        onComplete(Action.NO_ACTION)
    }
}