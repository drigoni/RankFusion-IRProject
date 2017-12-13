package RankFusion;

import RunObject.AssessmentList;
import RunObject.RunElement;
import RunObject.Run;
import RunObject.RunList;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: davide
 * Github Name: drigoni
 * Date: 09/12/17
 *
 * This class represents the rank fusion CombSUM algorithm
 */
public class CombSUM extends AbsRankFusion{

    public Run Fuse(RunList runList, AssessmentList assessmentList){
        List<RunElement> elementList = new ArrayList<RunElement>();
        // Get all document in the RunList
        List<String> documentList = runList.getAllDocumentNames();
        for (String name: documentList){
            RunElement[][] elements = runList.getElements(name);
            for(RunElement[] curTopic: elements) {
                RunElement max = Sum(curTopic);
                // Add element to the list in order to generate the fusion
                if(max != null)
                    elementList.add(max);
            }
        }

        Run finalRun = new Run("CombSUM.res", elementList, true);
        return finalRun;
    }

    /**
     * his methods returns the element the sum of the score
     * @param elements Array of elements
     * @return  The element with sum score
     */
    private RunElement Sum(RunElement[] elements){
        // Index used to copy the element
        int lastIndex = -1;
        double sum = 0;

        for(int i = 0 ; i < elements.length; i++){
            double v;
            // Unretrieved documents are assigned a relevance score of 0
            if(elements[i] == null)
                v = 0;
            else {
                lastIndex = i;
                v = elements[i].getScore();
            }

            // Sum the score or initialize sum with the first value
            if(i == 0){
                sum = v;
            } else{
                sum += v;
            }
        }

        if(lastIndex != -1) {
            // Make a new RunElement copying some data from another one
            RunElement newEl = elements[lastIndex].deepCopy();
            newEl.setScore(sum);
            newEl.setModel("CombSUM");

            return newEl;
        }else{
            return null;
        }
    }
}
