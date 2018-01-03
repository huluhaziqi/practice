import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LinTestClientApplication {
    private static Logger logger = LoggerFactory.getLogger(LinTestApplication.class);
    public static void main(String[] args) {
        logger.info("start lin test");
        new ClassPathXmlApplicationContext("classpath:META-INF/spring/*.xml");
        logger.info("end lin test");
    }
}
