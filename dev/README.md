##Development Guide

Please do not delete this file because it contains useful for devs code chunks

/register

```
{
"login":"qwerty",
"password":"Qwerty_1",
"phone":"+79875677890",
"flat": {"number":23},
"name":"Olga", 
"email":"kozhi.197@mail.ru"
}
```


/sendIndications


```
[
  {
    "counter" : { "type" : "ELECTRICITY" },
    "value" : "10"
  },
  {
    "counter" : { "type" : "HOT_WATER" },
    "value" : "11"
  },
  {
    "counter" : { "type" : "COLD_WATER" },
    "value" : "12"
  }
]
```

/report 

```
{
  "leftDate" : "2019-03-03",
  "rightDate" : "2019-03-13"
}
```

или

```
{
  "standardPeriod" : "THIS_YEAR"
}
```

Только для админов:

```
{
  "standardPeriod" : "THIS_MONTH",
  "flat" : { "number" : "13" },
  "status" : "ACTIVE"
}
```

Response: 

Сообщение генерируется на основе параметров запроса.
```
{
    "message": "Отчет за 2019 год для квартиры 23 по пользователям со статусом Активный",
    "rows": [
        {
            "flat": {
                "number": 23
            },
            "date": "22.03.2019",
            "daysPassed": 0,
            "indicationMap": {
                "COLD_WATER": {
                    "type": "COLD_WATER",
                    "value": 12
                },
                "HOT_WATER": {
                    "type": "HOT_WATER",
                    "value": 11
                },
                "ELECTRICITY": {
                    "type": "ELECTRICITY",
                    "value": 13
                }
            }
        },
        ...
    ],
    "total": {
        "COLD_WATER": {
            "type": "COLD_WATER",
            "value": 0
        },
        "ELECTRICITY": {
            "type": "ELECTRICITY",
            "value": 3
        },
        "HOT_WATER": {
            "type": "HOT_WATER",
            "value": 0
        }
    }
}
```


/userInfo - гет без параметров

Response:

```
{
    "name": "Администратор Великий Ужаснович",
    "role": "ADMIN",
    "status": "ACTIVE",
    "defaultPeriodBetweenSendings": 30,
    "countNewcomers": 7,
    "countWhoDidNotSend": 3
}
```

или

```
{
    "name": "Olga",
    "role": "USER",
    "status": "ACTIVE",
    "daysOverDefaultPeriodOfCountersSending": 0
}
```

/getAdminContacts - гет без параметров

Response:

```
{
    "name": "Администратор Великий Ужаснович",
    "email": "Administrator@jkh.ru",
    "phone": "+79990005555"
}
```

/admin/setNotificationsPeriod - параметры передаются в url:

```
/admin/setNotificationsPeriod?notificationsPeriod=30
```

Response: 200 или 401 и 

```
{ "message" : "Это действие недоступно" }
```

/admin/getWhoDidNotSend - гет без параметров

Response:

```
[
    {
        "name": "abacaba qwe",
        "flatNumber": 16,
        "phone": "+79005553536",
        "login": "abacaba1",
        "email": "abacaba1@gmail.com",
        "daysOverDefaultPeriodOfCountersSending": 5
    },
    {
        "name": "abacaba",
        "flatNumber": 13,
        "phone": "+79005553535",
        "login": "abacaba",
        "email": "abacaba@gmail.com",
        "daysOverDefaultPeriodOfCountersSending": 5
    },
    {
        "name": "abacaba",
        "flatNumber": 19,
        "phone": "+79005553537",
        "login": "abacaba7",
        "email": "abacaba7@gmail.com",
        "daysOverDefaultPeriodOfCountersSending": 69
    }
]
```

/admin/getNewcomers - гет без параметров

Response:

