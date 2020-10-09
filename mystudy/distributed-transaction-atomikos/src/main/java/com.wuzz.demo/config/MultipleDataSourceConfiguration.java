package com.wuzz.demo.config;//package com.wuzz.demo.config;
//
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//
//import javax.sql.DataSource;
//
///**
// * 多数据源 DataSource 配置
// * @author ：wuzhenzhao
// * @date ：Created in 2019/1/24 9:26
// */
//
//@Configuration
//public class MultipleDataSourceConfiguration {
//
//    @Bean
//    @Primary
//    public DataSource masterDataSource(){
//
////        spring.datasource.driverClassName =com.mysql.jdbc.Driver
////        spring.datasource.url = jdbc:mysql://localhost:3306/test
////        spring.datasource.username=root
////        spring.datasource.password=123456
//        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
//
//        DataSource dataSource = dataSourceBuilder
//                .driverClassName("com.mysql.jdbc.Driver")
//                .url("jdbc:mysql://localhost:3306/disaster")
//                .username("root")
//                .password("zhenzhao123456")
//                .build();
//        System.out.println(dataSource);
//        return dataSource;
//    }
//
//    @Bean
//    public DataSource salveDataSource(){
//
//        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
//
//        DataSource dataSource = dataSourceBuilder
//                .driverClassName("com.mysql.jdbc.Driver")
//                .url("jdbc:mysql://localhost:3306/disaster1")
//                .username("root")
//                .password("zhenzhao123456")
//                .build();
//        System.out.println(dataSource);
//        return dataSource;
//
//    }
//
//}
//
