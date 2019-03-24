package com.jkh.backend.configuration;

import com.jkh.backend.model.Common;
import com.jkh.backend.model.User;
import com.jkh.backend.model.enums.Role;
import com.jkh.backend.model.enums.Status;
import com.jkh.backend.service.CommonService;
import com.jkh.backend.service.UserService;
import com.jkh.backend.utils.CommonKeys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
public class AppInitializer implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private CommonService commonService;

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Value("${default.period.between.counters.sendings}")
    private Integer n;

    @Value("${admin.login}")
    private String login;

    @Value("${admin.password}")
    private String password;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (commonService.findCommonByKey(CommonKeys.DEFAULT_PERIOD_BETWEEN_COUNTERS_SENDINGS) == null) {
            insertDefaultValuesCommon();
        }

        if (userService.findUserByLogin(login) == null) {
            initializeAdmin();
        }
    }

    public void insertDefaultValuesCommon() {
        Common defaultPeriodBetweenCountersSendings = new Common();
        defaultPeriodBetweenCountersSendings.setKey(CommonKeys.DEFAULT_PERIOD_BETWEEN_COUNTERS_SENDINGS);
        defaultPeriodBetweenCountersSendings.setValue(n.toString());
        commonService.save(defaultPeriodBetweenCountersSendings);
    }

    public void initializeAdmin() {
        User admin = new User();
        admin.setLogin(login);
        admin.setName(login);
        admin.setStatus(Status.ACTIVE);
        admin.setPassword(bCryptPasswordEncoder.encode(password));
        admin.setRole(Role.ADMIN);
        userService.save(admin);
    }
}
