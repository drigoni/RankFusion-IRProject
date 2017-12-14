package RunObject;

import java.util.*;

/**
 * Author: davide
 * Github Name: drigoni
 * Date: 06/12/17
 *
 * This class represent the run file data
 */
public class Run implements Iterable<RunElement>{

    /**
     * This field represents the name of the run
     */
    private String name;

    /**
     * This field represents the list of element inside the run
     */
    private List<RunElement> listOfRow;

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
     * @param name  Name of the file
     * @param listOfRow List of RunElement
     */
    public Run(String name,  List<RunElement> listOfRow){
        this.name = name;
        this.listOfRow = listOfRow;
        this.topics = this.getTopicsID();
        this.nTopics = this.topics.size();
    }

    /**
     * Constructor
     * @param name  Name of the file
     * @param listOfRow List of RunElement
     * @param sort True if the run should be sorted
     */
    public Run(String name, List<RunElement> listOfRow, boolean sort){
        this(name,listOfRow);
        this.sort();
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
     * This method sort the Run element by score and update the rank
     */
    public void sort(){
        Collections.sort(this.listOfRow);
        String topic = "";
        int rank = 0;
        for(int i = 0; i < listOfRow.size(); i++) {
            String currentTopic = listOfRow.get(i).getTopic();
            if (i == 0)
                topic = currentTopic;
            if (!topic.equals(currentTopic))
                rank = 0;

            listOfRow.get(i).setRank(rank);
            rank++;
        }
    }

    /**
     * Implementation of the iterable interface
     * @return An iterator of RunElement
     */
    public Iterator<RunElement> iterator() {
        return listOfRow.iterator();
    }

    /**
     * This methods returns the list of topic id inside the run
     * @return List of topic id
     */
    private List<String> getTopicsID(){
        List<String> list = new ArrayList<String>();
        for(RunElement el: this.listOfRow){
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
     * Allow access to the private field name
     * @return The name of the run
     */
    public String getName(){
        return this.name;
    }

    /**
     * Allow access to the size of the list listOfRow
     * @return The size of the list listOfRow
     */
    public int getSize(){
        return this.listOfRow.size();
    }

    /**
     * This methods returns the topics inside the run
     * @return Topics
     */
    public String[] getTopics(){
        return this.topics.toArray(new String[topics.size()]);
    }

    /**
     * This methods returns the number of topics inside the run
     * @return Number of topics
     */
    public int getNTopics(){
        return this.nTopics;
    }


    /**
     * Allow access to the private field listOfRow
     * @param index Index
     * @return  The RunElement
     */
    public RunElement get(int index){
        return this.listOfRow.get(index);
    }

    /**
     * Allow access to the private field listOfRow given a document name and a
     * topic id
     * @param documentName Document name
     * @param topicID Id of the topic
     * @return  The RunElement or null if it is not present
     */
    public RunElement get(String documentName, String topicID){
        for(RunElement el: listOfRow){
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
    public RunElement[] get(String documentName){
        int size = this.getNTopics();
        RunElement[] values = new RunElement[size];
        for(int i = 0; i < this.listOfRow.size(); i++){
            if(listOfRow.get(i).getDocument().equals(documentName))
                values[i] = listOfRow.get(i);
        }
        return values;
    }

}
