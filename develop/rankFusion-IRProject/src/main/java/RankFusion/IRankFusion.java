package RankFusion;

import RunObject.AssessmentList;
import RunObject.Run;
import RunObject.RunList;


/**
 * Author: Davide Rigoni
 * Github Name: drigoni
 * Date: 06/12/17
 *
 * This interface represent the public contract of the rank fusion algorithms
 */
public interface IRankFusion {

    /**
     * This method fuse all the runs in order to create a new one
     * @param runList List of all the runs
     * @param assessmentList Assessments
     * @return  The new fuse run
     */
    Run Fuse(RunList runList, AssessmentList assessmentList);
}
