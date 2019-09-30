package wade.wei.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import wade.wei.entity.Role;
import wade.wei.entity.User;

import java.util.List;

/**
 * @author Administrator
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class RoleChildren extends Role {
    private List<User> users;
}
