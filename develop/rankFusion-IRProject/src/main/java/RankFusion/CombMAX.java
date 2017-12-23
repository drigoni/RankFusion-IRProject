package RankFusion;

import RunObject.AssessmentList;
import RunObject.RunElement;
import RunObject.Run;
import RunObject.RunList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Author: Davide Rigoni
 * Github Name: drigoni
 * Date: 09/12/17
 *
 * This class represents the rank fusion CombMAX algorithm
 */
public class CombMAX extends AbsRankFusion{

    public Run Fuse(RunList runList, AssessmentList assessmentList){
        Map<Run.Key, RunElement> elementList =
                new HashMap<Run.Key, RunElement>();
        // Get all document in the RunList
        List<String> documentList = runList.getAllDocuments();
        for (String name: documentList){
            RunElement[][] elements = runList.getElements(name);
            for(RunElement[] curTopic: elements) {
                RunElement max = Max(curTopic);
                // Add element to the list in order to generate the fusion
                if(max != null)
                    elementList.put(
                            new Run.Key(max.getTopic(), max.getDocument()),
                            max);
            }
        }

        Run finalRun = new Run("CombMAX.res", elementList, true);
        return finalRun;
    }

    /**
     * This methods returns the element with max score
     * @param elements Array of elements
     * @return  The element with max score
     */
    private RunElement Max(RunElement[] elements){
        int lastIndex = -1;
        int maxIndex = 0;
        double maxScore = Double.MIN_VALUE;

        for(int i = 0 ; i < elements.length; i++){
            double v;
            // Unretrieved documents are assigned a relevance score of 0
            if(elements[i] == null)
                v = 0;
            else {
                v = elements[i].getScore();
                lastIndex = i;
            }

            // Check for max
            if(v > maxScore) {
                maxScore = v;
                maxIndex = i;
            }
        }
        if(lastIndex != -1) {
            // Make a new RunElement copying some data from another one
            RunElement newEl = elements[maxIndex].deepCopy();
            newEl.setModel("CombMAX");

            return newEl;
        }
        else{
            return null;
        }
    }
}
