package wade.wei;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import wade.wei.entity.User;
import wade.wei.mapper.UserMapper;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTest {

    @Autowired
    public UserMapper userMapper;

    @Test
    public void test1() {
        User wade1 = new User().setName("wade1").setAge(20).setEmail("123@qq.com");
        int wade = userMapper.insert(wade1);
        System.out.println(wade1.getId());
    }

    @Test
    public void test2() {
        User wade1 = new User().setName("wade3").setAge(20).setEmail("123@qq.com");
        int wade = userMapper.insert(wade1);
        System.out.println(wade1.getId());
    }
}
