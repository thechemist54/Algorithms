import java.util.Map;

/**
 * This is a Helper class that helps keep track of status, classification and
 * name of animals. This helper class is the associated value of map.
 */


public class AnimalCon
{
    private String name; //instance variables
    private String classification;//(carnivore,herbivore,insectivore,omnivore)
    private String status; //(en,vu,nt,cd,lc,do,ne)

    /**
     * An argument constructor which takes in name, classification and status as arguments
     * @param name the name of the animal.
     * @param classification the classification of the animal.
     * @param status the status of the animal.
     */

    public AnimalCon(String name, String classification, String status)
    {
        this.name = name;
        this.classification = classification;
        this.status = status;
    }

    /**
     * Getter method to get name of particular animal.
     * @return the name of the animal.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Getter method to get classification of particular animal.
     * @return the classification of the animal.
     */

    public String getClassification()
    {
        return classification;
    }

    /**
     * Getter method to get the status of the particular animal.
     * @return the status of the animal.
     */
    public String getStatus()
    {
        return status;
    }







}
