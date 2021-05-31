# This TODO list is out of date!
This list is not accurate. Instead, checkout the TODO list on the `dev` branch [here,](https://github.com/brysonsteck/wiimmfi-watcher/blob/dev/TODO.md) which is updated as development progresses. This file here on the `master` branch is here to show what progress is being/has been made at the time of each release.

# TODO
These are issues in Wiimmfi Watcher I am at least aware of. Please **DO NOT** submit a bug report on the Google Form or an issue here on GitHub if it is mentioned on this list, unless you found a way that the bug can crash the app.

## Completed For Release 1.1.3
* Added language translations for Korean, Portuguese and Spanish.
* Fixed a bug where the error message that appears does not disappear when clicking on a recent friend code.
* Added a new dialog between searching for a friend code and the watcher screen.
* Fixed a bug where the internal Java error on the watcher screen wouldn't show after pressing the refresh button at least once.
* Fixed a bug where the final button of the recent friend codes wouldn't show if it reached past the bottom of the screen, especially with devices that have the software navigation bar.
* Fixed a bug in the English version of the app where the "License" portion of the about page was not formatted correctly.

## Working On
* Create a better looking header for the watcher activity
    * Add 'sections' I guess???
* Adding the option to toggle dark mode manually so older devices can have that privledge as well

## Aware Of
* The about page causes part of the screen to get cut off as the animation plays (only if there are not a lot of recent friend codes present)
* The navigation menu in Dark Mode is messed up
    * The selected player detail text and icon is black
    * The highlight color is barely visible
* Pressing the watch or refresh buttons cause the app to hang until refreshing is completed.
    * Working with threads should fix this
    * Adding a dialog helps make it look like the app is working for now

# Features I would like to add
* The watcher activity does not refresh automatically like the official website does
    * Added a refresh button, but is there a better way with Jsoup? Like a new Thread?
* Add the Mario Kart Wii font
    * I'll have to see if I can pull it from the Wiimmfi website somehow
* Add pictures for Nintendo and CTGP tracks
    * Google's policies might prevent this...
* The ability to save friend codes and name them, not just save recent friend codes
* Create a more feature rich settings and about page
    * manual dark mode toggles, clear recent codes button, etc.
* Add the ability to search for a Mii name instead of a friend code
* Show statistics for other games
