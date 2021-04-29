import java.io.File;
import java.util.Scanner;

public class csv_combiner {
  public static void main(String[] args) throws Exception
  {
    // Add a single header at the top of the file.
    System.out.println("\"email_hash\",\"category\",\"filename\"");

    // Iterate through the command line arguments, i.e. the input files, calling appendFile.
    for (String arg : args) {
      appendFile(arg);
    }
  }

  /* appendFile: Appends the contents of each input file to the output, with the filename column filled. */
  public static void appendFile(String filePath) throws Exception {
    File file = new File(filePath);
    Scanner scanner = new Scanner(file);

    // Skip the header line of the current file.
    scanner.nextLine();

    // Print the original line, followed by a comma, followed by the filename with quotation marks around it.
    // Repeat for each line in the file.
    while(scanner.hasNextLine()) {
      System.out.println(scanner.nextLine() + "," + "\"" + trimFilePath(filePath) + "\"");
    }
  }

  /* trimFilePath: Trims the file path to contain just the basename, for the purpose of adding it to the filename column. */
  public static String trimFilePath(String filePathToTrim) {
    // Just return the original filename if it is already the basename.
    if(filePathToTrim.charAt(0) != '.') return filePathToTrim;

    // Iterate across the characters of the string in reverse until you find a front slash.
    int slashIndex = filePathToTrim.length() - 1;
    while(filePathToTrim.charAt(slashIndex) != '/') {
      slashIndex--;
    }
    // Every character after that makes up the basename. Return that substring.
    return filePathToTrim.substring(slashIndex + 1, filePathToTrim.length());
  }
}