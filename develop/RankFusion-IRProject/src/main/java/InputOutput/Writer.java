/**
 * Author: davide
 * Github Name: drigoni
 * Date: 06/12/17
 */

package InputOutput;


import RunObject.Element;
import RunObject.Run;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * This class saves a RunObject
 */
public class Writer {

    /**
     * This field represents the path to the folder
     */
    private File path = null;

    /**
     * Constructor
     * @param pathToFolderStr String represents the path to the folder
     */
    public Writer(String pathToFolderStr){
        this.path = new File(pathToFolderStr);
        if(!this.path.isDirectory()){
            this.path = this.path.getParentFile();
        }
    }

    /**
     * Save the list of Run in the folder
     * @param runs List of run object
     */
    public void Save(List<Run> runs) throws IOException {
        for(Run run: runs){
            this.Save(run);
        }
    }

    /**
     * Save the Run in the folder
     * @param run Run object
     * @param inTime TRUE implies that every Element are wrote when read
     *               FALSE implies that the all Elements are wrote in the end
     */
    public void Save(Run run, Boolean inTime ) throws IOException {
        String fileName = path.toString() + "/" + run.getName();
        FileWriter f = new FileWriter(fileName);
        PrintWriter wr = new PrintWriter(f);
        if(inTime) {
            for(int i=0; i < run.getSize(); i++){
                wr.println(run.getElement(i));
            }
        } else{
            wr.print(run);
        }
        wr.close();
    }

    /**
     * Save the Run in the folder with the param inTime=FALSE
     * @param run Run object
     */
    public void Save(Run run) throws IOException {
        this.Save(run, Boolean.FALSE);
    }

}
