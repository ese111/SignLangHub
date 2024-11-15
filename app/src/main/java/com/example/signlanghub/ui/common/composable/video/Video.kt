package com.example.signlanghub.ui.common.composable.video

import android.graphics.Color
import android.net.Uri
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.OptIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory
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
    val playerView : MutableState<PlayerView?> = remember { mutableStateOf(null) }
    val httpDataSourceFactory = remember { DefaultHttpDataSource.Factory().setAllowCrossProtocolRedirects(true) }
    val mediaDataSource = remember { DefaultMediaSourceFactory(context).setDataSourceFactory(httpDataSourceFactory) }
    val mediaUri = remember { Uri.parse(uri) }
    val mediaItem = remember { MediaItem.fromUri(mediaUri) }
    val mediaSource = remember {
        ProgressiveMediaSource
            .Factory(httpDataSourceFactory)
            .createMediaSource(mediaItem)
    }
    val exoPlayer = remember {
        ExoPlayer.Builder(context).setMediaSourceFactory(mediaDataSource).build().apply {
            setMediaItem(mediaItem)
            repeatMode = ExoPlayer.REPEAT_MODE_ONE
            prepare()
            play()
            playWhenReady = true
            setMediaSource(mediaSource)
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            exoPlayer.release()
            playerView.value?.player = null
        }
    }

    AndroidView(
        modifier = modifier,
        factory = { androidViewContext ->
            PlayerView(androidViewContext).apply {
                playerView.value = this
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
                this.setKeepContentOnPlayerReset(false)
            }
        },
    )

}

@OptIn(UnstableApi::class)
@Composable
fun MediaVideo(
    modifier: Modifier = Modifier,
    uri: String,
) {
    val context = LocalContext.current
    val mediaDataSource = remember { DefaultMediaSourceFactory(context) }
    val mediaUri = remember { Uri.parse(uri) }
    val mediaItem = remember { MediaItem.fromUri(mediaUri) }
    val playerView : MutableState<PlayerView?> = remember { mutableStateOf(null) }

    val exoPlayer = remember {
        ExoPlayer.Builder(context).setMediaSourceFactory(mediaDataSource).build().apply {
            setMediaItem(mediaItem)
            repeatMode = ExoPlayer.REPEAT_MODE_OFF
            prepare()
            play()
            playWhenReady = true
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            exoPlayer.release()
            playerView.value?.player = null
        }
    }

    AndroidView(
        modifier = modifier,
        factory = { androidViewContext ->
            PlayerView(androidViewContext).apply {
                playerView.value = this
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
                this.setKeepContentOnPlayerReset(false)
            }
        },
    )

}