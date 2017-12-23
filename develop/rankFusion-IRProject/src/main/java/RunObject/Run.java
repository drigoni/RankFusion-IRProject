package RunObject;

import RankFusion.ProbFuse;

import java.util.*;

/**
 * Author: Davide Rigoni,
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
    private Map<Key, RunElement> listOfRow;

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
    public Run(String name,  Map<Key, RunElement> listOfRow){
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
    public Run(String name, Map<Key, RunElement> listOfRow, boolean sort){
        this(name, listOfRow);
    }

    @Override
    public String toString(){
        List<RunElement> list =
                new ArrayList<RunElement>(this.listOfRow.values());
        Collections.sort(list);
        String s = "";
        for(int i = 0; i < list.size(); i++){
            if(i > 0)
                s+= "\r\n";
            s+= list.get(i);
        }
        return s;
    }

    /**
     * This method sort the Run element by score and update the rank
     */
    public List<RunElement> sort(){
        List<RunElement> list =
                new ArrayList<RunElement>(this.listOfRow.values());
        Collections.sort(list);
        String topic = "";
        int rank = 0;
        for(int i = 0; i < list.size(); i++) {
            String currentTopic = list.get(i).getTopic();
            if (i == 0)
                topic = currentTopic;
            if (!topic.equals(currentTopic)) {
                rank = 0;
                topic = currentTopic;
            }

            list.get(i).setRank(rank);
            rank++;
        }
        return list;
    }

    /**
     * Implementation of the iterable interface
     * @return An iterator of RunElement
     */
    public Iterator<RunElement> iterator() {
        return listOfRow.values().iterator();
    }


    /**
     * This methods returns the list of topic id inside the run
     * @return List of topic id
     */
    private List<String> getTopicsID(){
        List<String> list = new ArrayList<String>();
        for(RunElement el: this.listOfRow.values()){
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
     * @param key key
     * @return  The RunElement
     */
    public RunElement get(Run.Key key){
        return this.listOfRow.get(key);
    }

    /**
     * Allow access to the private field listOfRow given a document name and a
     * topic id
     * @param documentName Document name
     * @param topicID Id of the topic
     * @return  The RunElement or null if it is not present
     */
    public RunElement get(String documentName, String topicID){
        return this.get(new Run.Key(topicID, documentName));
    }



    /**
     * Wrapper around topic and document values used for multi-key map
     */
    public static class Key {
        /**
         * The topic identifier
         */
        private String topic;

        /**
         * The document identifier
         */
        private String document;

        /**
         * Constructor
         * @param topic The topic identifier
         * @param document The document identifier
         */
        public Key(String topic, String document) {
            this.topic = topic;
            this.document = document;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;

            Run.Key key = (Run.Key) o;
            return topic.equals(key.topic) &&
                    document.equals(key.document);
        }

        @Override
        public int hashCode() {
            int result = topic != null ? topic.hashCode() : 0;
            result = 31 * result + (document != null ? document.hashCode() : 0);
            return result;
        }
    }
}
