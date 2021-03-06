package InputOutput;

import RunObject.Run;
import RunObject.RunElement;
import RunObject.RunList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


/**
 * Author:  Davide Rigoni
 * Github Name: drigoni
 * Date: 06/12/17
 *
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
     * @param runs RunList of run object
     */
    public void Save(RunList runs) throws IOException {
        for(Run run: runs){
            this.Save(run);
        }
    }

    /**
     * Save the Run in the folder
     * @param run Run object
     * @param inTime TRUE implies that every RunElement are wrote when read
     *               FALSE implies that the all Elements are wrote in the end
     */
    public void Save(Run run, Boolean inTime ) throws IOException {
        String fileName = path.toString() + "/" + run.getName();
        FileWriter f = new FileWriter(fileName);
        PrintWriter wr = new PrintWriter(f);
        List<RunElement> runElements = run.sort();
        if(inTime) {
            for(int i=0; i < runElements.size(); i++){
                wr.println(runElements.get(i));
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
