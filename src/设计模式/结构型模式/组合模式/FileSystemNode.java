package 设计模式.结构型模式.组合模式;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件系统节点类
 */
public abstract class FileSystemNode {
    protected String path;

    public FileSystemNode(String path) {
        this.path = path;
    }

    /**
     * 计算目录下的文件数量
     * @return
     */
    public abstract int countNumOfFiles();

    /**
     * 计算目录下的文件大小
     * @return
     */
    public abstract long countSizeOfFiles();

    public String getPath() {
        return path;
    }
}
