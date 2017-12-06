package Normalize;

import RunObject.Run;

/**
 * Author: davide
 * Github Name: drigoni
 * Date: 06/12/17
 *
 * This interface represent he Normalization algorithms public contract
 */
public interface INormalize {
    /**
     * This method normalizes the scores between runs
     * @param run Run
     * @return Run with normalization values
     */
    Run execute(Run run);
}
