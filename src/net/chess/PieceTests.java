package net.chess;

import net.chess.Piece.Type;
import student.TestCase;

// -------------------------------------------------------------------------
/**
 * Tests for the {@link Piece} class.
 *
 * @author James Taylor <jamestay@vt.edu>
 * @version 2014.12.01
 */
public class PieceTests
    extends TestCase
{
    // ~ Fields ................................................................

    private Piece piece1;
    private Piece piece2;


    // ~ Public methods ........................................................

    // ----------------------------------------------------------
    /**
     * Creates a brand new, empty stack for each test method.
     */
    public void setUp()
    {
        piece1 = new Piece(Type.WHITE_KING, new Location(4, 7));
        piece2 = new Piece(Type.BLACK_PAWN, new Location(0, 1));
    }


    // ----------------------------------------------------------
    /**
     * Tests the {@link Piece#getLocation()} method.
     */
    public void testGetLocation()
    {
        Location location = new Location(4, 7);

        assertTrue(piece1.getLocation().equals(location));
        assertFalse(piece2.getLocation().equals(location));

    }


    // ----------------------------------------------------------
    /**
     * Tests the {@link Piece#setLocation(Location)} method.
     */
    public void testSetLocation()
    {
        Location oldLocation = piece1.getLocation();
        Location newLocation = new Location(5, 6);

        piece1.setLocation(newLocation);

        assertTrue(piece1.getLocation().equals(newLocation));
        assertFalse(piece2.getLocation().equals(oldLocation));

    }


    // ----------------------------------------------------------
    /**
     * Tests the {@link Piece#getLocation()} method.
     */
    public void testGetSymbol()
    {
        char symbol = (char)0x2654;

        assertEquals(symbol, piece1.getSymbol());
        assertNotSame(symbol, piece2.getSymbol());

    }

}
