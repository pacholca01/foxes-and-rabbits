import java.util.List;

/**
 * Trees reproduce every 5 turns.
 * They never move or age.
 * 60% chance of dying in fire.
 */
public class Tree extends Plant
{
    private static final int REPRODUCTION_INTERVAL = 5;
    private static final double PROBABILITY_OF_DYING = 0.60;

    public Tree(Location loc)
    {
        super(loc);
    }

    @Override
    public void act(Field currentField, Field nextFieldState)
    {
        turnsSinceReproduction++;

        List<Location> free = nextFieldState.getFreeAdjacentLocations(location);

        if(turnsSinceReproduction >= REPRODUCTION_INTERVAL)
        {
            tryToReproduce(nextFieldState, free);
            turnsSinceReproduction = 0;
        }

        nextFieldState.placeTree(this, location);
    }

    @Override
    protected void tryToReproduce(Field nextFieldState, List<Location> free)
    {
        if(free.isEmpty()) return;

        Location birthLoc = free.get(0);

        // Trees may overwrite grass to obey 10-vegetation max rule
        nextFieldState.clearGrassAt(birthLoc);

        nextFieldState.placeTree(new Tree(birthLoc), birthLoc);
    }

    @Override
    public double getFireDeathProbability()
    {
        return PROBABILITY_OF_DYING;
    }
}
