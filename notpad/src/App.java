
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class App extends JFrame implements ActionListener {
    private JTextArea textArea = new JTextArea();
    private JFileChooser fileChooser = new JFileChooser();
    private File currentFile = null;

    public App() {
        // Set up the JFrame
        setTitle("Notepad");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set up the JTextArea
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane);

        // Set up the JMenuBar
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        // Set up the File menu
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        // Set up the New menu item
        JMenuItem newMenuItem = new JMenuItem("New");
        newMenuItem.addActionListener(this);
        fileMenu.add(newMenuItem);

        // Set up the Open menu item
        JMenuItem openMenuItem = new JMenuItem("Open");
        openMenuItem.addActionListener(this);
        fileMenu.add(openMenuItem);

        // Set up the Save menu item
        JMenuItem saveMenuItem = new JMenuItem("Save");
        saveMenuItem.addActionListener(this);
        fileMenu.add(saveMenuItem);

        // Set up the Save As menu item
        JMenuItem saveAsMenuItem = new JMenuItem("Save As");
        saveAsMenuItem.addActionListener(this);
        fileMenu.add(saveAsMenuItem);

        // Set up the Exit menu item
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(this);
        fileMenu.add(exitMenuItem);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("New")) {
            textArea.setText("");
            currentFile = null;
            setTitle("Notepad");
            return;
        }

        if (command.equals("Open")) {
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                try {
                    File selectedFile = fileChooser.getSelectedFile();
                    BufferedReader reader = new BufferedReader(new FileReader(selectedFile));
                    textArea.read(reader, null);
                    reader.close();
                    currentFile = selectedFile;
                    setTitle(currentFile.getName() + " - Notepad");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error opening file: " + ex.getMessage());
                }
            }
            return;
        }

        if (command.equals("Save")) {
            if (currentFile == null) {
                int result = fileChooser.showSaveDialog(this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    try {
                        File selectedFile = fileChooser.getSelectedFile();
                        BufferedWriter writer = new BufferedWriter(new FileWriter(selectedFile));
                        textArea.write(writer);
                        writer.close();
                        currentFile = selectedFile;
                        setTitle(currentFile.getName() + " - Notepad");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(this, "Error saving file: " + ex.getMessage());
                    }
                }
            } else {
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(currentFile));
                    textArea.write(writer);
                    writer.close();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error saving file: " + ex.getMessage());
                }
            }
            return;
        }

        if (command.equals("Save As")) {
            int result = fileChooser.showSaveDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                try {
                    File selectedFile = fileChooser.getSelectedFile();
                    BufferedWriter writer = new BufferedWriter(new FileWriter(selectedFile));
                    textArea.write(writer);
                    writer.close();
                    currentFile = selectedFile;
                    setTitle(currentFile.getName() + " - Notepad");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error saving file: " + ex.getMessage());
                }
            }
            return;
        }

        if (command.equals("Exit")) {
            System.exit(0);
            return;
        }
    }

    public static void main(String[] args) {
        App notepad = new App();
        notepad.setVisible(true);
    }
}