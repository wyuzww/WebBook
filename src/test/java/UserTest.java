import com.ethan.dao.UserDao;
import com.ethan.entity.User;
import com.ethan.factory.Factory;
import com.ethan.service.UserService;
import com.ethan.service.impl.UserServiceImpl;

import java.sql.SQLException;

public class UserTest {
    public static void main(String[] args) {
//        System.setProperty("com.mchange.v2.c3p0.cfg.xml",System.getProperty("user.dir")+"/src/main/resources/c3p0-config.xml");
        UserServiceImpl userService = Factory.getUserServiceInstance();

        User user = new User(1,"admin","123456","","","","",0);
        User userResult = null;
        userService.login(user);
        System.out.println(userResult);
    }
}
