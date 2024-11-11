package com.example.signlanghub.ui.common.composable.video

import android.graphics.Color
import android.net.Uri
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.OptIn
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.MediaSource
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView


@OptIn(UnstableApi::class)
@Composable
fun Video() {
    val context = LocalContext.current
    val httpDataSourceFactory = DefaultHttpDataSource.Factory().setAllowCrossProtocolRedirects(true)
    val exoPlayer = ExoPlayer.Builder(context).build()
    val mediaUri = Uri.parse("http://sldict.korean.go.kr/multimedia/multimedia_files/convert/20201211/785178/MOV000357024_700X466.mp4")
    val mediaItem = MediaItem.fromUri(mediaUri)
    exoPlayer.setMediaItem(mediaItem)
    exoPlayer.repeatMode = ExoPlayer.REPEAT_MODE_ALL
    exoPlayer.prepare()
    exoPlayer.play()
    exoPlayer.playWhenReady = true
    val mediaSource = ProgressiveMediaSource.Factory(httpDataSourceFactory)
        .createMediaSource(mediaItem)
    exoPlayer.setMediaSource(mediaSource)
    AndroidView(
        modifier = Modifier.size(300.dp),
        factory = { androidViewContext ->
            PlayerView(androidViewContext).apply {
                layoutParams = FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                this.setBackgroundColor(Color.TRANSPARENT)
                this.player = exoPlayer
                this.overlayFrameLayout
                this.useController = false
                this.setShutterBackgroundColor(Color.TRANSPARENT)
                this.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIT
            }
        }
    )
    DisposableEffect(Unit) {
        onDispose {
            exoPlayer.release()
        }
    }
}