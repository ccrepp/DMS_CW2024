# **DMS_CW2024** - _Sky Battle_

## 1. About This Repository
- Repository Owner : Chan Chun Ren, 20469170

## 2. Compilation Instructions
### 2.1 - Prerequisites
- **Java Development Kit (JDK)** - Version 21 or later
- **Maven** - often bundled with Java-supporting IDEs
- **IntelliJ IDEA** - or any alternative IDE capable of Maven intregration
- **JavaFX Library** - latest version
### 2.2 - Cloning the Project
- **From Git** : using `git clone [repository URL]` to clone repository
- **Via ZIP** : download the repository to your device, then unzip the downloaded folder to a suitable location
### 2.3 - Importing Project to your IDE
- **1st-Timer** : 
1. Select "Open" / "Import"
2. Browse to Project Location
3. Choose Project's root directory and confirm
- **Existing User** : 
1. Go to `File > New > Project from Existing Sources...`
2. Browse to Project Location
3. Choose Project's root directory and confirm
### 2.4 - JDK, JavaFX and Maven
- **For JDK** :
  - go to your IDE's `File > Project Structure` and ensure Project SDK has been set to JDK 21
- **For JavaFX and Maven** : 
  - configuration should occur automatically upon `pom.xml` file read
  - if needed, manually update/download Maven dependencies and reload
### 2.5 - Compilation and Running _Sky Battle_
- in your IDE's run configurations, set it so that `Main.java` of this project is being targeted
- ensure that all other options have been set to an appropriate version (jdk21 and better)
- ensure working directory is correct
- `apply` -> `OK` -> `Run`

## 3. Implemented and Working 
- Utility Classes
- Interface Classes
- NEW Levels
- Screens

## 4. Implemented BUT NOT Working
- GameLoopManager
  - handles Game Looping and Level Transition logic
- LevelInitialiser
  - handles Level Initialisation logic

## 5. Features NOT Implemented
- Game Animations
  - when planning out my game, an idea I was highly interested in was to have animations such as spinning and explosions be part of my game
  - however, with each attempt to add this feature, the game's quality worsened, thus the eventual removal
- More Story Mode Branches
  - as my gameplay strongly features a Story Mode, where I attempted to recreate iconic scenes and plotlines from the Star Wars movie franchise within the levels, I was disheartened to not be able to fully flesh out the stories
  - the original plan was to have all 6 original movies be within the branches, leading to an original finale where a custom designed final boss will be waiting at the end, though the idea was then scrapped for the final 3 branches featuring the first 2 movies only
- Scoring System
  - drawing inspiration from games I've played, I wanted to implement a scoring system to the base game, but that idea was quickly scrapped as it felt redundant


## 6. NEW Java Classes
### 6.1 - Star Wars Additions

**6.1.1** - _XWing_ and _XWingProjectile_ , _TieFighter_ and _TieFighterProjectile_ classes
_XWing_
- NEW user controlled plane
  - same logic as _UserPlane_
  - uses NEW 'xwing.png' image
_XWingProjectile_
- NEW projectile
  - same logic as _UserProjectile_
  - uses NEW 'bluelaser.png' image
  - only used with _XWing_
_TieFighter_
- NEW enemy plane
  - same logic as _EnemyPlane_
  - uses NEW 'tiefighter.png' image
_TieFighterProjectile_
- NEW projectile
  - same logic as _UserProjectile_
  - uses NEW 'greenlaser.png' image
  - only used with _TieFighter_

**6.1.2** - _LevelSW_ class
- NEW level
  - same logic as _LevelOne_
  - uses NEW 'backgroundsw.jpg' image
  - showcases _XWing_ and _TieFighter_

**6.1.3** - _ISD_ and _ISDProjectile_ and _ISDShield_ classes
  - NEW boss plane
    - same logic as _Boss_
    - uses NEW 'ISD.png' image
  - NEW projectile
    - same logic as _BossProjectile_
    - uses NEW 'redlaser.png' image
    - only used with _ISD_
  - NEW shield
    - same logic as _ShieldImage_
    - uses NEW 'ISDShield.png' image

**6.1.4** - _LevelISD_ class
- NEW level
  - same logic as _LevelTwo_
  - uses NEW 'backgroundsw.jpg' image
  - showcases _ISD_, _ISDProjectile_ and _ISDShield_

### 6.2 - Refactoring Branch New Classes
**6.2.1** - _BaseBoss_
- NEW class to manage Boss and ISD
- holds all methods and initialisation needs of Boss and ISD

**6.2.2** - _ProjectileProduction_ and _ProjectileFactory_
- NEW classes to handle projectile production
  - _ProjectileFactory_ uses switch with details for projectiles for production
  - _ProjectileProduction_ acts as middleman between _ProjectileFactory_ and _Projectile_
- allowed for deletion of ALL other projectile classes
  - after moving variables and details over to _ProjectileFactory_
 
**6.2.3** - _PlaneFactory_
- NEW class for user controlled plane production
- allows for easier default AND custom plane initialisation

**6.2.4** - _ProjectileManager_
- NEW class to handle projectile logic from _LevelParent_
- allowed for deletion of projectile logic in _LevelParent_
  - _LevelParent_ utilises _ProjectileManager_ for projectile logic now

**6.2.5** - _KeyHandler_
- NEW class for key handling logic, taken from _LevelParent_
- allowed for deletion of key handling logic in _LevelParent_
  - _LevelParent_ utilises _KeyHandler_ for key handling logic now

