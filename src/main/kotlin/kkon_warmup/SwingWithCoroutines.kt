package kkon_warmup

import kotlinx.coroutines.*
import kotlinx.coroutines.swing.Swing
import utilities.log
import java.awt.Font
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.event.KeyEvent
import javax.swing.*
import javax.swing.border.EmptyBorder
import kotlin.coroutines.CoroutineContext

class ExampleApp : ActionListener, CoroutineScope {

    private val frame: JFrame = JFrame()
    private val startButton = JButton()
    private val stopButton = JButton()
    private val statusText = JTextField()

    override val coroutineContext: CoroutineContext = Dispatchers.Swing
    private var job: Job? = null

    private fun createJob(text: JTextField): Job = launch {
        var counter = 0
        while (isActive) {
            log("set: $counter")
            text.text = "$counter"
            delay(1000L)
            counter += 1
        }
    }

    init {
        with(frame) {
            with(startButton) {
                text = "Start"
                isEnabled = true
                addActionListener {
                    log("Start coroutine")
                    startButton.isEnabled = false
                    stopButton.isEnabled = true
                    job = createJob(statusText)
                }
            }
            with(stopButton) {
                text = "Stop coroutine"
                isEnabled = false
                addActionListener {
                    log("Stop")
                    startButton.isEnabled = true
                    stopButton.isEnabled = false
                    job?.cancel()
                    statusText.text = ""
                }
            }

            title = "Coroutines with Swing"
            defaultCloseOperation = JFrame.EXIT_ON_CLOSE
            jMenuBar = createMenuBar(this@ExampleApp)
            setSize(480, 270)

            val buttons = JPanel().apply {
                layout = BoxLayout(this, BoxLayout.X_AXIS)
                border = EmptyBorder(10, 10, 10, 10)
                add(startButton)
                add(stopButton)
            }
            with(statusText) {
                text = ""
                horizontalAlignment = JTextField.CENTER
                isEditable = false
                setFont(Font("Arial Black", Font.BOLD, 42))
            }
            add(JPanel().apply {
                layout = BoxLayout(this, BoxLayout.Y_AXIS)
                add(buttons)
                add(statusText)
            })
            isVisible = true
        }
    }

    override fun actionPerformed(e: ActionEvent) {
        when (e.actionCommand) {
            "About" -> about(frame)
            "Quit" -> quit(frame)
            else -> throw RuntimeException("Unknown Command")
        }
    }
}

private fun about(frame: JFrame) {
    log("about")
    JOptionPane.showMessageDialog(
        frame,
        "This is an example application that shows how to use coroutines with Swing.",
        "About this app",
        JOptionPane.PLAIN_MESSAGE
    )
}

private fun quit(frame: JFrame) {
    val choice = JOptionPane.showOptionDialog(
        frame,
        "Do you really want to quit?",
        "Quit",
        JOptionPane.YES_NO_OPTION,
        JOptionPane.QUESTION_MESSAGE, null, null, null
    )
    if (choice == 0) {
        System.exit(0)
    }
}

private fun createMenuBar(parent: ActionListener): JMenuBar {
    return JMenuBar().apply {
        add(JMenu("File").apply {
            mnemonic = KeyEvent.VK_F
            addSeparator()
            add(JMenuItem("Quit").apply {
                mnemonic = KeyEvent.VK_Q
                addActionListener(parent)
            })
        })
        add(JMenu("Help").apply {
            mnemonic = KeyEvent.VK_H
            add(JMenuItem("About").apply {
                mnemonic = KeyEvent.VK_A
                addActionListener(parent)
            })
        })
    }
}

fun main() {
    ExampleApp()
}
