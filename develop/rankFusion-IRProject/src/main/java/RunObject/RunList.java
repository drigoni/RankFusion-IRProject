package RunObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Author: davide
 * Github Name: drigoni
 * Date: 09/12/17
 *
 * This class represents a list of Run
 */
public class RunList implements Iterable<Run> {

    /**
     * Private field representing the list of Run
     */
    private List<Run> listOfRun = new ArrayList<Run>();

    /**
     * This method adds a Run in the list
     * @param run Run to add
     */
    public void add(Run run){
        this.listOfRun.add(run);
    }

    /**
     * This method gets a Run in the list by its index
     * @param i Index
     * @return The Run
     */
    public Run get(int i){
        return this.listOfRun.get(i);
    }

    /**
     * This method gets a Run in the list by its name
     * @param runName Name of the Run
     * @return The Run
     */
    public Run get(String runName){
        for (Run run: listOfRun) {
            if (run.getName() == runName)
                return run;
        }
        return null;
    }

    /**
     * This method removes a Run in the list
     * @param i Index
     */
    public void remove(int i){
        this.listOfRun.remove(i);
    }


    /**
     * This method search the document inside all the runs
     * @param documentName Name of the document to search
     * @return  An array fo result or null if it is not inside at least one run.
     *          The array has the size of the number of Run and the
     *          corresponding values are null if the document is not present.
     */
    public Element[] getElements(String documentName){
        Element[] values = new Element[this.listOfRun.size()];
        for(int i = 0; i < listOfRun.size(); i++){
            values[i] = listOfRun.get(i).getElement(documentName);
        }
        return values;
    }

    /**
     * This method returns all the documents name in all the runs
     * @return The list of the document name
     */
    public List<String> getAllDocumentNames(){
        List<String> list = new ArrayList<String>();
        for(Run run: listOfRun){
           for (Element el: run){
               if(!list.contains(el.getDocument())){
                   list.add(el.getDocument());
               }
           }
        }

        return list;
    }

    /**
     * Implementation of the iterable interface
     * @return An iterator of Element
     */
    public Iterator<Run> iterator() {
        return listOfRun.iterator();
    }
}
