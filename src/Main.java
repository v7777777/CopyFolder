import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Scanner;


public class Main {


    public static void main(String[] args) throws IOException {

        for (; ; ) {

            System.out.println("please type address of folder to copy");
            Scanner scannerCopy = new Scanner(System.in);
            Path copyFolder = Paths.get(scannerCopy.nextLine());
            System.out.println("please type address of folder to past");
            Scanner scannerPast = new Scanner(System.in);
            Path pastFolder = Paths.get(scannerPast.nextLine());


       if (pastFolder.equals(copyFolder.resolve(copyFolder.relativize(pastFolder)))) {
           System.out.println("copy folder inside itself is not possible"); continue;   }

        Path pastFolderUpd = Paths.get(pastFolder + "\\" + copyFolder.toFile().getName());
        pastFolderUpd.toFile().mkdir();


           Files.walkFileTree(copyFolder, new SimpleFileVisitor<>() {
              @Override
           public FileVisitResult preVisitDirectory(Path currentDir, BasicFileAttributes attrs) throws IOException
             {

                 pastFolderUpd.resolve(copyFolder.relativize(currentDir)).toFile().mkdir();
                 return FileVisitResult.CONTINUE;
             }


             @Override
            public FileVisitResult visitFile(Path currentFile, BasicFileAttributes attrs) throws IOException {

                Path finalPath = pastFolderUpd.resolve(copyFolder.relativize(currentFile));

                try{ Files.copy(currentFile, finalPath, StandardCopyOption.REPLACE_EXISTING);}
                catch (IOException e) {e.printStackTrace();}
                return FileVisitResult.CONTINUE;

            }


        });

    }



    }}
















