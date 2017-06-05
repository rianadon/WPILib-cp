# Cross-patform WPILib

This is an attempt to make WPILib work on platforms besides the RoboRio, with native JNI methods implemented in Java.

NOTE: This project has no intentions of replacing WPILib, and if you use it instead of WPILib there will probably be a lot of things that will fail. It's more for if you want to use a platform on which WPILib is not supported (i.e. anything that doesn't have the architecture of the RoboRio as the NI libraries are distributed as shared libraries), because a semi-working WPILib is better than no WPILib.

For now (and very likely forever), this only supports Java.

## Stuff that works

* Joystick control from Driver Station (axes, buttons, POV hats)
* NetConsole (if you send a lot of stuff really quickly QDriverStation will drop messages)

## How it's compiled

This project uses Gradle for compilation. Here is a brief overview of the tasks involved in `build.gradle`:

1. [WPILib](github.com/wpilibsuite/allwpilib) is cloned  and put in Gradle's build folder
2. A [Java AST generator](https://github.com/javaparser/javaparser) patches all  native methods, replacing them with ones that fulfill the  needed return types but return constants
3. Patches under the `patches` directory are merged with the corresponding files in the src folder by [diffing](https://github.com/SQiShER/java-object-diff) the ASTs
4. The project is built

## Usage

If you want to test things out, run `gradlew run` (you don't need Gradle installed), then launch a driver station such as [QDriverStation](https://github.com/FRC-Utilities/QDriverStation) and point the robot ip to `127.0.0.1`.