**6.2.6** - _ActorManager_
- NEW class for actor managing, taking logic from _LevelParent_
- allowed for deletion of actor management logic in _LevelParent_
  - _LevelParent_ utilises _ActorManager_ for actor management logic now

**6.2.7** - _GameState_
- NEW class for game state update logic from _LevelParent_
- allowed for deletion of game state update logic in _LevelParent_
  - _LevelParent_ utilises _GameState_ for game state update logic now

**6.2.8** - Interfaces
- all used for abstract methods only, so no further explanation
- _Collidable_
- _Destructible_
- _GameStartEnd_
- _InitialiseActors_
- _Movable_
- _Shield_

### 6.3 - Addition-More Branch New Classes

**6.3.1** - Screens
- added Start, Pause, Transition, Win and Lose screens
- _Start_
  - displays at start-up, allows for entering of game
- _Pause_
  - allows for pausing of game via 'P' or 'ESC' key input
  - game can be resumed OR can exit game
- _Transition_
  - placeholders between levels
  - allows for storytelling
  - any key input or mouse click will proceed
- _Win_
  - displayed after Winning
  - allows for return to Start or Exit
- _Lose_
  - displayed after Losing
  - allows for return to Start or Exit

**6.3.2** - Story Mode
- continuing down the Star Wars route...
- _StoryMode_ is an alternative option from the main game, allowing players to immerse themselves into the world of Star Wars through recreations of the original movies
- the first 2 movies have been adapted into playable levels, letting players hop into the seats of the Star Wars ships and get in on the action

**6.3.2.1** - Star Wars EP1 : The Phantom Menace
- take to the skies on the N1SF as you join Naboo in their fight against the invasion!
- 3 NEW TPM levels
- NEW user-controlled plane : _N1SF_
- NEW enemies : _AAT_ and _VultureDroid_
- NEW boss : _TFS_ 

**6.3.2.2** - Star Wars EP2.1 : Attack of the Droids
- play as the bad guys as you take on the Jedi in the Attack of the Droids!
- 3 NEW AOTCd levels
- NEW users : _Slave1_, _BattleDroid_ and _B1Droid_
- NEW bosses : _ObiWanShip_ and _Padme_
  - NEW shield : _Anakin_

**6.3.2.3** - Star Wars EP2.2 : Attack of the Clones
- play as the Clones as you fight back against the large waves of droids!
- 4 NEW AOTCc levels
- NEW users : _ATTE_ and _RepublicGunship_
- NEW enemies : _HailfireDroid_ and _HomingSpiderDroid_

**6.3.3** - BGM and Projectile Audio
- added audio to the game, breathing some life into it
- created .mp3 audio resources and stored them in new audio directory within resources
- BGM
  - BGM.mp3
  - SWBGM.mp3
  - SWBossBGM.mp3
  - SWEpic.mp3
  - TPMBGM.mp3
  - JFvsOBW.mp3
  - GeonosisBGM.mp3
  - GeonosisBattle.mp3
- Projectile Audio
  - UserFire.mp3
  - BossFire.mp3
  - XWingFire.mp3
  - TieFighterFire.mp3
  - AATFire.mp3
  - ISDFire.mp3
  - Slave1Fire.mp3
  - BlasterFire1.mp3
  - BlasterFire2.mp3
  - ATTEFire.mp3
  - HailFire.mp3
  - SpiderFire.mp3
- attempts at being as accurate to the movies were conciously made
  - credits for all audio files to the original creators

## 7. Modified Java Classes
### 7.1 - Plane Classes
**7.1.1** - _Boss_
- addition of _ShieldImage_ properties and logic

**7.1.2** - _UserPlane_
- addition of Left-Right movement logic
- modifications to constructor to allow for dynamic hot-swapping

### 7.2 - Level Classes
**7.2.1** - _LevelParent_
- addition of **endGame()** method
  - to address 'screen freezing' issue amidst level transition
- addition of **Supplier** function for plane initialisation
- removal of multiple method logics
  - moved to utility classes

**7.2.2** - _Level_(s)
- constructor modified to accommodate new changes 

### 7.3 - Controller Class
- swapped out repeated exception handling with **showErrorAlert** method
- swapped out _Observer_ for _PropertyChangeListener_

## 8. Unexpected Problems
- JUnit difficulties
  - as testing of previous works have all been done manually, usage of such tests within the program itself was of a high difficulty and thus forgone
  - working on this would be a step to take going forward
- Time Management
  - due to scheduling conflicts, the amount of planning and actual doing did not correspond to each other
  - improvements on Time Management would be actively sought after
- Lacking in Java experience
  - as other programming languages are favoured, my interactions and experience with Java were lacking and much was learnt throughout the project
  - learning as I go allowed for an overall fruitful experience

## 9. Repository Timeline

### 9.1 - Merged First Branch (debug-and-fixes) on 23/11/2024
- made fixes to game's issues
- made additional movement supports

### 9.2 - Merged Second Branch (addition-sw) on 30/11/2024
- added NEW Star Wars themed levels and resources (images)
- made amendments to pre-existing classes to fit changes

### 9.3 - Merged Third Branch (refactoring) on 3/12/2024
- added and deleted many classes
- repackaged all java files
- further details all within modified and new classes

### 9.4 - Merged Fourth Branch (addition-more) on 13/12/2024
- addition of sounds to game
- addition of screens
- further addition of Star Wars levels and resources
