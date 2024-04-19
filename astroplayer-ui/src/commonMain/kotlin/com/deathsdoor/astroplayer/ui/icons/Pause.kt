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

public val Icons.Outlined.Pause: ImageVector
    get() {
        if (_pause != null) {
            return _pause!!
        }
        _pause = Builder(name = "Pause", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 960.0f, viewportHeight = 960.0f).apply {
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(520.0f, 760.0f)
                verticalLineToRelative(-560.0f)
                horizontalLineToRelative(240.0f)
                verticalLineToRelative(560.0f)
                lineTo(520.0f, 760.0f)
                close()
                moveTo(200.0f, 760.0f)
                verticalLineToRelative(-560.0f)
                horizontalLineToRelative(240.0f)
                verticalLineToRelative(560.0f)
                lineTo(200.0f, 760.0f)
                close()
                moveTo(600.0f, 680.0f)
                horizontalLineToRelative(80.0f)
                verticalLineToRelative(-400.0f)
                horizontalLineToRelative(-80.0f)
                verticalLineToRelative(400.0f)
                close()
                moveTo(280.0f, 680.0f)
                horizontalLineToRelative(80.0f)
                verticalLineToRelative(-400.0f)
                horizontalLineToRelative(-80.0f)
                verticalLineToRelative(400.0f)
                close()
                moveTo(280.0f, 280.0f)
                verticalLineToRelative(400.0f)
                verticalLineToRelative(-400.0f)
                close()
                moveTo(600.0f, 280.0f)
                verticalLineToRelative(400.0f)
                verticalLineToRelative(-400.0f)
                close()
            }
        }
        .build()
        return _pause!!
    }

private var _pause: ImageVector? = null
