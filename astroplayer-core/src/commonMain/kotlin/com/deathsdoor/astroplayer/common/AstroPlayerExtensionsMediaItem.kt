package com.deathsdoor.astroplayer.common

import com.deathsdoor.astroplayer.AstroPlayer
import com.deathsdoor.astroplayer.dataclasses.MediaItem


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
fun AstroPlayer.rearrange(from : Int, to : Int){
    if (from >= mediaItems.size || to >= mediaItems.size) throw IndexOutOfBoundsException("Index out of range")
    mediaItems[to] = mediaItems[from].also { mediaItems[from] = mediaItems[to] }

    updatePlayerAfterRearrange(from,to)
}
