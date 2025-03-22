package org.audiophile.ui

import net.miginfocom.swing.MigLayout
import javax.swing.ButtonGroup
import javax.swing.JButton
import javax.swing.JDialog
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JRadioButton
import javax.swing.JTabbedPane
import javax.swing.JTextField

internal object UI: JTabbedPane() {
    lateinit var frame: JFrame

    private val mainPanel = object: JPanel(MigLayout()) {}
    private val synthPanel = object: JPanel(MigLayout()) {
        private val newAudioLibrary = JButton("New Audio Library")
        init {
            newAudioLibrary.addActionListener { audioLibraryDialog() }
            add(newAudioLibrary)
        }
        private fun audioLibraryDialog() {
            val dialog = JDialog(frame, "New Audio Library", true)
            dialog.layout = MigLayout("wmin 300, fillx")

            dialog.add(JLabel("Name:"), "cell 0 0")

            val nameField = JTextField()
            dialog.add(nameField, "cell 1 0, spanx 2, growx")

            dialog.add(JLabel("Type:"), "cell 0 1")

            val typeGroup = ButtonGroup()

            val standardSynth = JRadioButton("Standard Synth")
            typeGroup.add(standardSynth)
            dialog.add(standardSynth, "cell 0 2, spanx")

            val physicalSimulation = JRadioButton("Physical Simulation")
            typeGroup.add(physicalSimulation)
            dialog.add(physicalSimulation, "cell 0 3, spanx")

            val audioShader = JRadioButton("Audio Shader")
            typeGroup.add(audioShader)
            dialog.add(audioShader, "cell 0 4, spanx")

            val analyzeAudio = JRadioButton("Analyze Existing Audio")
            typeGroup.add(analyzeAudio)
            dialog.add(analyzeAudio, "cell 0 5, spanx")

            val okButton = JButton("OK")
            dialog.add(okButton, "cell 2 6, align right")
            okButton.addActionListener {
                dialog.dispose()
            }

            dialog.pack()
            dialog.defaultCloseOperation = JDialog.DISPOSE_ON_CLOSE
            dialog.isVisible = true
        }
    }
    init {
        addTab("Main", mainPanel)
        addTab("Synth", synthPanel)
    }
}