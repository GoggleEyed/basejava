package com.goggleeyed.webapp;

import com.goggleeyed.webapp.model.*;

import java.time.Month;
import java.util.Arrays;
import java.util.UUID;

public class ResumeTestData {
    public static final String UUID_1 = UUID.randomUUID().toString();
    public static final Resume RESUME_1 = of(UUID_1, "name1");
    public static final String UUID_2 = UUID.randomUUID().toString();
    public static final Resume RESUME_2 = of(UUID_2, "name2");
    public static final String UUID_3 = UUID.randomUUID().toString();
    public static final Resume RESUME_3 = of(UUID_3, "name3");
    public static final String UUID_4 = UUID.randomUUID().toString();
    public static final Resume RESUME_4 = of(UUID_4, "name3");


    public static Resume of(String uuid, String fullName) {
        Resume resume = new Resume(uuid, fullName);

        resume.addContact(ContactType.PHONE, fullName + " phone number");
        resume.addContact(ContactType.SKYPE, fullName + " skype");
        resume.addContact(ContactType.MAIL, fullName + " mail");
        resume.addContact(ContactType.LINKEDIN, fullName + " linkedin");
        resume.addContact(ContactType.GITHUB, fullName + " github");
        resume.addContact(ContactType.STACKOVERFLOW, fullName + " stackoverflow");
        resume.addContact(ContactType.HOME_PAGE, fullName + " home_page");

        resume.addSection(SectionType.PERSONAL, new TextSection("personal"));
        resume.addSection(SectionType.OBJECTIVE, new TextSection("objective"));
        resume.addSection(SectionType.ACHIEVEMENT, new ListSection("achievement1", "achievement2", "achievement3"));
        resume.addSection(SectionType.QUALIFICATIONS, new ListSection("qualification1", "qualification2", "qualification3"));

        Section experience = new OrganizationSection(
                new Organization("name1", "url1",
                        new Organization.Position(2000, Month.of(1), 2001, Month.of(2),
                                "title1", "desc1"),
                        new Organization.Position(2001, Month.of(2), 2002, Month.of(3),
                                "title2", "desc2"),
                        new Organization.Position(2002, Month.of(3), 2003, Month.of(4),
                                "title3", "desc3")
                ),
                new Organization("name2", "url2",
                        new Organization.Position(2000, Month.of(1), 2001, Month.of(2),
                                "title1", "desc1"),
                        new Organization.Position(2001, Month.of(2), 2002, Month.of(3),
                                "title2", "desc2"),
                        new Organization.Position(2002, Month.of(3), 2003, Month.of(4),
                                "title3", "desc3")
                ),
                new Organization("name3", "url3",
                        new Organization.Position(2000, Month.of(1), 2001, Month.of(2),
                                "title1", "desc1"),
                        new Organization.Position(2001, Month.of(2), 2002, Month.of(3),
                                "title2", "desc2"),
                        new Organization.Position(2002, Month.of(3), 2003, Month.of(4),
                                "title3", "desc3")
                )
        );
        resume.addSection(SectionType.EXPERIENCE, experience);

        Section education = new OrganizationSection(
                Arrays.asList(
                        new Organization("name1", "url1",
                                new Organization.Position(2000, Month.of(1), 2001, Month.of(2),
                                        "title1", "desc1"),
                                new Organization.Position(2001, Month.of(2), 2002, Month.of(3),
                                        "title2", "desc2"),
                                new Organization.Position(2002, Month.of(3), 2003, Month.of(4),
                                        "title3", "desc3")
                        ),
                        new Organization("name2", "url2",
                                new Organization.Position(2000, Month.of(1), 2001, Month.of(2),
                                        "title1", "desc1"),
                                new Organization.Position(2001, Month.of(2), 2002, Month.of(3),
                                        "title2", "desc2"),
                                new Organization.Position(2002, Month.of(3), 2003, Month.of(4),
                                        "title3", "desc3")
                        ),
                        new Organization("name3", "url3",
                                new Organization.Position(2000, Month.of(1), 2001, Month.of(2),
                                        "title1", "desc1"),
                                new Organization.Position(2001, Month.of(2), 2002, Month.of(3),
                                        "title2", "desc2"),
                                new Organization.Position(2002, Month.of(3), 2003, Month.of(4),
                                        "title3", "desc3")
                        )
                )
        );
        resume.addSection(SectionType.EDUCATION, education);

        return resume;
    }

//    public static void main(String[] args) {
//        Resume resume = new Resume("Григорий Кислин");
//
//        resume.addContact(ContactType.PHONE, "+7(921) 855-0482");
//        resume.addContact(ContactType.SKYPE, "skype:grigory.kislin");
//        resume.addContact(ContactType.MAIL, "gkislin@yandex.ru");
//        resume.addContact(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
//        resume.addContact(ContactType.GITHUB, "https://github.com/gkislin");
//        resume.addContact(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473");
//        resume.addContact(ContactType.HOME_PAGE, "http://gkislin.ru/");
//
//        Section personal = new TextSection("Аналитический склад ума, сильная логика, " +
//                "креативность, инициативность. Пурист кода и архитектуры.");
//        resume.addSection(SectionType.PERSONAL, personal);
//
//        Section objective = new TextSection("Ведущий стажировок и корпоративного обучения " +
//                "по Java Web и Enterprise технологиям");
//        resume.addSection(SectionType.OBJECTIVE, objective);
//
//        Section achievement = new ListSection(Arrays.asList(
//                "Организация команды и успешная реализация Java проектов для сторонних заказчиков: приложения автопарк " +
//                        "на стеке Spring Cloud/микросервисы, система мониторинга показателей спортсменов на Spring Boot, " +
//                        "участие в проекте МЭШ на Play-2, многомодульный Spring Boot + Vaadin проект для комплексных DIY смет",
//                "С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный " +
//                        "maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие " +
//                        "(JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 3500 выпускников.",
//                "Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с " +
//                        "Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.",
//                "Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, " +
//                        "Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: " +
//                        "Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, " +
//                        "интеграция CIFS/SMB java сервера."
//        ));
//        resume.addSection(SectionType.ACHIEVEMENT, achievement);
//
//        Section qualification = new ListSection(Arrays.asList(
//                "JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2",
//                "Version control: Subversion, Git, Mercury, ClearCase, Perforce",
//                "DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle, MySQL, SQLite, MS SQL, HSQLDB",
//                "Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy",
//                "XML/XSD/XSLT, SQL, C/C++, Unix shell scripts"
//        ));
//        resume.addSection(SectionType.QUALIFICATIONS, qualification);
//
//        Section experience = new OrganizationSection(Arrays.asList(
//                new Organization("Java Online Projects", "http://javaops.ru/",
//                        new Organization.Position(DateUtil.of(2013, Month.of(10)), LocalDate.now(),
//                                "Автор проекта.", "Создание, организация и проведение Java онлайн проектов и стажировок.")),
//                new Organization("Wrike", "https://www.wrike.com/",
//                        new Organization.Position(DateUtil.of(2014, Month.of(10)), DateUtil.of(2016, Month.of(1)),
//                                "Старший разработчик (backend)", "Проектирование и разработка онлайн платформы " +
//                                "управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, " +
//                                "Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.")),
//                new Organization("RIT Center", "",
//                        new Organization.Position(DateUtil.of(2012, Month.of(4)), DateUtil.of(2014, Month.of(10)),
//                                "Java архитектор", "Организация процесса разработки системы ERP для разных " +
//                                "окружений: релизная политика, версионирование, ведение CI (Jenkins), миграция базы (кастомизация " +
//                                "Flyway), конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной " +
//                                "части системы. Разработка интергационных сервисов: CMIS, BPMN2, 1C (WebServices), сервисов " +
//                                "общего назначения (почта, экспорт в pdf, doc, html). Интеграция Alfresco JLAN для online " +
//                                "редактирование из браузера документов MS Office. Maven + plugin development, Ant, Apache " +
//                                "Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, " +
//                                "Unix shell remote scripting via ssh tunnels, PL/Python"))
//        ));
//        resume.addSection(SectionType.EXPERIENCE, experience);
//
//        Section education = new OrganizationSection(Arrays.asList(
//                new Organization("Coursera", "https://www.coursera.org/course/progfun",
//                        new Organization.Position(DateUtil.of(2013, Month.of(3)), DateUtil.of(2013, Month.of(5)),
//                                "'Functional Programming Principles in Scala' by Martin Odersky", null)),
//                new Organization("Luxoft", "http://www.luxoft-training.ru/training/catalog/course.html?ID=22366",
//                        new Organization.Position(DateUtil.of(2011, Month.of(3)), DateUtil.of(2011, Month.of(4)),
//                                "Курс 'Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.'", ""))
//        ));
//        resume.addSection(SectionType.EDUCATION, education);
//
//        for (SectionType section : SectionType.values()) {
//            System.out.println(section.getTitle() + ": " + resume.getSection(section));
//        }
//    }
}