package 设计模式.结构型模式.组合模式;

import java.util.ArrayList;
import java.util.List;

/**
 * 目录类
 */
public class Directory extends FileSystemNode {
    /**
     * 记录目录下的目录及文件
     */
    private List<FileSystemNode> nodes = new ArrayList<>();

    public Directory(String path) {
        super(path);
    }

    @Override
    public int countNumOfFiles() {
        int result = 0;
        for (FileSystemNode node : nodes) {
            result += node.countNumOfFiles();
        }
        return result;
    }

    @Override
    public long countSizeOfFiles() {
        long size = 0;
        for (FileSystemNode node : nodes) {
            size += node.countSizeOfFiles();
        }
        return size;
    }

    public void addNode(FileSystemNode node) {
        nodes.add(node);
    }

    public void removeNode(FileSystemNode node) {
/*        for(int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i).getPath().equals(node.getPath())) {
                nodes.remove(i);
                break;
            }
        }*/
        int size = nodes.size();
        for (int i = 0; i < size; i++) {
            if (nodes.get(i).getPath().equals(node.getPath())) {
                nodes.remove(i);
                break;
            }
        }
    }
}
