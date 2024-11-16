# Practical Work #1
## Application with common JavaEE architecture
### Общая задача
Нужно разработать приложение, используя стандартную архитектуру и технологии JavaEE. Оно должно состоять из трёх слоёв (данные, логика, представление) и обеспечивать работу с базой данных.

#### Задание 1
1. Скачайте сервер приложений GlassFish.
2. Установите его, распаковав архив.
3. Запустите сервер приложений.
4. Откройте административную консоль и изучите её возможности.

Запуск сервера приложений GlassFish реализовывался с помощью следующей команды:
```
C:\путь\к\glassfish\bin\asadmin start-domain
```
На скриншоте представлен пример использования команды и успешный запуск:
![image](https://github.com/user-attachments/assets/fbacb6c9-6f3c-415b-9113-759540ec76d2)

Проверить, что сервер работает корректно, можно с помощью команды `asadmin list-domains`, остановить работу сервера можно с помощью команды `asadmin stop-domain`, пример использования которых представлен на скриншоте выше.

С помощью `http://localhost:4848` можно открыть административную консоль сервера приложений GlassFish, где есть возможности настраивать соединения с базой данных, управлять конфигурацией сервера и др.
![image](https://github.com/user-attachments/assets/58eaee73-c62e-447b-b215-66091c6ac76d)

#### Задание 2
В качестве SQL СУБД была выбрана программа **PostgreSQL** версии 17.1

#### Задание 3
В качестве предметной области было выбрано **образование в сфере танцев**.  
Сущности (отношение один ко многим):
1. **Танцевальная студия** хранит информацию о студиях (например, название и адрес).
2. **Занятие** связано с танцевальной студией и хранит информацию о классах (например, стиль танца, расписание и уровень).

Ниже представлен скрипт для создания таблиц DanceStudio и DanceClass:
```
CREATE TABLE DanceStudio (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,  
    address VARCHAR(255),   
    phone VARCHAR(20)  
);

CREATE TABLE DanceClass (
    id SERIAL PRIMARY KEY, 
    style VARCHAR(50) NOT NULL,
    level VARCHAR(50) NOT NULL,
    schedule VARCHAR(100),
    studio_id INT NOT NULL,
    CONSTRAINT fk_studio
        FOREIGN KEY (studio_id)
        REFERENCES DanceStudio (id)
        ON DELETE CASCADE
);
```
Примеры того, как выглядят таблицы, представлены на скриншотах ниже:
![image](https://github.com/user-attachments/assets/be4a2843-8b7b-40d8-b1b1-5d96b662fdae)
![image](https://github.com/user-attachments/assets/1dd89f1d-d64a-404e-8d97-c069351cb258)

#### Задание 4


