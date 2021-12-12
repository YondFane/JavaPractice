package 有道云翻译;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import cn.hutool.setting.Setting;

import java.util.ArrayList;

/*
 * 翻译机
 * @author YFAN
 * @date 2021/12/12/012
 */
public class Translator {
    private static final Log log = LogFactory.get();
    // 调用地址
    private static final String YOUDAO_API = "https://openapi.youdao.com/api";
    // 配置信息
    private static final Setting setting = new Setting("classpath:/有道云翻译/config.properties");

    /*
     * 翻译
     * 默认中译英
     * @author YFAN
     * @date 2021/12/12/012
     * @param  * @param text 翻译内容
     * @return java.util.ArrayList<java.lang.String> 翻译结果
     */
    public static ArrayList<String> traslate(String text) {
        return traslate(text, "zh-CHS", "en");
    }

    public static ArrayList<String> traslate(String text, String from, String to) {
        return traslateResultVo(text, from, to).getTranslation();
    }

    /*
     * 翻译
     * 默认中译英
     * @author YFAN
     * @date 2021/12/12/012
     * @param  * @param text 翻译内容
     * @return ResultVo 响应结果对象
     */
    public static ResultVo traslateResultVo(String text) {
        return traslateResultVo(text, "zh-CHS", "en");
    }

    /*
     * 描述
     * @author YFAN
     * @date 2021/12/12/012
     * @param  * @param text 翻译文本
     * @param from 源语言 例如：zh-CHS
     * @param to 目标语言 例如：en
     * @return ResultVo 响应结果对象
     */
    public static ResultVo traslateResultVo(String text, String from, String to) {
        long time = System.currentTimeMillis();
        SendVo sendVo = new SendVo.SendVoBuilder()
                .setQ(text)
                .setFrom(from)
                .setTo(to)
                .setAppKey(setting.get("appKey"))
                .setSalt(String.valueOf(time))
                .setAppToken(setting.get("appToken"))
                .setVocabId(setting.get("vocabId"))
                // 当前UTC时间戳(秒)
                .setCurtime(String.valueOf(time / 1000))
                .setExt(setting.get("ext"))
                .setVoice(setting.get("voice"))
                .setStrict(setting.get("strict"))
                .builder();
        HttpResponse httpResponse = HttpRequest.post(YOUDAO_API)
                .form(BeanUtil.beanToMap(sendVo))
                .execute();
        log.info("url:{}", YOUDAO_API);
        log.info("form:{}", BeanUtil.beanToMap(sendVo));
        String body = httpResponse.body();
        log.info("body:{}", body);
        return JSONUtil.toBean(body, ResultVo.class);
    }
}
