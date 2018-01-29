package RunObject;

/**
 * Author: Davide Rigoni
 * Github Name: drigoni
 * Date: 06/12/17
 *
 * This class represents the element inside the run
 */
public class AssessmentElement {
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
     * This field represents the assessment
     */
    private boolean assessment;

    /**
     * Constructor
     * @param topic Topic of the query
     * @param query Number of the query
     * @param document  Name of the document
     * @param assessment  Rank of the document
     */
    public AssessmentElement(String topic, String query, String document,
                             String assessment){
        this.topic = topic;
        this.query = query;
        this.document = document;
        this.assessment = assessment.equals("1");
    }

    /**
     * Constructor
     * @param topic Topic of the query
     * @param query Number of the query
     * @param document  Name of the document
     * @param assessment  Rank of the document
     */
    public AssessmentElement(String topic, String query, String document,
                             boolean assessment){
        this.topic = topic;
        this.query = query;
        this.document = document;
        this.assessment = assessment;

    }

    @Override
    public String toString(){

        return this.getTopic() + " " + this.getQuery() + " " +
                this.getDocument() + " " + this.getAssessment();
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
     * Allow access to the private field assessment
     * @return Assessment
     */
    public boolean getAssessment(){
        return this.assessment;
    }


}
