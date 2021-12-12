package 有道云翻译;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;

/*
 * 调用参数
 *
 * @author YFAN
 * @date 2021/12/12/012
 */
public class SendVo {
    // 待翻译文本	必填	必须是UTF-8编码
    private String q;
    // 源语言	必填	参考下方 支持语言 (可设置为auto)
    private String from;
    // 目标语言	必填	参考下方 支持语言 (可设置为auto)
    private String to;
    // 应用ID	必填	可在 应用管理 查看
    private String appKey;
    // UUID	必填	uuid，唯一通用识别码
    private String salt;
    // 签名	必填	sha256(应用ID+input+salt+curtime+应用密钥)
    private String sign;
    // 名类型	必填	v3
    private String signType;
    // 当前UTC时间戳(秒)	必填	TimeStamp
    private String curtime;
    // 翻译结果音频格式，支持mp3	false	mp3
    private String ext;
    // 翻译结果发音选择	非必填	0为女声，1为男声。默认为女声
    private String voice;
    // 是否严格按照指定from和to进行翻译：true/false 非必填	 如果为false，则会自动中译英，英译中。默认为false
    private String strict;
    // 用户上传的词典	非必填	用户指定的词典 out_id，目前支持英译中
    private String vocabId;

    public SendVo(){}
    public SendVo(SendVoBuilder builder) {
        this.q = builder.q;
        this.from = builder.from;
        this.to = builder.to;
        this.appKey = builder.appKey;
        this.salt = builder.salt;
        this.sign = builder.sign;
        this.signType = builder.signType;
        this.curtime = builder.curtime;
        this.ext = builder.ext;
        this.voice = builder.voice;
        this.strict = builder.strict;
        this.vocabId = builder.vocabId;
    }

    public String getQ() {
        return q;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getAppKey() {
        return appKey;
    }

    public String getSalt() {
        return salt;
    }

    public String getSign() {
        return sign;
    }

    public String getSignType() {
        return signType;
    }

    public String getCurtime() {
        return curtime;
    }

    public String getExt() {
        return ext;
    }

    public String getVoice() {
        return voice;
    }

    public String getStrict() {
        return strict;
    }

    public String getVocabId() {
        return vocabId;
    }

    @Override
    public String toString() {
        return "SendVo{" +
                "q='" + q + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", appKey='" + appKey + '\'' +
                ", salt='" + salt + '\'' +
                ", sign='" + sign + '\'' +
                ", signType='" + signType + '\'' +
                ", curtime='" + curtime + '\'' +
                ", ext='" + ext + '\'' +
                ", voice='" + voice + '\'' +
                ", strict='" + strict + '\'' +
                ", vocabId='" + vocabId + '\'' +
                '}';
    }

    // 构建者模式
    public static class SendVoBuilder {
        private String q;
        private String from;
        private String to;
        private String appKey;
        private String salt;
        private String sign;
        private String signType = "v3";
        private String curtime;
        private String ext;
        private String voice;
        private String strict;
        private String vocabId;
        // 应用密钥
        private String appToken;

        public SendVo builder() {
            // 必填校验
            String msg = required();
            if(!StrUtil.hasEmpty(msg)) {
                throw new RuntimeException(msg);
            }

            // 生成签名
            createSign();
            return new SendVo(this);
        }

        /*
         * 生成签名
         * 签名规则
         * signType=v3；
         * sign=sha256(应用ID+input+salt+curtime+应用密钥)；
         * 其中，input的计算方式为：input=q前10个字符 + q长度 + q后10个字符（当q长度大于20）或 input=q字符串（当q长度小于等于20）；
         * @author YFAN
         * @date 2021/12/12/012
         * @param  * @param
         * @return void
         */
        private void createSign() {
            if ("v3".equals(this.signType)) {
                StringBuilder sb = new StringBuilder();
                sb.append(appKey);
                if (q.length() < 20) {
                    sb.append(q);
                } else {
                    sb.append(q.substring(0, 10));
                    sb.append(q.length());
                    sb.append(q.substring(q.length() - 10, q.length()));
                }
                sb.append(salt);
                sb.append(curtime);
                sb.append(appToken);
                this.sign = SecureUtil.signParamsSha256(null,sb.toString()).toUpperCase();
            }
        }

        /*
         * 必填校验
         * @author YFAN
         * @date 2021/12/12/012
         * @param  * @param
         * @return java.lang.String
         */
        private String required() {
            StringBuilder sb = new StringBuilder();
            if (StrUtil.hasEmpty(this.q)) {
                sb.append("待翻译文本q不能为空！");
            }
            if (StrUtil.hasEmpty(this.from)) {
                sb.append("源语言本from不能为空！");
            }
            if (StrUtil.hasEmpty(this.to)) {
                sb.append("目标语言to不能为空！");
            }
            if (StrUtil.hasEmpty(this.appKey)) {
                sb.append("应用IDappKey不能为空！");
            }
            if (StrUtil.hasEmpty(this.salt)) {
                sb.append("UUIDsalt不能为空！");
            }
            if (StrUtil.hasEmpty(this.signType)) {
                sb.append("签名类型signType不能为空！");
            }
            if (StrUtil.hasEmpty(this.curtime)) {
                sb.append("当前UTC时间戳(秒)curtime不能为空！");
            }
            if (StrUtil.hasEmpty(this.appToken)) {
                sb.append("应用密钥appToken不能为空！");
            }
            if (StrUtil.hasEmpty(this.ext)) {
                this.ext = null;
            }
            if (StrUtil.hasEmpty(this.voice)) {
                this.voice = null;
            }
            if (StrUtil.hasEmpty(this.strict)) {
                this.strict = null;
            }
            if (StrUtil.hasEmpty(this.vocabId)) {
                this.vocabId = null;
            }
            return sb.toString();
        }

        public SendVoBuilder setQ(String q) {
            this.q = q;
            return this;
        }

        public SendVoBuilder setFrom(String from) {
            this.from = from;
            return this;
        }

        public SendVoBuilder setTo(String to) {
            this.to = to;
            return this;
        }

        public SendVoBuilder setAppKey(String appKey) {
            this.appKey = appKey;
            return this;
        }

        public SendVoBuilder setSalt(String salt) {
            this.salt = salt;
            return this;
        }

        public SendVoBuilder setSignType(String signType) {
            this.signType = signType;
            return this;
        }

        public SendVoBuilder setCurtime(String curtime) {
            this.curtime = curtime;
            return this;
        }

        public SendVoBuilder setExt(String ext) {
            this.ext = ext;
            return this;
        }

        public SendVoBuilder setVoice(String voice) {
            this.voice = voice;
            return this;
        }

        public SendVoBuilder setStrict(String strict) {
            this.strict = strict;
            return this;
        }

        public SendVoBuilder setVocabId(String vocabId) {
            this.vocabId = vocabId;
            return this;
        }

        public SendVoBuilder setAppToken(String appToken) {
            this.appToken = appToken;
            return this;
        }
    }
}
