package 有道云翻译;

import java.util.ArrayList;

public class TranslatorTest {
    public static void main(String[] args) {
        ArrayList<String> stringArrayList = Translator.traslate("测试", "zh-CHS", "en");
        System.out.println(stringArrayList);
    }
/* 测试控制台输出
[2021-12-12 15:14:24] [DEBUG] cn.hutool.log.LogFactory: Use [Hutool Console Logging] Logger As Default.
[2021-12-12 15:14:24] [DEBUG] cn.hutool.setting.SettingLoader: Load setting file [file:/D:/IDEA_WORKSPACE/JavaPractice/target/classes/%e6%9c%89%e9%81%93%e4%ba%91%e7%bf%bb%e8%af%91/config.properties]
[2021-12-12 15:14:25] [INFO] 有道云翻译.Translator: url:https://openapi.youdao.com/api
[2021-12-12 15:14:25] [INFO] 有道云翻译.Translator: form:{q=测试, from=zh-CHS, to=en, appKey=769e19f3a9ae7fe5, salt=1639293264462, sign=33A599532FF8D9FE32889ABD783CC8CC7DA1DA26640664B93B8983FB23651533, signType=v3, curtime=1639293264, ext=null, voice=null, strict=null, vocabId=null}
[2021-12-12 15:14:25] [INFO] 有道云翻译.Translator: body:{"returnPhrase":["测试"],"query":"测试","errorCode":"0","l":"zh-CHS2en","tSpeakUrl":"https://openapi.youdao.com/ttsapi?q=test&langType=en&sign=7B58992A5DDB06ECA80099DEE4E81D75&salt=1639293267358&voice=4&format=mp3&appKey=769e19f3a9ae7fe5&ttsVoiceStrict=false","web":[{"value":["Test","TST test","Beta","testing"],"key":"测试"},{"value":["Unit testing","Junit","unit system acceptance testing","PHPUnit"],"key":"单元测试"},{"value":["software testing","BTEST","Ron Patton","What is SoftWare Test"],"key":"软件测试"}],"requestId":"0a78446d-52f9-4bf6-9703-c1cc0a60c92e","translation":["test"],"dict":{"url":"yddict://m.youdao.com/dict?le=eng&q=%E6%B5%8B%E8%AF%95"},"webdict":{"url":"http://mobile.youdao.com/dict?le=eng&q=%E6%B5%8B%E8%AF%95"},"basic":{"phonetic":"cè shì","explains":["[试验] test","measurement"]},"isWord":true,"speakUrl":"https://openapi.youdao.com/ttsapi?q=%E6%B5%8B%E8%AF%95&langType=zh-CHS&sign=AD5CEFB770EBB353FF1807138CABC48A&salt=1639293267358&voice=4&format=mp3&appKey=769e19f3a9ae7fe5&ttsVoiceStrict=false"}
[test]
*/
}
