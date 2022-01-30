package ui;

import model.Flashcard;
import model.Folder;

import java.util.*;

/*
add functionality
- user enters "add", directed to new method
new method:
    - You have chosen add
    - scanner asks for phrase
    - scanner asks for translation
    - scanner asks for rating
    - scanner creates new flashcard and adds it to folder
    - Flashcard added!
    - returns to asking for command

remove functionality
- user enters "remove", directed to new method
- if there are no flashcards, output there are no flashcards
    - return to command list
- if there are flashcards, list the phrases of each
    - scanner asks for phrase
    - delete flashcard with corresponding phrase
    - flashcard removed!
    - returns to asking for command

update functionality
- user enters "update proficiency rating"
- if there are no flashcards, output there are no flashcards
    - return to command list
- if there are flashcards,
    - for each flashcard, list [phrase, rating]
    - scanner asks for phrase
    - scanner asks for new rating
    - update flashcard rating
    - flashcard updated!
    - returns to asking for command

*/

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
        phrase = getStringInput("Please enter the flashcard phrase: ");
        System.out.println("You entered phrase as: " + phrase);
        translation = getStringInput("Please enter the flashcard translation: ");
        System.out.println("You entered translation as: " + translation);
        proficiencyRating = getIntegerInput("Please enter the phrase's proficiency rating: ");
        System.out.println("You entered proficiency rating as: " + proficiencyRating);

        Flashcard flashcard = new Flashcard(phrase, translation, proficiencyRating);
        this.folder.addFlashcard(flashcard);
    }

    // MODIFIES: this
    // EFFECTS: prompts user for input and removes the corresponding flashcard
    //          if the number of flashcards in the folder is non-zero
    private void doRemoveFlashcard() {
    }

    // MODIFIES: this
    // EFFECTS: prompts user for input and updates the proficiency rating for the
    //          corresponding flashcard if the number of flashcards in the folder is non-zero
    private void doUpdateFlashcard() {
    }

    // EFFECTS: obtains the user's string input
    private String getStringInput(String message) {
        System.out.println(message);
        String userInput = input.nextLine();
        return userInput;
    }

    // EFFECTS: obtains the user's integer input
    private Integer getIntegerInput(String message) {
        System.out.println(message);
        int userInput = input.nextInt();
        return userInput;
    }

    // EFFECTS: produces true if string has a non-zero length, else false
    private boolean checkNonZeroStringLength() {
        return false;
    }

    // EFFECTS: produces true if integer is [1, 5], else false
    private boolean checkIntegerBetweenOneAndFive() {
        return false;
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        for (String command : this.commands) {
            System.out.println("\t" + command.toLowerCase().charAt(0) + " -> " + command);
        }
    }

    public static void main(String[] args) {
        new QuizolingoApp();
    }
}
