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

    /**
     * Wrapper around topic and document values used for multi-key map
     */
    static class Key {
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
        Key(String topic, String document) {
            this.topic = topic;
            this.document = document;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;

            Key key = (Key) o;
            return topic.equals(key.topic) &&
                    document.equals(key.document);
        }

        @Override
        public int hashCode() {
            return Objects.hash(topic, document);
        }
    }


    public Run Fuse(RunList runList, AssessmentList assessmentList) {
        List<Map<Key, Double>> probabilities = new ArrayList<Map<Key, Double>>();

        for (Run r : runList)
            probabilities.add(calculateRunProbabilities(r, assessmentList));

        List<RunElement> elements = getResultElements(assessmentList.getTopics(), runList.getAllDocuments(),
                probabilities);

        return new Run("ProbFuse", elements);
    }

    /**
     * Calculates the probabilities P(d_k | m) where m is given run for each document d returned by query in topic k.
     * This assumes that there is only one query!!
     * @param run The run obtained with a certain model
     * @return The probabilities
     */
    private Map<Key, Double> calculateRunProbabilities(Run run, AssessmentList assessmentList) {
        Map<Key, Double> probabilities = new HashMap<Key, Double>();

        // getting number of relevant and total documents for each topic
        Map<String, Integer> relevantDocsCount = new HashMap<String, Integer>();
        Map<String, Integer> docsCount = new HashMap<String, Integer>();
        for (AssessmentElement element : assessmentList) {
            String topic = element.getTopic();
            docsCount.put(topic, docsCount.getOrDefault(topic, 0) + 1);

            if (element.getAssessment())
                relevantDocsCount.put(topic, relevantDocsCount.getOrDefault(topic, 0) + 1);
        }

        // calculating probabilities
        for (RunElement element : run) {
            String topic = element.getTopic();
            double probability = (double) relevantDocsCount.get(topic) / docsCount.get(topic);

            Key key = new Key(topic, element.getDocument());
            probabilities.put(key, probability);
        }

        return probabilities;
    }

    /**
     * Creates resulting elements that will form the fused run using probabilities previously calculated.
     * @param topics The list of topics
     * @param documents The list of all documents
     * @param probabilities The probabilities indexed by topic and document
     * @return The element that will form the fused run.
     */
    private List<RunElement> getResultElements(String[] topics, List<String> documents,
                                               List<Map<Key, Double>> probabilities) {
        List<RunElement> elements = new ArrayList<RunElement>();

        int rank = 0;
        for (String topic : topics) {
            for (String document : documents) {
                double score = 0;

                for (Map<Key, Double> probs : probabilities) {
                    Key key = new Key(topic, document);
                    score += probs.getOrDefault(key, 0d);
                }

                // assuming 1 query
                elements.add(new RunElement(topic, "Q0", document, rank++, score, "ProbFuse"));
            }
        }

        return elements;
    }
}
