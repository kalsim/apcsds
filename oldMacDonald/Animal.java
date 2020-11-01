package oldMacDonald;

import java.util.ArrayList;
import java.util.List;
// Provides an abstract implementation for an Animal with general methods such as getCommonName and compareTo
public abstract class Animal implements Comparable
{
    //String keeping track of the latinName of the animal
    private String latinName;
    //String keeping track of the common name of the animal
    private String commonName;

    /**
     * Constructs an Animal object with latinName set to initLatinName and commonName set to initCommonName
     * @param initLatinName the latinName of the animal
     * @param initCommonName the commonName of the animal
     */
    public Animal(String initLatinName, String initCommonName)
    {
        latinName = initLatinName;
        commonName = initCommonName;
    }

    /**
     * returns the latin name of the animal
     * @return the latin name of the animal
     */
    public String getLatinName()
    {
        return latinName;
    }

    public void setLatinName(String newLatinName)
    {
        latinName = newLatinName;
    }

    public String getCommonName()
    {
        return commonName;
    }

    public void setCommonName(String newCommonName)
    {
        commonName = newCommonName;
    }

    public abstract String speak();

    public int compareTo(Object other)
    {
        if (other == null)
        {
            throw new NullPointerException();
        } else if (!(other instanceof Animal))
        {
            throw new IllegalArgumentException();
        } else
        {
            return commonName.compareTo(((Animal) other).getCommonName());
        }
    }
}