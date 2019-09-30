package wade.wei;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
    public UserMapper userMapper;

    @Autowired
    public RoleMapper roleMapper;

    @Test
    public void test1() {
        System.out.println("普通查询");
        List<Role> roles = roleMapper.selectList(new QueryWrapper<Role>().lambda().eq(Role::getId, 2L));
        System.out.println(roles);
        List<User> users = userMapper.selectList(new QueryWrapper<User>().lambda().ge(User::getId, 3L));
        System.out.println(users);
    }

    @Test
    public void test2() {
        System.out.println("带子查询（sql注入）");
        //SELECT id,name,email,age FROM user WHERE (id IN (select id from role where id = 2))
        List<User> users = userMapper.selectList(new QueryWrapper<User>().lambda()
                .inSql(User::getId, "select id from role where id = 2"));
        System.out.println(users);

        System.out.println("带嵌套的查询");
        //SELECT id,role_id,name,email,age FROM user WHERE ( (role_id = 2 OR role_id = 3) ) AND ( (age > 20) )
        List<User> users1 = userMapper.selectList(new QueryWrapper<User>().lambda()
                .nested(i -> i.eq(User::getRoleId, 2L)
                        .or()
                        .eq(User::getRoleId, 3L))
                .and(i -> i.gt(User::getAge, 20)));
        System.out.println(users1);

        //SELECT id,role_id,name,email,age FROM user WHERE ( (age > 18 AND email LIKE '%@%') ) AND ( (role_id = 1 OR role_id = 2) )
        List<User> users3 = userMapper.selectList(new LambdaQueryWrapper<User>()
                .nested(i -> i.gt(User::getAge, 18).like(User::getEmail, "@"))
                .nested(i -> i.eq(User::getRoleId, 1L).or().eq(User::getRoleId, 2L)));
        System.out.println(users3);


        System.out.println("自定义sql注入----->拼接 sql");
        //SELECT id,role_id,name,email,age FROM user WHERE (role_id=2 and age >18)
        List<User> users2 = userMapper.selectList(new QueryWrapper<User>().lambda().apply("role_id={0} and age >{1}", 2, 18));
        System.out.println(users2);


    }


}
