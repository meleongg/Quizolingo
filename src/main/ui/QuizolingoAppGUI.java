package ui;

import model.Folder;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuizolingoAppGUI extends JFrame {
    private static final String JSON_STORE = "./data/folder.json";
    private Folder folder;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private Timer timer;
    private JProgressBar progress;

    public static final int MIN_PROFICIENCY_RATING = 1;
    public static final int MAX_PROFICIENCY_RATING = 5;
    private static final int FRAME_WIDTH = 600;
    private static final int FRAME_HEIGHT = 500;

    public QuizolingoAppGUI() {
        super("Quizolingo App");
        renderLoadingScreen();
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void renderLoadingScreen() {
        final JWindow loadingScreenWindow = initializeLoadingScreenWindow();
        JPanel logoPanel = initializeLogoPanel(loadingScreenWindow);
        addLogo(logoPanel);
        addProgressBar(loadingScreenWindow);
        addTimer(loadingScreenWindow, progress);
        timer.start();
    }

    private JPanel initializeLogoPanel(JWindow loadingScreenWindow) {
        JPanel logoPanel = new JPanel();
        logoPanel.setBackground(Color.white);
        logoPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        loadingScreenWindow.add(logoPanel);
        return logoPanel;
    }

    private JWindow initializeLoadingScreenWindow() {
        final JWindow loadingScreenWindow = new JWindow(this);
        loadingScreenWindow.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        loadingScreenWindow.setLocationRelativeTo(null);
        loadingScreenWindow.setVisible(true);
        return loadingScreenWindow;
    }

    private void addLogo(JPanel panel) {
        String imagePath = "images/duo.png";
        JLabel label = new JLabel(new ImageIcon(imagePath));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label);

        JLabel titleLabel = new JLabel("Quizolingo App");
        panel.add(titleLabel);

        String imagePath2 = "images/quizlet_logo.png";
        ImageIcon imageIcon = new ImageIcon(new ImageIcon(imagePath2).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        JLabel label2 = new JLabel(imageIcon);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label2);
    }

    private void addProgressBar(JWindow loadingScreen) {
        progress = new JProgressBar(0, 100);
        progress.setForeground(Color.orange);
        loadingScreen.add(BorderLayout.PAGE_END, progress);
        loadingScreen.revalidate();
    }

    private void addTimer(JWindow loadingScreen, JProgressBar progress) {
        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int x = progress.getValue();
                if (x == 100) {
                    loadingScreen.dispose();
                    QuizolingoAppGUI.this.setVisible(true);
                    timer.stop();
                } else {
                    progress.setValue(x + 4);
                }
            }
        });
    }
}
