package Run;

import java.util.List;

/**
 * Author: davide
 * Github Name: drigoni
 * Date: 06/12/17
 */
public class Run {

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
     */
    public Run(String name,  List<Element> listOfRow){
        this.listOfRow = listOfRow;
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
     * Allow access to the private field listOfRow
     * @param index Index
     * @return  The Element
     */
    public Element getElement(int index){
        return this.listOfRow.get(index);
    }
}
