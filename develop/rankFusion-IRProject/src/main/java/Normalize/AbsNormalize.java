package Normalize;

import RunObject.RunElement;
import RunObject.Run;

/**
 * Author:  Davide Rigoni
 * Github Name: drigoni
 * Date: 06/12/17
 *
 * This abstract class could be used to implement all the different
 * normalization algorithms
 */
public abstract class AbsNormalize implements INormalize{

    /**
     * This method translate the value of all scores
     * @param run Run
     */
    protected void translateValue(Run run, double min){
        if(min > 0){
            for(RunElement el: run){
                el.setScore(el.getScore() - min);
            }
        } else if(min < 0){
            for(RunElement el: run){
                el.setScore(el.getScore() - min); // like .. + Abs(min)
            }
        }
    }


    /**
     * This method find the min score value in the run
     * @param run Run
     * @return Min score
     */
    protected double minScore(Run run){
        double min = Double.MAX_VALUE;
        for(RunElement el: run){
            if(el.getScore() < min)
                min = el.getScore();
        }
        return min;
    }

    /**
     * This method find the max score value in the run
     * @param run Run
     * @return Max score
     */
    protected double maxScore(Run run){
        double max = Double.MIN_VALUE;
        for(RunElement el: run){
            if(el.getScore() > max)
                max = el.getScore();
        }
        return max;
    }

    /**
     * This method sum score values in the run
     * @param run Run
     * @return Sum of the scores
     */
    protected double sumScore(Run run){
        double sum = 0;
        for(RunElement el: run){
            sum += el.getScore();
        }
        return sum;
    }
}
