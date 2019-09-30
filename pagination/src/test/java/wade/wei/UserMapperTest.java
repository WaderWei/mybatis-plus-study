package wade.wei;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import wade.wei.entity.User;
import wade.wei.mapper.UserMapper;
import wade.wei.model.MyPage;
import wade.wei.model.ParamSome;
import wade.wei.model.RoleChildren;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTest {

    @Autowired
    public UserMapper userMapper;

    @Test
    public void test1() {
        System.out.println("自带分页");
        //SELECT id,role_id,name,email,age FROM user WHERE (age >= 10) ORDER BY age ASC LIMIT 0,3
        Page<User> userPage = new Page<>(1, 3);
        IPage<User> userIPage = userMapper.selectPage(userPage,
                new QueryWrapper<User>()
                        .lambda()
                        .ge(User::getAge, 10)
                        .orderByAsc(User::getAge));
        System.out.println("当前页数据：" + userIPage.getRecords());
        System.out.println("当前页码：" + userIPage.getCurrent());
        System.out.println("当前满足条件的总行数：" + userIPage.getTotal());
        System.out.println("当前分页总页数:" + userIPage.getSize());
        //传入的page对象与返回的page对象是同一个
        System.out.println(userPage == userIPage);

        System.out.println("序列化");
        /**
         *{
         * "current":1,
         * "orders":[],
         * "pages":2,
         * "records":[{"age":18,"email":"test1@baomidou.com","id":1,"name":"Jone","roleId":"1"},{"age":20,"email":"test2@baomidou.com","id":2,"name":"Jack","roleId":"2"},{"age":21,"email":"test4@baomidou.com","id":4,"name":"Sandy","roleId":"3"}],
         * "searchCount":true,
         * "size":3,
         * "total":5
         * }
         */
        String s = JSON.toJSONString(userIPage);
        Page page = JSON.parseObject(s, Page.class);
        System.out.println(s);
        System.out.println(page.getRecords());
    }

    @Test
    public void test2() {
        System.out.println("自定义注解");
        String name = "Tom";
        MyPage<User> wade = userMapper.mySelectPage(new MyPage<User>(1, 3).setSelectInt(18).setName("Jack"), new ParamSome(10, name));
        System.out.println(wade.getRecords());
    }

    @Test
    public void test3() {
        // Consume Time：6 ms 2019-09-29 15:51:39
        // Execute SQL：SELECT COUNT(1) FROM user WHERE (age >= 18 AND name = 'Jack') OR (age >= 10 AND name = 'Tom')
        //
        // Consume Time：0 ms 2019-09-29 15:51:39
        // Execute SQL：select * from user where (age >= 18 and name = 'Jack') or (age >= 10 and name = 'Tom') LIMIT 0,3
        System.out.println("自定义xml分页");
        MyPage<User> userMyPage = userMapper.mySelectPage(new MyPage<User>(1, 3).setSelectInt(18).setSelectStr("Jack"), new ParamSome(10, "Tom"));
        System.out.println(userMyPage.getRecords());
    }

    @Test
    public void test4(){
        /* 下面的 left join 不会对 count 进行优化,因为 where 条件里有 join 的表的条件 */
        //Consume Time：7 ms 2019-09-30 10:39:10
        // Execute SQL：SELECT COUNT(1) FROM role r LEFT JOIN user u ON r.Id = u.role_id WHERE (u.name = 'Jack' AND u.age >= 10) OR (age >= 10 AND name = 'Tom')
        //
        // Consume Time：0 ms 2019-09-30 10:39:10
        // Execute SQL：select r.role_name,r.role_describe,u.id,u.name,u.age,u.email from role r left join user u on r.Id = u.role_id where (u.name = 'Jack' and u.age >= 10) or (age >= 10 and name = 'Tom') LIMIT 0,5
        MyPage<RoleChildren> userMyPage = userMapper.roleChildren(new MyPage<RoleChildren>(1, 5).setSelectInt(10).setSelectStr("Jack"), new ParamSome(10, "Tom"));
        System.out.println(userMyPage.getRecords());

        /* 下面的 left join 会对 count 进行优化,因为 where 条件里没有 join 的表的条件 */
    }

    @Test
    public void test5(){
        MyPage<User> userMyPage = userMapper.userSelectPage(new MyPage<User>(1, 3), new ParamSome(10, "%a%"));
        System.out.println(userMyPage.getRecords());
    }

    @Test
    public void test6(){
        // Consume Time：7 ms 2019-09-30 11:04:26
        // Execute SQL：SELECT COUNT(1) FROM user WHERE name LIKE '%a%'
        //
        // Consume Time：0 ms 2019-09-30 11:04:26
        // Execute SQL：select * from user WHERE name like '%a%' LIMIT 0,3
        HashMap<String, String> stringStringHashMap = new HashMap<>(1);
        stringStringHashMap.put("name","%a%");
        MyPage<User> userMyPage = userMapper.mySelectPageMap(new MyPage<User>(1, 3), stringStringHashMap);
        System.out.println(userMyPage.getRecords());
    }
}
