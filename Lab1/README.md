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

Класс DanceStudio содержит поля для хранения информации о студии; установлена связь "один ко многим" с классом DanceClass с использованием аннотации `@OneToMany`. Класс DanceClass содержит поля для хранения данных о занятии; установлена связь "многие к одному" с классом DanceStudio через аннотацию `@ManyToOne` и связь с внешним ключом `studio_id`.  

Используются аннотации `@Entity`, `@Table`, `@Id`, `@GeneratedValue` для настройки сущностей и их соответствия таблицам в базе данных. Аннотации `@Column` применены для задания ограничений на поля, таких как длина и обязательность.

В классе Main создан пример работы с JPA для сохранения данных в базу:
![image](https://github.com/user-attachments/assets/947e831b-351a-4a0a-a784-8f4b57001a19)
![image](https://github.com/user-attachments/assets/a88ef03a-7315-4e3d-9d28-be2f50f38253)
![image](https://github.com/user-attachments/assets/ccce91fb-f18f-49a5-ae22-551ee99e5d68)

#### Задание 5
В данном пункте реализована бизнес-логика для управления студиями танцев и занятиями с использованием сессионных бинов (EJB).  

**DanceStudioService** позволяет создавать, читать, обновлять и удалять студии танцев, реализует метод для добавления занятий к студии.  
**DanceClassService** обрабатывает операции с занятиями, такие как создание, поиск, обновление и удаление.  
Для тестирования сессионных бинов был создан класс **MainServiceTest**.  

Таким образом, бизнес-логика охватывает управление студиями танцев, управление занятиями в этих студиях, логику добавления занятий к студиям.  

Также war-пакет был добавлен на сервер приложения:
![image](https://github.com/user-attachments/assets/cc366fad-8aaf-46c1-abff-a9422b0c8876)
Также был создан простой servlet, который выводит приветственное сообщение при обращении к URL /example:
![image](https://github.com/user-attachments/assets/8b491f6e-998f-49b5-a553-4b950f6c66ef)

#### Задание 6

Welcome-page
![image](https://github.com/user-attachments/assets/dd9dc583-d792-4f60-9efe-e0e11bd8cefb)


