# Buildup (Android) Collin Edition™ 

![](Aspose.Words.c50fcb7b-c095-45a8-9388-b20f2b25e37a.001.jpeg)
![](Aspose.Words.c50fcb7b-c095-45a8-9388-b20f2b25e37a.002.jpeg)
![](Aspose.Words.c50fcb7b-c095-45a8-9388-b20f2b25e37a.003.jpeg)

Real Life picture of Buildup Domino Game 

Note: it’s one of the few domino games that uses 2 different colored set of dominoes. 

## Bug Report 

The bugs detected include the location of the domino when listed in Help mode. When the domino location is listed in help mode, this may be incorrect. Another bug occurs sometimes when Human player goes back to back when scoring.  

Program execution 

This program was made using the Chipmunk Android Studio. The main executor is the Game.JAVA file. Any Android device running Android 7.0 (Nougat) or later (please note that if you do not have one you can use an emulator) will be able to run this game. All you have to do is go to settings, then about, and click on the build number 5-10 times. Finally, enable USB debugging. Now just plug your device into your computer and click run and you are all set. 
### Download the apk file in the repo and it should work assuming you are running android 7 (Nouget) or later
## Feature List 

- ### Missing Features 

![](Aspose.Words.c50fcb7b-c095-45a8-9388-b20f2b25e37a.004.png) Help mode sometimes doesn’t choose non double tile when it has one double tile 

left. 

- Extra features  

![](Aspose.Words.c50fcb7b-c095-45a8-9388-b20f2b25e37a.004.png) Clicked buttons have a red ripple effect to show what was clicked.

![](Aspose.Words.c50fcb7b-c095-45a8-9388-b20f2b25e37a.004.png) Exit game button requires long press so user doesn’t accidently click on it.

Data Structures Used 

- A vector of strings was the primary used data structure, and it was used for the following pieces: boneyard, hand, and stack. 
- A vector of Players was used in implementing the polymorphic behavior in the game. 

Classes 

- Round class (Handles the player models of the GUI) 

![](Aspose.Words.c50fcb7b-c095-45a8-9388-b20f2b25e37a.005.png)This class is responsible for all information about a round including who won the 

round, who won the tournament, saving the game (SERIALIZATION of a tournament), who goes first, setting up the round, setting up the tournament and  the GUI for playing a round. 

![](Aspose.Words.c50fcb7b-c095-45a8-9388-b20f2b25e37a.005.png)Compositions: 

- player\_list: Vector of player pointers so that players can interact in the round polymorphically 
- Game class (Handles the start of the GUI) 

![](Aspose.Words.c50fcb7b-c095-45a8-9388-b20f2b25e37a.005.png)This class is responsible for running the entire game; everything from the 

welcome menu, playing a new game/tournament, and loading in a game from a serialization file. These are some of the key variables used within the class:

- player\_list: Vector of player pointers so that players can interact in the round polymorphically. 
  - memory: Vector of strings used for reading in a SERIALIZATION file at each line and storing them.
- Domino class 

![](Aspose.Words.c50fcb7b-c095-45a8-9388-b20f2b25e37a.005.png)This class is responsible for handling all of the domino capabilities such as stacks, 

boneyards, and hands. In addition, it handles subsequent functions like determining if the domino can be placed at a location, or determining the value of the domino e.g w34 = white 7 pip domino. It uses 4 important variables that play a HUGE role in Buildup:

- bone\_yard: Vector of strings used for holding the tiles in the boneyard
- stack: Vector of strings used for holding the tiles in the stack
- hand: Vector of strings used for holding the tiles in the hand
- selected\_bone: String is used for holding the domino that was selected 
- Player class  

![](Aspose.Words.c50fcb7b-c095-45a8-9388-b20f2b25e37a.005.png)This class is the base class for each player. It handles all of the important player 

abilities like being able to call all of the important domino functions as well as  functions like being able to tally score, make a move(polymorphic), and  determining the legal placement of a domino.

![](Aspose.Words.c50fcb7b-c095-45a8-9388-b20f2b25e37a.005.png)Compositions:

- Table: Domino object used for the player which is able to interact with said dominoes.
- Human class  

![](Aspose.Words.c50fcb7b-c095-45a8-9388-b20f2b25e37a.005.png)This class is one of the two derived classes of the player class. It provides its own 

input validation (user taps input based on button clicking) for selection and making a move (polymorphism). All of the functions are from the player class. 

- Computer class  

![](Aspose.Words.c50fcb7b-c095-45a8-9388-b20f2b25e37a.005.png)This class is one of the two derived classes of the player class. It provides its own 

input validation (computer generates its own input) for selection and making a move (polymorphism). Most of the functions are from the player class, but computer also has ai\_decison function used for coming up with the domino selection (POLYMORPHIC) and will always return 2 (make a move since computer will ALWAYS play to win).

Log 

Since this project used a GUI, I completed this project twice; once in Eclipse (AKA ASCII) for the model M and again in Android for the view/controller VC to combine them to make an efficient MVC controlled program.  

- ASCII: 

