import Parser.Loader;
import Run.Run;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: davide
 * Github Name: drigoni
 * Date: 06/12/17
 */

public class Main {
    public static void main(String[] args) {
        if(args.length > 0){
            // Load
            Loader loader = new Loader(args[0]);

            try {
                List<Run> listOfRun = loader.StartLoad();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            System.out.println("asdas");
        }else{
            System.out.println("Path not found");
        }
    }
}
