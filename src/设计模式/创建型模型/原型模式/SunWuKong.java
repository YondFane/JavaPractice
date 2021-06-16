package 设计模式.创建型模型.原型模式;

import java.io.*;

/**
 * 孙悟空类
 * 孙悟空把一撮毫毛可以吹出很多分身
 * 孙悟空及分身不能共用一根金箍棒
 */
public class SunWuKong implements Cloneable,Serializable {

    public String name;

    public GoldenCudgel goldenCudgel;

    public SunWuKong(String name, GoldenCudgel goldenCudgel) {
        this.name = name;
        this.goldenCudgel = goldenCudgel;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        ByteArrayOutputStream bos = null;
        ObjectOutputStream oos = null;
        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;
        Object o = null;
        try {
            //序列化
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(this);
            // 反序列化
            bis = new ByteArrayInputStream(bos.toByteArray());
            ois = new ObjectInputStream(bis);
            o = ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                if (bos != null) {
                    bos.close();
                }
                if (oos != null) {
                    oos.close();
                }
                if (bis != null) {
                    bis.close();
                }
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return o;
    }

    @Override
    public String toString() {
        return "SunWuKong{" +
                "name='" + name + '\'' +
                ", goldenCudgel=" + goldenCudgel +
                '}';
    }
}
