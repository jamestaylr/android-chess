package net.chess;

import java.lang.IllegalStateException;

// -------------------------------------------------------------------------
/**
 * Represents locations and performs location-based functions.
 *
 * @author James Taylor <jamestay@vt.edu>
 * @version 2014.12.03
 */
public class Location
{
    private int rank;
    private int file;


    // ----------------------------------------------------------
    /**
     * Creates new Location object.
     *
     * @param rank the x-coordinate location of the position 0-7
     * @param file the y-coordinate location of the position 0-7
     */
    public Location(int rank, int file)
    {
        if (rank > 7 || rank < 0 || file > 7 || file < 0)
        {
            throw new IllegalStateException("The location is invalid!");
        }
        this.rank = rank;
        this.file = file;
    }


    // ----------------------------------------------------------
    /**
     * Returns the rank of the location (x-coordinate).
     *
     * @return rank the x-coordinate position
     */
    public int rank()
    {
        return rank;
    }


    // ----------------------------------------------------------
    /**
     * Returns the file of the location (y-coordinate).
     *
     * @return file the y-coordinate position
     */
    public int file()
    {
        return file;
    }


    // ----------------------------------------------------------
    /**
     * Determines if two locations are equal.
     *
     * @return true if the locations are equal; false if the locations are not
     *         equal
     */
    @Override
    public boolean equals(Object obj)
    {
        if (!(obj instanceof Location))
        {
            return false;
        }

        Location otherLocation = (Location)obj;

        return ((rank == otherLocation.rank()) && (file == otherLocation.file()));
    }

}
