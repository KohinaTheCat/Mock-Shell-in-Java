// **********************************************************
// Assignment2:
// Student1: Clara Chick
// UTORID user_name: chickcla
// UT Student #: 1005946764
// Author: Clara Chick
//
// Student2: Malhar Pandya
// UTORID user_name: pandyam8
// UT Student #: 1005893008
// Author: Malhar Pandya
//
// Student3: Sameer Khan
// UTORID user_name: khans295
// UT Student #: 1006104430
// Author: Sameer Khan
//
//
//
// Honor Code: I pledge that this program represents my own
// program code and that I have coded on my own. I received
// help from no one in designing and debugging my program.
// I have also read the plagiarism section in the course info
// sheet of CSC B07 and understand the consequences.
// *********************************************************

package commands;

import helpers.ErrorCheck;
import helpers.FileSystem;
import helpers.StandardError;
import interfaces.CommandInterface;

/**
 * Class Name: Cd This class contains the methods to change the user's current
 * working directory
 */
public class Cd implements CommandInterface {
  /**
   * Gets an instance of fileSystem so that the class can access the fileSystem
   */
  private static FileSystem fileSystem = FileSystem.getInstanceOfFileSystem();

  /**
   * Creates an instance of errorCheck so that the class can access the
   * fileSystem
   */
  private static ErrorCheck errorCheck = new ErrorCheck();

  /**
   * Changes the user's current working directory to path
   *
   * @param path Determines the path to the new working directory
   */
  protected void runCd(String path) {
    fileSystem.traverse(path);
  }

  /**
   * Checks if the path in arg[1] is a valid path. Send error to stdErr if
   * arg[1] is not a valid path, or returns output of the command.
   * 
   * @param arg Array of arguments
   * @return String Output of the Cd command for stdOut
   */
  public String check(String[] arg) {
    String path = arg[1];

    if (!errorCheck.dirExists(fileSystem, path)) {
      if (!path.contains("//") && errorCheck.fileExists(fileSystem, path)) {
        StandardError.errors.add("Error: specified path is not a directory\n");
        return "";
      }
      StandardError.errors.add("Error: Invalid directory: the directory " + path
          + " does not exist\n");
      return "";
    }
    runCd(path);

    return "";
  }
}

