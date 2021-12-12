package 有道云翻译;

import java.util.ArrayList;
import java.util.HashMap;

/*
 * 翻译结果对象
 * @author YFAN
 * @date 2021/12/12/012
 */
public class ResultVo {
    private String errorCode;// 错误返回码	一定存在
    private String query;//	源语言	查询正确时，一定存在
    private ArrayList<String> translation;// 翻译结果	查询正确时，一定存在
    private String basic;//	词义	基本词典，查词时才有
    private ArrayList<HashMap<String,ArrayList<String>>> web;//	Array	词义	网络释义，该结果不一定存在
    private String l;//	源语言和目标语言	一定存在
    private HashMap<String,String> dict;// 词典deeplink	查询语种为支持语言时，存在
    private HashMap<String,String> webdict;// webdeeplink	查询语种为支持语言时，存在
    private String tSpeakUrl;//	翻译结果发音地址	翻译成功一定存在，需要应用绑定语音合成实例才能正常播放 否则返回110错误码
    private String speakUrl;// 源语言发音地址	翻译成功一定存在，需要应用绑定语音合成实例才能正常播放 否则返回110错误码
    private ArrayList<String> returnPhrase;// 单词校验后的结果	主要校验字母大小写、单词前含符号、中文简繁体

    public ResultVo(){}

    @Override
    public String toString() {
        return "ResultVo{" +
                "errorCode='" + errorCode + '\'' +
                ", query='" + query + '\'' +
                ", translation=" + translation +
                ", basic='" + basic + '\'' +
                ", web=" + web +
                ", l='" + l + '\'' +
                ", dict='" + dict + '\'' +
                ", webdict='" + webdict + '\'' +
                ", tSpeakUrl='" + tSpeakUrl + '\'' +
                ", speakUrl='" + speakUrl + '\'' +
                ", returnPhrase=" + returnPhrase +
                '}';
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public void setTranslation(ArrayList<String> translation) {
        this.translation = translation;
    }

    public void setBasic(String basic) {
        this.basic = basic;
    }

    public void setWeb(ArrayList<HashMap<String,ArrayList<String>>> web) {
        this.web = web;
    }

    public void setL(String l) {
        this.l = l;
    }

    public void setDict(HashMap<String,String> dict) {
        this.dict = dict;
    }

    public void setWebdict(HashMap<String,String> webdict) {
        this.webdict = webdict;
    }

    public void settSpeakUrl(String tSpeakUrl) {
        this.tSpeakUrl = tSpeakUrl;
    }

    public void setSpeakUrl(String speakUrl) {
        this.speakUrl = speakUrl;
    }

    public void setReturnPhrase(ArrayList<String> returnPhrase) {
        this.returnPhrase = returnPhrase;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getQuery() {
        return query;
    }

    public ArrayList<String> getTranslation() {
        return translation;
    }

    public String getBasic() {
        return basic;
    }

    public ArrayList<HashMap<String, ArrayList<String>>> getWeb() {
        return web;
    }

    public String getL() {
        return l;
    }

    public HashMap<String,String> getDict() {
        return dict;
    }

    public HashMap<String,String> getWebdict() {
        return webdict;
    }

    public String gettSpeakUrl() {
        return tSpeakUrl;
    }

    public String getSpeakUrl() {
        return speakUrl;
    }

    public ArrayList<String> getReturnPhrase() {
        return returnPhrase;
    }
}
