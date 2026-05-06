import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Simple display window used by the GUI to show results.
 */
public class ResultFrame extends JFrame {
    private JTextArea resultArea;

    public ResultFrame() {
        setTitle("Store Results");
        setSize(500, 350);
        setLocation(760, 180);

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        add(new JScrollPane(resultArea));
    }

    public void showResult(String text) {
        resultArea.setText(text);
        setVisible(true);
    }
}
