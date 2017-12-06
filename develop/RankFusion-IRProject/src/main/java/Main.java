/**
 * Author: davide
 * Github Name: drigoni
 * Date: 06/12/17
 */

import InputOutput.Loader;
import InputOutput.Writer;
import RunObject.Element;
import RunObject.Run;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Main of the program
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("----------- START -----------");
        if(args.length > 0){
            System.out.println("Start loading all files in the given folder");
            Loader loader = new Loader(args[0]);

            try {
                loader.StartLoad();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


        }else{
            System.out.println("Path not found");
        }
        System.out.println("----------- END -----------");
    }
}
