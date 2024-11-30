package com.wcsm.androidlearninghub.guide_music_player_notification

interface Playable {
    fun onTrackPrevious()
    fun onTrackPlay()
    fun onTrackPause()
    fun onTrackNext()
}