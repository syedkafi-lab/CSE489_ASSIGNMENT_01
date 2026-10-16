================================================================================
CSE 489: Mobile Application Development - Assignment 1
Application Name: VangtiChai (Money Change Breakdown Calculator)
Student Name: Fahim Montasir
Student ID: 23141013
Department of Computer Science and Engineering, BRAC University
================================================================================

1. APP OVERVIEW & OBJECTIVE
---------------------------
VangtiChai is a native Android application built to calculate monetary change
in Bangladeshi Taka note denominations (500, 100, 50, 20, 10, 5, 2, 1) for any
entered money amount. 

The application utilizes a custom numeric keypad (0-9 and CLEAR) built from
scratch using ConstraintLayout, adhering to responsive multi-screen design
best practices and strict Android lifecycle state management across orientations.

2. REQUIREMENTS & FEATURES IMPLEMENTED
--------------------------------------
a) Custom Keypad:
   - Implemented from scratch using ConstraintLayout with MaterialButton widgets.
   - Digits 0 through 9 and a dynamic CLEAR button spanning two columns.
   - Prevents invalid leading zeros (typing '0' when amount is empty is ignored).
   - Maximum input length capped at 8 digits to protect against numeric overflow.
   - Tapping 'CLEAR' completely resets the input amount and change table to zero.

b) Real-time Greedy Change Breakdown:
   - Automatically computes and breaks down the amount into Bangladeshi Taka notes:
     500, 100, 50, 20, 10, 5, 2, 1.
   - As digits are appended from the right (e.g., typing '2', '3', '4' produces 2,
     then 23, then 234), note counts update instantly.

c) Alternative Layouts for Phones and Tablets (4 Distinct Configurations):
   - Phone Portrait (res/layout/activity_main.xml):
     Horizontally centered Taka display at the top, vertical note listing on the
     left, and 3x4 keypad on the right vertically centered relative to the notes.
   - Phone Landscape (res/layout-land/activity_main.xml):
     Horizontally centered Taka display at the top, 2-column note listing on the
     left (500, 100, 50, 20 and 10, 5, 2, 1), and 4x3 keypad on the right.
   - Tablet Portrait (res/layout-sw600dp/activity_main.xml):
     Tailored typography and scaled margins/paddings for 7-inch to 10-inch tablets.
   - Tablet Landscape (res/layout-sw600dp-land/activity_main.xml):
     2-column note listing and 4x3 keypad layout scaled proportionally for wide
     tablet displays.

d) Zero Hardcoding in Layout XMLs (sizes.xml):
   - In strict compliance with the assignment specification:
     All text sizes, paddings, button dimensions, insets, and margin values for all
     layout alternatives are defined in sizes.xml in the respective values folders:
       * res/values/sizes.xml
       * res/values-land/sizes.xml
       * res/values-sw600dp/sizes.xml
       * res/values-sw600dp-land/sizes.xml
   - All string literals and digit texts are externalized in res/values/strings.xml.

e) State Preservation & Lifecycle Handling:
   - Uses MVVM architecture with MainViewModel and SavedStateHandle.
   - Additionally implements onSaveInstanceState and onCreate Bundle restoration
     in MainActivity to provide dual-layer state preservation.
   - Entered amount and change breakdown are preserved seamlessly across device
     orientation changes (Portrait <-> Landscape) and activity recreation.

3. TESTED DEVICES & SCREEN SIZES
--------------------------------
The application was tested and verified across the following device screen sizes
and orientations:

1. Pixel XL (Phone)
   - Screen Specification: 411 x 731 dp (1440 x 2560 px @ 560 dpi)
   - Tested Orientations: Portrait & Landscape
   - Result: Perfectly aligned, layout switches between single-column notes (3x4 keypad)
     and two-column notes (4x3 keypad).

2. Nexus 10 (Tablet)
   - Screen Specification: 800 x 1280 dp (2560 x 1600 px @ 320 dpi, sw800dp)
   - Tested Orientations: Portrait & Landscape
   - Result: Automatically resolves layout-sw600dp and layout-sw600dp-land with scaled
     sizes and typography.

3. Pixel 7 Pro (Large Phone)
   - Screen Specification: 412 x 892 dp (1440 x 3120 px @ 560 dpi)
   - Tested Orientations: Portrait & Landscape
   - Result: Flawless layout scaling and responsiveness.

4. Nexus 5 / Pixel 4a (Standard Phone)
   - Screen Specification: 360 x 640 dp / 393 x 851 dp
   - Tested Orientations: Portrait & Landscape
   - Result: Compact fit without overlap or clipping.

5. Pixel Tablet (11-inch Tablet)
   - Screen Specification: 800 x 1280 dp (sw800dp)
   - Tested Orientations: Portrait & Landscape
   - Result: Crisp presentation and centered views.

4. BUILD & DEPLOYMENT INSTRUCTIONS
----------------------------------
To build and test the project:

- Build Debug APK:
  .\gradlew.bat assembleDebug
  Output APK: app/build/outputs/apk/debug/app-debug.apk

- Run Unit Tests:
  .\gradlew.bat test
  Runs JUnit 4 tests for MainViewModel (change calculation, leading zeros,
  digit input, and clear functionality).

- Run Android Lint:
  .\gradlew.bat lintDebug
  0 errors, 0 warnings.
================================================================================
