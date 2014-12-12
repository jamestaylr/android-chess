package net.chess;

import android.app.AlertDialog;
import net.chess.Piece.Type;
import java.util.EmptyStackException;
import java.util.Stack;
import java.util.LinkedList;

// -------------------------------------------------------------------------
/**
 * Defines the board and board functions.
 * @author James Taylor <jamestay@vt.edu>
 * @author Skylar Edwards <skyed@vt.edu>
 * @version 2014.12.03
 */
public class Board
{
    private LinkedList<Piece> pieces = new LinkedList<Piece>();
    private Stack<Move>       moves  = new Stack<Move>();

    private int               turn;


    // ----------------------------------------------------------
    /**
     * Creates a new board object.
     */
    public Board()
    {
        addPieces();
    }


    // ----------------------------------------------------------
    /**
     * Adds pieces to the board at default locations.
     */
    private void addPieces()
    {
        // 'white' pieces
        pieces.add(new Piece(Type.WHITE_KING, new Location(4, 7)));
        pieces.add(new Piece(Type.WHITE_QUEEN, new Location(3, 7)));
        pieces.add(new Piece(Type.WHITE_ROOK, new Location(0, 7)));
        pieces.add(new Piece(Type.WHITE_ROOK, new Location(7, 7)));
        pieces.add(new Piece(Type.WHITE_BISHOP, new Location(2, 7)));
        pieces.add(new Piece(Type.WHITE_BISHOP, new Location(5, 7)));
        pieces.add(new Piece(Type.WHITE_KNIGHT, new Location(1, 7)));
        pieces.add(new Piece(Type.WHITE_KNIGHT, new Location(6, 7)));
        pieces.add(new Piece(Type.WHITE_PAWN, new Location(0, 6)));
        pieces.add(new Piece(Type.WHITE_PAWN, new Location(1, 6)));
        pieces.add(new Piece(Type.WHITE_PAWN, new Location(2, 6)));
        pieces.add(new Piece(Type.WHITE_PAWN, new Location(3, 6)));
        pieces.add(new Piece(Type.WHITE_PAWN, new Location(4, 6)));
        pieces.add(new Piece(Type.WHITE_PAWN, new Location(5, 6)));
        pieces.add(new Piece(Type.WHITE_PAWN, new Location(6, 6)));
        pieces.add(new Piece(Type.WHITE_PAWN, new Location(7, 6)));

        // 'black' pieces
        pieces.add(new Piece(Type.BLACK_KING, new Location(3, 0)));
        pieces.add(new Piece(Type.BLACK_QUEEN, new Location(4, 0)));
        pieces.add(new Piece(Type.BLACK_ROOK, new Location(0, 0)));
        pieces.add(new Piece(Type.BLACK_ROOK, new Location(7, 0)));
        pieces.add(new Piece(Type.BLACK_BISHOP, new Location(2, 0)));
        pieces.add(new Piece(Type.BLACK_BISHOP, new Location(5, 0)));
        pieces.add(new Piece(Type.BLACK_KNIGHT, new Location(1, 0)));
        pieces.add(new Piece(Type.BLACK_KNIGHT, new Location(6, 0)));
        pieces.add(new Piece(Type.BLACK_PAWN, new Location(0, 1)));
        pieces.add(new Piece(Type.BLACK_PAWN, new Location(1, 1)));
        pieces.add(new Piece(Type.BLACK_PAWN, new Location(2, 1)));
        pieces.add(new Piece(Type.BLACK_PAWN, new Location(3, 1)));
        pieces.add(new Piece(Type.BLACK_PAWN, new Location(4, 1)));
        pieces.add(new Piece(Type.BLACK_PAWN, new Location(5, 1)));
        pieces.add(new Piece(Type.BLACK_PAWN, new Location(6, 1)));
        pieces.add(new Piece(Type.BLACK_PAWN, new Location(7, 1)));
    }


    // ----------------------------------------------------------
    /**
     * Called by the view when the user interacts with the screen.
     * @param target the target piece to move
     * @param from the initial location of the piece to move
     * @param to the final location
     * @param view the screen activity; used for triggering screen events
     */
    public void move(Piece target, Location from, Location to, View view)
    {

        if (isValid(target, to))
        {
            Piece piece = getPieceAtLocation(to);

            // there is a piece that needs to be removed from the board
            if (piece != null)
            {
                moves.push(new Move(target, from, to, piece));

                if (piece.getType() == Type.BLACK_KING)
                {
                    new AlertDialog.Builder(view).setTitle("Game Over!")
                        .setMessage("White wins!")
                        .setIcon(android.R.drawable.ic_dialog_alert).show();

                    resetBoard();
                    return;
                }
                else if (piece.getType() == Type.WHITE_KING)
                {
                    new AlertDialog.Builder(view).setTitle("Game Over!")
                        .setMessage("Black wins!")
                        .setIcon(android.R.drawable.ic_dialog_alert).show();

                    resetBoard();
                    return;
                }
                getPieces().remove(piece);
            }
            else
            {
                moves.push(new Move(target, from, to));
            }

            target.setLocation(to);

            turn++;

        }
    }


