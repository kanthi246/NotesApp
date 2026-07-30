package com.kanthi.notesapp.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.kanthi.notesapp.feature.auth.presentation.login.LoginScreen
import com.kanthi.notesapp.feature.auth.presentation.signup.SignupScreen
import com.kanthi.notesapp.feature.auth.presentation.splash.SplashScreen
import com.kanthi.notesapp.feature.notes.presentation.note_detail.NoteDetailScreen
import com.kanthi.notesapp.feature.notes.presentation.note_editor.EditorNotesScreen
import com.kanthi.notesapp.feature.notes.presentation.notes_list.NoteListScreen
import com.kanthi.notesapp.feature.notes.presentation.search.SearchScreen

object Routes {
    const val SPLASH = "splash"
    const val LOGIN = "login"
    const val SIGNUP = "signup"
    const val NOTES_LIST = "notes_list"
    const val SEARCH = "search"
    const val NOTE_DETAIL = "note_detail/{noteId}"
    const val NOTE_EDITOR = "note_editor?noteId={noteId}"

    fun noteDetail(id: Long) = "note_detail/$id"
    fun noteEditor(id: Long? = null) = if (id != null) "note_editor?noteId=$id" else "note_editor"
}

@Composable
fun NotesNavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = Routes.SPLASH) {
        composable(Routes.SPLASH) {
            SplashScreen(
                onNavigate = { loggedIn ->
                    navController.navigate(if (loggedIn) Routes.NOTES_LIST else Routes.LOGIN) {
                        popUpTo(Routes.SPLASH) { inclusive = true }
                    }
                }
            )
        }
        composable(Routes.LOGIN) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(Routes.NOTES_LIST) {
                        popUpTo(0) { inclusive = true }
                    }
                },
                onGoToSignup = { navController.navigate(Routes.SIGNUP) }
            )
        }
        composable(Routes.SIGNUP) {
            SignupScreen(
                onSignupSuccess = {
                    navController.navigate(Routes.NOTES_LIST) {
                        popUpTo(0) { inclusive = true }
                    }
                },
                onGoToLogin = { navController.navigate(Routes.LOGIN) }
            )
        }
        composable(Routes.NOTES_LIST) {
            NoteListScreen(
                onNoteClick = { id -> navController.navigate(Routes.noteDetail(id)) },
                onAddNote = { navController.navigate(Routes.noteEditor()) },
                onSearchClick = { navController.navigate(Routes.SEARCH) },
                onLoggedOut = {
                    navController.navigate(Routes.LOGIN) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }
        composable(Routes.SEARCH) {
            SearchScreen(
                onBack = { navController.popBackStack() },
                onNoteClick = { id -> navController.navigate(Routes.noteDetail(id)) }
            )
        }
        composable(
            route = Routes.NOTE_DETAIL,
            arguments = listOf(navArgument("noteId") { type = NavType.LongType })
        ) {
            NoteDetailScreen(
                onBack = { navController.popBackStack() },
                onEdit = { id -> navController.navigate(Routes.noteEditor(id)) }
            )
        }
        composable(
            route = Routes.NOTE_EDITOR,
            arguments = listOf(navArgument("noteId") { type = NavType.LongType; defaultValue = -1L })
        ) {
            EditorNotesScreen(
                onCancel = { navController.popBackStack() },
                onSaved = { savedId ->
                    navController.navigate(Routes.noteDetail(savedId)) {
                        popUpTo(Routes.NOTE_EDITOR) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}
