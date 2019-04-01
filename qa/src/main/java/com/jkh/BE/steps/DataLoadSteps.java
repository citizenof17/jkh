package com.jkh.BE.steps;

import com.jkh.BE.database.*;
import com.jkh.BE.models.Counter;
import com.jkh.BE.models.RegisterRequest;
import com.jkh.BE.models.enums.Status;
import com.jkh.utils.AllureUtils;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        indicationDao.deleteAll();
        usersDao.deleteAll();
        counterDao.deleteAll();
        flatDao.deleteAll();
        houseDao.deleteAll();
        tariffDao.deleteAll();
    }

    @Step("Select {1} last indication")
    public Integer lastIndication(String login, Counter.CounterType counterType) {
        Integer result = indicationDao.select(login, counterType);
        AllureUtils.saveText("Last " + counterType.toString() + " indication in DB: " + result.toString(), result.toString());
        return result;
    }

    @Step("Generating lists of all users by flats")
    public List<List<Map<String, Object>>> listsUsersByFlat() {
        List<Map<String, Object>> flats = flatDao.selectAllFlatsNumbers();
        List<List<Map<String, Object>>> users = new ArrayList<>();
        for (Map<String, Object> flat : flats) {
            List<Map<String, Object>> usersInFlat = usersDao.selectUserByFlat((Integer) flat.get("number"));
            users.add(usersInFlat);
        }
        return users;
    }

    @Step("Checking that new user saved in database")
    public void checkUser(RegisterRequest expectedUser) {
        Map<String, Object> actualUser = usersDao.selectUserByLogin(expectedUser.getLogin());
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(actualUser.get("login")).as("Incorrect login in DB").isEqualTo(expectedUser.getLogin());
        softAssertions.assertThat(actualUser.get("name")).as("Incorrect name in DB").isEqualTo(expectedUser.getName());
        softAssertions.assertThat(actualUser.get("number")).as("Incorrect flat number in DB").isEqualTo(expectedUser.getFlat().getNumber());
        softAssertions.assertThat(actualUser.get("email")).as("Incorrect email in DB").isEqualTo(expectedUser.getEmail());
        softAssertions.assertThat(actualUser.get("phone")).as("Incorrect phone number in DB").isEqualTo(expectedUser.getPhone());
        softAssertions.assertAll();
    }

    @Step("Checking that {0} user status equal in DB to: {1}")
    public void checkUserStatus(String login, Status status) {
        Assertions.assertThat(usersDao.selectUserByLogin(login).get("status")).as("Incorrect status in DB").isEqualTo(status.toString());
    }

    @Step("Getting information about user: {0}")
    public Map<String, Object> selectUserByLogin(String login) {
        return usersDao.selectUserByLogin(login);
    }

    @Step("Updating all users status to ACTIVE")
    public void updateAllUsersToActive() {
        usersDao.updateAllUsersToActive();
    }

    @Step("Getting information about date of sent indications by user {0}")
    public List<Map<String, Object>> selectDatesByUser(String login) {
        List<Map<String, Object>> result = indicationDao.selectDates(login);
        AllureUtils.saveText("Dates: " + result.toString(), result.toString());
        return result;
    }

    @Step("Selecting indication by date {0}")
    public Map<String, Object> selectIndicationByDate(String date) {
        Map<String, Object> result = indicationDao.selectIndicationByDate(date);
        AllureUtils.saveText("Indication " + date + ": " + result.toString(), result.toString());
        return result;
    }
}
