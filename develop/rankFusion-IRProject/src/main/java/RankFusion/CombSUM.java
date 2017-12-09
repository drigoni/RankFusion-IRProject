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
 * This class represents the rank fusion CombSUM algorithm
 */
public class CombSUM extends AbsRankFusion{

    public Run Fuse(RunList runList){
        List<Element> elementList = new ArrayList<Element>();
        // Get all document in the RunList
        List<String> documentList = runList.getAllDocumentNames();
        for (String name: documentList){
            Element[] elements = runList.getElements(name);
            Element max = Sum(elements);
            // Add element to the list in order to generate the fusion
            elementList.add(max);
        }

        Run finalRun = new Run("CombMax", elementList, true);
        return finalRun;
    }

    /**
     * his methods returns the element the sum of the score
     * @param elements Array of elements
     * @return  The element with sum score
     */
    private Element Sum(Element[] elements){
        // Index used to copy the element
        int lastIndex = 0;
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

        // Make a new Element copying some data from another one
        Element newEl = elements[lastIndex].deepCopy();
        newEl.setScore(sum);
        newEl.setModel("CombSUM");

        return newEl;
    }
}
