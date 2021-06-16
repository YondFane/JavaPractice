package 设计模式.创建型模型.建造者模式;

/**
 * 当 isRef 为 true 的时候，arg 表示 String 类型的 refBeanId，type 不需要设置；
 * 当 isRef 为 false 的时候，arg、type 都需要设置。
 */
public class ConstructorArg {
    private boolean isRef;
    private Class type;
    private Object arg;

    private ConstructorArg(Builder builder) {
        this.isRef = builder.isRef;
        this.type = builder.type;
        this.arg = builder.arg;
    }
    public static class Builder {
        private boolean isRef;
        private Class type;
        private Object arg;

        public ConstructorArg build() {

            if (arg == null) {
                throw new IllegalArgumentException("arg未设置异常");
            }
            // 校验逻辑放到这里来做
            if (isRef) {
                if (!(arg instanceof String)) {

                }
            } else {
                if (type == null) {
                    throw new IllegalArgumentException("type未设置异常");
                }
            }
            return new ConstructorArg(this);
        }

        public Builder setIsRef(boolean isRef) {
            this.isRef = isRef;
            return this;
        }

        public Builder setType(Class type) {
            this.type = type;
            return this;
        }

        public Builder setArg(Object arg) {
            this.arg = arg;
            return this;
        }
    }
    @Override
    public String toString() {
        return "ConstructorArg{" +
                "isRef=" + isRef +
                ", type=" + type +
                ", arg=" + arg +
                '}';
    }
}
