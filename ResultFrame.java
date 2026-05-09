import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Simple display window used by the GUI to show results.
 */
public class ResultFrame extends JFrame {
    private JTextArea resultArea;

    /**
     * Creates a result frame and initializes the text area used to display output.
     */
    public ResultFrame() {
        setTitle("Store Results");
        setSize(500, 350);
        setLocation(760, 180);

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        add(new JScrollPane(resultArea));
    }

    /**
     * Displays the given text inside the result frame and makes the frame visible.
     *
     * @param text the result text to display
     */
    public void showResult(String text) {
        resultArea.setText(text);
        setVisible(true);
    }
}
