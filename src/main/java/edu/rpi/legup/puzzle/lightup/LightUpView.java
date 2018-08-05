package edu.rpi.legup.puzzle.lightup;

import edu.rpi.legup.controller.BoardController;
import edu.rpi.legup.model.gameboard.Board;
import edu.rpi.legup.model.gameboard.CaseBoard;
import edu.rpi.legup.model.gameboard.PuzzleElement;
import edu.rpi.legup.ui.boardview.DataSelectionView;
import edu.rpi.legup.ui.boardview.GridBoardView;
import edu.rpi.legup.ui.boardview.SelectionItemView;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import edu.rpi.legup.controller.BoardController;
import javafx.scene.image.Image;
import edu.rpi.legup.model.gameboard.Board;
import edu.rpi.legup.model.gameboard.CaseBoard;
import edu.rpi.legup.model.gameboard.Element;
import edu.rpi.legup.ui.boardview.GridBoardView;

public class LightUpView extends GridBoardView
{
    static Image lightImage;
    static
    {
        lightImage = new Image(ClassLoader.getSystemClassLoader().getResourceAsStream("images/lightup/light.png"));
    }

    public LightUpView(LightUpBoard board)
    {
        super(new BoardController(), new LightUpCellController(), board.getDimension());

        for(PuzzleElement puzzleElement : board.getPuzzleElements())
        {
            LightUpCell cell = (LightUpCell) puzzleElement;
            Point2D loc = cell.getLocation();
            LightUpElementView elementView = new LightUpElementView(cell);
            elementView.setIndex(cell.getIndex());
            elementView.setSize(elementSize);
            elementView.setLocation(new Point2D(loc.x * elementSize.getWidth(), loc.y * elementSize.getHeight()));
            elementViews.add(elementView);
        }
    }

    @Override
    public void onBoardChanged(Board board)
    {
        setBoard(board);
        LightUpBoard lightUpBoard = board instanceof CaseBoard ? (LightUpBoard)((CaseBoard)board).getBaseBoard() : (LightUpBoard)board;
        lightUpBoard.fillWithLight();
    }
}
