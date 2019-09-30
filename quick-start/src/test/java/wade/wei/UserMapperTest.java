package wade.wei;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import wade.wei.entity.User;
import wade.wei.mapper.UserMapper;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test() {
        //SELECT id,name,email,age FROM user
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    @Test
    public void aInsert() {
        User user = new User();
        user.setName("wade");
        user.setAge(10);
        user.setEmail("abc@qq.com");
        //INSERT INTO user ( id, name, email, age ) VALUES ( 1177494184277467137, 'wade', 'abc@qq.com', 10 )
        int result = userMapper.insert(user);
        assertThat(result).isGreaterThan(0);
        //插入成功后可以直接拿到插入的id
        assertThat(user.getId()).isNotNull();
    }

    @Test
    public void bDelete() {
        //DELETE FROM user WHERE id=3
        int result1 = userMapper.deleteById(3L);

        //DELETE FROM user WHERE (name = 'wade')
        LambdaQueryWrapper<User> wade = new QueryWrapper<User>().lambda().eq(User::getName, "wade");
        int result2 = userMapper.delete(wade);

        System.out.println(String.format("%s,%s", result1, result2));
    }

    @Test
    public void cUpdate() {
        User user = new User();
        user.setId(5L);
        user.setName("wade");
        user.setAge(20);
        //UPDATE user SET name='wade', age=20 WHERE id=5
        int result = userMapper.updateById(user);


        User user1 = new User();
        user1.setEmail("eee@123.com");
        //UPDATE user SET email='eee@123.com', name='wade111' WHERE (id = 5)
        userMapper.update(
                user1,
                Wrappers.<User>lambdaUpdate()
                        .set(User::getName, "wade111")
                        .eq(User::getId, 5L));

        //UPDATE user SET email='eee@123.com' WHERE (id = 5)
        userMapper.update(
                user1,
                Wrappers.<User>lambdaUpdate()
                        .eq(User::getId, 5L));

        //UPDATE user SET email=NULL WHERE (name = 'wade111')
        userMapper.update(null,
                Wrappers.<User>lambdaUpdate()
                        .set(User::getEmail,null)
                        .eq(User::getName,"wade111"));

        int r1 = userMapper.update(new User().setAge(100),
                new QueryWrapper<User>().lambda().eq(User::getName, "wade111").gt(User::getAge, "25"));
    }

    @Test
    public void dSelect() {
        //SELECT id,name,email,age FROM user
        List<User> users = userMapper.selectList(null);
        System.out.println(users);

        //SELECT id,name,email,age FROM user WHERE (name = 'wade' AND email LIKE '%eee%')
        List<User> users1 = userMapper.selectList(new QueryWrapper<User>().lambda().eq(User::getName, "wade").like(User::getEmail, "eee"));
        System.out.println(users1);

        //SELECT id,name FROM user WHERE (email LIKE '%@%')
        List<User> users2 = userMapper.selectList(Wrappers.<User>lambdaQuery().select(User::getId, User::getName).like(User::getEmail, "@"));
        System.out.println(users2);

        //SELECT id,name,email,age FROM user WHERE id=1
        User user = userMapper.selectById(1L);
        System.out.println(user);

        //SELECT COUNT( 1 ) FROM user
        Integer integer = userMapper.selectCount(null);
        System.out.println(integer);

        //SELECT id,name,email,age FROM user ORDER BY age ASC
        List<User> users3 = userMapper.selectList(new QueryWrapper<User>().lambda().orderByAsc(User::getAge));
        System.out.println(users3);

        List<User> users4 = userMapper.selectList(new QueryWrapper<User>().select("id", "name", "age").lambda().gt(User::getAge, 20));
        System.out.println(users4);
    }


    @Test
    public void selectMapsPage(){
        //SELECT id,name,email,age FROM user WHERE (email LIKE '%@%')
        //IPage<Map<String, Object>> mapIPage = userMapper.selectMapsPage(new Page<>(1, 3), Wrappers.<User>query().lambda().like(User::getEmail,"@"));
        IPage<User> userIPage = userMapper.selectPage(new Page<>(1, 3), null);
        System.out.println(userIPage);
        System.out.println(userIPage.getCurrent());//1 当前页码
        System.out.println(userIPage.getTotal());//4 总的记录数
        System.out.println(userIPage.getPages());//2 总的页数
        System.out.println(userIPage.getSize());//3 当前页的记录数
        System.out.println(userIPage.getRecords());
    }




}
