package com.jkh.BE.steps;

import com.jkh.BE.database.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.yandex.qatools.allure.annotations.Step;

@Component
public class DataLoadSteps {

    private final UserDaoImpl usersDao;
    private final CounterDaoImpl counterDao;
    private final FlatDaoImpl flatDao;
    private final HouseDaoImpl houseDao;
    private final IndicationDaoImpl indicationDao;
    private final TariffDaoImpl tariffDao;

    @Autowired
    public DataLoadSteps(UserDaoImpl usersDao, CounterDaoImpl counterDao, FlatDaoImpl flatDao,
                         HouseDaoImpl houseDao, IndicationDaoImpl indicationDao, TariffDaoImpl tariffDao) {
        this.usersDao = usersDao;
        this.counterDao = counterDao;
        this.flatDao = flatDao;
        this.houseDao = houseDao;
        this.indicationDao = indicationDao;
        this.tariffDao = tariffDao;
    }

    @Step("Delete all data from DB")
    public void deleteAllData() {
        usersDao.deleteAll();
        counterDao.deleteAll();
        flatDao.deleteAll();
        houseDao.deleteAll();
        indicationDao.deleteAll();
        tariffDao.deleteAll();
    }
}
