package RunObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Author: davide
 * Github Name: drigoni
 * Date: 06/12/17
 *
 * This class represent the run file data
 */
public class AssessmentList implements Iterable<AssessmentElement>{

    /**
     * This field represents the list of assessment element
     */
    private List<AssessmentElement> listOfRow;

    /**
     * This field represents the number of topics
     */
    private int nTopics;

    /**
     * This field represents the list of topics
     */
    private List<String> topics;

    /**
     * Constructor
     * @param listOfRow List of AssessmentElement
     */
    public AssessmentList(List<AssessmentElement> listOfRow){
        this.listOfRow = listOfRow;
        this.topics = this.getTopicsID();
        this.nTopics = this.topics.size();
    }

    @Override
    public String toString(){
        String s = "";
        for(int i = 0; i < listOfRow.size(); i++){
            if(i > 0)
                s+= "\r\n";
            s+= listOfRow.get(i);
        }
        return s;
    }

    /**
     * Implementation of the iterable interface
     * @return An iterator of AssessmentElement
     */
    public Iterator<AssessmentElement> iterator() {
        return listOfRow.iterator();
    }

    /**
     * This methods returns the list of topic id inside the run
     * @return List of topic id
     */
    private List<String> getTopicsID(){
        List<String> list = new ArrayList<String>();
        for(AssessmentElement el: this.listOfRow){
            if(!list.contains(el.getTopic())){
                list.add(el.getTopic());
            }
        }
        return list;
    }

    //--------------------------------------------------------------------------
    //----------------------------- GETTERS ------------------------------------
    //--------------------------------------------------------------------------

    /**
     * Allow access to the size of the list listOfRow
     * @return The size of the list listOfRow
     */
    public int getSize(){
        return this.listOfRow.size();
    }

    /**
     * This methods returns the topics inside the assessment file
     * @return Topics
     */
    public String[] getTopics(){
        return this.topics.toArray(new String[topics.size()]);
    }

    /**
     * This methods returns the number of topics inside the assessment file
     * @return Number of topics
     */
    public int getNTopics(){
        return this.nTopics;
    }

    /**
     * Allow access to the private field listOfRow
     * @param index Index
     * @return  The assessmentElement
     */
    public AssessmentElement get(int index){
        return this.listOfRow.get(index);
    }

    /**
     * Allow access to the private field listOfRow given a document name and a
     * topic id
     * @param documentName Document name
     * @param topicID Id of the topic
     * @return  The assessmentElement or null if it is not present
     */
    public AssessmentElement get(String documentName, String topicID){
        for(AssessmentElement el: listOfRow){
            if(el.getDocument().equals(documentName)
                    && el.getTopic().equals(topicID))
                return el;
        }
        return null;
    }

    /**
     * Allow access to the private field listOfRow given a document name
     * @param documentName Document name
     * @return  Elements for every topics or null if it is not present
     */
    public AssessmentElement[] get(String documentName){
        int size = this.getNTopics();
        AssessmentElement[] values = new AssessmentElement[size];
        for(int i = 0; i < this.listOfRow.size(); i++){
            if(listOfRow.get(i).getDocument().equals(documentName))
                values[i] = listOfRow.get(i);
        }
        return values;
    }

}
