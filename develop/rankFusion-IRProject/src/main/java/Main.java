import InputOutput.Loader;
import RankFusion.CombMAX;
import RankFusion.CombMNZ;
import RankFusion.CombSUM;
import RunObject.Run;
import RunObject.RunList;

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
            System.out.println("Loading all files in the given folder");
            Loader loader = new Loader(args[0]);
            try {
                RunList runList = loader.StartLoad();
                System.out.println("Rank fusion with CombSUM");
                CombMNZ combMNZ = new CombMNZ();
                Run res = combMNZ.Fuse(runList);
                System.out.println(res);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


        }else{
            System.out.println("Path not found");
        }
        System.out.println("----------- END -----------");
    }
}
