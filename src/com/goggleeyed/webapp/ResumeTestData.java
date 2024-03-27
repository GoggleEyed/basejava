package com.goggleeyed.webapp;

import com.goggleeyed.webapp.model.*;

import java.time.LocalDate;
import java.util.Arrays;

public class ResumeTestData {
    public static void main(String[] args) {
        Resume resume = new Resume("Григорий Кислин");

        resume.setContact(ContactType.PHONE, "+7(921) 855-0482");
        resume.setContact(ContactType.SKYPE, "skype:grigory.kislin");
        resume.setContact(ContactType.MAIL, "gkislin@yandex.ru");
        resume.setContact(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        resume.setContact(ContactType.GITHUB, "https://github.com/gkislin");
        resume.setContact(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473");
        resume.setContact(ContactType.HOME_PAGE, "http://gkislin.ru/");

        Section personal = new TextSection("Аналитический склад ума, сильная логика, " +
                "креативность, инициативность. Пурист кода и архитектуры.");
        resume.setSection(SectionType.PERSONAL, personal);

        Section objective = new TextSection("Ведущий стажировок и корпоративного обучения " +
                "по Java Web и Enterprise технологиям");
        resume.setSection(SectionType.OBJECTIVE, objective);

        Section achievement = new ListSection(Arrays.asList(
                "Организация команды и успешная реализация Java проектов для сторонних заказчиков: приложения автопарк " +
                        "на стеке Spring Cloud/микросервисы, система мониторинга показателей спортсменов на Spring Boot, " +
                        "участие в проекте МЭШ на Play-2, многомодульный Spring Boot + Vaadin проект для комплексных DIY смет",
                "С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный " +
                        "maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие " +
                        "(JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 3500 выпускников.",
                "Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с " +
                        "Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.",
                "Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, " +
                        "Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: " +
                        "Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, " +
                        "интеграция CIFS/SMB java сервера."
        ));
        resume.setSection(SectionType.ACHIEVEMENT, achievement);

        Section qualification = new ListSection(Arrays.asList(
                "JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2",
                "Version control: Subversion, Git, Mercury, ClearCase, Perforce",
                "DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle, MySQL, SQLite, MS SQL, HSQLDB",
                "Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy",
                "XML/XSD/XSLT, SQL, C/C++, Unix shell scripts"
        ));
        resume.setSection(SectionType.QUALIFICATIONS, qualification);

        Section experience = new OrganizationSection(Arrays.asList(
                new Organization("Java Online Projects", "http://javaops.ru/",
                        new Organization.Position(LocalDate.of(2013, 10, 1), LocalDate.now(),
                                "Автор проекта.", "Создание, организация и проведение Java онлайн проектов и стажировок.")),
                new Organization("Wrike", "https://www.wrike.com/",
                        new Organization.Position(LocalDate.of(2014, 10, 1), LocalDate.of(2016, 1, 1),
                                "Старший разработчик (backend)", "Проектирование и разработка онлайн платформы " +
                                "управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, " +
                                "Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.")),
                new Organization("RIT Center", "",
                        new Organization.Position(LocalDate.of(2012, 4, 1), LocalDate.of(2014, 10, 1),
                                "Java архитектор", "Организация процесса разработки системы ERP для разных " +
                                "окружений: релизная политика, версионирование, ведение CI (Jenkins), миграция базы (кастомизация " +
                                "Flyway), конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной " +
                                "части системы. Разработка интергационных сервисов: CMIS, BPMN2, 1C (WebServices), сервисов " +
                                "общего назначения (почта, экспорт в pdf, doc, html). Интеграция Alfresco JLAN для online " +
                                "редактирование из браузера документов MS Office. Maven + plugin development, Ant, Apache " +
                                "Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, " +
                                "Unix shell remote scripting via ssh tunnels, PL/Python"))
        ));
        resume.setSection(SectionType.EXPERIENCE, experience);

        Section education = new OrganizationSection(Arrays.asList(
                new Organization("Coursera", "https://www.coursera.org/course/progfun",
                        new Organization.Position(LocalDate.of(2013, 3, 1), LocalDate.of(2013, 5, 1),
                                "", "'Functional Programming Principles in Scala' by Martin Odersky")),
                new Organization("Luxoft", "http://www.luxoft-training.ru/training/catalog/course.html?ID=22366",
                        new Organization.Position(LocalDate.of(2011, 3, 1), LocalDate.of(2011, 4, 1),
                                "", "Курс 'Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.'"))
        ));
        resume.setSection(SectionType.EDUCATION, education);

        for (SectionType section : SectionType.values()) {
            System.out.println(section.getTitle() + ": " + resume.getSection(section));
        }
    }
}
