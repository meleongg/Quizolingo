package ui;

import model.Flashcard;
import model.Folder;

import java.util.*;

// Quizolingo language flashcards application
public class QuizolingoApp {
    private Scanner input;
    private List<String> commands;
    private Folder folder;

    public static final int MIN_PROFICIENCY_RATING = 1;
    public static final int MAX_PROFICIENCY_RATING = 5;

    // EFFECTS: initializes a new language folder, a list of available commands,
    //          a new scanner, and processing user input functionality
    public QuizolingoApp() {
        this.folder = new Folder();
        this.commands = new ArrayList<>(Arrays.asList("Add Flashcard",
                "Remove Flashcard",
                "View Flashcards",
                "Update Flashcard Proficiency",
                "Quit"));
        input = new Scanner(System.in);
        runQuizolingoApp();
    }

    // MODIFIES: this
    // EFFECTS: processes the users input and executes the command if
    //          user input command is in the list of commands
    private void runQuizolingoApp() {
        String command = "";
        boolean currentlyGettingInput = true;

        while (currentlyGettingInput) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                currentlyGettingInput = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nYou have quit the application!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("a")) {
            doAddFlashcard();
        } else if (command.equals("r")) {
            doRemoveFlashcard();
        } else if (command.equals("v")) {
            doViewFlashcards();
        } else if (command.equals("u")) {
            doUpdateFlashcard();
        } else {
            System.out.println("Selection invalid!");
        }
    }

    // MODIFIES: this
    // EFFECTS: prompts user for input and creates a new flashcard for the folder
    private void doAddFlashcard() {
        String phrase;
        String translation;
        int proficiencyRating;

        System.out.println("You chose: Add Flashcard!");
        input.nextLine();
        phrase = getStringInput("Please enter the flashcard phrase: ");
        System.out.println("You entered phrase as: " + phrase);
        translation = getStringInput("Please enter the flashcard translation: ");
        System.out.println("You entered translation as: " + translation);
        proficiencyRating = getIntegerInput("Please enter the phrase's proficiency rating between 1 to 5: ");
        System.out.println("You entered proficiency rating as: " + proficiencyRating);

        Flashcard flashcard = new Flashcard(phrase, translation, proficiencyRating);
        this.folder.addFlashcard(flashcard);
        System.out.println("Flashcard added!");
    }

    // MODIFIES: this
    // EFFECTS: prompts user for input and removes the corresponding flashcard
    //          if the number of flashcards in the folder is non-zero and a
    //          flashcard with the given phrase exists
    private void doRemoveFlashcard() {
        String phrase;
        Flashcard flashcard;

        if (this.folder.getFlashcards().size() == 0) {
            System.out.println("There are no flashcards in this folder!");
        } else {
            System.out.println("You chose: Remove Flashcard!");
            input.nextLine();
            phrase = getStringInput("Please enter a flashcard phrase: ");
            flashcard = this.folder.getFlashcardByPhrase(phrase);

            if (flashcard.getPhrase().equals("")) {
                System.out.println("Flashcard does not exist!");
            } else {
                this.folder.removeFlashcard(flashcard);
                System.out.println("Flashcard removed!");
            }
        }
    }

    // EFFECTS: displays the names of all the flashcards in folder
    //          if the number of flashcards in the folder is non-zero
    private void doViewFlashcards() {
        if (this.folder.getFlashcards().size() == 0) {
            System.out.println("There are currently no flashcards!");
        } else {
            System.out.println("Flashcards currently in this folder: ");
            for (Flashcard flashcard : this.folder.getFlashcards()) {
                System.out.println("Flashcard");
                System.out.println("\t Phrase: " + flashcard.getPhrase());
                System.out.println("\t Translation: " + flashcard.getTranslation());
                System.out.println("\t Proficiency Rating: " + flashcard.getProficiencyRating());
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: prompts user for input and updates the proficiency rating for the
    //          corresponding flashcard if the number of flashcards in the folder is non-zero
    //          and a flashcard with the given phrase exists
    private void doUpdateFlashcard() {
        String phrase;
        int proficiencyRating;
        Flashcard flashcard;

        if (this.folder.getFlashcards().size() == 0) {
            System.out.println("There are no flashcards in this folder!");
        } else {
            System.out.println("You chose: Update Flashcard's Proficiency Rating!");
            input.nextLine();
            phrase = getStringInput("Please enter the phrase of the flashcard to update: ");
            flashcard = this.folder.getFlashcardByPhrase(phrase);

            if (flashcard.getPhrase().equals("")) {
                System.out.println("Flashcard does not exist!");
            } else {
                proficiencyRating = getIntegerInput("Please enter the new proficiency rating: ");
                flashcard.setProficiencyRating(proficiencyRating);
                System.out.println("Flashcard updated!");
            }
        }
    }

    // EFFECTS: prompts user for string input until given input is of type string
    //          and is of a non-zero length
    private String getStringInput(String message) {
        String userInput = "";
        boolean validatingInput = true;

        while (validatingInput) {
            System.out.println(message);
            userInput = input.nextLine();

            if (checkNonZeroStringLength(userInput)) {
                validatingInput = false;
            } else {
                System.out.println("Invalid input!");
            }
        }

        return userInput;
    }

    // REQUIRES: user input is of integer data type
    // EFFECTS: prompts user for input until given input is
    //          within [MIN_PROFICIENCY_RATING, MAX_PROFICIENCY_RATING]
    private Integer getIntegerInput(String message) {
        int userInput = 0;
        boolean validatingInput = true;

        while (validatingInput) {
            System.out.println(message);
            userInput = input.nextInt();
            input.nextLine();

            if (checkIntegerBetweenOneAndFive(userInput)) {
                validatingInput = false;
            } else {
                System.out.println("Invalid Input!");
            }
        }

        return userInput;
    }

    // EFFECTS: produces true if string has a non-zero length, else false
    private boolean checkNonZeroStringLength(String str) {
        if (str.length() != 0) {
            return true;
        }

        return false;
    }

    // EFFECTS: produces true if integer is within [MIN_PROFICIENCY_RATING, MAX_PROFICIENCY_RATING],
    //          else false
    private boolean checkIntegerBetweenOneAndFive(int integer) {
        if ((integer >= MIN_PROFICIENCY_RATING) && (integer <= MAX_PROFICIENCY_RATING)) {
            return true;
        }

        return false;
    }

    // EFFECTS: displays menu of command options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        for (String command : this.commands) {
            System.out.println("\t" + command.toLowerCase().charAt(0) + " -> " + command);
        }
    }
}
