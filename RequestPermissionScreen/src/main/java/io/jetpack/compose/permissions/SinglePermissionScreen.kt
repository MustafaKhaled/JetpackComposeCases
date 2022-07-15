package io.jetpack.compose.permissions

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavHostController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.rememberPermissionState


@ExperimentalPermissionsApi
@Composable
fun SinglePermissionScreen(navController: NavHostController) {
    var permissionStatus by remember { mutableStateOf("") }
    var requestButtonEnabled by remember { mutableStateOf(false) }
    var openCameraEnabled by remember { mutableStateOf(false) }
    val permissionState = rememberPermissionState(
        permission =
        Manifest.permission.CAMERA
    )

    when {
        permissionState.hasPermission -> {
            permissionStatus = "Permission is already granted, open your camera."
            openCameraEnabled = true
            requestButtonEnabled = false
        }
        permissionState.shouldShowRationale -> {
            permissionStatus = "Permission denied, show its importance"
            openCameraEnabled = false
            requestButtonEnabled = true
        }

        !permissionState.hasPermission -> {
            permissionStatus = "Permission completely denied, and never asked again."
            openCameraEnabled = false
            requestButtonEnabled = true
        }

    }

    Content(permissionState, permissionStatus, requestButtonEnabled, openCameraEnabled)

}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
private fun Content(
    permissionState: PermissionState,
    permissionStatus: String,
    requestButtonEnabled: Boolean,
    openCameraEnabled: Boolean
) {
    val context = LocalContext.current
    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.TakePicturePreview()) {}
    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
    val uri: Uri = Uri.fromParts("package", context.packageName, null)
    intent.data = uri
    Column(
        modifier = Modifier
            .padding(32.dp)
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        Text(permissionStatus)
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Button(
                { permissionState.launchPermissionRequest() },
                modifier = Modifier.weight(1f),
                enabled = requestButtonEnabled
            ) {
                Text(
                    "Request Permission",
                    fontSize = 12.sp
                )
            }
            Spacer(modifier = Modifier.width(4.dp))
            Button({
                if (permissionState.shouldShowRationale && permissionState.hasPermission)
                    launcher.launch()
                else
                    startActivity(context, intent, null)
            }, modifier = Modifier.weight(1f), enabled = openCameraEnabled) { Text("Open Camera") }

        }
    }
}
