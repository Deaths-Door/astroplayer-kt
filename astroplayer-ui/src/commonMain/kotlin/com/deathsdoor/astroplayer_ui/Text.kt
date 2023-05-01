package com.deathsdoor.astroplayer_ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import com.deathsdoor.astroplayer.AstroPlayer
import com.deathsdoor.astroplayer.platfromCommonFunctions.currentMediaItem
import com.deathsdoor.astroplayer.platfromCommonFunctions.currentMediaItemTrackLength

private val Long.formatToTime : String get() {
    val seconds = this / 1000
    val minutes = seconds / 60
    val remainingSeconds = seconds % 60
    return "$minutes:${remainingSeconds.toString().padStart(2, '0')}" // "0:05"
}

@Composable
fun AstroPlayer.CurrentPositionText(
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontStyle: FontStyle? = null,
    fontWeight: FontWeight? = null,
    fontFamily: FontFamily? = null,
){
    Text(
        text = currentMediaItemPosition.formatToTime,
        maxLines = 1,
        color = color,
        fontSize = fontSize,
        fontStyle = fontStyle,
        fontWeight = fontWeight,
        fontFamily = fontFamily
    )
}

@Composable
fun AstroPlayer.CurrentMediaItemTrackLengthText(
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontStyle: FontStyle? = null,
    fontWeight: FontWeight? = null,
    fontFamily: FontFamily? = null,
){
    Text(
        text = currentMediaItemTrackLength.formatToTime,
        maxLines = 1,
        color = color,
        fontSize = fontSize,
        fontStyle = fontStyle,
        fontWeight = fontWeight,
        fontFamily = fontFamily
    )
}

@Composable
fun AstroPlayer.CurrentMediaItemTrackTitle(
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontStyle: FontStyle? = null,
    fontWeight: FontWeight? = null,
    fontFamily: FontFamily? = null,
    ifTitleNull : String = "Given Title"
){
    Text(
        text = currentMediaItem.metadata.title ?: ifTitleNull,
        maxLines = 1,
        color = color,
        fontSize = fontSize,
        fontStyle = fontStyle,
        fontWeight = fontWeight,
        fontFamily = fontFamily
    )
}

@Composable
fun AstroPlayer.CurrentMediaItemTrackArtists(
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontStyle: FontStyle? = null,
    fontWeight: FontWeight? = null,
    fontFamily: FontFamily? = null,
    ifArtistNull : String = "No Given Artists",
    joinToString : List<String>?.() -> String = { this?.joinToString { "$it ," } ?: ifArtistNull }
){
    Text(
        text = currentMediaItem.metadata.artist.joinToString(),
        maxLines = 1,
        color = color,
        fontSize = fontSize,
        fontStyle = fontStyle,
        fontWeight = fontWeight,
        fontFamily = fontFamily
    )
}
