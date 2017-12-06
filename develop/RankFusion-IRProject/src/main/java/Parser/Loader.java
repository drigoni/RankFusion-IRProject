/**
 * Author: davide
 * Github Name: drigoni
 * Date: 06/12/17
 */

package Parser;

import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.util.Scanner;

import Run.*;



/**
 * This class loads the data in the run files
 */
public class Loader {

    /**
     * This field represents the path to the folder or to the file
     */
    private File path = null;

    /**
     * Constructor
     * @param pathToFolderStr String represents the path to the folder or to the
     *                        file
     */
    public Loader(String pathToFolderStr){
        this.path = new File(pathToFolderStr);
    }

    /**
     * This class execute the parser of the files of the runs
     * @return List of Run
     */
    public List<Run> StartLoad() throws FileNotFoundException {
        List<Run> results = new ArrayList<Run>();
        if(this.path.isDirectory()) {
            List<File> files = this.OpenFolder(this.path);
            results = this.LoadAll(files);
        } else{
            results.add(this.Load(this.path));
        }

        return  results;
    }

    /***
     * This class finds all .res file in the folder
     * @param pathToFolder Path to folder
     * @return  List of files
     */
    public List<File> OpenFolder(File pathToFolder){
        List<File> results = new ArrayList<File>();

        File[] files = pathToFolder.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith(".res"); } // Extension of the run
        });

        for (File file : files) {
            if (file.isFile()) {
                results.add(file);
            }
        }

        return results;
    }

    /***
     * This class load all the files
     * @param list List of files
     * @return  List of run
     */
    public List<Run> LoadAll(List<File> list) throws FileNotFoundException {
        List<Run> results = new ArrayList<Run>();
        for(File file: list){
            results.add(this.Load(file));
        }
        return results;
    }

    /***
     * This class load a file
     * @param file File represents the run
     * @return  The run object
     */
    public Run Load(File file) throws FileNotFoundException {
        String name = file.getName();
        List<Element> elements = new ArrayList<Element>();
        Scanner scan = new Scanner(file);
        while(scan.hasNextLine()){
            String line = scan.nextLine();
            elements.add(this.LoadRow(line));
        }
        return new Run(name, elements);
    }

    /**
     * This class load a row element given a line of the file
     * @param row row of the .res file
     * @return Element object
     */
    private Element LoadRow(String row){
        String[] values = row.split(" ");
        return new Element(values[0], values[1], values[2], values[3],
                values[4], values[5]);
    }
}
