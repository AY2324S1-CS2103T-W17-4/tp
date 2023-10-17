---
layout: page
title: User Guide
---
# lesSON User Guide
lesSON is a **flashcard software aimed to help individuals with their memory work in school, optimized for use via a Command Line Interface (CLI)** while still having the benefits of a Graphical User Interface (GUI). If you can type fast, lesSON can get your contact management tasks done faster than traditional GUI apps.

# Table of Contents
1. [Feature List](#feature-list)
    - [Adding a FlashCard](#adding-a-flashcard-add)
    - [Deleting a Flashcard](#deleting-a-flashcard--delete)
    - [View All Flashcards](#view-all-flashcards--list)
    - [Editing a Specific Flashcard](#editing-a-specific-flashcard--edit)
    - [Practise Flashcards](#practise-flashcards-practise)

--------------------------------------------------------------------------------------------------------------------

## Feature List

### Adding a Flashcard `add`
Adds a flashcard to the deck for the user.

Format: `add q/question a/answer [t/TAG]​`

**Tip**: Tagging is not supported in v1.2 and earlier

Examples:

```
- add q/What are the three ways to implement binary systems? a/1s Complement, 2s Complement, and Sign and Magnitude

- add q/How do you convert from binary to 1s Complement? a/By inverting all the bits, i.e. 0 to 1 and vice versa t/CS2100 t/Number Systems
```

#### Acceptable values for each parameters:
1. No Empty Input after q/, a/ and t/.
#### Expected outputs:
```
1. add q/What are the three ways to implement binary systems? a/1s Complement, 2s Complement, and Sign and Magnitude
“Successfully added flashcard!” message will be returned to the user via the CLI

2. add q/What are the three ways to implement binary systems? a/
”Missing answer for flashcard” error message will be returned to the user via the CLI

3. add a/10111
”Missing question for flashcard” error message will be returned to the user via the CLI
```
#### MockUp
![mock up of add command](./images/UserGuide/mockup_add.png)


### Deleting a Flashcard : `delete`
Deletes a flashcard in the deck

Format: `delete INDEX`
Examples:
```
- delete 2
 (deletes the 2nd flashcard in the deck)
```

#### Acceptable values for each parameters:
1. Index must be positive integer
2. Index cannot exceed size of the deck

#### Expected outputs:
```
1. delete 2
“Successfully deleted flashcard!” message will be returned to the user via the CLI

2. delete -100
“Invalid input - input (<input>) is not an accepted value. Please enter an integer between 0 and deck.lenght()” message will be displayed on error.
```
#### Mockup:
![mock up of delete command](./images/UserGuide/mockup_delete.png)


### View All Flashcards : `list`
Shows a list of all flashcards in the deck.

Format: `list`
Examples:
```
(list shows the full list of flashcards.)
```

#### Acceptable values for each parameters:
No parameters are needed

#### Expected output:
```
1. list
   (Questions to all the flashcards that are added with their index)

2. list potato
   “No parameters are allowed for this command.”
```

#### Mockup
![mock up of list command](./images/UserGuide/mockup_list.png)

### Editing a Specific Flashcard : `edit`
Edits an existing person in the address book.

Format: `edit INDEX (q/a)/ (question/answer)`

Examples:
```
1. edit 1 q/ What is the colour of the sun?
   (changes the question at index 1 to “What is the colour of the sun?”)

2. edit 1 a/ Red
   (changes the answer at index 1 to “Red”)
```
#### Expected output:
```
“Successfully edited flashcard” message will be returned to the user via the CLI
“The card index provided is invalid"
```
#### Mockup:
![mock up of edit command](./images/UserGuide/mockup_edit.png)


### Practise Flashcards: `practise`
Practise a single Flashcard in the deck

Format: `practise INDEX d/ DIFFICULTY`

#### Acceptable values for each parameters:
1. Index must be positive integer
2. Index cannot exceed size of the deck
3. Difficulty can only be `easy`, `medium` or `hard`

#### Examples:
```
practise 1 d/ easy
(displays the answer of the card of index 1)
```
#### Expected outputs:
```
practise 1 d/ easy
"Answer: ans (Difficulty level: easy)"

practise 10 d/ easy
"The card index provided is invalid"
```

#### Mockup:
![mock up of practise command](./images/UserGuide/mockup_practise.png)
