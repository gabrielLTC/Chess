package boardgame;

import boardgame.exception.BoardException;

public class Board {
    private int rows;
    private int columns;
    private Piece[][] pieces;

    public Board(int rows, int columns) {
       validateRowAndColumn(rows, columns);
        this.rows = rows;
        this.columns = columns;
        pieces = new Piece[rows][columns];
    }
    private void validateRowAndColumn(int rows, int columns){
        if(rows < 1 || columns < 1){
            throw new BoardException("Error trying to create the board: there must be at least 1 row and 1 column");
        }
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public Piece piece(Position position){
        return piece(position.getRow(), position.getColumn());
    }

    public Piece piece(int row, int column){
        if(!positionExists(row, column)){
            throw new BoardException("Position not on the board.");
        }
        return pieces[row][column];
    }

    public void placePiece(Piece piece, Position position){
        if(thereIsAPiece(position)){
            throw new BoardException("There is already a piece on position" + position);
        }
        pieces[position.getRow()][position.getColumn()] = piece;
        piece.position = position;
    }

    private boolean positionExists(int row, int column){
        return row >=0 && row <=rows && column >=0 && column <=columns;
    }

    public boolean positionExists(Position position){
       return positionExists(position.getRow(), position.getColumn());
    }

    public boolean thereIsAPiece(Position position){
        if(!positionExists(position)){
            throw new BoardException("Position not on the board.");
        }
        return piece(position) != null;
    }
}
