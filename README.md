# Cross-patform WPILib

This is an attempt to make WPILib work on platforms besides the RoboRio, with native JNI methods implemented in Java.

## Stuff that works

* Basic joystick control from Driver Station

## How it's compiled

This project uses Gradle for compilation. Here is a brief overview of the tasks involved in `build.gradle`:

1. [WPILib](github.com/wpilibsuite/allwpilib) is cloned  and put in Gradle's build folder
2. A [Java AST generator](https://github.com/javaparser/javaparser) patches all  native methods, replacing them with ones that fulfill the  needed return types but return constants
3. Patches under the `patches` directory are copied into  the cloned folder, replacing files already there
4. The project is built

## Usage

If you want to test things out, run `gradlew run` (you don't need Gradle installed), then launch a driver station such as [QDriverStation](https://github.com/FRC-Utilities/QDriverStation) and point the robot ip to `127.0.0.1`.
