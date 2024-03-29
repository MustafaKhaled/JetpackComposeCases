package io.jetpack.compose.otp

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.input.key.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun OtpScreen() {
    val context = LocalContext.current
    var enabled: Boolean by remember { mutableStateOf(true)}
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OtpTextFields(
            length = 4,
            callback = {
                Toast.makeText(context, "You entered ${it.joinToString()}", Toast.LENGTH_LONG).show()
                enabled = true
            }, enabled = enabled,)
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun OtpTextFields(
    length: Int,
    callback: (CharArray) -> Unit,
    enabled: Boolean
) {
    val code: CharArray by remember { mutableStateOf(CharArray(length)) }
    val focusManager = LocalFocusManager.current
    Row(
        modifier = Modifier.height(50.dp)
    ) {

        repeat((0 until length).count()) {
            var singleCode: String by remember { mutableStateOf("") }
            var enabled: Boolean by remember { mutableStateOf(enabled)}
            OutlinedTextField(
                modifier = Modifier
                    .width(50.dp)
                    .height(50.dp)
                    .onKeyEvent { event ->
                        if (event.type == KeyEventType.KeyUp &&
                            event.key == Key.Backspace
                        ) {
                            focusManager.moveFocus(FocusDirection.Left)
                            return@onKeyEvent true
                        } else {
                            return@onKeyEvent false
                        }
                    },
                textStyle = MaterialTheme.typography.body2.copy(
                    textAlign = TextAlign.Center, color = Black
                ),
                singleLine = true,
                value = singleCode,
                onValueChange = { value: String ->
                    if (value == "") {
                        singleCode = ""
                        code[it] = ' '
                    } else if (value != "") {
                        singleCode = value.last().toString()
                        code[it] = singleCode.last()
                        focusManager.moveFocus(FocusDirection.Right)
                    }
                    if (code.all { it.isLetterOrDigit() })
                        callback.invoke(code)
                        enabled = false
                },
                enabled = enabled,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = androidx.compose.ui.graphics.Color.Transparent,
                ),
                maxLines = 1
            )
            Spacer(modifier = Modifier.width(15.dp))
        }
    }
}
