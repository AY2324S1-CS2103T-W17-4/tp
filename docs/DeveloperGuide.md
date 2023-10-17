---
layout: page
title: Developer Guide
---

# Table of Contents
1. [Acknowledgements](Acknowledgements)
2. [Setting up, getting started](#Setting up,getting started)
3. [Design](#Design)
4. [Implementation](#Implementation)
5. [Documentation, logging, testing, configuration, dev-ops](#Documentation,logging,testing,configuration,dev-ops)
6. [Appendix: Requirements](#Appendix:Requirements)
7. [Appendix: Instructions for manual testing](#Appendix:Instructions for manual testing)

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

* This project is a **part of the se-education.org** initiative. If you would like to contribute code to this project, see [se-education.org](https://se-education.org#https://se-education.org/#contributing) for more info.

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document `docs/diagrams` folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.
</div>

### Architecture

<img src="images/ArchitectureDiagram.png" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** (consisting of classes [`Main`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/MainApp.java)) is in charge of the app launch and shut down.
* At app launch, it initializes the other components in the correct sequence, and connects them up with each other.
* At shut down, it shuts down the other components and invokes cleanup methods where necessary.

The bulk of the app's work is done by the following four components:

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete 1`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<img src="images/ComponentManagers.png" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `CardListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Card` object residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

The sequence diagram below illustrates the interactions within the `Logic` component, taking `execute("delete 1")` API call as an example.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it is passed to an `DeckParser` object which in turn creates a parser that matches the command (e.g., `DeleteCommandParser`) and uses it to parse the command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `DeleteCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to delete a card).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `DeckParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `AddressBookParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/model/Model.java)

<img src="images/ModelClassDiagram.png" width="450" />


The `Model` component,

* stores the address book data i.e., all `Card` objects (which are contained in a `UniqueCardList` object).
* stores the currently 'selected' `Card` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Card>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `Deck`, which `Card` references. This allows `Deck` to only require one `Tag` object per unique tag, instead of each `Card` needing their own `Tag` objects.<br>

<img src="images/BetterModelClassDiagram.png" width="450" />

</div>


### Storage component

**API** : [`Storage.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/storage/Storage.java)

<img src="images/StorageClassDiagram.png" width="550" />

The `Storage` component,
* can save both deck data and user preference data in JSON format, and read them back into corresponding objects.
* inherits from both `DeckStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**:

* is a Computer Science University student in NUS
* wishes to excel in their studies
* requires a learning aid that enforces their understanding and memorisation of the content taught
* may take several courses at once and each course is expected to have multiple topics/concepts
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**: For Computing students in University who struggle with memorisation and consolidation of knowledge, our app provides users the ability to create categorised flashcards to organise concepts taught to them fast. Optimised for CLI and users who type fast.


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                                | I want to …​                                                                                    | So that I can…​                                  |
|----------|----------------------------------------|-------------------------------------------------------------------------------------------------|--------------------------------------------------|
| `* * *`  | new user                               | create a new flashcard                                                                          | start adding content                             |
| `* * *`  | user                                   | edit the content of an existing flashcard                                                       | update my study materials                        |
| `* * *`  | user                                   | delete a flashcard                                                                              | remove irrelevant content                        |
| `* * *`  | user                                   | view a list of all my flashcards                                                                | quickly access my study materials                |
| `* * *`  | user                                   | organise my flashcards with tags                                                                | manage my study materials efficiently            |
| `*`      | user                                   | search for a specific flashcard                                                                 | edit the card accordingly                        |
| `* *`    | user                                   | sort my flashcards by date created or modified                                                  | view them in an organised manner                 |
| `*`      | user who learns better with pictures   | add images to my flashcards                                                                     | enhance my learning experience                   |
| `* * *`  | user who wants to update my flashcards | modify answers to my flashcard                                                                  | make sure that my content is up to date          |
| `* * *`  | user                                   | practise with my flashcards                                                                     | test my knowledge                                |
| `* *`    | user                                   | shuffle the order of my flashcards during practice                                              | avoid memorising in a fixed sequence             |
| `* * *`  | user                                   | mark a flashcard as "learnt" during practice                                                    | track my progress                                |
| `*`      | user                                   | see statistics on my practice sessions, such as the number of cards reviewed and the time spent |                                                  |
| `* * *`  | user                                   | tag my flashcards with course IDs and coursework                                                | easily categorise them by subject                |
| `* * *`  | user                                   | tag my flashcards with the type of concept (e.g., definitions, formulas, field)                 | filter them for specific study needs             |
| `* *`    | user                                   | can change my tag colours                                                                       | personalise my studying methods                  |
| `* *`    | user                                   | enable spaced repetition for my flashcards                                                      | optimise my memorization process                 |
| `* *`    | user                                   | adjust the spacing intervals for spaced repetition                                              | personalise my learning experience               |
| `* *`    | diligent user                          | review flashcards due for repetition                                                            | reinforce my memory                              |
| `* *`    | experienced user                       | format the text on my flashcards using Markdown or a similar markup language                    | create personalised rich content                 |
| `*`      | experienced user                       | preview the formatted text on my flashcards                                                     | ensure it appears as intended                    |
| `* *`    | picky user                             | customise the app's colour scheme and interface                                                 | personalise my learning environment              |
| `* *`    | user                                   | reset my progress and start fresh with my flashcards                                            | can reattempt my studies                         |
| `* *`    | user                                   | export my flashcards to a file                                                                  | back up my data or share it with others          |
| `* *`    | user                                   | import flashcards from an external file                                                         | easily migrate or collaborate on study materials |
| `* * *`  | user                                   | access a user guide                                                                             | learn how to use the app effectively             |
| `* *`    | user                                   | receive notifications to remind me of my study sessions and flashcard reviews                   |                                                  |
| `* *`    | user                                   | set daily or weekly study goals                                                                 | track my progress toward my learning objectives  |
| `*`      | user                                   | provide feedback or report issues to the app developers                                         | contribute to app improvement                    |
| `*`      | multilingual user                      | input non-english characters                                                                    | study in other languages                         |

### Use cases

(For all use cases below, the **System** is `lesSON` and the **Actor** is the `user`, unless specified otherwise)

**Use case: UC01 Creating a card**

**MSS:**

1. User inputs command to create a card, along with the necessary details.
2. lesSON displays a success message.

   Use case ends.

**Use case: UC02 deleting a card**

**MSS:**

1. User displays all cards (UC04).
2. User inputs command to delete a card, along with the index of the card.
3. lesSON displays a success message.

   Use case ends.


**Use case: UC03 viewing a card**

**MSS:**

1. User displays all cards (UC04).
2. User inputs command to view a card, along with the index of the card.
3. lesSON displays the selected card.

   Use case ends.


**Use case: UC04 displaying all cards**

**MSS:**

1. User inputs command to view all cards.
2. lesSON displays all cards.

   Use case ends.


**Use case: UC05 editing a card**

**MSS:**

1. User displays all cards (UC04).
2. User inputs command to edit a card, along with the necessary details.
3. lesSON displays a success message.

   Use case ends.


*{More to be added}*

### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  Should be able to hold up to 1000 flashcards without a noticeable sluggishness in performance for typical usage.
3.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.

*{More to be added}*

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Flashcard**: A two-sided card containing a question and an answer
* **Tag**: A label used to categorise flashcards

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   1. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

1. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

1. _{ more test cases …​ }_

### Deleting a person

1. Deleting a person while all persons are being shown

   1. Prerequisites: List all persons using the `list` command. Multiple persons in the list.

   1. Test case: `delete 1`<br>
      Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.

   1. Test case: `delete 0`<br>
      Expected: No person is deleted. Error details shown in the status message. Status bar remains the same.

   1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

1. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

   1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_
