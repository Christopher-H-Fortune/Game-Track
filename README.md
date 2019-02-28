# Game Track #

### Bitbucket Repository Link ###

**Clone Link**
- https://Chris_Fortune@bitbucket.org/Chris_Fortune/game-track.git

**Repo Link**
- [Click here to view repo on BitBucket.org.](https://bitbucket.org/Chris_Fortune/game-track/src/master/)

### Installation Instructions ###

* To run Game Track, you just need to open the project and run the App on an Android Device or emulator connected to the internet. 

### Hardware Considerations ###

* Currently, Game Track is set to run at a minimum SDK Version 21 and a compile SDK Version 28.

### List of Known Bugs ###

* No known bugs.

### Where to Find Milestones ###

** Login Screen **

- Enter in email and password and pressing login will login the user.
- Pressing the signup button will take the user to the signup screen.

** Signup Screen **

- Allows user to create Game Track Account
- Asks for user's email.
- Asks for password twice to make sure they remember the passwords and don't have spelling mistake.
- Pressing the create account button will create the users account and login.

** Game List Screen **

- Displays the games the user has entered onto their account from the Firebase Database.
- Pressing plus action button sends the user to the add game screen.
- Selecting a game from the list sends the user to the Match List Screen.

** Add Game Screen **

- Allows the user to enter in a game object to store to the Firebase Database.
- Pressing save action button saves the game data to the user's account in the Firebase Database and sends them to the Games List Screen.

** Match List Screen ** 

- Plus action button to allow the user to go Add Match Screen.
- Displays the matches the user has entered in a list view.
- Allows the user select a match and view it's details.

** Add Match Screen ** 

- Allows user to enter in a match object to store to the Firebase Database of the selected game.
- Pressing save acition button allows the user to save the Match to the database and send the user back to the match list screen.

** Compare Match Screen **

- Allow user to select between two matches to compare their data.
- After selecting two matches, the user can press compare.

** Compare Match Details Screen **

- Displays the compared match data to the user.

** Career Screen **

- Displays the selected game, players carrer.(Total kills, assists, etc.)