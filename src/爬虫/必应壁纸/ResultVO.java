package 爬虫.必应壁纸;

import java.util.List;

/*
 * 结果对象
 * @author YFAN
 * @date 2022/4/23/023
 */
public class ResultVO {
    private String url;
    private List<String> list;

    public ResultVO() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
}
