---
layout: page
title: Winson's Project Portfolio Page
---

### Project: lesSON

lesSON is a desktop flashcard application for Computing students in University who struggle with
memorisation and consolidation of knowledge. The user interacts with it using a CLI, and it has a
GUI created with JavaFX. It is written in Java.

Given below are my contributions to the project.

* **New Feature**: Deck and Cards Operations [#66](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/66):
  * What it does: Allow the user to perform the basic operations based on the Card class by taking in the
  parameters required,
  * Highlights: Users can now input add and edit commands based on the parameters specified by each
  command. 


* **New Feature**: Practise ([#71](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/71), [#92](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/92))
  * What it does: Allows users to look at the question of a specific card using its index.
  * Justification: It allows users to test his knowledge under exam condition.
  * Highlights: Practise allows users to access only the question of a flashcard, if command is entered without index, the flashcard at the top of the list will be practised.



* **New Feature**: Solve ([#92](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/92), [#95](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/95))
  * What it does: Allows user to look at the answer of a specific card  using its index.
  * Justification: It is the main practical function of a flashcard application for users to learn from his mistake
  * Highlights: Solve command reveals the answer to a flashcard to the users.


* **New Feature**: Set Difficulty [#95](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/95)
  * What it does: Allows user to set a specific difficulty level for a question of a flashcard using its index.
  * Justification: It is the main practical function of a flashcard application for users to evaluate the question
  solving process and the application and schedule their next practise based on the difficulty input.
  * Highlights: Users set a level of difficulty (easy, medium or hard) for a question using `set` command, the deck of card will then be rearranged accordingly.


* **New Feature** MarkDown Syntax [#117](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/117)
  * What it does: For advanced user or visual learners to stylised their flashcard, having ways to differentiate
  words by having different style can greatly enhance their learning process.
  * Justification: It serves as a good supplementary function to enhance to current revision process for user.
  * Highlights: Based on the syntax entered by the user, the corresponding styling will be reflected on
  the display card and the result view.


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=winson8222&breakdown=true)


* **Project management**:
  * Added CI Status Badge to README [#54](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/54)
  * Updated _config.yml remove ab3 traces [#56](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/56)


* **Enhancements to existing features**:
  * Card Class is changed to include difficulty attribute [#71](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/71)
  * Deck Class is created to contain a list of Cards [#66](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/66)
  * Enhanced DisplayResult and CardDisplay to support MarkDown syntax: bold, italic and underline. [#117](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/117)
  * Fixed bug where application does not start without existing JSON file [#71](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/71)
  * Created test cases to increase codecov to 65% (Pull Requests [#92](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/92), [#190](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/190), [#204](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/204), [#206](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/206))
  * Fixed bug where JSON format is wrong for the new JSON file created [#206](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/206)


* **Documentation**:
  * User Guide:
    * Added documentations and screenshots for the features `practise`, `solve` and `set` . [#111](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/111)
    * Added documentations for MarkDown Syntax feature. (Pull requests [#122](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/122), [#126](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/126/files),
    [#189](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/189))
    * Added labelled screenshots to describe the outcome of MarkDown syntaxes [#126](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/126/files)
    * Added notes to warn users to not stack MarkDown Syntax [#192](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/192/files)
  * Developer Guide:
    * Added documentation to explain the functions and design consideration of `practise`,
    `solve` and `set` and how they synergise with Spaced-Repetition. [#112](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/112)
    * Added extensions to use cases, to describe the cases when invalid parameters or incorrect command format is entered by the users. [#131](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/131)
    * Added Planed Enhancement to support Markdown Syntax stacking [#225](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/225)
  *  README:
    * Removed trace of ab3 and revamped README file for lesSON. [#50](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/50)


* **Community**:
  * Reviewed PR with Non-Trivial Comments
    * Removal of irrelevant test cases[#68](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/68)
    * Modifications of error messages [#74](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/74)
    * Adding NextPractiseDate and give advice to improve the feature further [#93](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/93)
    * Filtering function based on tag and question [#93](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/93)
  * Wrote further test cases of code implemented by other team member
  * Reported bugs and provide suggestions for other teams (Issues: [1](https://github.com/winson8222/ped/issues/1), [2](https://github.com/winson8222/ped/issues/2), [3](https://github.com/winson8222/ped/issues/3),[4](https://github.com/winson8222/ped/issues/4))


* **Tools**:
Updated README.md with CI Status Badge [#54](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/54)
