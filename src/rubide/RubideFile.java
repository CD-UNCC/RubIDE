package rubide;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.regex.*;
import java.util.Scanner;
import java.lang.IllegalStateException;

public class RubideFile {
   static String patternStr = "(.*)\\.(.+)";
   static Pattern namePattern = Pattern.compile(patternStr);
   

   File orig;
   String fileName;
   String fileExt;
   
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
   
   public String read() { 
      String result;
      try { result = new Scanner(orig).useDelimiter("\\Z").next(); }
      catch (FileNotFoundException e) { result = "File could not be read"; }
      return result;
   }
   
   public String getFileName() { return fileName; }
   public String getFileExtension() { return fileExt; }
   public String getAbsolutePath() { return orig.getAbsolutePath(); }
   
   public String toString() { return fileName + " (" + fileExt + ") [" + getAbsolutePath() + "]"; } 
      
}