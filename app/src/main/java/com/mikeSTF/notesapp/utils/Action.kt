package com.mikeSTF.notesapp.utils

enum class Action {
    ADD,
    NO_ACTION
}

fun String?.toAction(): Action {
    return if (this.isNullOrEmpty()) Action.NO_ACTION else Action.valueOf(this)
}