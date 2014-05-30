package MinesweeperPackage;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.Timer;

/** @see http://stackoverflow.com/questions/5528939*/
public class ClockExample extends JFrame
{
    private static final int N = 60;
    private static final String stop = "Stop";
    private static final String start = "Start";
    private final ClockListener cl = new ClockListener();
    private final Timer t = new Timer(1000, cl);
    private final JTextField tf = new JTextField(3);

    public ClockExample()
    {
        t.setInitialDelay(0);

        JPanel panel = new JPanel();
        tf.setHorizontalAlignment(JTextField.RIGHT);
        tf.setEditable(false);
        panel.add(tf);
        final JToggleButton b = new JToggleButton(stop);
        b.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                if (b.isSelected()) {
                    t.stop();
                    b.setText(start);
                } else {
                    t.start();
                    b.setText(stop);
                }
            }
        });
        panel.add(b);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(panel);
        this.setTitle("Timer");
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void start() {
        t.start();
    }

    private class ClockListener implements ActionListener {

        private int count;

        @Override
        public void actionPerformed(ActionEvent e) {
            count %= N;
            tf.setText(String.valueOf(count));
            count++;
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                ClockExample clock = new ClockExample();
                clock.start();
            }
        });
    }
}