package net.chess;

/**
 * Represents a move as two Locations and the piece that is moved
 *
 * @author James Taylor <jamestay@vt.edu>
 * @author Evan Teitelman <para@vt.edu>
 * @version 2014.12.03
 */
public class Move
{

    private Location from;
    private Location to;
    private Piece    piece;
    private Piece    removed;


    /**
     * Create new Move object.
     *
     * @param piece
     *            the piece moved
     * @param from
     *            the original location of the piece
     * @param to
     *            the final location of the piece
     */
    public Move(Piece piece, Location from, Location to)
    {
        this.piece = piece;
        this.from = from;
        this.to = to;
    }


    /**
     * Create new Move object; used if a move eliminates a piece from the board.
     *
     * @param piece
     *            the piece moved
     * @param from
     *            the original location of the piece
     * @param to
     *            the final location of the piece
     * @param removed
     *            the piece removed from the board
     */
    public Move(Piece piece, Location from, Location to, Piece removed)
    {
        this(piece, from, to);

        this.removed = removed;

    }


    /**
     * Get the original location of the piece.
     *
     * @return from the original location of the piece
     */
    public Location from()
    {
        return from;
    }


    /**
     * Get the final location of the piece.
     *
     * @return to the final location of the piece
     */
    public Location to()
    {
        return to;
    }


    /**
     * Get the moved piece.
     *
     * @return piece that was moved
     */
    public Piece piece()
    {
        return piece;
    }


    /**
     * Get the piece removed from the board during the move operation.
     *
     * @return piece that was removed
     */
    public Piece getRemovedPiece()
    {
        return removed;
    }
}
