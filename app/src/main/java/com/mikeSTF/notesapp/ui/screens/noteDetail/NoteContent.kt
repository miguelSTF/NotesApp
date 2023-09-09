package com.mikeSTF.notesapp.ui.screens.noteDetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mikeSTF.notesapp.R
import com.mikeSTF.notesapp.components.CustomText
import com.mikeSTF.notesapp.ui.theme.ChineseSilver
import com.mikeSTF.notesapp.ui.theme.fontFamily

@Composable
fun NoteContent(
    title: String,
    onTitleChange: (String) -> Unit,
    description: String,
    onDescriptionChange: (String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 32.dp)
    ) {
        CustomText(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp),
            text = stringResource(id = R.string.name),
            color = MaterialTheme.colors.secondary,
            fontSize = 20.sp,
            fontWeight = FontWeight.W600
        )

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = title,
            onValueChange = { text ->
                if (text.length <= 40) onTitleChange(text)
            },
            placeholder = {
                CustomText(
                    modifier = Modifier.alpha(ContentAlpha.medium),
                    text = stringResource(id = R.string.enter_name),
                    color = ChineseSilver,
                    fontWeight = FontWeight.W300,
                    fontSize = 18.sp
                )
            },
            textStyle = TextStyle(
                color = MaterialTheme.colors.secondary,
                fontFamily = fontFamily,
                fontWeight = FontWeight.W400,
                fontSize = 18.sp
            ),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done,
            ),
            colors = TextFieldDefaults.textFieldColors(
                cursorColor = MaterialTheme.colors.secondary,
                focusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                backgroundColor = Color.Transparent
            )
        )

        Divider(
            modifier = Modifier.height(8.dp), color = MaterialTheme.colors.primary
        )

        CustomText(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp),
            text = stringResource(id = R.string.description),
            color = MaterialTheme.colors.secondary,
            fontSize = 20.sp,
            fontWeight = FontWeight.W600
        )

        TextField(
            modifier = Modifier.fillMaxSize(),
            value = description,
            onValueChange = { text ->
                onDescriptionChange(text)
            },
            placeholder = {
                CustomText(
                    modifier = Modifier.alpha(ContentAlpha.medium),
                    text = stringResource(id = R.string.enter_description),
                    color = ChineseSilver,
                    fontWeight = FontWeight.W300,
                    fontSize = 18.sp
                )
            },
            textStyle = TextStyle(
                color = MaterialTheme.colors.secondary,
                fontFamily = fontFamily,
                fontWeight = FontWeight.W400,
                fontSize = 18.sp
            ),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.None),
            colors = TextFieldDefaults.textFieldColors(
                cursorColor = MaterialTheme.colors.secondary,
                focusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                backgroundColor = Color.Transparent
            )
        )
    }
}

@Composable
@Preview
fun NoteContentPreview() {
    NoteContent(
        title = "Title",
        onTitleChange = {},
        description = "Description",
        onDescriptionChange = {}
    )
}