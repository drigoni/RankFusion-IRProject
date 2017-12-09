package RunObject;

/**
 * Author: davide
 * Github Name: drigoni
 * Date: 06/12/17
 *
 * This class represents the element inside the run
 */
public class Element  implements Comparable<Element>{
    /**
     * This field represents the topic of the query
     */
    private String topic;

    /**
     * This field represents the query
     */
    private String query;

    /**
     * This field represents the document
     */
    private String document;

    /**
     * This field represents the rank
     */
    private int rank;

    /**
     * This field represents the score
     */
    private double score;

    /**
     * This field represents the model
     */
    private String model;

    /**
     * Constructor
     * @param topic Topic of the query
     * @param query Number of the query
     * @param document  Name of the document
     * @param rank  Rank of the document
     * @param score Score of the document
     * @param model   Model uses for the query
     */
    public Element(String topic, String query, String document, String rank,
                   String score, String model){
        this.topic = topic;
        this.query = query;
        this.document = document;
        this.rank = Integer.parseInt(rank);
        this.score = Double.parseDouble(score);
        this.model = model;
    }

    /**
     * Constructor
     * @param topic Topic of the query
     * @param query Number of the query
     * @param document  Name of the document
     * @param rank  Rank of the document
     * @param score Score of the document
     * @param model   Model uses for the query
     */
    public Element(String topic, String query, String document, int rank,
                   double score, String model){
        this.topic = topic;
        this.query = query;
        this.document = document;
        this.rank = rank;
        this.score = score;
        this.model = model;
    }

    @Override
    public String toString(){

        return this.getTopic() + " " + this.getQuery() + " " +
                this.getDocument() + " " + this.getRank() + " " +
                this.getScore() + " " + this.getModel();
    }

    /**
     * Implementation of the comparable interface
     * @param el Element
     * @return The value
     */
    public int compareTo(Element el) {
        double diff = this.getScore() - el.getScore();
        if(Integer.parseInt(this.getTopic()) > Integer.parseInt(el.getTopic())) {
            return 1;
        }else if(Integer.parseInt(this.getTopic()) <
                Integer.parseInt(el.getTopic())) {
            return -1;
        }else {
            if (diff > 0)
                return 1;
            else if (diff < 0) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    //--------------------------------------------------------------------------
    //----------------------------- GETTERS ------------------------------------
    //--------------------------------------------------------------------------

    /**
     * Allow access to the private field topic
     * @return Topic
     */
    public String getTopic(){
        return this.topic;
    }

    /**
     * Allow access to the private field query
     * @return Topic
     */
    public String getQuery(){
        return this.query;
    }

    /**
     * Allow access to the private field document
     * @return Topic
     */
    public String getDocument(){
        return this.document;
    }

    /**
     * Allow access to the private field rank
     * @return Topic
     */
    public int getRank(){
        return this.rank;
    }

    /**
     * Allow access to the private field score
     * @return Topic
     */
    public Double getScore(){
        return this.score;
    }

    /**
     * Allow access to the private field model
     * @return Topic
     */
    public String getModel(){
        return this.model;
    }

    /**
     * This method make a deep copy of this object
     * @return New Element
     */
    public Element deepCopy(){
        return new Element(this.topic, this.query, this.document, this.rank,
                this.score, this.model);
    }

    //--------------------------------------------------------------------------
    //----------------------------- SETTERS ------------------------------------
    //--------------------------------------------------------------------------

    /**
     * Allow access to the private field score
     * @param score Score
     */
    public void setScore(double score){
        this.score = score;
    }

    /**
     * Allow access to the private field score
     * @param rank Rank
     */
    public void setRank(int rank){
        this.rank = rank;
    }

    /**
     * Allow access to the private field score
     * @param model Model
     */
    public void setModel(String model){
        this.model = model;
    }
}
