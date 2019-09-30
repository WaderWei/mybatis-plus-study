package wade.wei.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Administrator
 */
@Data
@Accessors(chain = true)
public class User {

    //@TableId(value = "id",type = IdType.ID_WORKER)
    //@TableId(value = "id",type = IdType.AUTO)
    //private Long id;

    //@TableId(value = "id",type = IdType.UUID)
    @TableId(value = "id",type = IdType.ID_WORKER_STR)
    private String id;

    private String name;

    private Integer age;

    private String email;

    @TableField("role_id")
    private String roleId;
}
