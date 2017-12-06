package RunObject;

/**
 * Author: davide
 * Github Name: drigoni
 * Date: 06/12/17
 *
 * This class represents the element inside the run
 */
public class Element {
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

    @Override
    public String toString(){

        return this.getTopic() + " " + this.getQuery() + " " +
                this.getDocument() + " " + this.getRank() + " " +
                this.getScore() + " " + this.getModel();
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
}
