package 设计模式.结构型模式.组合模式;

/**
 * 目录和文件是一种竖型结构
 * 使用组合模式实现以下功能
 * 1、统计指定目录下的文件个数；
 * 2、统计指定目录下的文件总大小。
 * 3、动态地添加、删除某个目录下的子目录或文件；
 */
public class Test {
    public static void main(String[] args) {
        String rootPath = System.getProperty("user.dir");
        Directory root = new Directory(rootPath);
        Directory directory1 = new Directory(rootPath + "/src/设计模式");
        File file1 = new File(rootPath + "/README.md");
        directory1.addNode(new File(directory1.getPath()+"/设计模式的六大原则.md"));
        root.addNode(directory1);
        root.addNode(file1);
        // 计算root中的文件数量
        System.out.println(root.countNumOfFiles());
        // 计算root中的文件总大小
        System.out.println(root.countSizeOfFiles());
    }
}
