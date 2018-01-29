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
 * This class represents the rank fusion CombANZ algorithm
 */
public class CombANZ extends AbsRankFusion{

    public Run Fuse(RunList runList, AssessmentList assessmentList){
        Map<Run.Key, RunElement> elementList =
                new HashMap<Run.Key, RunElement>();
        // Get all document in the RunList
        List<String> documentList = runList.getAllDocuments();
        for (String name: documentList){
            RunElement[][] elements = runList.getElements(name);
            for(RunElement[] curTopic: elements) {
                RunElement max = SumANZ(curTopic);
                // Add element to the list in order to generate the fusion
                if(max != null)
                    elementList.put(
                            new Run.Key(max.getTopic(), max.getDocument()),
                            max);
            }
        }

        Run finalRun = new Run("CombANZ.res", elementList, true);
        return finalRun;
    }

    /**
     * his methods returns the element the sumANZ of the score
     * @param elements Array of elements
     * @return  The element with sumANZ score
     */
    private RunElement SumANZ(RunElement[] elements){
        // Index used to copy the element
        int lastIndex = -1;
        int nonZeroCount = 0;
        double sum = 0;

        for(int i = 0 ; i < elements.length; i++){
            double v;
            // Unretrieved documents are assigned a relevance score of 0
            if(elements[i] == null)
                v = 0;
            else {
                double currentScore = elements[i].getScore();
                if(currentScore != 0)
                    nonZeroCount ++;
                lastIndex = i;
                v = currentScore;
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
            newEl.setScore(sum / nonZeroCount);
            newEl.setModel("CombANZ");

            return newEl;
        } else{
            return null;
        }

    }
}
