package wade.wei.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import wade.wei.entity.User;

/**
 * @author Administrator
 */
@DS("mysql")
public interface UserMapper extends BaseMapper<User> {

}
