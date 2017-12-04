import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 服务启动
 * @author linxiaobin
 * @date 2017年12月01日10:47:08
 */
public class LinTestRpcServiceApplication {

    private static Logger logger = LoggerFactory.getLogger(LinTestRpcServiceApplication.class);

    public static void main(String[] args) {
        logger.info("<<<<<<<<<启动开始<<<<<<<<");
        new ClassPathXmlApplicationContext("classpath:META-INF/spring/*.xml");
        logger.info("<<<<<<<<<启动完成<<<<<<<<");
    }
}
