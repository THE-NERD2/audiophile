package org.audiophile.ui

import net.miginfocom.swing.MigLayout
import org.audiophile.audiolibraries.AudioLibrary
import org.audiophile.audiolibraries.AudioLibraryType
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
        private var currentAudioLibrary: AudioLibrary? = null
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
            val audioLibraryOptions = listOf(
                JRadioButton("Standard Synth"),
                JRadioButton("Physical Simulation"),
                JRadioButton("Audio Shader"),
                JRadioButton("Analyze Existing Audio")
            )
            var maxRowIndex = 0
            audioLibraryOptions.forEachIndexed { i, option ->
                typeGroup.add(option)
                dialog.add(option, "cell 0 ${i + 2}, spanx")
                maxRowIndex = i + 2
            }

            val errorLabel = JLabel()
            dialog.add(errorLabel, "cell 0 ${++maxRowIndex}, spanx")

            val okButton = JButton("OK")
            dialog.add(okButton, "cell 2 ${++maxRowIndex}, align right")
            okButton.addActionListener {
                val typeIndex = audioLibraryOptions.indexOfFirst { it.isSelected }
                if (typeIndex == -1) {
                    errorLabel.text = "Please select a type"
                    dialog.pack()
                    return@addActionListener
                }
                currentAudioLibrary = AudioLibrary.new(nameField.text, AudioLibraryType.entries[typeIndex])
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