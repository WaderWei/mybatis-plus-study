package wade.wei.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

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
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    private String name;

    private Integer age;

    private String email;

    @Version
    private Long version;

    @TableField("role_id")
    private String roleId;

    @TableField(value = "createtime", fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(value = "updatetime", fill = FieldFill.UPDATE)
    private Date updateTime;

    @TableField(value = "operator", fill = FieldFill.INSERT_UPDATE)
    private String operator;

    @TableLogic
    @TableField("deleteFlag")
    private Integer deleteFlag;
}
