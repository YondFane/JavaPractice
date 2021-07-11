package 设计模式.结构型模式.享元模式;

import java.util.HashMap;
import java.util.Map;

/**
 * 享元（棋子单元）工厂类
 * 享元缓存池
 */
public class ChessPieceUnitFactory {
    // 记录象棋中棋子单元
    private static Map<Integer, ChessPieceUnit> chessPieces = new HashMap<>();

    /**
     * 初始化象棋中的棋子单元信息
     */
    static {
        // 红棋子
        chessPieces.put(1, new ChessPieceUnit(1, "兵", ChessPieceUnit.Color.RED));
        chessPieces.put(2, new ChessPieceUnit(2, "跑", ChessPieceUnit.Color.RED));
        chessPieces.put(3, new ChessPieceUnit(3, "车", ChessPieceUnit.Color.RED));
        chessPieces.put(4, new ChessPieceUnit(4, "马", ChessPieceUnit.Color.RED));
        chessPieces.put(5, new ChessPieceUnit(5, "象", ChessPieceUnit.Color.RED));
        chessPieces.put(6, new ChessPieceUnit(6, "士", ChessPieceUnit.Color.RED));
        chessPieces.put(7, new ChessPieceUnit(7, "帅", ChessPieceUnit.Color.RED));
        // 黑棋子
        chessPieces.put(8, new ChessPieceUnit(8, "卒", ChessPieceUnit.Color.BLACK));
        chessPieces.put(9, new ChessPieceUnit(9, "跑", ChessPieceUnit.Color.BLACK));
        chessPieces.put(10, new ChessPieceUnit(10, "车", ChessPieceUnit.Color.BLACK));
        chessPieces.put(11, new ChessPieceUnit(11, "马", ChessPieceUnit.Color.BLACK));
        chessPieces.put(12, new ChessPieceUnit(12, "象", ChessPieceUnit.Color.BLACK));
        chessPieces.put(13, new ChessPieceUnit(13, "士", ChessPieceUnit.Color.BLACK));
        chessPieces.put(14, new ChessPieceUnit(14, "将", ChessPieceUnit.Color.BLACK));
    }

    /**
     * 获取棋子
     * @param id
     * @return
     */
    public static ChessPieceUnit getChessPieceUnit(Integer id) {
        return chessPieces.get(id);
    }
}
