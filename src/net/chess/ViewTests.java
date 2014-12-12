package net.chess;

import android.widget.Button;
import java.util.LinkedList;
import sofia.graphics.ShapeView;

// -------------------------------------------------------------------------
/**
 * Defines test classes for the {@link View} GUI class.
 *
 * @author James Taylor <jamestay@vt.edu>
 * @version 2014.12.02
 */
public class ViewTests
    extends student.AndroidTestCase<View>
{
    // ~ Fields ................................................................

    private ShapeView shapeView;
    private Button    undo;


    // ~ Constructors ..........................................................

    // ----------------------------------------------------------
    /**
     * Test cases that extend AndroidTestCase must have a parameterless
     * constructor that calls super() and passes it the screen/activity class
     * being tested.
     */
    public ViewTests()
    {
        super(View.class);
    }


    // ~ Public methods ........................................................

    // ----------------------------------------------------------
    /**
     * Initializes the text fixtures.
     */
    public void setUp()
    {
        // nothing here
    }


    // ----------------------------------------------------------
    /**
     * Tests the {@link View#initialize()} method.
     */
    public void testInitialize()
    {
        assertNotNull(getScreen().getBoard());
    }


    // ----------------------------------------------------------
    /**
     * Tests the {@link View#onTouchDown(float, float)} method.
     */
    public void testMovePiece1()
    {
        LinkedList<Piece> pieces = getScreen().getBoard().getPieces();

        Piece found = null;
        for (Piece piece : pieces)
        {
            if (piece.getLocation().equals(new Location(1, 2)))
            {
                found = piece;
            }
        }

        // there was not a piece at location (1, 2) in the default
        assertNull(found);

    }


    // ----------------------------------------------------------
    /**
     * Tests the {@link View#onTouchDown(float, float)} method.
     */
    public void testMovePiece2()
    {
        // moves a black pawn forward a space
        tapCell(1, 1);
        tapCell(1, 2);

        LinkedList<Piece> pieces = getScreen().getBoard().getPieces();

        Piece found = null;
        for (Piece piece : pieces)
        {
            if (piece.getLocation().equals(new Location(1, 2)))
            {
                found = piece;
            }
        }

        assertNotNull(found);

    }


    // ----------------------------------------------------------
    /**
     * Tests the {@link View#undoClicked()} method.
     */
    public void testUndoClicked1()
    {
        // moves a black pawn forward a space
        tapCell(1, 1);
        tapCell(1, 2);

        click(undo);

        LinkedList<Piece> pieces = getScreen().getBoard().getPieces();

        Piece found = null;
        for (Piece piece : pieces)
        {
            if (piece.getLocation().equals(new Location(1, 2)))
            {
                found = piece;
            }
        }

        // there should be no piece at the location because the move was undone
        assertNull(found);

    }


    // ----------------------------------------------------------
    /**
     * Tests the {@link View#undoClicked()} method.
     */
    public void testUndoClicked2()
    {
        click(undo);

        // makes sure popping an empty stack does not crash the application
        assertNotNull(getScreen().getBoard());

    }


    // ~ Private methods .......................................................

    // ----------------------------------------------------------
    /**
     * Simulates touching down on the middle of the specified cell in the chess
     * board.
     *
     * @param x - the x-coordinate location of the touch event
     * @param y - the y-coordinate location of the touch event
     */
    private void tapCell(int x, int y)
    {
        int tileLength =
            (int)(Math.min(getScreen().getWidth(), getScreen().getHeight()) / 8);

        touchDown(shapeView, (x + 0.5f) * tileLength, (y + 0.5f) * tileLength);
        touchUp();
    }

}
