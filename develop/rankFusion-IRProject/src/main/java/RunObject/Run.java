package RunObject;

import java.util.List;

/**
 * Author: davide
 * Github Name: drigoni
 * Date: 06/12/17
 *
 * This class represent the run file data
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
        this.name = name;
        this.listOfRow = listOfRow;
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
}
