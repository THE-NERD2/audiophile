package org.audiophile

import com.formdev.flatlaf.FlatLightLaf
import net.miginfocom.swing.MigLayout
import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.JTabbedPane
import javax.swing.SwingUtilities

private class UI: JTabbedPane() {
    companion object {
        lateinit var frame: JFrame
    }
    private val mainPanel = object: JPanel(MigLayout()) {}
    private val synthPanel = object: JPanel(MigLayout()) {
        private val newAudioLibrary = JButton("New Audio Library")
        init {
            newAudioLibrary.addActionListener { audioLibraryDialog() }
            add(newAudioLibrary)
        }
        private fun audioLibraryDialog() {
            // TODO
        }
    }
    init {
        addTab("Main", mainPanel)
        addTab("Synth", synthPanel)
    }
}

fun main() {
    FlatLightLaf.setup()
    SwingUtilities.invokeLater {
        UI.frame = JFrame("Audiophile")

        UI.frame.setSize(1000, 800)
        UI.frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        UI.frame.contentPane = UI()
        UI.frame.isVisible = true
    }
}