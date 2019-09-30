package wade.wei;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
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

    @Test
    public void test3() {
        System.out.println("乐观锁控制数据的更新");//会将version自动+1，然后对数据进行更新
        User user = userMapper.selectById("1");
        System.out.println(user);
        user.setName("wade111111");
        //UPDATE user SET role_id='1', name='wade111111', version=2, email='test1@baomidou.com', age=18 WHERE id='1' AND version=1
        //通过将version作为条件，如果version被更新过，则找不到记录无法更新，返回结果为0
        int i = userMapper.updateById(user);
        System.out.println(i);
    }

    @Test
    public void test4() {
        System.out.println("插入时填充默认值");
        User wade = new User().setName("wade").setEmail("123@qq.com").setAge(20);
        int insert = userMapper.insert(wade);
        System.out.println(insert);
    }

    @Test
    public void test5() {
        System.out.println("更新时填充默认值");
        User user = userMapper.selectById(1);
        user.setName("wadeeeee");
        int update = userMapper.update(user, null);
        System.out.println(update);
    }

    @Test
    public void test6() {
        System.out.println("如果没有设置版本");//没有设置版本则不会处理乐观锁等问题
        User user = new User();
        user.setName("wadeeeee").setId("1").setOperator("peter");
        int update = userMapper.updateById(user);
        System.out.println(update);
    }

    @Test
    public void test7(){
        System.out.println("逻辑删除");
        //UPDATE user SET deleteFlag=1 WHERE id='1' AND deleteFlag=0
        int i = userMapper.deleteById("1");
        System.out.println(i);

        //SELECT id,deleteFlag,createtime,role_id,name,updatetime,version,operator,email,age FROM user WHERE id=1 AND deleteFlag=0
        User user = userMapper.selectById(1);
        System.out.println(user);
    }
}
