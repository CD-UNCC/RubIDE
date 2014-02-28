package rubide;

import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.util.regex.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class RubideFile {
   static String patternStr = "(.*)\\.(.+)";
   static Pattern namePattern = Pattern.compile(patternStr);
   
   File orig;
   String fileName;
   String fileExt;
   boolean newFile = false;
   
   public RubideFile(File orig) {
      this.orig = orig;
      
      try {
         Matcher m = namePattern.matcher(orig.getName());
         m.find();
         fileName = m.group(1);
         fileExt = m.group(2);
      }
      catch (java.lang.IllegalStateException e) {
         System.out.println("Could not match " + orig.getName());
         fileName = "";
         fileExt = "";
      }
   }
   
   public RubideFile() {
	   newFile = true;
   }
   
   public String read() { 
      String result;
      try { result = new Scanner(orig).useDelimiter("\\Z").next(); }
      catch (FileNotFoundException | NoSuchElementException e) { result = "File could not be read (" + fileName + ")"; }
      return result;
   }
   
   public void save(String[] lines) {	   
      try {
         PrintWriter output = new PrintWriter(getAbsolutePath());
         for (String line : lines)
            output.println(line);
         output.close();
      }
      catch (FileNotFoundException e) { System.out.println("Could not write"); }
   }
   
   public void save(String txt) { save(txt.split("\\\n")); }
   
   public String getFileName() { return fileName; }
   public String getFileExtension() { return fileExt; }
   public String getAbsolutePath() { return orig.getAbsolutePath(); }
   public boolean isNewFile() { return newFile; }
   
   public String toString() { return fileName + " (" + fileExt + ") [" + getAbsolutePath() + "]"; } 
      
}