package com.epam.buscompany.config;

import com.epam.buscompany.dao.*;
import com.epam.buscompany.dao.impl.*;
import com.epam.buscompany.service.*;
import com.epam.buscompany.service.impl.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.validation.Validator;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = {
        "com.epam.buscompany"})
@EnableWebMvc
@EnableTransactionManagement
@Slf4j
public class TestConfig {

    private static final Marker FATAL = MarkerFactory.getMarker("FATAL");

    private static final String PERSISTENCE = "persistence.properties";

    @Bean
    public JdbcTemplate template() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(Arrays.asList("Access-Control-Allow-Headers", "Access-Control-Allow-Origin", "Access-Control-Request-Method", "Access-Control-Request-Headers", "Origin", "Cache-Control", "Content-Type", "Authorization"));
        configuration.setAllowedMethods(Arrays.asList("DELETE", "GET", "POST", "PATCH", "PUT"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean(destroyMethod = "shutdown")
    public EmbeddedDatabase dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .setName("test1")
                .addScript("./h2_create.sql")
                .build();
    }

    public Properties hibernateProp() {
        Properties prop = new Properties();
        try {
            prop.load(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(PERSISTENCE)));
        } catch (IOException ex) {
            log.error(FATAL, "hibernate properties error", ex);
        }

        return prop;
    }

    @Bean
    public SessionFactory sessionFactory()
            throws IOException {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setPackagesToScan("com.epam.buscompany.model.entity");
        sessionFactoryBean.setMappingResources();
        sessionFactoryBean.setHibernateProperties(hibernateProp());
        sessionFactoryBean.afterPropertiesSet();
        return sessionFactoryBean.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager()
            throws IOException {
        return new HibernateTransactionManager(sessionFactory());
    }

    @Bean
    public Validator localValidatorFactoryBean() {
        return new LocalValidatorFactoryBean();
    }

    @Bean(name = "busDaoImpl")
    public BusDao busDao() {
        return new BusDaoImpl();
    }

    @Bean(name = "driverDaoImpl")
    public DriverDao driverDao() {
        return new DriverDaoImpl();
    }

    @Bean(name = "garageDaoImpl")
    public GarageDao garageDao() {
        return new GarageDaoImpl();
    }

    @Bean(name = "routeDaoImpl")
    public RouteDao routeDao() {
        return new RouteDaoImpl();
    }

    @Bean(name = "stageDaoImpl")
    public StageDao stageDao() {
        return new StageDaoImpl();
    }

    @Bean(name = "townDaoImpl")
    public TownDao townDao() {
        return new TownDaoImpl();
    }

    @Bean(name = "busServiceImpl")
    public BusServiceImpl busService() {
        return new BusServiceImpl(busDao());
    }

    @Bean(name = "driverServiceImpl")
    public DriverService driverService() {
        return new DriverServiceImpl(driverDao());
    }

    @Bean(name = "garageServiceImpl")
    public GarageService garageService() {
        return new GarageServiceImpl(garageDao());
    }

    @Bean(name = "routeServiceImpl")
    public RouteService routeService() {
        return new RouteServiceImpl(routeDao());
    }

    @Bean(name = "stageServiceImpl")
    public StageService stageService() {
        return new StageServiceImpl(stageDao());
    }

    @Bean(name = "townServiceImpl")
    public TownService townService() {
        return new TownServiceImpl(townDao());
    }
}