![](Aspose.Words.c50fcb7b-c095-45a8-9388-b20f2b25e37a.004.png) 3/13/2023 Created an Eclipse Hello World project and attempted to wrap 

my head around the general rules of how Java worked in syntax. 5 hours 

![](Aspose.Words.c50fcb7b-c095-45a8-9388-b20f2b25e37a.004.png) 3/14/2023-3/15/2023 Translated the Domino, Player, Human, and 

Computer files from C++ to JAVA  5 hours each day  

![](Aspose.Words.c50fcb7b-c095-45a8-9388-b20f2b25e37a.004.png) 3/16/2023 Translated remaining files and did extensive testing to verify 

correctness of model took a total of 8 hours 

- GUI 

![](Aspose.Words.c50fcb7b-c095-45a8-9388-b20f2b25e37a.004.png) 3/17/2023 Created Hello World Android app and imported converted files 

to Android. Converted project and studied how Android programming works (messing around with different Android functions to get the hang of syntax). 8 hours 

![](Aspose.Words.c50fcb7b-c095-45a8-9388-b20f2b25e37a.004.png) 3/18/2023-3/21/2023 – Drew the GUI view via the xml files and was able 

to get the GUI to display the current state of the game (NOT INCLUDING AFTER MOVES AT THIS TIME).  Spent 4 hours each day 

![](Aspose.Words.c50fcb7b-c095-45a8-9388-b20f2b25e37a.004.png) 3/22/2023 Displays who goes first and why. 2 hours 

![](Aspose.Words.c50fcb7b-c095-45a8-9388-b20f2b25e37a.004.png) 3/23/2023-3/27/2023 Got the players to actually play (interacting with the 

(Round GUI)) and having the domino change its text and color to its new 

respective state to correctly match its model e.g: B06 on W05. 8 hours each day ![](Aspose.Words.c50fcb7b-c095-45a8-9388-b20f2b25e37a.004.png) 3/28/2023 Players now alternate up until a hand is empty then terminates. 

5 hours 

![](Aspose.Words.c50fcb7b-c095-45a8-9388-b20f2b25e37a.004.png) 3/29/2023-3/30/2023 Hand refills with next set of dominoes and scores are calculated, and the next hand is played up until a winner of a round is announced.  

Finally, dominoes now display an actual face image instead of a ASCII value like “B00”. 10 hours each day 

![](Aspose.Words.c50fcb7b-c095-45a8-9388-b20f2b25e37a.004.png) 3/31/2023 Help mode works when its human’s turn and computer now 

explains why it did what it did. 5 hours 

![](Aspose.Words.c50fcb7b-c095-45a8-9388-b20f2b25e37a.004.png) 4/1/2023 Saving a game works correctly along with giving the option to 

play another round or ending the tournament and announcing the winner. 4 hours 

![](Aspose.Words.c50fcb7b-c095-45a8-9388-b20f2b25e37a.004.png) 4/2/2023 Loading in a game now works correctly along with displaying 

the game state when the first human boneyard tile  = computer’s first boneyard tile and telling the user in this instance it will reset. 3 hours 

![](Aspose.Words.c50fcb7b-c095-45a8-9388-b20f2b25e37a.004.png) 4/3/2023 Added in spinner menu for serialization for the user to be able to 

select the desired file along with cleaning up any detected issues. 4 hours 

![](Aspose.Words.c50fcb7b-c095-45a8-9388-b20f2b25e37a.004.png) 4/4/2023 Documented code and cleaned up and added on the finishing 

touches. 9 hours  

Screen shots 

1. Main Menu

![](Aspose.Words.c50fcb7b-c095-45a8-9388-b20f2b25e37a.006.jpeg)

2. Round information so far

![](Aspose.Words.c50fcb7b-c095-45a8-9388-b20f2b25e37a.007.jpeg)

3. Computer player method

![](Aspose.Words.c50fcb7b-c095-45a8-9388-b20f2b25e37a.008.jpeg)

4. Help mode

![](Aspose.Words.c50fcb7b-c095-45a8-9388-b20f2b25e37a.009.jpeg)

5. Human input for stacking opponent’s stack (B46 was the played tile) 

![](Aspose.Words.c50fcb7b-c095-45a8-9388-b20f2b25e37a.010.jpeg)

6. Human input for stacking on its own stack 

![](Aspose.Words.c50fcb7b-c095-45a8-9388-b20f2b25e37a.011.jpeg)

7. Save game 

![](Aspose.Words.c50fcb7b-c095-45a8-9388-b20f2b25e37a.012.jpeg)

8. Load game 

![](Aspose.Words.c50fcb7b-c095-45a8-9388-b20f2b25e37a.013.jpeg)

9. When Computer player can’t make any moves 

![](Aspose.Words.c50fcb7b-c095-45a8-9388-b20f2b25e37a.014.jpeg)

10. Which player won

![](Aspose.Words.c50fcb7b-c095-45a8-9388-b20f2b25e37a.015.jpeg)

11. Which player won the tournament 

![](Aspose.Words.c50fcb7b-c095-45a8-9388-b20f2b25e37a.016.jpeg)
