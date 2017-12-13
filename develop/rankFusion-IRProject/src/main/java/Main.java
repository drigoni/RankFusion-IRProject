import InputOutput.Loader;
import InputOutput.LoaderAssesment;
import InputOutput.Writer;
import RankFusion.CombMAX;
import RankFusion.CombMNZ;
import RankFusion.CombSUM;
import RankFusion.ProbFuse;
import RunObject.AssessmentList;
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
            System.out.println("Loading assessments file");
            LoaderAssesment loaderAssessment = new LoaderAssesment(args[1]);
            AssessmentList assessmentList = null;
            try {
                assessmentList = loaderAssessment.StartLoad();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            System.out.println("Loading all files in the given folder");
            Loader loader = new Loader(args[0]);
            RunList runList = null;
            try {
                runList = loader.StartLoad();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            // Check if all files needed are loaded
            if(runList != null && assessmentList != null) {
                /*
                System.out.println("Rank fusion with CombMAX");
                CombMAX combMAX = new CombMAX();
                Run resCombMAX = combMAX.Fuse(runList, assessmentList);

                System.out.println("Rank fusion with CombSUM");
                CombSUM combSUM = new CombSUM();
                Run resCombSUM = combSUM.Fuse(runList, assessmentList);

                System.out.println("Rank fusion with CombMNZ");
                CombMNZ combMNZ = new CombMNZ();
                Run resCombMNZ = combMNZ.Fuse(runList, assessmentList);

                System.out.println("Rank fusion with ProbFuse");
                ProbFuse probFuse = new ProbFuse();
                Run resProbFuse = probFuse.Fuse(runList, assessmentList);

                Writer wr = new Writer(args[0]);
                wr.Save(resCombMAX);
                wr.Save(resCombSUM);
                wr.Save(resCombMNZ);
                */
            } else{
                System.out.println("all files needed are not loaded");
            }


        }else{
            System.out.println("Path not found");
        }
        System.out.println("----------- END -----------");
    }
}