    // ----------------------------------------------------------
    /**
     * Resets the board object and sets pieces to their default locations.
     */
    private void resetBoard()
    {
        pieces = new LinkedList<Piece>();
        moves = new Stack<Move>();

        turn = 0;

        this.addPieces();

    }


    // ----------------------------------------------------------
    /**
     * Called when the undo button is clicked.
     */
    public Move undoClicked()
    {
        Move move;
        try
        {
            move = moves.pop();
        }
        catch (EmptyStackException e)
        {
            return null;
        }

        move.piece().setLocation(move.from());

        if (move.getRemovedPiece() != null)
        {
            getPieces().add(move.getRemovedPiece());
        }
        turn--;

        return move;
    }


    // ----------------------------------------------------------
    /**
     * Checks to see if the move is valid for a piece.
     * @return true if the move is valid; false if the move is not valid
     */
    private boolean isValid(Piece piece, Location to)
    {

        if (piece.getSymbol() == (char)0x2654) // If White King...
        {
            if (Math.abs(piece.getLocation().rank() - to.rank()) <= 1
                && Math.abs(piece.getLocation().file() - to.file()) <= 1)
            {
                // False if there is a piece at that location that is white
                return getPieceAtLocation(to) == null
                    || !(getPieceAtLocation(to).getSymbol() <= (char)0x2659);
            }
            else
            {
                return false;
            }
        }
        else if (piece.getSymbol() == (char)0x265A) // If Black King...
        {
            if (Math.abs(piece.getLocation().rank() - to.rank()) <= 1
                && Math.abs(piece.getLocation().file() - to.file()) <= 1)
            {
                // False if there is a piece at that location that is black
                return getPieceAtLocation(to) == null
                    || !(getPieceAtLocation(to).getSymbol() <= (char)0x2659);
            }
            else
            {
                return false;
            }
        }
        else if (piece.getSymbol() == (char)0x2655 // If Queen...
            || piece.getSymbol() == (char)0x265B)
        {
            // Returns false if there is a white piece for the white queen or if
            // there is a black piece for the black queen at the target location
            if (compareColorOfPieceAtLocation(piece, to))
            {
                return false;
            }
            // Check to make sure the target location is in the same row,
            // column, or diagonal and that there is nothing in the way
            if (piece.getLocation().rank() == to.rank()
                || piece.getLocation().file() == to.file())
            {
                return checkRowsForObstacles(piece, to);
            }
            else if (Math.abs(piece.getLocation().rank() - to.rank()) == Math
                .abs(piece.getLocation().file() - to.file()))
            {
                return checkDiagonalsForObstacles(piece, to);
            }
            else
            {
                return false;
            }
        }
        else if (piece.getSymbol() == (char)0x2656 // If Rook...
            || piece.getSymbol() == (char)0x265C)
        {
            // Returns false if there is a white piece for the white rooks or if
            // there is a black piece for the black rooks at the target location
            if (compareColorOfPieceAtLocation(piece, to))
            {
                return false;
            }
            if (piece.getLocation().rank() == to.rank()
                || piece.getLocation().file() == to.file())
            {
                return checkRowsForObstacles(piece, to);
            }
            else
            {
                return false;
            }
        }
        else if (piece.getSymbol() == (char)0x2657 // If Bishop...
            || piece.getSymbol() == (char)0x265D)
        {
            // Returns false if there is a white piece for the white bishops or
            // if there is a black piece for the black bishops at the target
            // location
            if (compareColorOfPieceAtLocation(piece, to))
            {
                return false;
            }
            if (Math.abs(piece.getLocation().rank() - to.rank()) == Math
                .abs(piece.getLocation().file() - to.file()))
            {
                return checkDiagonalsForObstacles(piece, to);
            }
            else
            {
                return false;
            }
        }
        else if (piece.getSymbol() == (char)0x2658 // If Knight...
            || piece.getSymbol() == (char)0x265E)
        {
            // Returns false if there is a white piece for the white knights or
            // if there is a black piece for the black knights at the target
            // location
            if (compareColorOfPieceAtLocation(piece, to))
            {
                return false;
            }
            if ((Math.abs(piece.getLocation().rank() - to.rank()) == 2 && Math
                .abs(piece.getLocation().file() - to.file()) == 1)
                || Math.abs(piece.getLocation().rank() - to.rank()) == 1
                && Math.abs(piece.getLocation().file() - to.file()) == 2)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else if (piece.getSymbol() == (char)0x2659) // If White Pawn...
        {
            // Returns false if there is a white piece at the target location
            if (compareColorOfPieceAtLocation(piece, to))
            {
                return false;
            }
            // returns false if any piece is forward to the current piece
            if ((Math.abs(piece.getLocation().rank() - to.rank()) == 0)
                && (getPieceAtLocation(new Location(
                    piece.getLocation().rank(),
                    piece.getLocation().file() - 1)) != null))
            {
                return false;
            }
            if (((Math.abs(to.rank() - piece.getLocation().rank()) == 1) && (!compareColorOfPieceAtLocation(
                piece,
                to))) && (getPieceAtLocation(to) != null))
            {
                // if the rank is different, but there's a different color piece
                // in the spot, the move is okay
                return true;
            }
            else if (Math.abs(to.rank() - piece.getLocation().rank()) == 1)
            {
                // if the rank is different, but it's the same color, the move
                // is not okay
                return false;
            }
            if (to.file() == piece.getLocation().file() - 1)
            {
                return piece.getLocation().rank() == to.rank()
                    || (Math.abs(piece.getLocation().rank() - to.rank()) == 1 && !compareColorOfPieceAtLocation(
                        piece,
                        to));
            }
            else if ((to.file() == piece.getLocation().file() - 2)
                && (piece.getLocation().file() == 6))
            {
                return piece.getLocation().rank() == to.rank()
                    || (Math.abs(piece.getLocation().rank() - to.rank()) == 2 && !compareColorOfPieceAtLocation(
                        piece,
                        to));
            }
            else
            {
                return false;
            }
        }
        else if (piece.getSymbol() == (char)0x265F) // If Black Pawn...
        {
            // Returns false if there is a black piece at the target location
            if (compareColorOfPieceAtLocation(piece, to))
            {
                return false;
            }
            // returns false if any piece is forward to the current piece
            if ((Math.abs(piece.getLocation().rank() - to.rank()) == 0)
                && (getPieceAtLocation(new Location(
                    piece.getLocation().rank(),
                    piece.getLocation().file() + 1)) != null))
            {
                return false;
            }
            if (((Math.abs(to.rank() - piece.getLocation().rank()) == 1) && (!compareColorOfPieceAtLocation(
                piece,
                to))) && (getPieceAtLocation(to) != null))
            {
                // if the rank is different, but there's a different color piece
                // in the spot, the move is okay
                return true;
            }
            else if (Math.abs(to.rank() - piece.getLocation().rank()) == 1)
            {
                // if the rank is different, but it's the same color, the move
                // is not okay
                return false;
            }
            if (to.file() == piece.getLocation().file() + 1)
            {
                return piece.getLocation().rank() == to.rank()
                    || (Math.abs(piece.getLocation().rank() - to.rank()) == 1 && !compareColorOfPieceAtLocation(
                        piece,
                        to));
            }
            else if ((to.file() == piece.getLocation().file() + 2)
                && (piece.getLocation().file() == 1))
            {
                return piece.getLocation().rank() == to.rank()
                    || (Math.abs(piece.getLocation().rank() - to.rank()) == 2 && !compareColorOfPieceAtLocation(
                        piece,
                        to));
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }

    }


    // ----------------------------------------------------------
    /**
     * Checks to see if the piece moved should be moved. White pieces always
     * move first. White pieces move on even turns and black pieces move on odd
     * turns.
     * @param piece the piece about to be moved
     * @return true if the turn is in order; false if the turn is out of order
     */
    protected boolean properTurn(Piece piece)
    {
        switch (piece.getType())
        {
            case WHITE_KING:
                return (turn % 2 == 0);
            case WHITE_QUEEN:
                return (turn % 2 == 0);
            case WHITE_BISHOP:
                return (turn % 2 == 0);
            case WHITE_KNIGHT:
                return (turn % 2 == 0);
            case WHITE_ROOK:
                return (turn % 2 == 0);
            case WHITE_PAWN:
                return (turn % 2 == 0);
            case BLACK_KING:
                return (turn % 2 == 1);
            case BLACK_QUEEN:
                return (turn % 2 == 1);
            case BLACK_BISHOP:
                return (turn % 2 == 1);
            case BLACK_KNIGHT:
                return (turn % 2 == 1);
            case BLACK_ROOK:
                return (turn % 2 == 1);
            case BLACK_PAWN:
                return (turn % 2 == 1);
        }
        return false;
    }


    // ----------------------------------------------------------
    /**
     * Gets the list of pieces that are registered on the board.
     * @return pieces the list of pieces on the board
     */
    public LinkedList<Piece> getPieces()
    {
        return pieces;
    }


    // ----------------------------------------------------------
    /**
     * Gets the piece at a specific location and returns it if it exists.
     * @param target the location to be checked
     * @return the piece in the location if one exists, null otherwise
     */
    protected Piece getPieceAtLocation(Location target)
    {
        for (Piece piece : pieces)
        {
            if (piece.getLocation().equals(target))
            {
                return piece;
            }
        }
        return null;
    }


    // ----------------------------------------------------------
    /**
     * Helper method that checks the locations between a piece and a target
     * location that are in the same row for other pieces that would make a move
     * invalid.
     * @param piece the piece that is attempting to move
     * @param to the location the piece is trying to move to
     * @return true if there are no pieces between this piece and the target
     *         location; false if there are pieces between this piece and the
     *         target location
     */
    private boolean checkRowsForObstacles(Piece piece, Location to)
    {
        if (piece.getLocation().rank() == to.rank())
        {
            if (piece.getLocation().file() > to.file())
            {
                for (int i = piece.getLocation().file() - 1; i > to.file(); i--)
                {
                    if (getPieceAtLocation(new Location(to.rank(), i)) != null)
                    {
                        return false;
                    }
                }
                return true;
            }
            else if (piece.getLocation().file() < to.file())
            {
                for (int i = piece.getLocation().file() + 1; i < to.file(); i++)
                {
                    if (getPieceAtLocation(new Location(to.rank(), i)) != null)
                    {
                        return false;
                    }
                }
                return true;
            }
            else
            {
                return false;
            }
        }
        else if (piece.getLocation().file() == to.file())
        {
            if (piece.getLocation().rank() > to.rank())
            {
                for (int i = piece.getLocation().rank() - 1; i > to.rank(); i--)
                {
                    if (getPieceAtLocation(new Location(i, to.file())) != null)
                    {
                        return false;
                    }
                }
                return true;
            }
            else if (piece.getLocation().rank() < to.rank())
            {
                for (int i = piece.getLocation().rank() + 1; i < to.rank(); i++)
                {
                    if (getPieceAtLocation(new Location(i, to.file())) != null)
                    {
                        return false;
                    }
                }
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }


    // ----------------------------------------------------------
    /**
     * Helper method that checks the locations diagonal to a piece and between
     * it and a target location to see if there are any pieces in the way.
     * @param piece the piece that wants to move
     * @param to the location the piece is trying to move to
     * @return true if there are no pieces between piece and it's target
     *         location in any diagonal direction; false otherwise
     */
    private boolean checkDiagonalsForObstacles(Piece piece, Location to)
    {
        int count = 0;

        if (Math.abs(piece.getLocation().rank() - to.rank()) == Math.abs(piece
            .getLocation().file() - to.file()))
        {
            for (int i = 0; i < Math
                .abs(piece.getLocation().rank() - to.rank()); i++)
            {
                if (to.rank() > piece.getLocation().rank()
                    && to.file() > piece.getLocation().file())
                {
                    if (getPieceAtLocation(new Location(piece.getLocation()
                        .rank() + i, piece.getLocation().file() + i)) != null)
                    {
                        count++;
                    }
                }
                else if (to.rank() > piece.getLocation().rank()
                    && to.file() < piece.getLocation().file())
                {
                    if (getPieceAtLocation(new Location(piece.getLocation()
                        .rank() + i, piece.getLocation().file() - i)) != null)
                    {
                        count++;
                    }
                }
                else if (to.rank() < piece.getLocation().rank()
                    && to.file() > piece.getLocation().file())
                {
                    if (getPieceAtLocation(new Location(piece.getLocation()
                        .rank() - i, piece.getLocation().file() + i)) != null)
                    {
                        count++;
                    }
                }
                else if (to.rank() < piece.getLocation().rank()
                    && to.file() < piece.getLocation().file())
                {
                    if (getPieceAtLocation(new Location(piece.getLocation()
                        .rank() - i, piece.getLocation().file() - i)) != null)
                    {
                        count++;
                    }
                }
            }
            return (count <= 1); // the method will also count itself
        }
        else
        {
            return false;
        }
    }


    // ----------------------------------------------------------
    /**
     * Checks a target location to see if there is a piece there of the same
     * color as a given piece.
     * @param piece the piece that the piece at a location is compared to
     * @param target the location that is being checked to see if there is a
     *        piece there of the same color
     * @return true if the piece at the location is the same color as the given
     *         piece; false otherwise
     */
    private boolean compareColorOfPieceAtLocation(Piece piece, Location target)
    {
        return (getPieceAtLocation(target) != null && ((piece.getSymbol() <= (char)0x2659 && getPieceAtLocation(
            target).getSymbol() <= (char)0x2659) || (piece.getSymbol() >= (char)0x265A && getPieceAtLocation(
            target).getSymbol() >= (char)0x265A)));

    }


    // ----------------------------------------------------------
    /**
     * Gets the turn number, used for rendering purposes.
     * @return turn the turn number
     */
    protected int getTurns()
    {
        return turn;
    }
}
