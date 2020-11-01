package oldMacDonald;

import java.util.ArrayList;
import java.util.List;

public class OldMacDonaldsFarm
{
    private String farmerName;
    private List<Animal> farmAnimals;

    public OldMacDonaldsFarm()
    {
        farmerName = "Old MacDonald";
        farmAnimals = new ArrayList<Animal>();
    }

    public static void main(String[] args)
    {
        OldMacDonaldsFarm singer = new OldMacDonaldsFarm();
        singer.farmAnimals.add(new Chicken());
        singer.singVerse();
        singer.farmAnimals.add(new Chick());
        singer.singVerse();
        singer.farmAnimals.add(new Rooster());
        singer.singVerse();
        singer.farmAnimals.add(new Pig());
        singer.singVerse();
    }

    public void singVerse()
    {
        String phrase1 = farmerName + " had a farm,";
        String ei = " E-I-E-I-O.";
        for (int i = farmAnimals.size() - 1; i >= 0; i--)
        {
            Animal a = farmAnimals.get(i);
            System.out.println(phrase1 + ei + " and on his farm he had some " +
                    a.getCommonName() + " ," + ei);
            System.out.println("With a " + a.speak() + "-" + a.speak() + " here, and an " + a.speak() + "-" + a.speak() + " there,");
            System.out.println("here a " + a.speak() + ", there a " + a.speak() + ", everywhere a " + a.speak() + "-" + a.speak());
        }
        System.out.println(phrase1 + ei);
        System.out.println();
    }
}
