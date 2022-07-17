package io.jetpack.compose.permissions

import android.Manifest
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.provider.Settings
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.rememberPermissionState
import io.jetpack.compose.permission.R


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun SinglePermissionScreen() {
    var permissionStatus by remember { mutableStateOf("") }
    var requestButtonEnabled by remember { mutableStateOf(false) }
    var buttonText by remember { mutableStateOf("") }
    var openCameraEnabled by remember { mutableStateOf(false) }
    val permissionState = rememberPermissionState(
        permission =
        Manifest.permission.CAMERA
    )

    when {
        permissionState.hasPermission -> {
            permissionStatus = stringResource(R.string.permission_granted_msg)
            openCameraEnabled = true
            requestButtonEnabled = false
            buttonText = stringResource(id = R.string.request_permission_label)
        }

        !permissionState.hasPermission &&
                !permissionState.shouldShowRationale &&
                !permissionState.permissionRequested
        -> {
            permissionStatus = stringResource(R.string.permission_denied_msg)
            openCameraEnabled = false
            requestButtonEnabled = true
            buttonText = stringResource(id = R.string.request_permission_label)
        }

        !permissionState.hasPermission
                && !permissionState.shouldShowRationale &&
                permissionState.permissionRequested
        -> {
            permissionStatus = stringResource(R.string.permission_fully_denied)
            openCameraEnabled = false
            requestButtonEnabled = true
            buttonText = stringResource(R.string.go_to_settings_label)
        }

        !permissionState.hasPermission
                && permissionState.shouldShowRationale &&
                permissionState.permissionRequested
        -> {
            permissionStatus = stringResource(R.string.permission_denied_rationale)
            openCameraEnabled = false
            requestButtonEnabled = true
        }

    }

    Content(
        permissionState,
        permissionStatus,
        buttonText,
        requestButtonEnabled,
        openCameraEnabled,
    )

}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
private fun Content(
    permissionState: PermissionState,
    permissionStatus: String,
    buttonText: String,
    requestButtonEnabled: Boolean,
    openCameraEnabled: Boolean
) {
    var capturedBitmap by remember { mutableStateOf<Bitmap?>(null) }
    val context = LocalContext.current
    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap ->
            capturedBitmap = bitmap
        }
    val intent = appSettings(context)
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
                {
                    if (!permissionState.hasPermission
                        && !permissionState.shouldShowRationale &&
                        permissionState.permissionRequested
                    )
                        context.startActivity(intent)
                    else
                        permissionState.launchPermissionRequest()
                },
                modifier = Modifier.weight(1f),
                enabled = requestButtonEnabled
            ) {
                Text(
                    buttonText,
                    fontSize = 12.sp
                )
            }
            Spacer(modifier = Modifier.width(4.dp))
            Button({
                launcher.launch()
            }, modifier = Modifier.weight(1f), enabled = openCameraEnabled) { Text(stringResource(R.string.open_camera_label)) }
        }
        capturedBitmap?.asImageBitmap()
            ?.let { Image(bitmap = it, contentDescription = "",
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxSize()) }
    }
}

private fun appSettings(context: Context): Intent {
    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
    intent.data = Uri.fromParts("package", context.packageName, null)
    return intent
}
