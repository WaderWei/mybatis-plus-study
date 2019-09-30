package wade.wei.config;

import com.baomidou.mybatisplus.extension.incrementer.H2KeyGenerator;
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
}
