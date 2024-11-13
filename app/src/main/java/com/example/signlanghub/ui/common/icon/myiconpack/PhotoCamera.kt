package com.example.signlanghub.ui.common.icon.myiconpack

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.example.signlanghub.ui.common.icon.IconPack

val IconPack.PhotoCamera: ImageVector
    get() {
        if (_photoCamera != null) {
            return _photoCamera!!
        }
        _photoCamera = Builder(name = "PhotoCamera", defaultWidth = 24.0.dp, defaultHeight =
                24.0.dp, viewportWidth = 960.0f, viewportHeight = 960.0f).apply {
            path(fill = SolidColor(Color(0xFFe8eaed)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(480.0f, 700.0f)
                quadToRelative(75.0f, 0.0f, 127.5f, -52.5f)
                reflectiveQuadTo(660.0f, 520.0f)
                quadToRelative(0.0f, -75.0f, -52.5f, -127.5f)
                reflectiveQuadTo(480.0f, 340.0f)
                quadToRelative(-75.0f, 0.0f, -127.5f, 52.5f)
                reflectiveQuadTo(300.0f, 520.0f)
                quadToRelative(0.0f, 75.0f, 52.5f, 127.5f)
                reflectiveQuadTo(480.0f, 700.0f)
                close()
                moveTo(480.0f, 620.0f)
                quadToRelative(-42.0f, 0.0f, -71.0f, -29.0f)
                reflectiveQuadToRelative(-29.0f, -71.0f)
                quadToRelative(0.0f, -42.0f, 29.0f, -71.0f)
                reflectiveQuadToRelative(71.0f, -29.0f)
                quadToRelative(42.0f, 0.0f, 71.0f, 29.0f)
                reflectiveQuadToRelative(29.0f, 71.0f)
                quadToRelative(0.0f, 42.0f, -29.0f, 71.0f)
                reflectiveQuadToRelative(-71.0f, 29.0f)
                close()
                moveTo(160.0f, 840.0f)
                quadToRelative(-33.0f, 0.0f, -56.5f, -23.5f)
                reflectiveQuadTo(80.0f, 760.0f)
                verticalLineToRelative(-480.0f)
                quadToRelative(0.0f, -33.0f, 23.5f, -56.5f)
                reflectiveQuadTo(160.0f, 200.0f)
                horizontalLineToRelative(126.0f)
                lineToRelative(74.0f, -80.0f)
                horizontalLineToRelative(240.0f)
                lineToRelative(74.0f, 80.0f)
                horizontalLineToRelative(126.0f)
                quadToRelative(33.0f, 0.0f, 56.5f, 23.5f)
                reflectiveQuadTo(880.0f, 280.0f)
                verticalLineToRelative(480.0f)
                quadToRelative(0.0f, 33.0f, -23.5f, 56.5f)
                reflectiveQuadTo(800.0f, 840.0f)
                lineTo(160.0f, 840.0f)
                close()
                moveTo(160.0f, 760.0f)
                horizontalLineToRelative(640.0f)
                verticalLineToRelative(-480.0f)
                lineTo(638.0f, 280.0f)
                lineToRelative(-73.0f, -80.0f)
                lineTo(395.0f, 200.0f)
                lineToRelative(-73.0f, 80.0f)
                lineTo(160.0f, 280.0f)
                verticalLineToRelative(480.0f)
                close()
                moveTo(480.0f, 520.0f)
                close()
            }
        }
        .build()
        return _photoCamera!!
    }

private var _photoCamera: ImageVector? = null
