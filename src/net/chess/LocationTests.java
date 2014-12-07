package net.chess;

import student.TestCase;

// -------------------------------------------------------------------------
/**
 * Tests for the {@link Location} class.
 *
 * @author James Taylor <jamestay@vt.edu>
 * @version 2014.12.01
 */
public class LocationTests
    extends TestCase
{
    // ~ Fields ................................................................

    private Location location1;
    private Location location2;


    // ~ Public methods ........................................................

    // ----------------------------------------------------------
    /**
     * Creates sample locations for each test method.
     */
    public void setUp()
    {
        location1 = new Location(0, 1);
        location2 = new Location(2, 3);
    }


    // ----------------------------------------------------------
    /**
     * Tests the {@link Location} constructor.
     */
    public void testCreate()
    {
        Location location;

        Exception thrown = null;
        try
        {
            location = new Location(20, 20);
        }
        catch (IllegalStateException e)
        {
            thrown = e;
        }
        assertNotNull(thrown);
        assertTrue(thrown instanceof IllegalStateException);
        assertEquals("The location is invalid!", thrown.getMessage());

    }


    // ----------------------------------------------------------
    /**
     * Tests the {@link Location#rank()} method.
     */
    public void testRank()
    {
        assertEquals(0, location1.rank());
        assertEquals(2, location2.rank());
    }


    // ----------------------------------------------------------
    /**
     * Tests the {@link Location#file()} method.
     */
    public void testFile()
    {
        assertEquals(1, location1.file());
        assertEquals(3, location2.file());
    }


    // ----------------------------------------------------------
    /**
     * Tests the {@link Location#equals(Object)} method.
     */
    public void testEquals()
    {
        Location location = new Location(2, 3);

        assertFalse(location1.equals(location));
        assertTrue(location2.equals(location));
    }

}
