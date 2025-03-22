package org.audiophile.audiolibraries

import kotlin.reflect.KClass

internal enum class AudioLibraryType(val clazz: KClass<*>) {
    STANDARD_SYNTH(StandardSynth::class),
    PHYSICAL_SIMULATION(PhysicalSimulation::class),
    AUDIO_SHADER(AudioShader::class),
    ANALYZE_AUDIO(AnalyzeAudio::class)
}