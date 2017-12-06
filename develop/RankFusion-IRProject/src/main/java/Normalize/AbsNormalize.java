/**
 * Author: davide
 * Github Name: drigoni
 * Date: 06/12/17
 */

package Normalize;

import RunObject.Element;

/**
 * This abstract class could be used to implement all the different
 * normalization algorithms
 */
public abstract class AbsNormalize implements INormalize{

    /**
     * This method creates a new Element copying the value from a  given param
     * but with different score
     * @param el    Element object to copy
     * @param score New score to use
     * @return  A new Element differing only from the score
     */
    protected Element newElementWithScore(Element el, double score){
        return new Element(el.getTopic(), el.getQuery(), el.getDocument(),
                "" + el.getRank(), ""+score, el.getModel());
    }
}
