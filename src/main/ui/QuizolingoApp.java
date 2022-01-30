package ui;

import model.Flashcard;
import model.Folder;

import java.util.*;

// Quizolingo language flashcards application
public class QuizolingoApp {
    private Scanner input;
    private List<String> commands;
    private Folder folder;

    // EFFECTS: intializing a new scanner, processing user input functionality,
    //          a list of available commands, and a new language folder
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
        input.nextLine(); // this somehow fixes the weird input bug
        phrase = getStringInput("Please enter the flashcard phrase: ");
        System.out.println("You entered phrase as: " + phrase);
        translation = getStringInput("Please enter the flashcard translation: ");
        System.out.println("You entered translation as: " + translation);
        proficiencyRating = getIntegerInput("Please enter the phrase's proficiency rating between 1 to 5: ");
        System.out.println("You entered proficiency rating as: " + proficiencyRating);

        Flashcard flashcard = new Flashcard(phrase, translation, proficiencyRating);
        this.folder.addFlashcard(flashcard);
    }

    // EFFECTS: displays the names of all the flashcards in folder
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
    // EFFECTS: prompts user for input and removes the corresponding flashcard
    //          if the number of flashcards in the folder is non-zero
    private void doRemoveFlashcard() {
        String phrase;
        Flashcard flashcard;

        if (this.folder.getFlashcards().size() == 0) {
            System.out.println("There are no flashcards in this folder!");
        } else {
            System.out.println("You chose: Remove Flashcard!");
            // TODO: list the names of all the flashcards?
            input.nextLine();
            phrase = getStringInput("Please enter a flashcard phrase: ");
            flashcard = this.folder.getFlashcardByPhrase(phrase);
            this.folder.removeFlashcard(flashcard);
            // TODO: should I check for phrase DNE? using the getFlashcardByName and checking for dummy return value?
            System.out.println("Flashcard removed!");
        }
    }

    // MODIFIES: this
    // EFFECTS: prompts user for input and updates the proficiency rating for the
    //          corresponding flashcard if the number of flashcards in the folder is non-zero
    private void doUpdateFlashcard() {
        String phrase;
        int proficiencyRating;

        if (this.folder.getFlashcards().size() == 0) {
            System.out.println("There are no flashcards in this folder!");
        } else {
            System.out.println("You chose: Update Flashcard's Proficiency Rating!");
            // TODO: list the names of all the flashcards?
            input.nextLine();
            phrase = getStringInput("Please enter the phrase of the flashcard to update: ");
            // TODO: should I check for phrase DNE? using the getFlashcardByName and checking for dummy return value?
            Flashcard flashcard = this.folder.getFlashcardByPhrase(phrase);
            proficiencyRating = getIntegerInput("Please enter the new proficiency rating: ");
            flashcard.setProficiencyRating(proficiencyRating);
            System.out.println("Flashcard updated!");
        }
    }

    // EFFECTS: obtains the user's string input
    private String getStringInput(String message) {
        String userInput = "";
        boolean checkInputIsString = true;

        while (checkInputIsString) {
            System.out.println(message);
            userInput = input.nextLine();

            if ((userInput instanceof String) && checkNonZeroStringLength(userInput)) {
                checkInputIsString = false;
            } else {
                System.out.println("Invalid input!");
            }
        }

        return userInput;
    }

    // EFFECTS: obtains the user's integer input
    private Integer getIntegerInput(String message) {
        int userInput = 0;
        boolean checkInputIsInt = true;

        while (checkInputIsInt) {
            System.out.println(message);
            userInput = input.nextInt();
            input.nextLine();

            if ((userInput == (int)userInput) && checkIntegerBetweenOneAndFive(userInput)) {
                checkInputIsInt = false;
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

    // EFFECTS: produces true if integer is [1, 5], else false
    private boolean checkIntegerBetweenOneAndFive(int integer) {
        if ((integer >= 1) && (integer <= 5)) {
            return true;
        }

        return false;
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        for (String command : this.commands) {
            System.out.println("\t" + command.toLowerCase().charAt(0) + " -> " + command);
        }
    }
}
