# Practical Work #2
*Выполнили студенты группы 6132-010402D Макарова Мария и Таджитдинов Камиль* 
## Application using Spring Framework
### Общая задача
Нужно разработать приложение, используя общую архитектуру и технологии Spring. Оно должно содержать три уровня (данные, логика, представление) и обеспечивать работу с базой данных.

### Задание 1
Была выбрана также область танцевальных студий. Использовался тот же скрипт для PostgreSQL:
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
### Задание 2 
В файле application.properties прописаны параметры конфигурации для подключения к базе данных PostgreSQL. Реализованы две (модели данных) сущности: **DanceClass** и **DanceStudio**.  

Для каждой из сущностей были созданы с аннотациями для Hibernate:
* Аннотации `@Entity`, `@Table`, `@Id`, `@GeneratedValue`, `@ManyToOne`, `@OneToMany`, и `@JoinColumn` были использованы для работы с Hibernate.
* Для **DanceStudio** и **DanceClass** указаны связи между сущностями: в DanceClass — связь `@ManyToOne` с DanceStudio, в DanceStudio — связь `@OneToMany` с DanceClass.

### Задание 3
Сервисный слой был реализован с использованием `@Service` аннотации для обеспечения бизнес-логики. Также созданы сервисы **DanceStudioService** и **DanceClassService**, которые используют репозитории для работы с базой данных.  

### Задание 4
Web-слой был реализован с использованием Spring MVC. Были созданы контроллеры для обработки запросов и отображения данных в представлениях. Контроллеры используют аннотацию `@Controller` и методы с аннотациями `@GetMapping` и `@PostMapping`.  

Также использовался Thymeleaf в качестве шаблонизатора для генерации HTML-страниц.
### Задание 5
Все части приложения были объединены:
* Data Layer: сущности и репозитории были настроены для работы с Hibernate и базой данных.
* Business Layer: сервисы реализуют бизнес-логику.
* View Layer: контроллеры обеспечивают взаимодействие с пользователем, а представления отображают данные.

Чтобы запустить приложение нужно использовать `mvn clean install` и `java -jar target/Lab2-1.0-SNAPSHOT.jar`, после чего в браузере открыть http://localhost:8082/.  
На данном этапе можно увидеть следующее:  
![image](https://github.com/user-attachments/assets/d99adc16-8f44-4735-b4ed-49991b76a051)
Соответсвенно, можно перейти по ссылкам, где отображаются данные по студиям или занятиям.  
![image](https://github.com/user-attachments/assets/8170667b-23e1-41e7-a12c-c9a4ba8bd9f4)
![image](https://github.com/user-attachments/assets/7107326f-0df9-4129-a78d-9fe76a50bd99)






