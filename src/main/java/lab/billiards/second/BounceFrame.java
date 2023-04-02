package lab.billiards.second;

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
        canvas.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                canvas.appendPockets(40);
            }
        });

        Container content = this.getContentPane();
        content.add(this.canvas, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.lightGray);

        JButton buttonStart = new JButton("Add ball");
        JButton buttonStop = new JButton("Stop");
        JLabel scoreLabel = new JLabel();
        canvas.setScoreLabel(scoreLabel);

        buttonStart.addActionListener(e -> {
            Ball b = new Ball(canvas);
            canvas.add(b);
            BallThread thread = new BallThread(b, canvas);
            thread.start();
        });

        buttonStop.addActionListener(e -> System.exit(0));

        buttonPanel.add(buttonStart);
        buttonPanel.add(buttonStop);
        buttonPanel.add(scoreLabel);
        content.add(buttonPanel, BorderLayout.SOUTH);
    }
}
