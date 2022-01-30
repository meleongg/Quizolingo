package ui;

import model.Folder;
import model.Flashcard;

import java.util.List;
import java.util.Scanner;

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
    private Scanner scanner;
    private List<String> commands;

    // EFFECTS: intializing a new scanner, processing user input functionality,
    //          a list of available commands, and a new language folder
    public QuizolingoApp() {

    }

    // REQUIRES: user input to be one of the strings listed in commands
    // EFFECTS: processes the users input and executes the command
    private void processCommand() {

    }

    // EFFECTS: obtains the user's string input
    private String getStringInput() {
        return "";
    }

    // EFFECTS: obtains the user's integer input
    private Integer getIntegerInput() {
        return 0;
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
        // stub
    }

    public static void main(String[] args) {
        new QuizolingoApp();
    }
}