```
[
    [
        {
            "name": "abacaba",
            "flatNumber": 19,
            "phone": "+79005553534",
            "login": "abacaba6",
            "email": "abacaba6@gmail.com",
            "status": "UNVERIFIED"
        },
        {
            "name": "abacaba",
            "flatNumber": 19,
            "phone": "+79005553537",
            "login": "abacaba7",
            "email": "abacaba7@gmail.com",
            "status": "ACTIVE"
        }
    ],
    [
        {
            "name": "Olga",
            "flatNumber": 23,
            "phone": "+79871677890",
            "login": "qwerty11",
            "email": "kozhi.1197@mail.ru",
            "status": "ACTIVE"
        },
        {
            "name": "Olga",
            "flatNumber": 23,
            "phone": "+79875677890",
            "login": "qwerty",
            "email": "kozhi.197@mail.ru",
            "status": "UNVERIFIED"
        }
    ],
    [
        {
            "name": "Olga",
            "flatNumber": 11,
            "phone": "+79875677891",
            "login": "qwerty1",
            "email": "kozhi.1917@mail.ru",
            "status": "UNVERIFIED"
        }
    ],
    [
        {
            "name": "Olga",
            "flatNumber": 24,
            "phone": "+79875677894",
            "login": "qwerty4",
            "email": "kozhi.194@mail.ru",
            "status": "UNVERIFIED"
        }
    ],
    [
        {
            "name": "Mikhail Levshunov",
            "flatNumber": 123,
            "phone": "+78275558150",
            "login": "abacaba12",
            "email": "abacaba12@gmail.com",
            "status": "UNVERIFIED"
        },
        {
            "name": "Михаил Александрович Левшунов",
            "flatNumber": 123,
            "phone": "+7527555150",
            "login": "abacaba11",
            "email": "abacaba11@gmail.com",
            "status": "REMOVED"
        },
        {
            "name": "Михаил Александрович Левшунов",
            "flatNumber": 123,
            "phone": "+76271666191",
            "login": "abacaba111",
            "email": "abacaba111@gmail.com",
            "status": "UNVERIFIED"
        },
        {
            "name": "Михаил Александрович Левшунов",
            "flatNumber": 123,
            "phone": "+74271554444",
            "login": "abacaba1111",
            "email": "abacaba1111@gmail.com",
            "status": "UNVERIFIED"
        }
    ]
]

```

/admin/setNewcomers - на вход отправляется полученный с гета респонс с заменой статусов
 
Response: 200 или 409 и 

```
{
    "isOk": false,
    "messages": [
        "Блок статусов некорректен в квартире 11",
        "Блок статусов некорректен в квартире 24"
    ]
}
```

/admin/getFlatInhabitants - параметры передаются в url:
                   
```
/admin/getFlatInhabitants?flatNumber=13
```

Response: 200 и

```
[
    {
        "name": "abacaba",
        "flatNumber": 19,
        "phone": "+79005553537",
        "login": "abacaba7",
        "email": "abacaba7@gmail.com",
        "status": "ACTIVE"
    },
    {
        "name": "abacaba",
        "flatNumber": 19,
        "phone": "+79005553534",
        "login": "abacaba6",
        "email": "abacaba6@gmail.com",
        "status": "UNVERIFIED"
    }
]
```

или 409 и 

```
{
    "message": "Квартира не найдена"
}
```

/admin/setFlatInhabitants - на вход отправляется полученный с гета респонс с заменой статусов

Response: 200 или 409 и

```
{
    "isOk": false,
    "messages": [
        "Блок статусов некорректен в квартире 123"
    ]
}
```

Для создание БД в MySQL запустить скрипт со следующим кодом:

```
CREATE DATABASE IF NOT EXISTS jkh;
DROP USER IF EXISTS 'jkh_admin'@'%';

CREATE USER 'jkh_admin'@'%' IDENTIFIED BY 'jkh_admin';
GRANT ALL PRIVILEGES ON jkh.* TO 'jkh_admin'@'%';
FLUSH PRIVILEGES;

SET GLOBAL sql_mode = '';
```