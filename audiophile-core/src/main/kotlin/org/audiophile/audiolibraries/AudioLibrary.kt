package org.audiophile.audiolibraries

internal sealed class AudioLibrary(private val name: String, val type: String) {
    companion object {
        fun new(name: String, type: AudioLibraryType) = type.clazz.constructors.first().call(name) as AudioLibrary
    }
}
internal class StandardSynth(name: String): AudioLibrary(name, "Standard Synth")
internal class PhysicalSimulation(name: String): AudioLibrary(name, "Physical Simulation")
internal class AudioShader(name: String): AudioLibrary(name, "Audio Shader")
internal class AnalyzeAudio(name: String): AudioLibrary(name, "Analyze Audio")