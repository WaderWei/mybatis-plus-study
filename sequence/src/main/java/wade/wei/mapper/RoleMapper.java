package wade.wei.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import wade.wei.entity.Role;

/**
 * @author Administrator
 */
@DS("sqlserver")
public interface RoleMapper extends BaseMapper<Role> {
}
