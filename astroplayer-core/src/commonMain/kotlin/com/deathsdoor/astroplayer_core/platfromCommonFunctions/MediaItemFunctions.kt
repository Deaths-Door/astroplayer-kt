package com.deathsdoor.astroplayer_core.platfromCommonFunctions

import com.deathsdoor.astroplayer_core.AstroPlayer
import com.deathsdoor.astroplayer_core.dataclasses.MediaItem
import com.deathsdoor.astroplayer_core.swap

//TODO update media player when functions are called

fun AstroPlayer.clear(){
    this.mediaItems.clear()
    this.updatePlayerAfterClear()
}

fun AstroPlayer.addMediaItem(mediaItem: MediaItem){
    this.mediaItems.add(mediaItem)
    this.updatePlayerAfterAddMediaItem()
}

fun AstroPlayer.addMediaItem(index:Int, mediaItem: MediaItem){
    this.mediaItems.add(index,mediaItem)
    this.updatePlayerAfterAddMediaItem(index,mediaItem)
}

fun AstroPlayer.addMediaItems(mediaItems: List<MediaItem>){
    this.mediaItems.addAll(mediaItems)
    this.updatePlayerAfterAddMediaItems(mediaItems)

}

fun AstroPlayer.addMediaItems(index:Int, mediaItems: List<MediaItem>){
    this.mediaItems.addAll(index,mediaItems)
    this.updatePlayerAfterAddMediaItems(index,mediaItems)

}

fun AstroPlayer.remove(mediaItem: MediaItem){
    val index = this.mediaItems.indexOf(mediaItem)
    this.mediaItems.removeAt(index)
}

fun AstroPlayer.removeAt(index:Int){
    this.mediaItems.removeAt(index)
    this.updatePlayerAfterRemoveAt(index)
}

@Throws(IndexOutOfBoundsException::class)
fun AstroPlayer.rearrange(from:Int, to:Int){
    if (from >= mediaItems.size || to >= mediaItems.size) throw IndexOutOfBoundsException("Index out of range")
    mediaItems.swap(from,to)
    updatePlayerAfterRearrange(from,to)
}


