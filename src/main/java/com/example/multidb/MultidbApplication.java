package com.example.multidb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

//ataSource를 직접 설정해야하기 때문에 Spring에서 DataSourceAutoConfiguration 클래스를 제외해야합니다.
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class MultidbApplication {

    /**
     * 이터베이스가 하나만 존재하는 경우에는 간단하게 application.properties 에 설정 내용만 추가하면 바로 사용이 가능했습니다.
     * 그러나 데이터베이스가 여러 개 존재하는 경우에는 여러 데이터소스를 만들어서 transaction도 잡아주고 DB위치도 다르게 잡아줘야 합니다.
     */

    public static void main(String[] args) {
        SpringApplication.run(MultidbApplication.class, args);
    }

}
