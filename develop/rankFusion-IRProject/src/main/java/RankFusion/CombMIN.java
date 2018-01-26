package RankFusion;

import RunObject.AssessmentList;
import RunObject.Run;
import RunObject.RunElement;
import RunObject.RunList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Author: Davide Rigoni
 * Github Name: drigoni
 * Date: 09/12/17
 *
 * This class represents the rank fusion CombMIN algorithm
 */
public class CombMIN extends AbsRankFusion{

    public Run Fuse(RunList runList, AssessmentList assessmentList){
        Map<Run.Key, RunElement> elementList =
                new HashMap<Run.Key, RunElement>();
        // Get all document in the RunList
        List<String> documentList = runList.getAllDocuments();
        for (String name: documentList){
            RunElement[][] elements = runList.getElements(name);
            for(RunElement[] curTopic: elements) {
                RunElement min = Min(curTopic);
                // Add element to the list in order to generate the fusion
                if(min != null)
                    elementList.put(
                            new Run.Key(min.getTopic(), min.getDocument()),
                            min);
            }
        }

        Run finalRun = new Run("CombMIN.res", elementList, true);
        return finalRun;
    }

    /**
     * This methods returns the element with min score
     * @param elements Array of elements
     * @return  The element with max score
     */
    private RunElement Min(RunElement[] elements){
        int lastIndex = -1;
        int minIndex = 0;
        double minScore = Double.MAX_VALUE;

        for(int i = 0 ; i < elements.length; i++){
            double v;
            // Unretrieved documents are assigned a relevance score of 0
            if(elements[i] == null)
                v = Double.MAX_VALUE;
            else {
                v = elements[i].getScore();
                lastIndex = i;
            }

            // Check for min
            if(v < minScore) {
                minScore = v;
                minIndex = i;
            }
        }
        if(lastIndex != -1) {
            // Make a new RunElement copying some data from another one
            RunElement newEl = elements[minIndex].deepCopy();
            newEl.setModel("CombMIN");

            return newEl;
        }
        else{
            return null;
        }
    }
}
