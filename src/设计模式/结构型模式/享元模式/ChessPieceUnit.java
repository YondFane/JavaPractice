package 设计模式.结构型模式.享元模式;

/**
 * 享元类
 * 享元类对象是不可变对象，不能有setter方法
 * 棋子单元
 * 记录象棋中棋子的ID、名称、颜色
 */
public class ChessPieceUnit {
    private Integer id;
    private String name;
    private Color color;

    public ChessPieceUnit(Integer id, String name, Color color) {
        this.id = id;
        this.name = name;
        this.color = color;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public static enum Color{
        RED,BLACK
    }
}
