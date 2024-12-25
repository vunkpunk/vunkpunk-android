package com.vunkpunk.app.presentation

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.material3.Surface
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vunkpunk.app.common.Constants.PARAM_CARD_ID
import com.vunkpunk.app.common.Constants.PARAM_EMAIL
import com.vunkpunk.app.common.Constants.PARAM_SEARCH
import com.vunkpunk.app.presentation.card_detail.CardDetailScreen
import com.vunkpunk.app.presentation.main.MainScreen
import com.vunkpunk.app.presentation.profile.ProfileScreen
import com.vunkpunk.app.presentation.about.AboutScreen
import com.vunkpunk.app.presentation.edit_profile.EditProfileScreen
import com.vunkpunk.app.presentation.edit_profile.EditProfileViewModel
import com.vunkpunk.app.presentation.post_card.PostCardScreen
import com.vunkpunk.app.presentation.post_card.PostCardViewModel
import com.vunkpunk.app.presentation.login_system.code.CodeScreen
import com.vunkpunk.app.presentation.login_system.login.LoginScreen
import com.vunkpunk.app.presentation.login_system.sign_up.SignUpScreen
import com.vunkpunk.app.presentation.login_system.welcome.WelcomeScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val postCardViewModel: PostCardViewModel by viewModels()
    val editProfileViewModel: EditProfileViewModel by viewModels()

    var getImagesLauncher = registerForActivityResult(
        ActivityResultContracts.GetMultipleContents()
    ) { uris: List<Uri> ->
        postCardViewModel.onEvent(PostCardViewModel.UiEvent.AddImages(uris))
    }
    var getImageLauncher = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        editProfileViewModel.onEvent(EditProfileViewModel.UiEvent.AddImage(uri ?: Uri.EMPTY))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val sharedPreferences = getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)
        val a = sharedPreferences.getInt("user_id", 1)
        var start = Screen.MainScreen.route
        if (sharedPreferences.getString("auth_token", null) == null) {
            start = Screen.WelcomeScreen.route
        }
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor = android.graphics.Color.TRANSPARENT

        setContent {
            Surface() {
                val navController = rememberNavController()
                NavHost(
                    navController = navController, startDestination = start
                ) {
                    composable(
                        route = Screen.SearchScreen.route + "/{$PARAM_SEARCH}"
                    ) {
                        MainScreen(navController)
                    }
                    composable(
                        route = Screen.MainScreen.route
                    ) {
                        MainScreen(navController)
                    }
                    composable(
                        route = Screen.PostCardScreen.route,
                    ) {
                        PostCardScreen(navController, postCardViewModel) { openGallery() }
                    }
                    composable(
                        route = Screen.ProfileScreen.route
                    ) {
                        ProfileScreen(navController)
                    }
                    composable(
                        route = Screen.EditProfileScreen.route
                    ) {
                        EditProfileScreen(
                            navController, editProfileViewModel
                        ) { openSingleGallery() }
                    }
                    composable(
                        route = Screen.CardDetailScreen.route + "/{$PARAM_CARD_ID}",
                    ) {
                        CardDetailScreen(navController)
                    }
                    composable(
                        route = Screen.AboutScreen.route,
                    ) {
                        AboutScreen(navController)
                    }
                    composable(
                        route = Screen.WelcomeScreen.route,
                    ) {
                        WelcomeScreen(navController)
                    }
                    composable(
                        route = Screen.SignUpScreen.route,
                    ) {
                        SignUpScreen(navController)
                    }
                    composable(
                        route = Screen.CodeScreen.route + "/{$PARAM_EMAIL}",
                    ) {
                        CodeScreen(navController)
                    }
                    composable(
                        route = Screen.LoginScreen.route,
                    ) {
                        LoginScreen(navController)
                    }
                }
            }
        }
    }

    private fun openGallery() {
        getImagesLauncher.launch("image/*")
    }

    private fun openSingleGallery() {
        getImageLauncher.launch("image/*")
    }
}