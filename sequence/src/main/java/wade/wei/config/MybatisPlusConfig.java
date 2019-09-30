package wade.wei.config;

import com.baomidou.mybatisplus.extension.incrementer.H2KeyGenerator;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Administrator
 */
@Configuration
@MapperScan("wade.wei.mapper")
public class MybatisPlusConfig {
    @Bean
    public H2KeyGenerator h2KeyGenerator() {
        return new H2KeyGenerator();
    }

    /**
     * 乐观锁
     *  1. 数据库中要有version字段
     *  2. 对应的实体字段加上@Version注解
     *  3. mp更新数据时会将version作为条件更新，如果找得到，version会被更新+1，如果找不到，则更新结果返回0
     *
     * @return
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }
}
