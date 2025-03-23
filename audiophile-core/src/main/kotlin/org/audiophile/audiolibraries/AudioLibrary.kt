package org.audiophile.audiolibraries

internal sealed class AudioLibrary(private val name: String, val type: String) {
    companion object {
        fun new(name: String, type: AudioLibraryType) = type.clazz.constructors.first().call(name) as AudioLibrary
    }
}