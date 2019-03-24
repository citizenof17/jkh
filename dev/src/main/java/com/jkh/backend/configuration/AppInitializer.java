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
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AppInitializer implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private CommonService commonService;

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Value("${DEFAULT_PERIOD_BETWEEN_COUNTERS_SENDINGS:30}")
    private Integer n;

    @Value("${ADMIN_LOGIN:Administrator}")
    private String login;

    @Value("${ADMIN_PASSWORD:Administrator_1}")
    private String password;

    @Value("${ADMIN_NAME:Администратор Великий Ужаснович}")
    private String name;

    @Value("${ADMIN_EMAIL:Administrator@jkh.ru}")
    private String email;

    @Value("${ADMIN_PHONE:+79995550000}")
    private String phone;

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
        admin.setName(name);
        admin.setEmail(email);
        admin.setPhone(phone);
        admin.setStatus(Status.ACTIVE);
        admin.setPassword(bCryptPasswordEncoder.encode(password));
        admin.setRole(Role.ADMIN);
        userService.save(admin);
    }
}
