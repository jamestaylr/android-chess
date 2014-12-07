package net.chess;

import java.util.LinkedList;
import net.chess.Piece.Type;
import student.TestCase;

// -------------------------------------------------------------------------
/**
 * Tests for the {@link Board} class.
 *
 * @author James Taylor <jamestay@vt.edu>
 * @version 2014.12.01
 */
public class BoardTests
    extends TestCase
{
    // ~ Fields ................................................................

    private Board board;


    // ~ Public methods ........................................................

    // ----------------------------------------------------------
    /**
     * Creates sample locations for each test method.
     */
    public void setUp()
    {
        board = new Board();
    }


    // ----------------------------------------------------------
    /**
     * Tests the {@link Board} constructor.
     */
    public void testCreate()
    {
        assertNotNull(board.getPieces());
        assertEquals(32, board.getPieces().size());

    }


    // ----------------------------------------------------------
    /**
     * Tests the {@link Board#move(Piece, Location, Location, View)} method.
     */
    public void testMovePawn1()
    {
        Location location = new Location(0, 6);
        Piece piece = this.getPieceAtLocation(location);
        board.move(piece, location, new Location(0, 4), null);

        assertEquals(piece, this.getPieceAtLocation(new Location(0, 4)));
    }

 // ----------------------------------------------------------
    /**
     * Tests the {@link Board#move(Piece, Location, Location, View)} method.
     */
    public void testMovePawn2()
    {
        Location location = new Location(0, 6);
        Piece piece = this.getPieceAtLocation(location);
        board.move(piece, location, new Location(2, 2), null);

        assertNull(this.getPieceAtLocation(new Location(2, 2)));
    }


    // ----------------------------------------------------------
    /**
     * Tests the {@link Board#move(Piece, Location, Location, View)} method.
     */
    public void testMoveRook1()
    {
        removePawns();
        Location location = new Location(0, 7);
        Piece piece = this.getPieceAtLocation(location);
        board.move(piece, location, new Location(0, 4), null);

        assertEquals(piece, this.getPieceAtLocation(new Location(0, 4)));
    }


    // ----------------------------------------------------------
    /**
     * Tests the {@link Board#move(Piece, Location, Location, View)} method.
     */
    public void testMoveRook2()
    {
        removePawns();
        Location location = new Location(0, 7);
        Piece piece = this.getPieceAtLocation(location);
        board.move(piece, location, new Location(1, 2), null);

        assertNull(this.getPieceAtLocation(new Location(1, 2)));
    }


    // ----------------------------------------------------------
    /**
     * Tests the {@link Board#move(Piece, Location, Location, View)} method.
     */
    public void testMoveKnight1()
    {
        removePawns();
        Location location = new Location(1, 7);
        Piece piece = this.getPieceAtLocation(location);
        board.move(piece, location, new Location(2, 5), null);

        assertEquals(piece, this.getPieceAtLocation(new Location(2, 5)));
    }


    // ----------------------------------------------------------
    /**
     * Tests the {@link Board#move(Piece, Location, Location, View)} method.
     */
    public void testMoveKnight2()
    {
        removePawns();
        Location location = new Location(1, 7);
        Piece piece = this.getPieceAtLocation(location);
        board.move(piece, location, new Location(0, 2), null);

        assertNull(this.getPieceAtLocation(new Location(0, 2)));
    }


    // ----------------------------------------------------------
    /**
     * Tests the {@link Board#move(Piece, Location, Location, View)} method.
     */
    public void testMoveBishop1()
    {
        removePawns();
        Location location = new Location(2, 7);
        Piece piece = this.getPieceAtLocation(location);
        board.move(piece, location, new Location(5, 4), null);

        assertEquals(piece, this.getPieceAtLocation(new Location(5, 4)));
    }


    // ----------------------------------------------------------
    /**
     * Tests the {@link Board#move(Piece, Location, Location, View)} method.
     */
    public void testMoveBishop2()
    {
        removePawns();
        Location location = new Location(2, 7);
        Piece piece = this.getPieceAtLocation(location);
        board.move(piece, location, new Location(0, 2), null);

        assertNull(this.getPieceAtLocation(new Location(0, 2)));
    }


    // ----------------------------------------------------------
    /**
     * Tests the {@link Board#move(Piece, Location, Location, View)} method.
     */
    public void testMoveQueen1()
    {
        removePawns();
        Location location = new Location(3, 7);
        Piece piece = this.getPieceAtLocation(location);
        board.move(piece, location, new Location(3, 4), null);

        assertEquals(piece, this.getPieceAtLocation(new Location(3, 4)));
    }


    // ----------------------------------------------------------
    /**
     * Tests the {@link Board#move(Piece, Location, Location, View)} method.
     */
    public void testMoveQueen2()
    {
        removePawns();
        Location location = new Location(3, 7);
        Piece piece = this.getPieceAtLocation(location);
        board.move(piece, location, new Location(0, 4), null);

        assertEquals(piece, this.getPieceAtLocation(new Location(0, 4)));
    }


    // ----------------------------------------------------------
    /**
     * Tests the {@link Board#move(Piece, Location, Location, View)} method.
     */
    public void testMoveQueen3()
    {
        removePawns();
        Location location = new Location(3, 7);
        Piece piece = this.getPieceAtLocation(location);
        board.move(piece, location, new Location(0, 2), null);

        assertNull(this.getPieceAtLocation(new Location(0, 2)));
    }


    // ----------------------------------------------------------
    /**
     * Tests the {@link Board#move(Piece, Location, Location, View)} method.
     */
    public void testMoveKing1()
    {
        removePawns();
        Location location = new Location(4, 7);
        Piece piece = this.getPieceAtLocation(location);
        board.move(piece, location, new Location(4, 6), null);

        assertEquals(piece, this.getPieceAtLocation(new Location(4, 6)));
    }


    // ----------------------------------------------------------
    /**
     * Tests the {@link Board#move(Piece, Location, Location, View)} method.
     */
    public void testMoveKing2()
    {
        removePawns();
        Location location = new Location(4, 7);
        Piece piece = this.getPieceAtLocation(location);
        board.move(piece, location, new Location(4, 5), null);

        assertNull(this.getPieceAtLocation(new Location(4, 5)));
    }


    // ~ Private methods .......................................................

    /**
     * Removes the pawns from the board to test other piece movements.
     */
    private void removePawns()
    {
        LinkedList<Piece> toRemove = new LinkedList<Piece>();

        for (Piece piece : board.getPieces())
        {
            if ((piece.getType() == Type.BLACK_PAWN)
                || (piece.getType() == Type.WHITE_PAWN))
            {
                toRemove.add(piece);
            }
        }

        for (Piece piece : toRemove)
        {
            board.getPieces().remove(piece);
        }

    }


    // ----------------------------------------------------------
    /**
     * Gets the piece at a specific location and returns it if it exists.
     *
     * @param target
     *            the location to be checked
     * @return the piece in the location if one exists, null otherwise
     */
    private Piece getPieceAtLocation(Location target)
    {
        for (Piece piece : board.getPieces())
        {
            if (piece.getLocation().equals(target))
            {
                return piece;
            }
        }
        return null;
    }

}
