package wade.wei.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import wade.wei.entity.Role;
import wade.wei.entity.User;
import wade.wei.model.MyPage;
import wade.wei.model.ParamSome;
import wade.wei.model.RoleChildren;

/**
 * @author Administrator
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 3.x 的 page 可以进行取值,多个入参记得加上注解
     * 自定义 page 类必须放在入参第一位
     * 返回值可以用 IPage<T> 接收 也可以使用入参的 MyPage<T> 接收
     * <li> 3.1.0 之前的版本使用注解会报错,写在 xml 里就没事 </li>
     * <li> 3.1.0 开始支持注解,但是返回值只支持 IPage ,不支持 IPage 的子类</li>
     */
    /**
     * @param myPage
     * @param paramSome
     * @return
     */
    //@Select("select * from user where ( age >= #{pg.selectInt} and name  = #{pg.selectStr}) or (age >= #{ps.yihao} and name = #{ps.erhao})")
    MyPage<User> mySelectPage(@Param("pg") MyPage<User> myPage, @Param("ps") ParamSome paramSome);


    @ResultMap("userChildrenMap")
    @Select("select r.role_name,r.role_describe,u.id,u.name,u.age,u.email " +
            "from role r left join user u on r.Id = u.role_id " +
            "where (u.name = #{pg.selectStr} and u.age >= #{pg.selectInt}) or (age >= #{ps.yihao} and name = #{ps.erhao})")
    MyPage<RoleChildren> roleChildren(@Param("pg") MyPage<RoleChildren> myPage, @Param("ps") ParamSome paramSome);
}
