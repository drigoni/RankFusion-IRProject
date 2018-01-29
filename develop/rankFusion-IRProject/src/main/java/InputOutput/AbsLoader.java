package InputOutput;

import java.io.File;

/**
 * Author:  Davide Rigoni
 * Github Name: drigoni
 * Date: 13/12/17
 *
 * This abstract class represents the function of the loader
 */
public class AbsLoader {

    /**
     * This field represents the path to the folder or to the file
     */
    protected File path = null;

    /**
     * Constructor
     * @param pathToFolderStr Path to folder
     */
    public AbsLoader(String pathToFolderStr){
        this.path = new File(pathToFolderStr);
    }
}
