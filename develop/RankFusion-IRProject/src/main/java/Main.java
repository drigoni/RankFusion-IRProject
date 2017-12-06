import InputOutput.Loader;
import java.io.FileNotFoundException;

/**
 * Author: davide
 * Github Name: drigoni
 * Date: 06/12/17
 *
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
