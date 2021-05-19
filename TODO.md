# TODO
These are issues in Wiimmfi Watcher I am at least aware of. Please **DO NOT** submit a bug report on the Google Form or an issue here on GitHub if it is mentioned on this list, unless you found a way that the bug can crash the app.

## Completed For Next Release
* Lowered minimum SDK to 19 (Jelly Bean 4.4)
    * originally lowered to 16 (Jelly Bean 4.1), but after adding Fragment animations this had to be changed.
* Created the dark mode

## Working On
* The last player in the list has the potential to hide behind the refresh button
* Fixing lag when pressing the watch button
    * Working with threads should fix this
* Recent friend codes do not scroll like they should
    * Problem with ConstraintLayout probably, convert to a new Layout?

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
* The ability to save friend codes and name them, not just save recent friend codes
* Create a more feature rich settings and about page
    * manual dark mode toggles, clear recent codes button, etc.


