package 设计模式.结构型模式.享元模式;

/**
 * 棋子
 */
public class ChessPiece {
    private ChessPieceUnit chessPieceUnit;//棋子单元信息
    private int pointX;// 横坐标
    private int pointY;// 纵坐标

    public ChessPiece(ChessPieceUnit chessPieceUnit, int pointX, int pointY) {
        this.chessPieceUnit = chessPieceUnit;
        this.pointX = pointX;
        this.pointY = pointY;
    }

    public int getPointX() {
        return pointX;
    }

    public void setPointX(int pointX) {
        this.pointX = pointX;
    }

    public int getPointY() {
        return pointY;
    }

    public void setPointY(int pointY) {
        this.pointY = pointY;
    }
}
