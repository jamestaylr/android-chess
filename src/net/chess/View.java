package net.chess;

import android.widget.TextView;
import sofia.graphics.*;
import sofia.app.ShapeScreen;

// -------------------------------------------------------------------------
/**
 * Handles the application view and associated graphics functions.
 * @author James Taylor <jamestay@vt.edu>
 * @version 2012.12.02
 */
public class View
    extends ShapeScreen
{

    private RectangleShape[][] tiles;
    private Board              board;

    private static int         tileLength;
    private Location           from;
    private Location           to;
    private Piece              target;
    private TextView           status;


    // ----------------------------------------------------------
    /**
     * Initializes the screen and adds elements; entry point for the
     * application.
     */
    public void initialize()
    {
        board = new Board();

        final int tileNumber = 8; // width and height of a standard chess board

        tileLength = (int)(Math.min(getWidth(), getHeight()) / tileNumber);

        tiles = new RectangleShape[tileNumber][tileNumber];

        for (int i = 0; i < tileNumber; i++)
        {
            for (int j = 0; j < tileNumber; j++)
            {
                RectangleShape tile =
                    new RectangleShape(
                        j * tileLength,
                        i * tileLength,
                        (j * tileLength) + tileLength,
                        (i * tileLength) + tileLength);

                if ((i + j) % 2 == 0)
                {
                    tile.setFillColor(Color.bisque);
                }
                else
                {
                    tile.setFillColor(Color.white);
                }
                tiles[i][j] = tile;
                add(tile);
            }
        }

        for (Piece piece : board.getPieces())
        {
            TextShapeFilled text =
                new TextShapeFilled("" + piece.getSymbol(), piece.getLocation()
                    .rank() * tileLength, piece.getLocation().file()
                    * tileLength);
            text.setTypeSize(tileLength / 5);

            text.setColor(Color.black);

            piece.setShape(text);
            add(text);
        }

        status.setText("White's turn!");
    }


    // ----------------------------------------------------------
    /**
     * Redraws components at a given location, refreshing the screen.
     */
    protected void redraw(Location location)
    {
        Piece piece = board.getPieceAtLocation(location);

        remove(tiles[location.file()][location.rank()]);
        add(tiles[location.file()][location.rank()]);

        if (piece != null)
        {
            // if there is a piece at the location, remove the old shape and
            // redraw it
            remove(piece.getShape());
            piece.revalidate(tileLength);
            add(piece.getShape());
        }

    }


    // ----------------------------------------------------------
    /**
     * Gets the size of the tile.
     * @return the calculated tile length
     */
    public static int getTileLength()
    {
        return tileLength;
    }


    // ----------------------------------------------------------
    /**
     * Called when the user touches the screen.
     * @param x the x coordinate location of the touch event
     * @param y the y coordinate location of the touch event
     */
    public void onTouchDown(float x, float y)
    {
        processInput(x, y);
    }


    // ----------------------------------------------------------
    /**
     * Processes the user input.
     * @param x the x-coordinate location of the touch event
     * @param y the y-coordinate location of the touch event
     */
    private void processInput(float x, float y)
    {
        int ix = (int)(x / tileLength);
        int iy = (int)(y / tileLength);

        if (((ix > 7) || (ix < 0)) || ((iy > 7) || (iy < 0)))
        {
            return;
        }

        if (from == null)
        {
            from = new Location(ix, iy);

            // tries to see if the location clicked has a piece
            for (Piece piece : board.getPieces())
            {
                if (piece.getLocation().equals(from))
                {
                    target = piece;
                    break;
                }

            }

            if ((target == null) || (!(board.properTurn(target))))
            {
                // this location is not valid
                from = null;
            }

        }
        else
        {
            to = new Location(ix, iy);
            board.move(target, from, to, this);

            redraw(to);
            redraw(from);

            from = null;

            if (board.getTurns() % 2 == 1)
            {
                status.setText("Black's turn!");
            }
            else
            {
                status.setText("White's turn!");
            }

        }

    }


    // ----------------------------------------------------------
    /**
     * Called when the undo button is clicked.
     */
    public void undoClicked()
    {
        Move move = board.undoClicked();

        if (board.getTurns() % 2 == 1)
        {
            status.setText("Black's turn!");
        }
        else
        {
            status.setText("White's turn!");
        }

        if (move != null)
        {
            redraw(move.from());
            redraw(move.to());
        }
    }


    // ----------------------------------------------------------
    /**
     * Gets the chess board object; for testing purposes.
     * @return board the chess board object
     */
    protected Board getBoard()
    {
        return board;
    }

}
