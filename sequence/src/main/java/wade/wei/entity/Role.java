package wade.wei.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Administrator
 */
@Data
@Accessors(chain = true)
@TableName("role")
public class Role {
    @TableId(value = "userid", type = IdType.ID_WORKER_STR)
    private String userId;

    @TableField("roleid")
    private String roleId;

    @TableField("pwd")
    private String pwd;

    @TableField("loginid")
    private String loginId;

}
