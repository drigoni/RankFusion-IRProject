package RankFusion;

import RunObject.Run;
import java.util.List;

/**
 * Author: davide
 * Github Name: drigoni
 * Date: 06/12/17
 *
 * This interface represent the public contract of the rank fusion algorithms
 */
public interface IRankFusion {

    /**
     * This method fuse all the runs in order to create a new one
     * @param listOfRun List of all the runs
     * @return  The new fuse run
     */
    Run Fuse(List<Run> listOfRun);
}
