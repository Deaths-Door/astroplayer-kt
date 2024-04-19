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

public val Icons.Outlined.PauseCircle: ImageVector
    get() {
        if (_pauseCircle != null) {
            return _pauseCircle!!
        }
        _pauseCircle = Builder(name = "PauseCircle", defaultWidth = 24.0.dp, defaultHeight =
                24.0.dp, viewportWidth = 960.0f, viewportHeight = 960.0f).apply {
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(360.0f, 640.0f)
                horizontalLineToRelative(80.0f)
                verticalLineToRelative(-320.0f)
                horizontalLineToRelative(-80.0f)
                verticalLineToRelative(320.0f)
                close()
                moveTo(520.0f, 640.0f)
                horizontalLineToRelative(80.0f)
                verticalLineToRelative(-320.0f)
                horizontalLineToRelative(-80.0f)
                verticalLineToRelative(320.0f)
                close()
                moveTo(480.0f, 880.0f)
                quadToRelative(-83.0f, 0.0f, -156.0f, -31.5f)
                reflectiveQuadTo(197.0f, 763.0f)
                quadToRelative(-54.0f, -54.0f, -85.5f, -127.0f)
                reflectiveQuadTo(80.0f, 480.0f)
                quadToRelative(0.0f, -83.0f, 31.5f, -156.0f)
                reflectiveQuadTo(197.0f, 197.0f)
                quadToRelative(54.0f, -54.0f, 127.0f, -85.5f)
                reflectiveQuadTo(480.0f, 80.0f)
                quadToRelative(83.0f, 0.0f, 156.0f, 31.5f)
                reflectiveQuadTo(763.0f, 197.0f)
                quadToRelative(54.0f, 54.0f, 85.5f, 127.0f)
                reflectiveQuadTo(880.0f, 480.0f)
                quadToRelative(0.0f, 83.0f, -31.5f, 156.0f)
                reflectiveQuadTo(763.0f, 763.0f)
                quadToRelative(-54.0f, 54.0f, -127.0f, 85.5f)
                reflectiveQuadTo(480.0f, 880.0f)
                close()
                moveTo(480.0f, 800.0f)
                quadToRelative(134.0f, 0.0f, 227.0f, -93.0f)
                reflectiveQuadToRelative(93.0f, -227.0f)
                quadToRelative(0.0f, -134.0f, -93.0f, -227.0f)
                reflectiveQuadToRelative(-227.0f, -93.0f)
                quadToRelative(-134.0f, 0.0f, -227.0f, 93.0f)
                reflectiveQuadToRelative(-93.0f, 227.0f)
                quadToRelative(0.0f, 134.0f, 93.0f, 227.0f)
                reflectiveQuadToRelative(227.0f, 93.0f)
                close()
                moveTo(480.0f, 480.0f)
                close()
            }
        }
        .build()
        return _pauseCircle!!
    }

private var _pauseCircle: ImageVector? = null
