package net.chess;

import junit.framework.TestCase;

// -------------------------------------------------------------------------
/**
 * Tests for the {@link Move} class.
 *
 * @author Skylar Edwards <skyed@vt.edu>
 * @version 2014.12.03
 */

public class MoveTests
    extends TestCase
{
    // ~ Fields ................................................................

    private Move  move1;
    private Move  move2;
    private Piece piece;
    private Piece piece2;


    // ~ Public methods ........................................................

    // ----------------------------------------------------------
    /**
     * Creates sample pieces and move objects for each test method.
     */
    protected void setUp()
    {
        piece = new Piece(Piece.Type.WHITE_QUEEN, new Location(4, 4));
        piece2 = new Piece(Piece.Type.BLACK_PAWN, new Location(6, 4));
        move1 = new Move(piece, new Location(4, 4), new Location(6, 4));
        move2 = new Move(piece, new Location(4, 4), new Location(6, 4), piece2);
    }


    // ----------------------------------------------------------
    /**
     * Tests the {@link Move#from()} method.
     */
    public void testFrom()
    {
        assertEquals(new Location(4, 4), move1.from());
    }


    // ----------------------------------------------------------
    /**
     * Tests the {@link Move#to()} method.
     */
    public void testTo()
    {
        assertEquals(new Location(6, 4), move1.to());
    }


    // ----------------------------------------------------------
    /**
     * Tests the {@link Move#piece()} method.
     */
    public void testPiece()
    {
        assertEquals(piece, move1.piece());
    }


    // ----------------------------------------------------------
    /**
     * Tests the {@link Move#getRemovedPiece()} method.
     */
    public void testGetRemovedPiece()
    {
        assertEquals(piece2, move2.getRemovedPiece());
    }

}
