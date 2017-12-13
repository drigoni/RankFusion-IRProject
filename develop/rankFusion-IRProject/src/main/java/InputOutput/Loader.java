package InputOutput;

import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.util.Scanner;
import RunObject.*;

/**
 * Author: davide
 * Github Name: drigoni
 * Date: 06/12/17
 *
 * This class loads the data in the run files
 */
public class Loader extends  AbsLoader{

    /**
     * Constructor
     * @param pathToFolderStr Path to folder
     */
    public Loader(String pathToFolderStr){
        super(pathToFolderStr);
    }

    /**
     * This class execute the parser of the files of the runs
     * @return RunList of Run
     */
    public RunList StartLoad() throws FileNotFoundException {
        RunList results = new RunList();
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
    private List<File> OpenFolder(File pathToFolder){
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
     * @return  RunList of run
     */
    private RunList LoadAll(List<File> list) throws FileNotFoundException {
        RunList results = new RunList();
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
    private Run Load(File file) throws FileNotFoundException {
        String name = file.getName();
        List<RunElement> elements = new ArrayList<RunElement>();
        Scanner scan = new Scanner(file);
        while(scan.hasNextLine()) {
            String line = scan.nextLine();
            elements.add(this.LoadRow(line));
        }
        scan.close();
        return new Run(name, elements);
    }

    /**
     * This class load a row element given a line of the file
     * @param row row of the .res file
     * @return RunElement object
     */
    private RunElement LoadRow(String row){
        String[] values = row.split(" ");
        return new RunElement(values[0], values[1], values[2], values[3],
                values[4], values[5]);
    }
}

