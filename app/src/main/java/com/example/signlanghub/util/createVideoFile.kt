package com.example.signlanghub.util

import android.content.ContentResolver
import android.content.ContentValues
import android.net.Uri
import android.provider.MediaStore
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun createVideoFile(contentResolver: ContentResolver): Uri? {
    val now = SimpleDateFormat("yyMMdd_HHmmss", Locale.KOREAN).format(Date())
    val content =
        ContentValues().apply {
            put(MediaStore.Video.Media.DISPLAY_NAME, "video_$now.mpeg")
            put(MediaStore.Video.Media.MIME_TYPE, "video/*")
            put(MediaStore.Video.Media.HEIGHT, 1280)
            put(MediaStore.Video.Media.WIDTH, 1280)
        }
    return contentResolver.insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, content)
}
