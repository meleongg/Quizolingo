# Quizolingo

> A study tool to help with language learning!

## About 
Quizolingo is a desktop application for **aspiring polyglots** to improve 
their world language proficiencies in a fun and interactive way.  

Some basic features _currently_ include:
- creating flashcards with a phrase, translation, and proficiency rating 
- removing flashcards by phrase
- viewing all the flashcards within a folder
- updating flashcard proficiency ratings by phrase

More advanced features _may_ include:
- multiple folders for different languages
- practice sessions using a random assortment of user-created flashcards
- timed practice sessions (5min, 10min, 20min, 30min)
- a way for the app to automatically edit the "proficiency" of each flashcard based on viewing frequency
- a greater variety of mini-games (e.g. matching)
- in-game shop with currency (_Quizos_?) (e.g. boosts, stickers, etc.) 

## Inspiration
Since the start of my first year at UBC, I've been challenging myself to improve
my French and Mandarin skills. Whenever I encountered a new word, I would jot it down
on a piece of paper. However, this piece of paper quickly became messy and illegible. So, I 
want to create a Java application to keep track of my learning
for me. I hope to never have to worry about running out of paper space ever again!

## User Stories
1. As a user, I want to be able to add a new flashcard to the language folder.
2. As a user, I want to be able to remove an existing flashcard in the language folder.
3. As a user, I want to be able to view a list of all the flashcards in the language folder.
4. As a user, I want to be able to update an existing flashcard's proficiency rating on a scale of one to five. 
5. As a user, I want to be able to save my folder with flashcards to file.
6. As a user, I want to be able to load my folder with flashcards from file. 

## Phase 4: Task 2
Mon Mar 28 18:12:48 PDT 2022
Flashcard with phrase: "anglais" added to folder!
Mon Mar 28 18:12:54 PDT 2022
Flashcard with phrase: "lardon" added to folder!
Mon Mar 28 18:13:00 PDT 2022
Flashcard with phrase: "anglais" removed from folder!
Mon Mar 28 18:13:02 PDT 2022
Flashcard with phrase: "lardon" removed from folder!

## Phase 4: Task 3
- refactor the QuizolingoAppGUI class into smaller subclasses, where each subclass represents a specific portion of the GUI, such as TitlePanel, LoadingScreenWindow, MenuPanel, etc.
  - many of the panels share similar BoxLayouts, so maybe I could create an abstract BoxPanel class and have all the main GUI panels extend it and override its behaviour as needed
  - refactor duplicated code for centering labels/panels into a helper method
  - refactor error-checking methods into a separate class (e.g. checkNonZeroStringLength())
  - refactor methods that create external JWindows into their own independent classes, such as the loading screen and the input form
- refactor the Folder class to store Flashcards in a HashSet instead of an ArrayList, so I don't need to worry about checking for duplicate flashcards when adding flashcards
