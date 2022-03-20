package ui;

import exceptions.IllegalInputException;
import model.Flashcard;
import model.Folder;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.List;

// Represents the GUI for the Quizolingo App
public class QuizolingoAppGUI extends JFrame {
    private static final String JSON_STORE = "./data/folder.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private Folder folder;

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

    private JWindow flashcardInputFormWindow;
    private JTextField phraseInput;
    private JTextField translationInput;
    private JSpinner proficiencyRatingInput;
    private JButton cancelBtn;
    private JButton confirmBtn;

    public static final int MIN_PROFICIENCY_RATING = 1;
    public static final int MAX_PROFICIENCY_RATING = 5;
    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 500;
    private static final int FORM_WIDTH = 300;
    private static final int FORM_HEIGHT = 300;
    private static final int BTN_WIDTH = 120;
    private static final int BTN_HEIGHT = 40;
    private static final int PADDING = 40;

    // creates GUI for Quizolingo App
    public QuizolingoAppGUI() {
        super("Quizolingo App");
        renderLoadingScreen();
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1, 0, 20));

        this.folder = new Folder();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        renderMainPage();
        centreOnScreen();
    }

    // MODIFIES: this
    // EFFECTS: renders the loading screen
    private void renderLoadingScreen() {
        final JWindow loadingScreenWindow = initializeLoadingScreenWindow();
        JPanel logoPanel = renderLogoPanel(loadingScreenWindow);
        addLogo(logoPanel);
        addProgressBar(loadingScreenWindow);
        addTimer(loadingScreenWindow, progress);
        timer.start();
    }

    // MODIFIES: this
    // EFFECTS: renders the logo panel onto the loading screen JWindow
    private JPanel renderLogoPanel(JWindow loadingScreenWindow) {
        JPanel logoPanel = new JPanel();
        logoPanel.setBackground(Color.white);
        logoPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        loadingScreenWindow.add(logoPanel);
        return logoPanel;
    }

    // MODIFIES: this
    // EFFECTS: initializes the JWindow's properties for the loading screen
    private JWindow initializeLoadingScreenWindow() {
        final JWindow loadingScreenWindow = new JWindow(this);
        loadingScreenWindow.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        loadingScreenWindow.setLocationRelativeTo(null);
        loadingScreenWindow.setVisible(true);
        return loadingScreenWindow;
    }

    // MODIFIES: this
    // EFFECTS: adds and adjusts the logo image onto the logo panel
    private void addLogo(JPanel panel) {
        String imagePath = "data/logo.png";
        ImageIcon imageIcon = new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(FRAME_WIDTH,
                FRAME_HEIGHT,
                Image.SCALE_DEFAULT));
        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(imageLabel);
    }

    // MODIFIES: this
    // EFFECTS: adds a JProgressBar representing the application loading
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

    // MODIFIES: this
    // EFFECTS: adds a timer to keep track of the loading time and when to switch to the home page
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

    // MODIFIES: this
    // EFFECTS: renders the main home page for the app with new instances of all of its panels
    private void renderMainPage() {
        getContentPane().removeAll();
        getContentPane().revalidate();
        getContentPane().repaint();

        renderTitle();
        renderMenuPanel();
        renderFolderPanel();
        renderAddBtn();
    }

    // MODIFIES: this
    // EFFECTS: renders the title on the home page
    private void renderTitle() {
        titleLbl = new JLabel("Quizolingo App!", JLabel.CENTER);
        add(titleLbl);
    }

    // MODIFIES: this
    // EFFECTS: renders the menu panel
    private void renderMenuPanel() {
        menuPanel = new JPanel(new GridLayout(1, 3, 60, 0));
        menuPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        addPadding(menuPanel);

        renderYourCardsLabel();
        renderRandomTranslationButton();
        renderSaveLoadPanel();

        add(menuPanel);
    }

    // MODIFIES: this
    // EFFECTS: renders the "Your Cards" JLabel
    private void renderYourCardsLabel() {
        JLabel yourCardsLabel = new JLabel("Your cards");
        yourCardsLabel.setHorizontalAlignment(JLabel.LEFT);
        yourCardsLabel.setVerticalAlignment(JLabel.BOTTOM);
        menuPanel.add(yourCardsLabel);
    }

    // MODIFIES: this
    // EFFECTS: renders the random word button and activates its event listener
    private void renderRandomTranslationButton() {
        JPanel randomWordBtnPanel = new JPanel();
        randomWordButton = new JButton("Random Word");
        randomWordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getRandomTranslation();
            }
        });
        randomWordButton.setHorizontalAlignment(JLabel.CENTER);
        randomWordButton.setVerticalAlignment(JLabel.CENTER);
        randomWordButton.setPreferredSize(new Dimension(BTN_WIDTH, BTN_HEIGHT));
        randomWordBtnPanel.add(randomWordButton);
        menuPanel.add(randomWordBtnPanel);
    }

    // MODIFIES: this
    // EFFECTS: gets a random translation that has already been added
    private void getRandomTranslation() {
        try {
            List<Flashcard> flashcards = folder.getFlashcards();
            Random r = new Random();
            int number = r.nextInt(flashcards.size());
            String phrase = flashcards.get(number).getPhrase();
            String translation = flashcards.get(number).getTranslation();
            renderRandomTranslation(phrase, translation);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, "Must add flashcards before generating a random translation!");
        }
    }

    // MODIFIES: this
    // EFFECTS: renders a new JWindow with the translation
    private void renderRandomTranslation(String phrase, String translation) {
        final JWindow randomTranslationWindow = initializeRandomTranslationWindow();
        JPanel randomTranslationPanel = new JPanel(new GridLayout(2, 1, 0, 20));
        addPadding(randomTranslationPanel);
        renderTranslationLabel(phrase, translation, randomTranslationPanel);
        renderCloseButton(randomTranslationWindow, randomTranslationPanel);
    }

    // MODIFIES: this
    // EFFECTS: initializes the JWindow's properties for the random translation
    private JWindow initializeRandomTranslationWindow() {
        final JWindow randomTranslationWindow = new JWindow(this);
        randomTranslationWindow.setSize(400, 200);
        randomTranslationWindow.setLocationRelativeTo(null);
        randomTranslationWindow.setVisible(true);
        return randomTranslationWindow;
    }

    // MODIFIES: this
    // EFFECTS: renders the JLabel for the random translation
    private void renderTranslationLabel(String phrase, String translation, JPanel randomTranslationPanel) {
        JLabel randomTranslationLabel = new JLabel("The word " + phrase + " means " + translation + ".");

        randomTranslationLabel.setHorizontalAlignment(JLabel.CENTER);
        randomTranslationLabel.setVerticalAlignment(JLabel.CENTER);
        randomTranslationPanel.add(randomTranslationLabel);
        randomTranslationPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Random Translation"));
    }

    // MODIFIES: this
    // EFFECTS: renders the close button for the random translation JWindow and activates its event listener
    private void renderCloseButton(JWindow randomTranslationWindow, JPanel randomTranslationPanel) {
        JPanel closeBtnPanel = new JPanel();
        JButton closeBtn = new JButton("Close");
        closeBtnPanel.add(closeBtn);
        closeBtn.setSize(BTN_WIDTH, BTN_HEIGHT);
        closeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                randomTranslationWindow.dispose();
            }
        });
        randomTranslationPanel.add(closeBtnPanel);

        randomTranslationWindow.add(randomTranslationPanel);
    }

    // MODIFIES: this
    // EFFECTS: renders the save and load panel
    private void renderSaveLoadPanel() {
        saveLoadPanel = new JPanel(new GridLayout(2, 1, 0, 10));
        saveLoadPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        renderSaveButton();
        renderLoadButton();
        menuPanel.add(saveLoadPanel);
    }

    // MODIFIES: this
    // EFFECTS: renders the load button and activates its event listener
    private void renderLoadButton() {
        JPanel loadBtnPanel = new JPanel();
        loadBtn = new JButton("Load Cards");
        loadBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doLoadFolder();
                renderMainPage();
            }
        });
        loadBtn.setHorizontalAlignment(JButton.CENTER);
        loadBtn.setVerticalAlignment(JButton.CENTER);
        loadBtn.setPreferredSize(new Dimension(BTN_WIDTH, BTN_HEIGHT));
        loadBtnPanel.add(loadBtn);
        saveLoadPanel.add(loadBtnPanel);
    }

    // MODIFIES: this
    // EFFECTS: loads folder from file
    private void doLoadFolder() {
        try {
            this.folder = jsonReader.read();
            System.out.println("Loaded folder from " + JSON_STORE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Unable to read from file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: renders the save button and activates its event listener
    private void renderSaveButton() {
        JPanel saveBtnPanel = new JPanel();
        saveBtn = new JButton("Save Cards");
        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doSaveFolder();
                renderMainPage();
            }
        });
        saveBtn.setHorizontalAlignment(JButton.CENTER);
        saveBtn.setVerticalAlignment(JButton.CENTER);
        saveBtn.setPreferredSize(new Dimension(BTN_WIDTH, BTN_HEIGHT));
        saveBtnPanel.add(saveBtn);
        saveLoadPanel.add(saveBtnPanel);
    }

    // EFFECTS: saves the folder to file
    private void doSaveFolder() {
        try {
            jsonWriter.open();
            jsonWriter.write(this.folder);
            jsonWriter.close();
            System.out.println("Saved folder to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: renders the folder panel to display all the flashcards
    private void renderFolderPanel() {
        folderPanel = new JPanel(new GridLayout(0, 5, 25, 20));
        folderPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        addPadding(folderPanel);

        renderFlashCardPanels(folderPanel);
        add(folderPanel);
    }

    // MODIFIES: this
    // EFFECTS: renders each flashcard added to the folder object
    private void renderFlashCardPanels(JPanel panel) {
        List<Flashcard> flashcards = folder.getFlashcards();

        for (Flashcard flashcard : flashcards) {
            JPanel flashcardPanel = renderFlashCardPanel(flashcard);
            panel.add(flashcardPanel);
        }
    }

    // MODIFIES: this
    // EFFECTS: renders one flashcard and adds a removeBtn event listener
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
        removeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                folder.removeFlashcard(flashcard);
                renderMainPage();
            }
        });

        flashcardPanel.add(phraseLbl);
        flashcardPanel.add(translationLbl);
        flashcardPanel.add(proficiencyLbl);
        flashcardPanel.add(removeBtn);

        return flashcardPanel;
    }

    // MODIFIES: this
    // EFFECTS: renders the add flashcard button and activates its event listener
    private void renderAddBtn() {
        JPanel addBtnPanel = new JPanel();
        addBtn = new JButton("Add Flashcard");
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                renderFlashcardInputForm();
            }
        });
        addBtn.setHorizontalAlignment(JButton.CENTER);
        addBtn.setVerticalAlignment(JButton.CENTER);
        addBtn.setPreferredSize(new Dimension(BTN_WIDTH, BTN_HEIGHT));
        addBtnPanel.add(addBtn);
        add(addBtnPanel);
    }

    // MODIFIES: this
    // EFFECTS: renders the form to add a new flashcard
    private void renderFlashcardInputForm() {
        flashcardInputFormWindow = initializeFlashcardInputForm();
        renderFlashcardInputFormWindow(flashcardInputFormWindow);
    }

    // MODIFIES: this
    // EFFECTS: initializes the JWindow properties of the flashcard input form
    private JWindow initializeFlashcardInputForm() {
        flashcardInputFormWindow = new JWindow(this);
        flashcardInputFormWindow.setFocusable(true);
        flashcardInputFormWindow.setSize(FORM_WIDTH, FORM_HEIGHT);
        flashcardInputFormWindow.setLocationRelativeTo(null);
        flashcardInputFormWindow.setVisible(true);
        return flashcardInputFormWindow;
    }

    // MODIFIES: this
    // EFFECTS: renders the JWindow to add a new flashcard
    private void renderFlashcardInputFormWindow(JWindow window) {
        JPanel flashcardInputFormPanel = initializeFlashcardInputFormPanel();
        renderFlashcardInputFormPanelInputs(flashcardInputFormPanel);
        renderFlashcardInputFormPanelButtons(flashcardInputFormPanel);
        window.add(flashcardInputFormPanel);
    }

    // MODIFIES: this
    // EFFECTS: initializes the JPanel's properties for the flashcard input form
    private JPanel initializeFlashcardInputFormPanel() {
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 40, 40));
        formPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        addPadding(formPanel);
        formPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Add Flashcard"));
        return formPanel;
    }

    // MODIFIES: this
    // EFFECTS: renders the JButtons for the flashcard input form
    private void renderFlashcardInputFormPanelButtons(JPanel flashcardInputFormPanel) {
        renderFlashcardInputFormPanelCancelBtn(flashcardInputFormPanel);
        renderFlashcardInputFormPanelConfirmBtn(flashcardInputFormPanel);
    }

    // MODIFIES: this
    // EFFECTS: renders the confirm button to add a new flashcard for the flashcard input form
    private void renderFlashcardInputFormPanelConfirmBtn(JPanel flashcardInputFormPanel) {
        confirmBtn = new JButton("Add");
        confirmBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    processUserInput();
                    flashcardInputFormWindow.dispose();
                    renderMainPage();
                } catch (IllegalInputException ex) {
                    JDialog dialog = new JDialog();
                    dialog.setAlwaysOnTop(true);
                    JOptionPane.showMessageDialog(dialog, "All fields must be filled!");
                }
            }
        });
        flashcardInputFormPanel.add(confirmBtn);
    }

    // MODIFIES: this
    // EFFECTS: creates a new flashcard with user input, throws IllegalInputException
    // if phraseInput or translationInput are left blank
    private void processUserInput() throws IllegalInputException {
        String phrase = phraseInput.getText();
        String translation = translationInput.getText();

        if (!checkNonZeroStringLength(phrase) || (!checkNonZeroStringLength(translation))) {
            throw new IllegalInputException();
        }

        int proficiencyRating = (int) proficiencyRatingInput.getValue();
        Flashcard flashcard = new Flashcard(phrase, translation, proficiencyRating);
        folder.addFlashcard(flashcard);
    }

    // EFFECTS: produces true if string has a non-zero length, else false
    private boolean checkNonZeroStringLength(String str) {
        if (str.length() != 0) {
            return true;
        }

        return false;
    }


    // MODIFIES: this
    // EFFECTS: renders the cancel button for the flashcard input form
    private void renderFlashcardInputFormPanelCancelBtn(JPanel flashcardInputFormPanel) {
        cancelBtn = new JButton("Cancel");
        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                flashcardInputFormWindow.dispose();
                QuizolingoAppGUI.this.setVisible(true);
            }
        });
        flashcardInputFormPanel.add(cancelBtn);
    }

    // MODIFIES: this
    // EFFECTS: renders the input fields for the flashcard input form
    private void renderFlashcardInputFormPanelInputs(JPanel flashcardInputFormPanel) {
        JLabel phraseLbl = new JLabel("Phrase: ");
        flashcardInputFormPanel.add(phraseLbl);

        phraseInput = new JTextField(20);
        flashcardInputFormPanel.add(phraseInput);

        JLabel translationLbl = new JLabel("Translation: ");
        flashcardInputFormPanel.add(translationLbl);

        translationInput = new JTextField(20);
        flashcardInputFormPanel.add(translationInput);

        JLabel proficiencyRatingLbl = new JLabel("<html>Proficiency<br /> Rating: </html>");
        flashcardInputFormPanel.add(proficiencyRatingLbl);

        SpinnerModel yearModel = new SpinnerNumberModel(1, MIN_PROFICIENCY_RATING, MAX_PROFICIENCY_RATING, 1);
        proficiencyRatingInput = new JSpinner(yearModel);
        flashcardInputFormPanel.add(proficiencyRatingInput);
    }

    // MODIFIES: this
    // EFFECTS: adds padding to the left and right of a given JPanel
    private void addPadding(JPanel panel) {
        panel.setBorder(BorderFactory.createEmptyBorder(0, PADDING, 0, PADDING));
    }

    // MODIFIES: this
    // EFFECTS: centers the JFrame in the middle of the user's screen
    private void centreOnScreen() {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screen.width - getWidth()) / 2, (screen.height - getHeight()) / 2);
    }
}
