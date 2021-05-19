# This TODO list is out of date!
This list is not accurate. Instead, checkout the TODO list on the `dev` branch [here,](https://github.com/brysonsteck/wiimmfi-watcher/blob/dev/TODO.md) which is updated as development progresses. This file here on the `master` branch is here to show what progress is being/has been made at the time of each release.

# TODO
These are issues in Wiimmfi Watcher I am at least aware of. Please **DO NOT** submit a bug report on the Google Form or an issue here on GitHub if it is mentioned on this list, unless you found a way that the bug can crash the app.

## Completed For Next Release
* Lowered minimum SDK to 19 (Jelly Bean 4.4)
    * originally lowered to 16 (Jelly Bean 4.1), but after adding Fragment animations this had to be changed.
* Created the dark mode
* Recent friend codes now scroll like they should
* The last player in the list no longer hides behind the refresh button, if the list is long enough

## Working On
* Fixing lag when pressing the watch button
    * Working with threads should fix this

## Aware Of
* Fix the repeating recent friend codes
* The about page causes part of the screen to get cut off as the animation plays
* The navigation menu in Dark Mode is messed up
    * The selected player detail text and icon is black
    * The highlight color is barely visible

# Features I would like to add
* The watcher activity does not refresh automatically like the official website does
    * Added a refresh button, but is there a better way with Jsoup? Like a new Thread?
* Create a better looking header for the watcher activity
    * Add 'sections' I guess???
* Add the Mario Kart Wii font
* Add pictures for Nintendo and CTGP tracks
    * Google's policies might prevent this...
* The ability to save friend codes and name them, not just save recent friend codes
* Create a more feature rich settings and about page
    * manual dark mode toggles, clear recent codes button, etc.


