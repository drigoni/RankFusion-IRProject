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
 * This class represents the rank fusion CombMAX algorithm
 */
public class CombMAX extends AbsRankFusion{

    public Run Fuse(RunList runList){
        List<Element> elementList = new ArrayList<Element>();
        // Get all document in the RunList
        List<String> documentList = runList.getAllDocumentNames();
        for (String name: documentList){
            Element[][] elements = runList.getElements(name);
            for(Element[] curTopic: elements) {
                Element max = Max(curTopic);
                // Add element to the list in order to generate the fusion
                if(max != null)
                    elementList.add(max);
            }
        }

        Run finalRun = new Run("CombMAX", elementList, true);
        return finalRun;
    }

    /**
     * //TODO: Potrebbe capitare un errore se l'unico score migliore è minore di 0
     * //TODO: In quanto potrebbe essere che
     * This methods returns the element with max score
     * @param elements Array of elements
     * @return  The element with max score
     */
    private Element Max(Element[] elements){
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
        if(lastIndex != -1)
            return elements[maxIndex];
        else{
            return null;
        }
    }
}
