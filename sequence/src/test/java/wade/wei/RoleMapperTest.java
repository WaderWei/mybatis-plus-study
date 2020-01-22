package wade.wei;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import wade.wei.entity.Role;
import wade.wei.entity.User;
import wade.wei.mapper.RoleMapper;
import wade.wei.mapper.UserMapper;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RoleMapperTest {

    @Autowired
    public RoleMapper roleMapper;

    @Test
    public void test1() {
        List<Role> roles = roleMapper.selectList(null);
        System.out.println(roles);
    }
}
