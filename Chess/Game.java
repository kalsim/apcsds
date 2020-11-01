package Chess;

import Chess.Board;

import java.awt.*;
import java.util.*;

/**
 * plays a game of chess
 * @author Montek Kalsi
 * @version 5/29/18
 */
public class Game {
    public static void main (String[] args)
    {
        Board board = new Board();
        BoardDisplay display = new BoardDisplay(board);

        /**
         * initializing Kings
         */
        King blackKing = new King(Color.BLACK, "black_king.gif");
        blackKing.putSelfInGrid(board, new Location(0, 4));
        King whiteKing = new King(Color.WHITE, "white_king.gif");
        whiteKing.putSelfInGrid(board, new Location(7, 4));

        /**
         * initializing Queens
         */
        Queen blackQueen = new Queen(Color.BLACK, "black_queen.gif");
        blackQueen.putSelfInGrid(board, new Location(0, 3));
        Queen whiteQueen = new Queen(Color.WHITE, "white_queen.gif");
        whiteQueen.putSelfInGrid(board, new Location(7, 3));

        /**
         * initializing Rooks
         */
        Rook whiteRookLeft = new Rook(Color.WHITE, "white_rook.gif");
        whiteRookLeft.putSelfInGrid(board, new Location(board.getNumRows() - 1, board.getNumCols() - 1));
        Rook whiteRookRight = new Rook(Color.WHITE, "white_rook.gif");
        whiteRookRight.putSelfInGrid(board, new Location(board.getNumRows() - 1, 0));
        Rook blackRookLeft = new Rook(Color.BLACK, "black_rook.gif");
        blackRookLeft.putSelfInGrid(board, new Location(0,0));
        Rook blackRookRight = new Rook(Color.BLACK, "black_rook.gif");
        blackRookRight.putSelfInGrid(board, new Location(0, board.getNumCols() - 1));

        /**
         * initializing Knights
         */
        Knight whiteKnight1 = new Knight(Color.WHITE, "white_knight.gif");
        whiteKnight1.putSelfInGrid(board, new Location(7, 1));
        Knight whiteKnight2 = new Knight(Color.WHITE, "white_knight.gif");
        whiteKnight2.putSelfInGrid(board, new Location(7, 6));
        Knight blackKnight1 = new Knight(Color.BLACK, "black_knight.gif");
        blackKnight1.putSelfInGrid(board, new Location(0, 1));
        Knight blackKnight2 = new Knight(Color.BLACK, "black_knight.gif");
        blackKnight2.putSelfInGrid(board, new Location(0, 6));

        /**
         * initializing Bishops
         */
        Bishop whiteBishop1 = new Bishop(Color.WHITE, "white_bishop.gif");
        whiteBishop1.putSelfInGrid(board, new Location(7, 2));
        Bishop whiteBishop2 = new Bishop(Color.WHITE, "white_bishop.gif");
        whiteBishop2.putSelfInGrid(board, new Location(7, 5));
        Bishop blackBishop1 = new Bishop(Color.BLACK, "black_bishop.gif");
        blackBishop1.putSelfInGrid(board, new Location(0, 2));
        Bishop blackBishop2 = new Bishop(Color.BLACK, "black_bishop.gif");
        blackBishop2.putSelfInGrid(board, new Location(0, 5));
        /**
         * initializing white pawns
         */
        Pawn white1 = new Pawn(Color.WHITE, "white_pawn.gif");
        white1.putSelfInGrid(board, new Location(6, 0));
        Pawn white2 = new Pawn(Color.WHITE, "white_pawn.gif");
        white2.putSelfInGrid(board, new Location(6, 1));
        Pawn white3 = new Pawn(Color.WHITE, "white_pawn.gif");
        white3.putSelfInGrid(board, new Location(6, 2));
        Pawn white4 = new Pawn(Color.WHITE, "white_pawn.gif");
        white4.putSelfInGrid(board, new Location(6, 3));
        Pawn white5 = new Pawn(Color.WHITE, "white_pawn.gif");
        white5.putSelfInGrid(board, new Location(6, 4));
        Pawn white6 = new Pawn(Color.WHITE, "white_pawn.gif");
        white6.putSelfInGrid(board, new Location(6, 5));
        Pawn white7 = new Pawn(Color.WHITE, "white_pawn.gif");
        white7.putSelfInGrid(board, new Location(6, 6));
        Pawn white8 = new Pawn(Color.WHITE, "white_pawn.gif");
        white8.putSelfInGrid(board, new Location(6, 7));
        /**
         * initializing black pawns
         */
        Pawn black1 = new Pawn(Color.BLACK, "black_pawn.gif");
        black1.putSelfInGrid(board, new Location(1, 0));
        Pawn black2 = new Pawn(Color.BLACK, "black_pawn.gif");
        black2.putSelfInGrid(board, new Location(1, 1));
        Pawn black3 = new Pawn(Color.BLACK, "black_pawn.gif");
        black3.putSelfInGrid(board, new Location(1, 2));
        Pawn black4 = new Pawn(Color.BLACK, "black_pawn.gif");
        black4.putSelfInGrid(board, new Location(1, 3));
        Pawn black5 = new Pawn(Color.BLACK, "black_pawn.gif");
        black5.putSelfInGrid(board, new Location(1, 4));
        Pawn black6 = new Pawn(Color.BLACK, "black_pawn.gif");
        black6.putSelfInGrid(board, new Location(1, 5));
        Pawn black7 = new Pawn(Color.BLACK, "black_pawn.gif");
        black7.putSelfInGrid(board, new Location(1, 6));
        Pawn black8 = new Pawn(Color.BLACK, "black_pawn.gif");
        black8.putSelfInGrid(board, new Location(1, 7));



        SmartPlayer a = new SmartPlayer(board, "Black", Color.BLACK);
        HumanPlayer b = new HumanPlayer(board, "White", Color.WHITE, display);
        play(board, display, a, b);


    }

    /**
     * simulates one turn of the game
     * @param board the board of the chess game
     * @param display the display of the board
     * @param player the player's turn
     */
    private static void nextTurn(Board board, BoardDisplay display, Player player)
    {
        display.setTitle(player.getName());
        Move nucc = player.nextMove();
        board.executeMove(nucc);
        display.clearColors();
        display.setColor(nucc.getSource(), Color.yellow);
        display.setColor(nucc.getDestination(), Color.yellow);
        try { Thread.sleep(2000); } catch(InterruptedException e) {}

    }

    /**
     * plays the entire game
     * @param board the board of the game
     * @param display the display of the board
     * @param black the color black
     * @param white the color white
     */
    public static void play(Board board, BoardDisplay display, Player black, Player white)
    {
        while(true)
        {
            nextTurn(board, display, white);
            nextTurn(board, display, black);
        }
    }
}
