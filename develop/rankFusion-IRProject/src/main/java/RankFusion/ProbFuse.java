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

    private static final int SEGMENT_SIZE = 20;

    public Run Fuse(RunList runList, AssessmentList assessmentList) {
        List<Map<Run.Key, Double>> probabilities =
                new ArrayList<Map<Run.Key, Double>>();

        for (Run r : runList)
            probabilities.add(calculateRunProbabilities(r, assessmentList));

        Map<Run.Key, RunElement> elements = getResultElements(
                assessmentList.getTopics(),
                runList.getAllDocuments(),
                probabilities);

        return new Run("ProbFuse.res", elements, true);
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
        System.out.println("Calculating run " + run.getName());

        Map<Run.Key, Double> probabilities = new HashMap<Run.Key, Double>();

        Map<String, List<RunElement>> elementsByTopic = new HashMap<String, List<RunElement>>();

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
                if (segmentElements.size() == SEGMENT_SIZE) {
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

        System.out.println("Finished calculating run " + run.getName());

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

        int rank = 0;
        for (String topic : topics) {
            for (String document : documents) {
                //System.out.println("Topic: " + topic + " - document: " + document);
                double score = 0;

                for (Map<Run.Key, Double> probs : probabilities) {
                    Run.Key key = new Run.Key(topic, document);
                    score += probs.containsKey(key) ? probs.get(key) : 0d;
                    //System.out.println("Score updated to: " + score);
                }

                // assuming 1 query
                if (score > 0)
                    elements.put(
                            new Run.Key(topic, document),
                            new RunElement(topic, "Q0", document, rank++, score,
                                    "ProbFuse"));
            }
        }

        return elements;
    }
}
