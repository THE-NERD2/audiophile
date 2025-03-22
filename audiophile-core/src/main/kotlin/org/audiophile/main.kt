package org.audiophile

import com.formdev.flatlaf.FlatLightLaf
import org.audiophile.ui.UI
import javax.swing.JFrame
import javax.swing.SwingUtilities

fun main() {
    FlatLightLaf.setup()
    SwingUtilities.invokeLater {
        UI.frame = JFrame("Audiophile")

        UI.frame.setSize(1000, 800)
        UI.frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        UI.frame.contentPane = UI
        UI.frame.isVisible = true
    }
}