import java.util.List;

/**
 * Grass reproduces every 2 turns.
 * Never moves, never ages.
 * 75% chance of dying in fire.
 */
public class Grass extends Plant
{
    private static final int REPRODUCTION_INTERVAL = 2;
    private static final double PROBABILITY_OF_DYING = 0.75;

    public Grass(Location loc)
    {
        super(loc);
    }

    @Override
    public void act(Field currentField, Field nextFieldState)
    {
        turnsSinceReproduction++;

        // Gather adjacent free squares
        List<Location> free = nextFieldState.getFreeAdjacentLocations(location);

        // Attempt reproduction
        if(turnsSinceReproduction >= REPRODUCTION_INTERVAL)
        {
            tryToReproduce(nextFieldState, free);
            turnsSinceReproduction = 0;
        }

        // Grass does not move
        nextFieldState.placeGrass(this, location);
    }

    @Override
    protected void tryToReproduce(Field nextFieldState, List<Location> free)
    {
        if(free.isEmpty()) return;

        Location birthLoc = free.get(0);
        nextFieldState.placeGrass(new Grass(birthLoc), birthLoc);
    }

    @Override
    public double getFireDeathProbability()
    {
        return PROBABILITY_OF_DYING;
    }
}
