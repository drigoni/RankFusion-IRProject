package RankFusion;

import RunObject.Element;
import RunObject.Run;
import RunObject.RunList;

import java.util.ArrayList;
import java.util.List;


/**
 * Author: davide
 * Github Name: drigoni
 * Date: 09/12/17
 *
 * This class represents the rank fusion CombMNZ algorithm
 */
public class CombMNZ extends AbsRankFusion{

    public Run Fuse(RunList runList){
        List<Element> elementList = new ArrayList<Element>();
        // Get all document in the RunList
        List<String> documentList = runList.getAllDocumentNames();
        for (String name: documentList){
            Element[][] elements = runList.getElements(name);
            for(Element[] curTopic: elements) {
                Element max = SumMNZ(curTopic);
                // Add element to the list in order to generate the fusion
                if(max != null)
                    elementList.add(max);
                System.out.println("Element: " + max);
            }
            break;
        }

        Run finalRun = new Run("CombMNZ", elementList, true);
        return finalRun;
    }

    /**
     * his methods returns the element the sumMNZ of the score
     * @param elements Array of elements
     * @return  The element with sumMNZ score
     */
    private Element SumMNZ(Element[] elements){
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
            // Make a new Element copying some data from another one
            Element newEl = elements[lastIndex].deepCopy();
            newEl.setScore(sum * nonZeroCount);
            newEl.setModel("CombMNZ");

            return newEl;
        } else{
            return null;
        }

    }
}
