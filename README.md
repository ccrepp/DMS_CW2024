# **DMS_CW2024** - _Sky Battle_

## 1. About This Repository
- Repository Owner : Chan Chun Ren, 20469170

## 2. Compilation Instructions

## 3. Implemented and Working 

## 4. Implemented BUT NOT Working

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

**7.2.2** - _LevelOne_
- constructor modification

**7.2.3** - _LevelTwo_
- constructor modification

### 7.3 - Controller Class
- swapped out repeated exception handling with **showErrorAlert** method

## 8. Unexpected Problems

## 9. Repository Timeline

### 9.1 - Merged First Branch (debug-and-fixes) on 23/11/2024
- made fixes to game's issues
- made additional movement supports
