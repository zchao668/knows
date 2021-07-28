package com.work.knows;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@MapperScan("com.work.knows.mapper")
@SpringBootApplication
public class KnowsApplication {

    private static final Logger LOG = LoggerFactory.getLogger(KnowsApplication.class);

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(KnowsApplication.class);
        Environment env = app.run(args).getEnvironment();
        LOG.info("启动成功！！");
        LOG.info("地址: \thttp://127.0.0.1:{}", env.getProperty("server.port"));
    }

}
