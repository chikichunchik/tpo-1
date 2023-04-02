package lab.billiards.third;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class BounceFrame extends JFrame {
    private final BallCanvas canvas;

    public BounceFrame() {
        this.setSize(400, 300);
        this.setTitle("Lab 1");

        this.canvas = new BallCanvas();

        Container content = this.getContentPane();
        content.add(this.canvas, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.lightGray);

        JButton buttonStartBlue = new JButton("Add ball(blue)");
        JButton buttonStartRed = new JButton("Add ball(red)");
        JButton buttonStop = new JButton("Stop");

        buttonStartBlue.addActionListener(e -> {
            Ball b = new Ball(canvas, 'b');
            canvas.add(b);
            BallThread thread = new BallThread(b, canvas);
            thread.start();
        });

        buttonStartRed.addActionListener(e -> {
            Ball b = new Ball(canvas, 'r');
            canvas.add(b);
            BallThread thread = new BallThread(b, canvas);
            thread.start();
        });

        buttonStop.addActionListener(e -> System.exit(0));

        buttonPanel.add(buttonStartBlue);
        buttonPanel.add(buttonStartRed);
        buttonPanel.add(buttonStop);
        content.add(buttonPanel, BorderLayout.SOUTH);
    }
}
