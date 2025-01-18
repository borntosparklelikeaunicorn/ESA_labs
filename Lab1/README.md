# Practical Work #1
*Выполнили студенты группы 6132-010402D Макарова Мария и Таджитдинов Камиль* 
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
Сущности:
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
Был реализован слой данных с использованием Java Beans.  

Класс **Studio** содержит поля для хранения информации о студии. Класс **DanceClass** содержит поля для хранения данных о занятии.  

Каждая сущность была аннотирована JPA-аннотациями, такими как `@Entity`, `@Table`, `@Id` и др., для корректной работы с таблицами базы данных.
Настроен persistence.xml для подключения к базе данных PostgreSQL.

Во временном классе Main создан пример работы с JPA для сохранения данных в базу:
![image](https://github.com/user-attachments/assets/947e831b-351a-4a0a-a784-8f4b57001a19)
![image](https://github.com/user-attachments/assets/a88ef03a-7315-4e3d-9d28-be2f50f38253)
![image](https://github.com/user-attachments/assets/ccce91fb-f18f-49a5-ae22-551ee99e5d68)

#### Задание 5
В данном пункте реализована бизнес-логика для управления танцевыми студиями и занятиями с использованием сервисных классов.   

**DanceStudioService** позволяет добавлять новые студии с помощью метода addStudio() и получать список всех студий через метод getAll().  
**DanceClassService** обрабатывает создание новых занятий через метод addDanceClass() и получение списка всех занятий через метод getAll().  

Оба сервиса взаимодействуют с базой данных через методы, определенные в интерфейсах DAO (StudioDAO и DanceClassDAO). Важным элементом является метод getAll(), который выполняет SQL-запросы для получения всех записей из соответствующих таблиц, преобразует их в объекты Java и возвращает список.  

#### Задание 6
Для реализации пользовательского интерфейса использованы Java Servlets, которые обеспечивают обработку HTTP-запросов и передачу данных от клиента к серверу и обратно.  

**DanceClassServlet** и **DanceStudioServlet** отвечают за обработку запросов на страницы, связанные с занятиями и студиями соответственно. **HelloServlet** перенаправляет пользователя на главную страницу.  

Для отображения данных о студиях и занятиях созданы JSP-страницы.  
**index.jsp** - страница с приветствием и ссылками на страницы студий и занятий;
**danceClassList.jsp** - страница отображает список всех занятий, здесь выводятся такие данные, как ID, стиль, уровень, расписание и ID студии для каждого занятия;
**studioList.jsp** - страница отображает список всех студий танцев, тут выводятся данные о студиях: ID, название, адрес и телефон.

#### Задание 7
В рамках этой задачи были интегрированы все ранее реализованные компоненты.  

С помощью `mvn clean install` запускается приложение и создается war-файл, который загружается на сервер GlassFish в раздел Applications. Также ранее были настроены JDBC Resources и Connection Pools.
![image](https://github.com/user-attachments/assets/48634c7e-1fbe-4bcf-91b9-09b2cc80d015)

Далее с помощью `http://localhost:8080/` можно увидеть в действии данное приложение.
![image](https://github.com/user-attachments/assets/e70d3a59-2dd4-4ea2-9435-04db19c9aa9a)
![image](https://github.com/user-attachments/assets/1595b2c8-ed0c-45e3-9b91-79f6b147fb9f)
![image](https://github.com/user-attachments/assets/9463ed0d-8848-4991-a1d4-aab405bcc0ae)

Пример работы функции добавления:
![image](https://github.com/user-attachments/assets/2b7e3c98-eec1-41ff-88f2-94765bee1769)
![image](https://github.com/user-attachments/assets/b992bae9-6cb2-4d9d-a7b1-c583b31bf475)
![image](https://github.com/user-attachments/assets/fadbc9d7-3681-4ebf-8d4f-6b9b243dc48e)


