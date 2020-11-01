package oldMacDonald;

public class Chicken extends Animal
{
    public Chicken()
    {
        this("chicken");
    }

    public Chicken(String chickenType)
    {
        super("Gallus Gallus Domesticus", chickenType);
    }

    public String speak()
    {
        return "bawk";
    }
}
