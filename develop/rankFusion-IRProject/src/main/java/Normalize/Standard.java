package Normalize;

import RunObject.Element;
import RunObject.Run;
import RunObject.RunList;

/**
 * Author: davide
 * Github Name: drigoni
 * Date: 09/12/17
 *
 * This class represent the standard normalization where
 * shift min to 0 and max to 1
 */
public class Standard extends  AbsNormalize{

    public void execute(RunList listOfRun) {
        for(Run run: listOfRun){
            double min = this.minScore(run);
            this.translateValue(run, min);

            // Scale
            double max = this.maxScore(run);
            for(Element el: run){
                el.setScore(el.getScore() / max);
            }
        }
    }


}
