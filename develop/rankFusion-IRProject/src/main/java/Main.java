import InputOutput.Loader;
import InputOutput.LoaderAssesment;
import InputOutput.Writer;
import Normalize.Standard;
import RankFusion.*;
import RunObject.AssessmentList;
import RunObject.Run;
import RunObject.RunList;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Author: Davide Rigoni, Alex Beccaro
 * Github Name: drigoni, abeccaro
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

            Writer wr = new Writer(args[0]);



            // Check if all files needed are loaded
            if(runList != null && assessmentList != null) {
                // Normalization
                System.out.println("Standard Normalization");
                Standard std = new Standard();
                std.execute(runList);

                // Make Rank Fusion
                System.out.println("Rank fusion with CombMAX");
                CombMAX combMAX = new CombMAX();
                Run resCombMAX = combMAX.Fuse(runList, assessmentList);
                System.out.println("Save fusion as CombMAX.res");
                try {
                    wr.Save(resCombMAX, true);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                System.out.println("Rank fusion with CombSUM");
                CombSUM combSUM = new CombSUM();
                Run resCombSUM = combSUM.Fuse(runList, assessmentList);
                System.out.println("Save fusion as CombSUM.res");
                try {
                    wr.Save(resCombSUM, true);
                } catch (IOException e) {
                    e.printStackTrace();
                }


                System.out.println("Rank fusion with CombMIN");
                CombMIN combMIN = new CombMIN();
                Run resCombMIN = combMIN.Fuse(runList, assessmentList);
                System.out.println("Save fusion as CombMIN.res");
                try {
                    wr.Save(resCombMIN, true);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                System.out.println("Rank fusion with CombMNZ");
                CombMNZ combMNZ = new CombMNZ();
                Run resCombMNZ = combMNZ.Fuse(runList, assessmentList);
                System.out.println("Save fusion as CombMNZ.res");
                try {
                    wr.Save(resCombMNZ, true);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                System.out.println("Rank fusion with CombANZ");
                CombANZ combANZ = new CombANZ();
                Run resCombANZ = combANZ.Fuse(runList, assessmentList);
                System.out.println("Save fusion as CombANZ.res");
                try {
                    wr.Save(resCombANZ, true);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //--------------------------------------------------------------
                //------------------------- ProbFuse ---------------------------
                //--------------------------------------------------------------

                System.out.println("Rank fusion with ProbFuse20");
                ProbFuse probFuse20 = new ProbFuse(20);
                Run resProbFuse20 = probFuse20.Fuse(runList, assessmentList);
                System.out.println("Save fusion as ProbFuse20.res");
                try {
                    wr.Save(resProbFuse20, true);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                System.out.println("Rank fusion with ProbFuse50");
                ProbFuse probFuse50 = new ProbFuse(50);
                Run resProbFuse50 = probFuse50.Fuse(runList, assessmentList);
                System.out.println("Save fusion as ProbFuse50.res");
                try {
                    wr.Save(resProbFuse50, true);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                System.out.println("Rank fusion with ProbFuse70");
                ProbFuse probFuse70 = new ProbFuse(70);
                Run resProbFuse70 = probFuse70.Fuse(runList, assessmentList);
                System.out.println("Save fusion as ProbFuse70.res");
                try {
                    wr.Save(resProbFuse70, true);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                System.out.println("Rank fusion with ProbFuse90");
                ProbFuse probFuse90 = new ProbFuse(90);
                Run resProbFuse90 = probFuse90.Fuse(runList, assessmentList);
                System.out.println("Save fusion as ProbFuse90.res");
                try {
                    wr.Save(resProbFuse90, true);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                System.out.println("Rank fusion with ProbFuse110");
                ProbFuse probFuse110 = new ProbFuse(110);
                Run resProbFuse110 = probFuse110.Fuse(runList, assessmentList);
                System.out.println("Save fusion as ProbFuse110.res");
                try {
                    wr.Save(resProbFuse110, true);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else{
                System.out.println("All files needed are not loaded");
            }


        }else{
            System.out.println("Path not found");
        }
        System.out.println("----------- END -----------");
    }
}
