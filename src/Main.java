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


        Path pastFolderUpd = Paths.get(pastFolder + "\\" + copyFolder.toFile().getName());
        pastFolderUpd.toFile().mkdir();  //  создаем папку корень (которую копируем)


        Files.walkFileTree(copyFolder, new SimpleFileVisitor<>() {
              @Override
           public FileVisitResult preVisitDirectory(Path currentDir, BasicFileAttributes attrs) throws IOException //preVisitDirectory – Invoked before a directory's entries are visited.
             {
                 if (attrs.isDirectory())
                 {pastFolderUpd.resolve(copyFolder.relativize(currentDir)).toFile().mkdir();}  // создаем папку в папке вставить
                 return FileVisitResult.CONTINUE;
             }


             @Override
            public FileVisitResult visitFile(Path currentFile, BasicFileAttributes attrs) throws IOException { //Invoked on the file being visited
                                                                                              // вычисляем путь между корнем (без корня) и текущей папкой
                Path finalPath = pastFolderUpd.resolve(copyFolder.relativize(currentFile)); // добавляем [путь между корнем (без корня) и текущей папкой] к [папке назначения + корень копируемой]
            
                try{ Files.copy(currentFile, finalPath, StandardCopyOption.REPLACE_EXISTING);}
                catch (IOException e) {e.printStackTrace();}
                return FileVisitResult.CONTINUE;

            }


        });

    }}}
















