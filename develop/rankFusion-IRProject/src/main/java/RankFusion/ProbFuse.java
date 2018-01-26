package RankFusion;
import RunObject.*;
import java.util.*;


/**
 * Author: Alex Beccaro
 * Github Name: abeccaro
 * Date: 09/12/17
 *
 * This class represents the rank fusion ProbFuse algorithm
 */
public class ProbFuse extends AbsRankFusion {

    private static final int SEGMENTS = 110;

    private static final String QUERY = "Q0";
    private static final String MODEL_NAME = "ProbFuse";


    public Run Fuse(RunList runList, AssessmentList assessmentList) {
        List<Map<Run.Key, Double>> probabilities =
                new ArrayList<Map<Run.Key, Double>>();

        // calculate probabilities from each model
        for (Run r : runList)
            probabilities.add(calculateRunProbabilities(r, assessmentList));

        // sum documents probabilities of different models
        Map<Run.Key, RunElement> elements = getResultElements(
                assessmentList.getTopics(),
                runList.getAllDocuments(),
                probabilities);

        // creating and returning result run
        return new Run(MODEL_NAME + SEGMENTS + "s.res", elements, true);
    }

    /**
     * Calculates the probabilities P(d_k | m) where m is given run for
     * each document d returned by query in topic k.
     * This assumes that there is only one query!!
     * @param run The run obtained with a certain model
     * @return The probabilities
     */
    private Map<Run.Key, Double> calculateRunProbabilities
    (Run run, AssessmentList assessmentList) {
        Map<Run.Key, Double> probabilities = new HashMap<Run.Key, Double>();

        Map<String, List<RunElement>> elementsByTopic = new HashMap<String, List<RunElement>>();

        // mapping run elements by their topic
        for (RunElement e : run) {
            String topic = e.getTopic();
            if (elementsByTopic.containsKey(topic))
                elementsByTopic.get(topic).add(e);
            else {
                List<RunElement> list = new ArrayList<RunElement>();
                list.add(e);
                elementsByTopic.put(topic, list);
            }
        }

        int relevant, nonRelevant, segment;
        List<RunElement> segmentElements = new ArrayList<RunElement>();

        for (String topic: elementsByTopic.keySet()) {
            List<RunElement> topicElements = elementsByTopic.get(topic);

            long segmentSize = Math.round((double) topicElements.size() / SEGMENTS);

            // reset segment variables
            segmentElements.clear();
            relevant = 0;
            nonRelevant = 0;
            segment = 1;

            for (RunElement element : topicElements) {
                // if relevant increment relative counter
                AssessmentElement ae = assessmentList.get(element.getDocument(), element.getTopic());
                if (ae != null) {
                    if (ae.getAssessment())
                        relevant++;
                    else
                        nonRelevant++;
                }

                // adding element to segment
                segmentElements.add(element);

                // if current segment is full calculate probabilities and clear segment data
                if (segmentElements.size() == segmentSize) {
                    double probability = ((double) relevant / (relevant + nonRelevant)) / segment;
                    addSegmentProbabilities(probabilities, segmentElements, probability);

                    segmentElements.clear();
                    relevant = 0;
                    nonRelevant = 0;

                    segment++;
                }
            }

            // calculate probabilities of last segment if not complete
            if (!segmentElements.isEmpty()) {
                double probability = ((double) relevant / (relevant + nonRelevant)) / segment;
                addSegmentProbabilities(probabilities, segmentElements, probability);
            }
        }

        return probabilities;
    }

    /**
     * Adds the partial probabilities to already calculated ones of each document in a segment
     * @param probabilities The already calculated probabilities
     * @param segmentElements The elements in the segment
     * @param probability The probability to add to each element in the segment
     */
    private void addSegmentProbabilities(Map<Run.Key, Double> probabilities,
                                               List<RunElement> segmentElements,
                                               double probability) {
        for (RunElement elem : segmentElements) {
            Run.Key key = new Run.Key(elem.getTopic(), elem.getDocument());

            // if key already present
            if (probabilities.containsKey(key))
                // add probability to old value
                probabilities.put(key, probabilities.get(key) + probability);
            else
                // else set it to probability
                probabilities.put(key, probability);
        }
    }

    /**
     * Creates resulting elements that will form the fused run using
     * probabilities previously calculated.
     * @param topics The list of topics
     * @param documents The list of all documents
     * @param probabilities The probabilities indexed by topic and document
     * @return The element that will form the fused run.
     */
    private Map<Run.Key, RunElement> getResultElements
    (String[] topics, List<String> documents,
     List<Map<Run.Key, Double>> probabilities) {
        Map<Run.Key, RunElement> elements = new HashMap<Run.Key, RunElement>();

        for (String topic : topics)
            for (String document : documents) {
                double score = 0;
                Run.Key key = new Run.Key(topic, document);

                // add probabilities to score
                for (Map<Run.Key, Double> probs : probabilities)
                    score += probs.containsKey(key) ? probs.get(key) : 0d;

                // if score > 0 create and add new RunElement
                // note that rank is not important as it will be defined when sorting Run
                if (score > 0)
                    elements.put(
                            new Run.Key(topic, document),
                            new RunElement(topic, QUERY, document, 0, score, MODEL_NAME + SEGMENTS));
            }

        return elements;
    }
}
