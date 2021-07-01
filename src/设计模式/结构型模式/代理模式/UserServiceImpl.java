package 设计模式.结构型模式.代理模式;

import com.sun.deploy.util.StringUtils;
import com.sun.deploy.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class UserServiceImpl implements IUserService{

    private Map<String, String> map = new HashMap<>();


    @Override
    public String getUserName() {
        return map.getOrDefault("UserName","");

    }

    @Override
    public void setUserName(String username) {
        map.put("UserName", username);
    }
}
