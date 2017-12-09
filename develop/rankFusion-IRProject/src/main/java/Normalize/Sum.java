package Normalize;

import RunObject.Element;
import RunObject.Run;
import RunObject.RunList;

/**
 * Author: davide
 * Github Name: drigoni
 * Date: 09/12/17
 *
 * This class represent the sum normalization where
 * shift min to 0 and sum to 1
 */
public class Sum extends  AbsNormalize{
    public void execute(RunList listOfRun) {

        for (Run run : listOfRun) {
            double min = this.minScore(run);
            this.translateValue(run, min);


            // Scale
            double sum = this.sumScore(run);
            for (Element el : run) {
                el.setScore(el.getScore() / sum);
            }
        }
    }
}