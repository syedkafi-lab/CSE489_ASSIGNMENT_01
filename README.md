# VangtiChai - CSE 489 Assignment 1

**Course**: CSE 489 - Mobile Application Development  
**Assignment**: Assignment 1 (VangtiChai Money Change Calculator)  
**Student Name**: Fahim Montasir  
**Student ID**: 23141013  
**Institution**: BRAC University  

---

## 📱 App Overview
**VangtiChai** is a native Android application designed to calculate change in Bangladeshi Taka notes (`500`, `100`, `50`, `20`, `10`, `5`, `2`, `1`) for any user-entered monetary amount.

The user inputs the amount using a numeric keypad (digits 0–9 and a CLEAR button) built from scratch using `ConstraintLayout`.

---

## ✨ Features & Requirements Implemented
1. **Custom Keypad**:
   - Built from scratch using `ConstraintLayout` with digit buttons (`0`–`9`) and `CLEAR`.
   - Prevents leading zeros (typing `0` when empty is ignored).
   - Maximum input length capped at 8 digits to prevent numeric overflow.
   - `CLEAR` button resets the input amount and change table.

2. **Greedy Change Breakdown**:
   - Calculates exact note breakdown for Taka denominations: `500, 100, 50, 20, 10, 5, 2, 1`.
   - Dynamic real-time updates as user taps keys.

3. **Responsive & Alternative Layouts**:
   - **Phone Portrait** (`res/layout/activity_main.xml`): Single column note list on the left, 3x4 keypad on the right.
   - **Phone Landscape** (`res/layout-land/activity_main.xml`): 2 columns of notes on the left, 4x3 keypad on the right.
   - **Tablet Portrait** (`res/layout-sw600dp/activity_main.xml`): Scaled spacing and typography for tablet screens.
   - **Tablet Landscape** (`res/layout-sw600dp-land/activity_main.xml`): Scaled spacing and 2-column note list + 4x3 keypad layout.

4. **Resource Management**:
   - Zero hardcoded dimension or text size values in layout XMLs.
   - All margins, paddings, button sizes, and font sizes are defined in resource files (`res/values/sizes.xml`, `res/values-land/sizes.xml`, `res/values-sw600dp/sizes.xml`, `res/values-sw600dp-land/sizes.xml`).

5. **State Preservation**:
   - Utilizes `MainViewModel`, `SavedStateHandle`, and Activity `onSaveInstanceState` / `savedInstanceState` to preserve entered amount and change breakdown seamlessly across orientation/configuration changes.

---

## 🧪 Tested Devices & Screen Sizes
The application layout and functionality were tested and verified on:
1. **Pixel XL Phone** (411 x 731 dp) - Portrait & Landscape
2. **Nexus 10 Tablet** (800 x 1280 dp) - Portrait & Landscape
3. **Pixel 7 Pro Phone** (412 x 892 dp) - Portrait & Landscape
4. **Nexus 5 Phone** (360 x 640 dp) - Portrait & Landscape
5. **Pixel Tablet** (800 x 1280 dp) - Portrait & Landscape

---

## 🛠️ Tech Stack & Architecture
- **Language**: Kotlin
- **Min SDK**: API 21 (Android 5.0 Lollipop) / Target SDK 35
- **Architecture**: MVVM (`MainViewModel`, `SavedStateHandle`, `LiveData`, `ViewBinding`, Activity lifecycle state restoration)
- **Layout Engine**: `ConstraintLayout`
- **Testing**: JUnit 4 & `InstantTaskExecutorRule` unit tests for `MainViewModel`
