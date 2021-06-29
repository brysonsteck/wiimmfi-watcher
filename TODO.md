# TODO
These are issues in Wiimmfi Watcher I am at least aware of. Please **DO NOT** submit a bug report on the Google Form or an issue here on GitHub if it is mentioned on this list, unless you found a way that the bug can crash the app.

### Completed For Release 1.1.4
* Added an updater to make sure subsequent releases are up to date
* Implement the updater into the UI

### Working On
* Create a better looking header for the watcher activity
    * Add 'sections' I guess???

### Aware Of
* The about fragment causes part of the screen to get cut off as the animation plays when returning to the main fragment (only if there are a few recent friend codes)
* The navigation menu in Dark Mode is messed up (colors)
    * The selected player detail text and icon is black
    * The highlight color is barely visible
* Adding the option to toggle dark mode manually so older devices can have that privledge as well

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
