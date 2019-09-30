package wade.wei.model;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author Administrator
 * 自定义分页
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class MyPage<T> extends Page<T> {
    private static final long serialVersionUID = -4243158285649213902L;

    private String selectStr;
    private Integer selectInt;
    private String name;

    public MyPage(long current, long size) {
        super(current, size);
    }
}
