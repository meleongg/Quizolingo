package ui;

import model.Flashcard;
import model.Folder;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class QuizolingoAppGUI extends JFrame {
    private static final String JSON_STORE = "./data/folder.json";
    private Folder folder;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private Timer timer;
    private JProgressBar progress;
    private JLabel titleLbl;
    private JPanel menuPanel;
    private JButton randomWordButton;
    private JPanel saveLoadPanel;
    private JButton saveBtn;
    private JButton loadBtn;
    private JPanel folderPanel;
    private JButton addBtn;

    public static final int MIN_PROFICIENCY_RATING = 1;
    public static final int MAX_PROFICIENCY_RATING = 5;
    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 500;
    private static final int BTN_WIDTH = 40;
    private static final int BTN_HEIGHT = 20;
    private static final int PADDING = 40;

    public QuizolingoAppGUI() {
        super("Quizolingo App");
        renderLoadingScreen();
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1, 0, 20));

        this.folder = new Folder();

        renderTitle();
        renderMenuPanel();
        renderFolderPanel();
        renderAddBtn();
    }

    private void renderAddBtn() {
        addBtn = new JButton("Add Flashcard");
        addBtn.setPreferredSize(new Dimension(BTN_WIDTH, BTN_HEIGHT));
        add(addBtn);
    }

    private void renderFolderPanel() {
        folderPanel = new JPanel(new GridLayout(0, 5, 10, 10));
        folderPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        folderPanel.setBorder(BorderFactory.createEmptyBorder(0, PADDING, 0, PADDING));

        renderFlashCardPanels(folderPanel);
        add(folderPanel);
    }

    private void renderFlashCardPanels(JPanel panel) {
        Flashcard testFlashcard = new Flashcard("bonjour", "hello", 3);
        Flashcard testFlashcard2 = new Flashcard("bonsoir", "hello", 3);
        folder.addFlashcard(testFlashcard);
        folder.addFlashcard(testFlashcard2);
        List<Flashcard> flashcards = folder.getFlashcards();

        for (Flashcard flashcard : flashcards) {
            JPanel flashcardPanel = renderFlashCardPanel(flashcard);
            panel.add(flashcardPanel);
        }
    }

    private JPanel renderFlashCardPanel(Flashcard flashcard) {
        JLabel phraseLbl;
        JLabel translationLbl;
        JLabel proficiencyLbl;
        JButton removeBtn;

        JPanel flashcardPanel = new JPanel(new GridLayout(4, 1, 0, 10));
        flashcardPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        phraseLbl = new JLabel(flashcard.getPhrase());
        translationLbl = new JLabel(flashcard.getTranslation());
        proficiencyLbl = new JLabel("" + flashcard.getProficiencyRating());
        removeBtn = new JButton("Remove");

        flashcardPanel.add(phraseLbl);
        flashcardPanel.add(translationLbl);
        flashcardPanel.add(proficiencyLbl);
        flashcardPanel.add(removeBtn);

        return flashcardPanel;
    }

    private void renderMenuPanel() {
        menuPanel = new JPanel(new GridLayout(1, 3, 60, 0));
        menuPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        menuPanel.setBorder(BorderFactory.createEmptyBorder(0, PADDING, 0, PADDING));

        renderYourCardsLabel();
        renderRandomWordButton();
        renderSaveLoadPanel();

        add(menuPanel);
    }

    private void renderSaveLoadPanel() {
        saveLoadPanel = new JPanel(new GridLayout(2, 1, 0, 20));
        saveLoadPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        saveBtn = new JButton("Save Cards");
        saveBtn.setPreferredSize(new Dimension(BTN_WIDTH, BTN_HEIGHT));
        saveLoadPanel.add(saveBtn);

        loadBtn = new JButton("Load Cards");
        loadBtn.setPreferredSize(new Dimension(BTN_WIDTH, BTN_HEIGHT));
        saveLoadPanel.add(loadBtn);

        menuPanel.add(saveLoadPanel);
    }

    private void renderRandomWordButton() {
        randomWordButton = new JButton("Random Word");
        menuPanel.add(randomWordButton);
    }

    private void renderYourCardsLabel() {
        JLabel yourCardsLabel = new JLabel("Your cards");
        yourCardsLabel.setHorizontalAlignment(JLabel.LEFT);
        yourCardsLabel.setVerticalAlignment(JLabel.BOTTOM);
        menuPanel.add(yourCardsLabel);
    }

    private void renderTitle() {
        titleLbl = new JLabel("Quizolingo App!", JLabel.CENTER);
        add(titleLbl);
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
        String imagePath = "data/logo.png";
        ImageIcon imageIcon = new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(FRAME_WIDTH,
                FRAME_HEIGHT,
                Image.SCALE_DEFAULT));
        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(imageLabel);
    }

    private void addProgressBar(JWindow loadingScreen) {
        UIManager.put("ProgressBar.background", Color.white);
        UIManager.put("ProgressBar.foreground", Color.green);
        UIManager.put("ProgressBar.selectionBackground", Color.red);
        UIManager.put("ProgressBar.selectionForeground", Color.green);
        progress = new JProgressBar(0, 100);
        progress.setForeground(Color.green);
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
