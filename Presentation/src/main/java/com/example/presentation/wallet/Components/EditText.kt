import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AddWalletEditText(
    text: String,
    prefix : String? = null,
    suffix : String? = null,
    keyboardType: KeyboardType,
    imeAction: ImeAction,
    placeHolder : String,
    placeHolderSize : Int,
    placeHolderPosition : TextAlign,
    isError : Boolean = false,
    supportingText : String = "",
    onValueChanged : (text : String) -> Unit,

    ) {

    OutlinedTextField(
        modifier = Modifier.padding(horizontal = 12.dp),
        value = text,
        onValueChange = onValueChanged,
        placeholder = {
            Text(
                text = placeHolder,
                fontSize = placeHolderSize.sp,
                textAlign = placeHolderPosition
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction

        ),
        textStyle = TextStyle(
            fontSize = 40.sp,
        ),
        suffix = {
            Text(
                text = suffix?:"",
                fontSize = 40.sp
            )
        },

        isError = isError,
        supportingText = {
            Text(
                text = supportingText,
            )
        },
        colors = OutlinedTextFieldDefaults.colors(

            focusedSuffixColor = Color.Black,
            disabledSuffixColor = Color.Black,
            unfocusedSuffixColor = Color.Black,
            errorSuffixColor = Color.Black,

            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black,

            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,

            focusedBorderColor = Color.DarkGray,
            unfocusedBorderColor = Color.DarkGray,

            focusedPlaceholderColor = Color.Transparent,
            unfocusedPlaceholderColor = Color.DarkGray,

            focusedPrefixColor = Color.Black,
            unfocusedPrefixColor = Color.Black,

            errorSupportingTextColor = Color.Red,
            errorContainerColor = Color.White,
            errorTextColor = Color.Black,
            errorBorderColor = Color.Red,

        )
    )
}