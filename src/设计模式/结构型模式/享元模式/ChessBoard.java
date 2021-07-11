package 设计模式.结构型模式.享元模式;

import java.util.HashMap;
import java.util.Map;

/**
 * 棋盘
 * 游戏大厅中可能同时有上千人（甚至更多）在下棋
 * 每一盘棋都有32个棋子
 * 如果对每个棋子保存棋子名称、颜色信息，那么服务器的内存就会消耗过高
 * 例如有2000盘棋，那么就会存在64000个棋子对象
 * 64000个棋子对象中存在一些相同且不可变的信息：名称和颜色，棋子中这些名称和颜色抽离出来
 * 创建一个享元类ChessPieceUnit，2000个棋盘中相同的棋子ChessPiece指向同一个ChessPieceUnit享元
 * 那么就可以节约很多内存
 * 这就是享元模式
 * 享元模式重要的是复用对象，节约内存
 */
public class ChessBoard {
    //象棋棋盘上的32个棋子信息
    private Map<Integer, ChessPiece> chessPieces = new HashMap<>();

    public ChessBoard() {
        initlize();
    }

    /**
     * 初始化棋盘
     * 象棋棋盘是个9x10的二维坐标系
     * 共有32个棋子，红黑两方各16个棋子
     */
    private void initlize() {
        // 红方
        // 兵
        chessPieces.put(1, new ChessPiece(ChessPieceUnitFactory.getChessPieceUnit(1), 0, 3));
        chessPieces.put(2, new ChessPiece(ChessPieceUnitFactory.getChessPieceUnit(1), 2, 3));
        chessPieces.put(3, new ChessPiece(ChessPieceUnitFactory.getChessPieceUnit(1), 4, 3));
        chessPieces.put(4, new ChessPiece(ChessPieceUnitFactory.getChessPieceUnit(1), 6, 3));
        chessPieces.put(5, new ChessPiece(ChessPieceUnitFactory.getChessPieceUnit(1), 8, 3));
        // 炮
        chessPieces.put(6, new ChessPiece(ChessPieceUnitFactory.getChessPieceUnit(2), 1, 2));
        chessPieces.put(7, new ChessPiece(ChessPieceUnitFactory.getChessPieceUnit(2), 7, 2));
        // 车
        chessPieces.put(8, new ChessPiece(ChessPieceUnitFactory.getChessPieceUnit(3), 0, 0));
        chessPieces.put(9, new ChessPiece(ChessPieceUnitFactory.getChessPieceUnit(3), 8, 0));
        // 马
        chessPieces.put(10, new ChessPiece(ChessPieceUnitFactory.getChessPieceUnit(4), 1, 0));
        chessPieces.put(11, new ChessPiece(ChessPieceUnitFactory.getChessPieceUnit(4), 7, 0));
        // 象
        chessPieces.put(12, new ChessPiece(ChessPieceUnitFactory.getChessPieceUnit(5), 2, 0));
        chessPieces.put(13, new ChessPiece(ChessPieceUnitFactory.getChessPieceUnit(5), 6, 0));
        // 士
        chessPieces.put(14, new ChessPiece(ChessPieceUnitFactory.getChessPieceUnit(6), 3, 0));
        chessPieces.put(15, new ChessPiece(ChessPieceUnitFactory.getChessPieceUnit(6), 5, 0));
        // 帅
        chessPieces.put(16, new ChessPiece(ChessPieceUnitFactory.getChessPieceUnit(7), 4, 0));

        // 黑方
        // 卒
        chessPieces.put(17, new ChessPiece(ChessPieceUnitFactory.getChessPieceUnit(8), 0, 6));
        chessPieces.put(18, new ChessPiece(ChessPieceUnitFactory.getChessPieceUnit(8), 2, 6));
        chessPieces.put(19, new ChessPiece(ChessPieceUnitFactory.getChessPieceUnit(8), 4, 6));
        chessPieces.put(20, new ChessPiece(ChessPieceUnitFactory.getChessPieceUnit(8), 6, 6));
        chessPieces.put(21, new ChessPiece(ChessPieceUnitFactory.getChessPieceUnit(8), 8, 6));
        // 炮
        chessPieces.put(22, new ChessPiece(ChessPieceUnitFactory.getChessPieceUnit(9), 1, 7));
        chessPieces.put(23, new ChessPiece(ChessPieceUnitFactory.getChessPieceUnit(9), 7, 7));
        // 车
        chessPieces.put(24, new ChessPiece(ChessPieceUnitFactory.getChessPieceUnit(10), 0, 9));
        chessPieces.put(25, new ChessPiece(ChessPieceUnitFactory.getChessPieceUnit(10), 8, 9));
        // 马
        chessPieces.put(26, new ChessPiece(ChessPieceUnitFactory.getChessPieceUnit(11), 1, 9));
        chessPieces.put(27, new ChessPiece(ChessPieceUnitFactory.getChessPieceUnit(11), 7, 9));
        // 象
        chessPieces.put(28, new ChessPiece(ChessPieceUnitFactory.getChessPieceUnit(12), 2, 9));
        chessPieces.put(29, new ChessPiece(ChessPieceUnitFactory.getChessPieceUnit(12), 6, 9));
        // 士
        chessPieces.put(30, new ChessPiece(ChessPieceUnitFactory.getChessPieceUnit(13), 3, 9));
        chessPieces.put(31, new ChessPiece(ChessPieceUnitFactory.getChessPieceUnit(13), 5, 9));
        // 将
        chessPieces.put(32, new ChessPiece(ChessPieceUnitFactory.getChessPieceUnit(14), 4, 9));
    }

    /**
     * 棋子移动
     */
    public void move(int chessPieceId, int toPointX, int toPointY) {
        ChessPiece chessPiece = chessPieces.get(chessPieceId);
        chessPiece.setPointX(toPointX);
        chessPiece.setPointY(toPointY);
    }
}
