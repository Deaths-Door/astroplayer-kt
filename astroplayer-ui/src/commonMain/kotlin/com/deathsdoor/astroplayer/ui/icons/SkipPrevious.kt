package com.deathsdoor.astroplayer.ui.icons

import androidx.compose.material.icons.Icons
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

public val Icons.Outlined.SkipPrevious: ImageVector
    get() {
        if (_skipPrevious != null) {
            return _skipPrevious!!
        }
        _skipPrevious = Builder(name = "SkipPrevious", defaultWidth = 24.0.dp, defaultHeight =
                24.0.dp, viewportWidth = 960.0f, viewportHeight = 960.0f).apply {
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(220.0f, 720.0f)
                verticalLineToRelative(-480.0f)
                horizontalLineToRelative(80.0f)
                verticalLineToRelative(480.0f)
                horizontalLineToRelative(-80.0f)
                close()
                moveTo(740.0f, 720.0f)
                lineTo(380.0f, 480.0f)
                lineToRelative(360.0f, -240.0f)
                verticalLineToRelative(480.0f)
                close()
                moveTo(660.0f, 480.0f)
                close()
                moveTo(660.0f, 570.0f)
                verticalLineToRelative(-180.0f)
                lineToRelative(-136.0f, 90.0f)
                lineToRelative(136.0f, 90.0f)
                close()
            }
        }
        .build()
        return _skipPrevious!!
    }

private var _skipPrevious: ImageVector? = null
