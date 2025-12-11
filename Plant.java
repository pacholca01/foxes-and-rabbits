import java.util.List;
import java.util.Random;

/**
 * Superclass for all plant types (Grass, Tree).
 * Plants do not age or move.
 * They only reproduce and die in fire events.
 */
public abstract class Plant
{
    protected boolean alive;
    protected Location location;
    protected int turnsSinceReproduction;
    protected static final Random rand = Randomizer.getRandom();

    public Plant(Location location)
    {
        this.location = location;
        this.alive = true;
        this.turnsSinceReproduction = rand.nextInt(3); // random offset so no lockstep reproduction
    }

    public boolean isAlive()
    {
        return alive;
    }

    public void setDead()
    {
        alive = false;
        location = null;
    }

    public Location getLocation()
    {
        return location;
    }

    /**
     * Plants do not move. They only act by attempting reproduction.
     */
    public abstract void act(Field currentField, Field nextFieldState);

    /**
     * Each plant subtype defines:
     * - reproduction interval
     * - reproduction rules
     * - fire mortality chance
     */
    protected abstract void tryToReproduce(Field nextFieldState, List<Location> adjacent);
    
    public abstract double getFireDeathProbability();
}
