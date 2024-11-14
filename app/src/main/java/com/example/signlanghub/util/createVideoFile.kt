package com.example.signlanghub.util

import android.content.ContentResolver
import android.content.ContentValues
import android.net.Uri
import android.provider.MediaStore
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun createVidoeFile(contentResolver: ContentResolver): Uri? {
    val now = SimpleDateFormat("yyMMdd_HHmmss", Locale.KOREAN).format(Date())
    val content =
        ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, "img_$now.mp4")
            put(MediaStore.Images.Media.MIME_TYPE, "video/mp4")
            put(MediaStore.Images.Media.HEIGHT, 1280)
            put(MediaStore.Images.Media.WIDTH, 1280)
        }
    return contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, content)
}
