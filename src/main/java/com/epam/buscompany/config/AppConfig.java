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
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;
import javax.validation.Validator;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;
import java.util.ResourceBundle;

@Configuration
@ComponentScan(basePackages = {
        "com.epam.buscompany"})
@EnableWebMvc
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableTransactionManagement
@Slf4j
public class AppConfig {

    private static final Marker FATAL = MarkerFactory.getMarker("FATAL");

    private static final String DATA_SOURCE = "datasource";
    private static final String PERSISTENCE = "persistence.properties";
    private static final String JDBC_URL = "jdbcUrl";
    private static final String DRIVER_CLASS_NAME = "driverClassName";
    private static final String USERNAME = "username";
    private static final String PASS_WORD = "password";

    // Mail formatter
    private static final String MAIL_SOURCE = "mail";
    private static final String MAIL_HOST = "mail.host";
    private static final String MAIL_PORT = "mail.port";
    private static final String MAIL_USERNAME = "mail.username";
    private static final String MAIL_PASS_WORD = "mail.password";
    private static final String MAIL_PROTOCOL = "mail.protocol";
    private static final String TRANSPORT_PROTOCOL = "mail.transport.protocol";
    private static final String MAIL_DEBUG = "mail.debug";
    private static final String MAIL_SMTP_ENABLE = "mail.smtp.starttls.enable";
    private static final String MAIL_SMTP_REQUIRED = "mail.smtp.starttls.required";

    @Bean
    public JdbcTemplate template() {
        return new JdbcTemplate(dataSource());
    }


    @Bean
    public DataSource dataSource() {
        ResourceBundle bundle = ResourceBundle.getBundle(DATA_SOURCE);
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(bundle.getString(DRIVER_CLASS_NAME));
        dataSource.setUrl(bundle.getString(JDBC_URL));
        dataSource.setUsername(bundle.getString(USERNAME));
        dataSource.setPassword(bundle.getString(PASS_WORD));
        return dataSource;
    }

    private Properties hibernateProperties() {
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
        sessionFactoryBean.setHibernateProperties(hibernateProperties());
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

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        ResourceBundle bundle = ResourceBundle.getBundle(MAIL_SOURCE);

        mailSender.setHost(bundle.getString(MAIL_HOST));
        mailSender.setPort(Integer.parseInt(bundle.getString(MAIL_PORT)));
        mailSender.setUsername(bundle.getString(MAIL_USERNAME));
        mailSender.setPassword(bundle.getString(MAIL_PASS_WORD));

        Properties properties = mailSender.getJavaMailProperties();

        properties.setProperty(TRANSPORT_PROTOCOL, bundle.getString(MAIL_PROTOCOL));
        properties.setProperty(MAIL_DEBUG, bundle.getString(MAIL_DEBUG));
        properties.setProperty(MAIL_SMTP_ENABLE, bundle.getString(MAIL_SMTP_ENABLE));
        properties.setProperty(MAIL_SMTP_REQUIRED, bundle.getString(MAIL_SMTP_REQUIRED));

        return mailSender;
    }

    @Bean
    public InfoMailSender infoMailSender() {
        return new InfoMailSender();
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
