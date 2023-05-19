package com.ead.project.ourivesariarumor.presentation.register.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ead.project.ourivesariarumor.presentation.util.EmptyString

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutlineHintTextField(
    text: String,
    onValueChange: (String) -> Unit,
    onFocusChange : (FocusState) -> Unit,
    modifier: Modifier = Modifier,
    hint : String,
    isHintContentVisible : Boolean,
    textStyle: TextStyle = LocalTextStyle.current.copy(
        color = MaterialTheme.colorScheme.inverseSurface
    ),
    singleLine: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    supportingText: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    maxLines: Int = Int.MAX_VALUE,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    shape: Shape = TextFieldDefaults.outlinedShape,
    colors: TextFieldColors = TextFieldDefaults.outlinedTextFieldColors(
        focusedBorderColor = MaterialTheme.colorScheme.primary,
        unfocusedBorderColor = MaterialTheme.colorScheme.secondary
    )
) {
    Box(
        modifier = modifier
    ) {
        // If color is not provided via the text style, use content color as a default
        val mergedTextStyle = textStyle.merge(TextStyle(color = textStyle.color))
        val modifierCase = if (!isHintContentVisible) {
            modifier
                .semantics(mergeDescendants = true) {}
                .padding(top = 8.dp)
        } else {
            modifier
        }
        CompositionLocalProvider(LocalTextSelectionColors provides TextSelectionColors(textStyle.color,MaterialTheme.colorScheme.surface)) {
            @OptIn(ExperimentalMaterial3Api::class)
            BasicTextField(
                value = text,
                modifier = modifierCase
                    .defaultMinSize(
                        minWidth = TextFieldDefaults.MinWidth,
                        minHeight = TextFieldDefaults.MinHeight
                    )
                    .onFocusChanged { onFocusChange(it) },
                onValueChange = onValueChange,
                enabled = enabled,
                readOnly = readOnly,
                textStyle = mergedTextStyle,
                visualTransformation = visualTransformation,
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions,
                interactionSource = interactionSource,
                singleLine = singleLine,
                maxLines = maxLines,
                decorationBox = @Composable { innerTextField ->
                    TextFieldDefaults.OutlinedTextFieldDecorationBox(
                        value = text,
                        visualTransformation = visualTransformation,
                        innerTextField = innerTextField,
                        placeholder = placeholder,
                        label = {
                                if (!isHintContentVisible) {
                                    Text(text = hint)
                                }
                        },
                        leadingIcon = leadingIcon,
                        trailingIcon = trailingIcon,
                        supportingText = supportingText,
                        singleLine = singleLine,
                        enabled = enabled,
                        isError = isError,
                        interactionSource = interactionSource,
                        colors = colors,
                        container = {
                            TextFieldDefaults.OutlinedBorderContainerBox(
                                enabled,
                                isError,
                                interactionSource,
                                colors,
                                shape
                            )
                        }
                    )
                }
            )
        }
        if (isHintContentVisible) {
            Text(
                modifier = modifierCase
                    .defaultMinSize(
                        minWidth = TextFieldDefaults.MinWidth,
                        minHeight = TextFieldDefaults.MinHeight
                    )
                    .padding(
                        all = 16.dp
                    )
                    .padding(
                        start = if (leadingIcon != null) 32.dp else 0.dp,
                        end = if (trailingIcon != null) 32.dp else 0.dp
                    ),
                text = hint,
                style = textStyle,
                color = Color.LightGray
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OutlineHintTextFieldPreview() {
    OutlineHintTextField(
        text = EmptyString,
        hint = "Lorem ipsum",
        onValueChange = {},
        isHintContentVisible = true,
        onFocusChange = {}
    )
}