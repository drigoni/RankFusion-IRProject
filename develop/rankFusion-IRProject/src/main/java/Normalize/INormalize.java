package Normalize;

import RunObject.RunList;

/**
 * Author: davide
 * Github Name: drigoni
 * Date: 06/12/17
 *
 * This interface represent the normalization algorithms public contract
 */
public interface INormalize {
    /**
     * This method normalizes the runs
     * @param listOfRun RunList
     */
    void execute(RunList listOfRun);
}
