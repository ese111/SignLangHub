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
import com.example.signlanghub.ui.common.icon.MyIconPack

val MyIconPack.Inbox: ImageVector
    get() {
        if (_inbox != null) {
            return _inbox!!
        }
        _inbox = Builder(name = "Inbox", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            path(fill = SolidColor(Color(0xFF1D1B20)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(5.0f, 21.0f)
                curveTo(4.45f, 21.0f, 3.975f, 20.8083f, 3.575f, 20.425f)
                curveTo(3.1917f, 20.025f, 3.0f, 19.55f, 3.0f, 19.0f)
                verticalLineTo(5.0f)
                curveTo(3.0f, 4.45f, 3.1917f, 3.9833f, 3.575f, 3.6f)
                curveTo(3.975f, 3.2f, 4.45f, 3.0f, 5.0f, 3.0f)
                horizontalLineTo(19.0f)
                curveTo(19.55f, 3.0f, 20.0167f, 3.2f, 20.4f, 3.6f)
                curveTo(20.8f, 3.9833f, 21.0f, 4.45f, 21.0f, 5.0f)
                verticalLineTo(19.0f)
                curveTo(21.0f, 19.55f, 20.8f, 20.025f, 20.4f, 20.425f)
                curveTo(20.0167f, 20.8083f, 19.55f, 21.0f, 19.0f, 21.0f)
                horizontalLineTo(5.0f)
                close()
                moveTo(12.0f, 16.0f)
                curveTo(12.6333f, 16.0f, 13.2083f, 15.8167f, 13.725f, 15.45f)
                curveTo(14.2417f, 15.0833f, 14.6f, 14.6f, 14.8f, 14.0f)
                horizontalLineTo(19.0f)
                verticalLineTo(5.0f)
                horizontalLineTo(5.0f)
                verticalLineTo(14.0f)
                horizontalLineTo(9.2f)
                curveTo(9.4f, 14.6f, 9.7583f, 15.0833f, 10.275f, 15.45f)
                curveTo(10.7917f, 15.8167f, 11.3667f, 16.0f, 12.0f, 16.0f)
                close()
            }
        }
        .build()
        return _inbox!!
    }

private var _inbox: ImageVector? = null
