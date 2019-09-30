package wade.wei.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author Administrator
 */
@Data
public class Role {

    @TableId(type = IdType.ID_WORKER)
    private Long id;

    @TableField(value = "role_name")
    private String roleName;

    @TableField("role_describe")
    private String roleDescribe;
}
