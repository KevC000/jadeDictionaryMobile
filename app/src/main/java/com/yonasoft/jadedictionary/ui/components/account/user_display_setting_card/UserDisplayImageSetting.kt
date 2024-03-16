package com.yonasoft.jadedictionary.ui.components.account.user_display_setting_card

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Upload
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.yonasoft.jadedictionary.R

@Composable
fun UserDisplayImageSetting(
    currentProfileImageLink: String,
    selectedImage: Uri?,
    onInitiateUpload: () -> Unit,
) {
    if (selectedImage != null || currentProfileImageLink.isNotEmpty()) {
        Card(
            modifier = Modifier.size(200.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            AsyncImage(
                modifier = Modifier.size(200.dp),
                contentScale = ContentScale.Crop,
                model = selectedImage ?: currentProfileImageLink,
                contentDescription = "User Profile Image"
            )
        }
    } else {
        Image(
            modifier = Modifier.size(200.dp),
            painter = painterResource(id = R.drawable.baseline_account_box_200),
            contentDescription = "Default Profile Image"
        )
    }
    TextButton(
        onClick = {
            onInitiateUpload()
        },
    ) {
        Icon(
            imageVector = Icons.Default.Upload,
            contentDescription = "Upload Icon",
        )
        Text(text = "Upload Profile Image")
    }
}