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
  "flat" : { "number" : "13" }
}
```

Для получения отчета о не предоставивших данные:

```
{
  "leftDate" : "2019-01-03",
  "rightDate" : "2019-03-13",
  "type" : "WHO_DID_NOT_SEND"
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