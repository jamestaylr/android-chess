package net.chess;

import sofia.graphics.Color;

// -------------------------------------------------------------------------
/**
 * Contains data about a piece.
 *
 * @author James Taylor <jamestay@vt.edu>
 * @version 2014.12.03
 */
public class Piece
{
    private Type            type;
    private Location        location;
    private TextShapeFilled text;


    // -------------------------------------------------------------------------
    /**
     * Enumerated list of possible piece types.
     *
     * @author James Taylor <jamestay@vt.edu>
     * @version 2014.12.03
     */
    public enum Type
    {
        /**
         * A white king piece.
         */
        WHITE_KING,
        /**
         * A white queen piece.
         */
        WHITE_QUEEN,
        /**
         * A white bishop piece.
         */
        WHITE_BISHOP,
        /**
         * A white knight piece.
         */
        WHITE_KNIGHT,
        /**
         * A white rook piece.
         */
        WHITE_ROOK,
        /**
         * A white pawn piece.
         */
        WHITE_PAWN,
        /**
         * A black king piece.
         */
        BLACK_KING,
        /**
         * A black queen piece.
         */
        BLACK_QUEEN,
        /**
         * A black bishop piece.
         */
        BLACK_BISHOP,
        /**
         * A black knight piece.
         */
        BLACK_KNIGHT,
        /**
         * A black rook piece.
         */
        BLACK_ROOK,
        /**
         * A place pawn piece.
         */
        BLACK_PAWN
    }


    // ----------------------------------------------------------
    /**
     * Create a new Piece object.
     *
     * @param type the type of the piece
     * @param location the location of the symbol on the board
     */
    public Piece(Type type, Location location)
    {
        this.type = type;
        this.location = location;
        this.revalidate();
    }


    // ----------------------------------------------------------
    /**
     * Gets the location of the piece on the board.
     *
     * @return location the location of the piece on the board
     */
    public Location getLocation()
    {
        return location;
    }


    // ----------------------------------------------------------
    /**
     * Sets the location of the piece on the board.
     *
     * @param newLocation the new location of the piece
     */
    public void setLocation(Location newLocation)
    {
        location = newLocation;
        this.revalidate();
    }


    // ----------------------------------------------------------
    /**
     * Gets the symbol representation of the piece on the board.
     *
     * @return symbol the symbol representation of the piece
     */
    public char getSymbol()
    {
        switch (type)
        {
            case WHITE_KING:
                return (char)0x2654;
            case WHITE_QUEEN:
                return (char)0x2655;
            case WHITE_BISHOP:
                return (char)0x2657;
            case WHITE_KNIGHT:
                return (char)0x2658;
            case WHITE_ROOK:
                return (char)0x2656;
            case WHITE_PAWN:
                return (char)0x2659;
            case BLACK_KING:
                return (char)0x265A;
            case BLACK_QUEEN:
                return (char)0x265B;
            case BLACK_BISHOP:
                return (char)0x265D;
            case BLACK_KNIGHT:
                return (char)0x265E;
            case BLACK_ROOK:
                return (char)0x265C;
            case BLACK_PAWN:
                return (char)0x265F;
        }

        return 0;
    }


    // ----------------------------------------------------------
    /**
     * Gets the type of the piece.
     *
     * @return type the type of the piece
     */
    public Type getType()
    {
        return type;

    }


    // ----------------------------------------------------------
    /**
     * Creates the new {@link TextShapeFilled} object based on the changed
     * position of the object. Called before {@link View#redraw()} to speed up
     * the drawing process.
     *
     * @param tileLength the length of the tile as determined by the
     *        {@link View}
     */
    public void revalidate()
    {
        TextShapeFilled textShape =
            new TextShapeFilled("" + this.getSymbol(), this.getLocation()
                .rank() * View.getTileLength(), this.getLocation().file() * View.getTileLength());
        textShape.setTypeSize(View.getTileLength() / 7);

        textShape.setColor(Color.black);

        setShape(textShape);
    }


    // ----------------------------------------------------------
    /**
     * Gets the {@link TextShapeFilled} graphics object for the piece.
     *
     * @return the graphics object for the piece
     */
    public TextShapeFilled getShape()
    {
        return text;
    }


    // ----------------------------------------------------------
    /**
     * Sets the {@link TextShapeFilled} graphics object for the piece.
     *
     * @param textShape the graphics object for the piece
     */
    public void setShape(TextShapeFilled textShape)
    {
        this.text = textShape;
    }
}
