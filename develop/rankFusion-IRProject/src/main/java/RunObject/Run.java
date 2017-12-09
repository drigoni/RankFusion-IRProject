package RunObject;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Author: davide
 * Github Name: drigoni
 * Date: 06/12/17
 *
 * This class represent the run file data
 */
public class Run implements Iterable<Element>{

    /**
     * This field represents the name of the run
     */
    private String name;

    /**
     * This field represents the list of element inside the run
     */
    private List<Element> listOfRow;

    /**
     * Constructor
     * @param name  Name of the file
     * @param listOfRow List of Element
     */
    public Run(String name,  List<Element> listOfRow){
        this.name = name;
        this.listOfRow = listOfRow;
    }

    /**
     * Constructor
     * @param name  Name of the file
     * @param listOfRow List of Element
     * @param sort True if the run should be sorted
     */
    public Run(String name,  List<Element> listOfRow, boolean sort){
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
        for(int i = 0; i<= listOfRow.size(); i++)
            listOfRow.get(i).setRank(i+1);
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
     * Allow access to the private field listOfRow
     * @param index Index
     * @return  The Element
     */
    public Element getElement(int index){
        return this.listOfRow.get(index);
    }

    /**
     * Allow access to the private field listOfRow
     * @param documentName Document name
     * @return  The Element or null if it is not present
     */
    public Element getElement(String documentName){
        for(Element el: listOfRow){
            if(el.getDocument().equals(documentName))
                return el;
        }
        return null;
    }

    /**
     * This method search the document score inside the Run
     * @param documentName Name of the document to search
     * @return  The score or null if it is not inside the Run
     */
    public Double getDocumentScore(String documentName){
        for(Element el: listOfRow){
            if(el.getDocument().equals(documentName)){
                return el.getScore();
            }
        }
        return null;
    }

    /**
     * Implementation of the iterable interface
     * @return An iterator of Element
     */
    public Iterator<Element> iterator() {
        return listOfRow.iterator();
    }
}
