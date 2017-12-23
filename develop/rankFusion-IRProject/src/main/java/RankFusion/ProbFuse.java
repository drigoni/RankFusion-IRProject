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
        Map<Run.Key, Double> probabilities = new HashMap<Run.Key, Double>();

        // getting number of relevant and total documents for each topic
        Map<String, Integer> relevantDocsCount = new HashMap<String, Integer>();
        Map<String, Integer> docsCount = new HashMap<String, Integer>();
        for (AssessmentElement element : assessmentList) {
            String topic = element.getTopic();
            docsCount.put(topic, (docsCount.containsKey(topic) ?
                    docsCount.get(topic) : 0) + 1);

            if (element.getAssessment())
                relevantDocsCount.put(topic,
                        (relevantDocsCount.containsKey(topic)
                                ? relevantDocsCount.get(topic) : 0) + 1);
        }

        // calculating probabilities
        for (RunElement element : run) {
            String topic = element.getTopic();
            double probability = (double) relevantDocsCount.get(topic)
                    / docsCount.get(topic);

            Run.Key key = new Run.Key(topic, element.getDocument());
            probabilities.put(key, probability);
        }

        return probabilities;
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
                double score = 0;

                for (Map<Run.Key, Double> probs : probabilities) {
                    Run.Key key = new Run.Key(topic, document);
                    score += probs.containsKey(key) ? probs.get(key) : 0d;
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
