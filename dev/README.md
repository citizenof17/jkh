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


Для создание БД в MySQL запустить скрипт со следующим кодом:

```
create database jkh;
create user 'jkh_admin'@'localhost' identified by 'jkh_admin';
grant all on jkh.* to 'jkh_admin'@'localhost';
```