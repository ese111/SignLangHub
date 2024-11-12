package com.example.signlanghub.ui.common.composable.video

import android.graphics.Color
import android.net.Uri
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.OptIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView

@OptIn(UnstableApi::class)
@Composable
fun Video(
    modifier: Modifier = Modifier,
    uri: String,
) {
    val context = LocalContext.current
    val httpDataSourceFactory = DefaultHttpDataSource.Factory().setAllowCrossProtocolRedirects(true)
    val exoPlayer = ExoPlayer.Builder(context).build()
    val mediaUri = Uri.parse(uri)
    val mediaItem = MediaItem.fromUri(mediaUri)
    exoPlayer.setMediaItem(mediaItem)
    exoPlayer.repeatMode = ExoPlayer.REPEAT_MODE_ONE
    exoPlayer.prepare()
    exoPlayer.play()
    exoPlayer.playWhenReady = true
    val mediaSource =
        ProgressiveMediaSource
            .Factory(httpDataSourceFactory)
            .createMediaSource(mediaItem)
    exoPlayer.setMediaSource(mediaSource)
    AndroidView(
        modifier = modifier,
        factory = { androidViewContext ->
            PlayerView(androidViewContext).apply {
                layoutParams =
                    FrameLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT,
                    )
                this.setBackgroundColor(Color.TRANSPARENT)
                this.useController = true
                this.player = exoPlayer
                this.setShutterBackgroundColor(Color.TRANSPARENT)
                this.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIT
                this.setKeepContentOnPlayerReset(true)
            }
        },
    )
    DisposableEffect(Unit) {
        onDispose {
            exoPlayer.release()
        }
    }
}
