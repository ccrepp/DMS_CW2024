# **DMS_CW2024** - _Sky Battle_

## 1. About This Repository
- Repository Owner : Chan Chun Ren, 20469170

## 2. Compilation Instructions

## 3. Implemented and Working 
- Mentioned Utility Classes
- Mentioned Interface Classes
- NEW Levels

## 4. Implemented BUT NOT Working
- GameLoopManager
  - handles Game Looping and Level Transition logic
- LevelInitialiser
  - handles Level Initialisation logic

## 5. Features NOT Implemented

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
  - uses NEW 'bluelaser' image
  - only used with _XWing_
_TieFighter_
- NEW enemy plane
  - same logic as _EnemyPlane_
  - uses NEW 'tiefighter.png' image
_TieFighterProjectile_
- NEW projectile
  - same logic as _UserProjectile_
  - uses NEW 'greenlaser' image
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
    - uses NEW 'redlaser' image
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
- addition of Left-Right movement and alternative movement keys 'WASD'
- addition of **Supplier** function for plane initialisation
- removal of multiple method logics
  - moved to utility classes

**7.2.2** - _LevelOne_, _LevelSW_
- constructor modification

**7.2.3** - _LevelTwo_, _LevelISD_
- constructor modification

### 7.3 - Controller Class
- swapped out repeated exception handling with **showErrorAlert** method
- swapped out _Observer_ for _PropertyChangeListener_

## 8. Unexpected Problems

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
