# Woody project

This is a project built from a greenfield Java project named after the Java mascot, _Duke_. Given below are instructions
on how to use it.

## Setting up in Intellij

Prerequisites: JDK 17, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project
   first)
1. Open the project into Intellij as follows:
    1. Click `Open`.
    1. Select the project directory, and click `OK`.
    1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 17** (not other versions) as explained
   in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
1. After that, locate the `src/main/java/Woody.java` file, right-click it, and choose `Run Woody.main()` (if the code
   editor is showing compile errors, try restarting the IDE).

**Warning:** Keep the `src\main\java` folder as the root folder for Java files (i.e., don't rename those folders or move
Java files to another folder outside of this folder path), as this is the default location some tools (e.g., Gradle)
expect to find Java files.

## Acknowledgement

- Woody.png - Image sourced from Flaticon
  [here](https://www.flaticon.com/free-icon/cowboy_2144115?term=cowboy&page=1&position=34&origin=tag&related_id=2144115)
- User.png - Image sourced from Flaticon
  [here](https://www.flaticon.com/free-icon/boy_1999625?term=young&page=1&position=10&origin=tag&related_id=1999625)

