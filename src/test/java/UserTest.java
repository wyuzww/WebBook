import com.alibaba.fastjson.JSON;
import com.ethan.dao.UserDao;
import com.ethan.entity.User;
import com.ethan.factory.Factory;
import com.ethan.service.UserService;
import com.ethan.service.impl.UserServiceImpl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserTest {
    public static void main(String[] args) {
//        System.setProperty("com.mchange.v2.c3p0.cfg.xml",System.getProperty("user.dir")+"/src/main/resources/c3p0-config.xml");
        UserServiceImpl userService = Factory.getUserServiceInstance();
//
//        User user = new User(1,"admin","123456","","","","","");
        User user = Factory.getUser();
//        user.setAccount("admin");
//        user.setPassword("123456");
//        User userResult = null;
//        userResult = userService.login(user);
//        System.out.println(userResult);
//        String account = "d";
        String name = "2";
//
//        user.setAccount(account);
        user.setUser_name(name);

        List<User> users = null;
        try {
            users = userService.allUser(user, 1, 10);
        } catch (SQLException e) {
            e.printStackTrace();
        }
//            System.out.println(users.toString());
        int total = 10;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rows", users);
        map.put("total", total);

        String jsonString = JSON.toJSONString(map);
        System.out.println(jsonString.toString());
    }
}
