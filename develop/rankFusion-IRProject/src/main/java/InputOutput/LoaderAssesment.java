package InputOutput;

import RunObject.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Author: davide
 * Github Name: drigoni
 * Date: 06/12/17
 *
 * This class loads the assessments
 */
public class LoaderAssesment extends  AbsLoader{

    /**
     * Constructor
     * @param pathToFolderStr Path to file
     */
    public LoaderAssesment(String pathToFolderStr){
        super(pathToFolderStr);
    }

    /**
     * This class execute the parser of the files of the assessments
     * @return List of assessment
     */
    public AssessmentList StartLoad() throws FileNotFoundException {
        if(this.path.toString().endsWith(".txt")){
            return this.Load(this.path);
        }else{
            throw new FileNotFoundException("File .txt not found");
        }
    }

    /***
     * This class load a file
     * @param file File represents the assessment
     * @return  The AssessmentList object
     */
    private AssessmentList Load(File file) throws FileNotFoundException {
        String name = file.getName();
        List<AssessmentElement> elements = new ArrayList<AssessmentElement>();
        Scanner scan = new Scanner(file);
        while(scan.hasNextLine()) {
            String line = scan.nextLine();
            elements.add(this.LoadRow(line));
        }
        scan.close();
        return new AssessmentList(elements);
    }

    /**
     * This class load a row element given a line of the file
     * @param row row of the .txt file
     * @return AssessmentElement object
     */
    private AssessmentElement LoadRow(String row){
        String[] values = row.split(" ");
        return new AssessmentElement(values[0], values[1], values[2], values[3]);
    }
}
