package com.urise.webapp;

import com.urise.webapp.model.resume.*;

import java.time.LocalDate;

public class ResumeTestData {
    public static void main(String[] args) {
        Resume r1 = new Resume("Григорий Кислин");

        // CONTACTS
        r1.setContact(new Contact(ContactType.MOBILEPHONE, "+7(921) 855-0482"));
        r1.setContact(new Contact(ContactType.SKYPE, "skype:grigory.kislin"));
        r1.setContact(new Contact(ContactType.EMAIL, "gkislin@yandex.ru"));
        r1.setContact(new Contact(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin"));
        r1.setContact(new Contact(ContactType.GITHUB, "https://github.com/gkislin"));
        r1.setContact(new Contact(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473"));
        r1.setContact(new Contact(ContactType.HOMEPAGE, "https://stackoverflow.com/users/548473"));

        // PERSONAL
        TextSection personalSection = new TextSection(SectionType.PERSONAL, "Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры.");
        r1.setSection(personalSection);

        // OBJECTIVE
        TextSection objectiveSection = new TextSection(SectionType.OBJECTIVE, "Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям");
        r1.setSection(objectiveSection);

        // ACHIEVEMENT
        ListSection achievementSection = new ListSection(SectionType.ACHIEVEMENT);
        achievementSection.addElement("Организация команды и успешная реализация Java проектов для сторонних заказчиков: приложения автопарк на стеке Spring Cloud/микросервисы, система мониторинга показателей спортсменов на Spring Boot, участие в проекте МЭШ на Play-2, многомодульный Spring Boot + Vaadin проект для комплексных DIY смет");
        achievementSection.addElement("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 3500 выпускников.");
        achievementSection.addElement("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.");
        achievementSection.addElement("Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.");
        achievementSection.addElement("Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.");
        achievementSection.addElement("Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios. Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django).");
        achievementSection.addElement("Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.");
        r1.setSection(achievementSection);

        // QUALIFICATIONS
        ListSection qualificationSection = new ListSection(SectionType.QUALIFICATIONS);
        qualificationSection.addElement("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        qualificationSection.addElement("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        qualificationSection.addElement("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle, MySQL, SQLite, MS SQL, HSQLDB");
        qualificationSection.addElement("Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy");
        qualificationSection.addElement("XML/XSD/XSLT, SQL, C/C++, Unix shell scripts");
        qualificationSection.addElement("Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring (MVC, Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements).");
        qualificationSection.addElement("Python: Django.");
        qualificationSection.addElement("JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js");
        qualificationSection.addElement("Scala: SBT, Play2, Specs2, Anorm, Spray, Akka");
        qualificationSection.addElement("Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT.");
        qualificationSection.addElement("Инструменты: Maven + plugin development, Gradle, настройка Ngnix");
        qualificationSection.addElement("Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, архитектурных шаблонов, UML, функционального программирования");
        qualificationSection.addElement("Родной русский, английский \"upper intermediate\"");
        r1.setSection(qualificationSection);

        // EXPERIENCE
        OrganizationSection experienceSection = new OrganizationSection(SectionType.EXPERIENCE);
        Organization organization = new Organization("Alcatel");
        organization.setWebsite("https://www.siemens.com/ru/ru/home.html");
        Period period = new Period("Разработчик ПО",
                LocalDate.of(1997, 9, 1),
                LocalDate.of(2005, 1, 1));
        period.setDescription("Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM).");
        organization.addPeriod(period);
        experienceSection.addOrganization(organization);

        organization = new Organization("Siemens AG");
        organization.setWebsite("http://www.alcatel.ru/");
        period = new Period("Разработчик ПО",
                LocalDate.of(2005, 1, 1),
                LocalDate.of(2007, 2, 1));
        period.setDescription("Разработка информационной модели, проектирование интерфейсов, реализация и отладка ПО на мобильной IN платформе Siemens @vantage (Java, Unix).");
        organization.addPeriod(period);
        experienceSection.addOrganization(organization);

        organization = new Organization("Enkata");
        organization.setWebsite("http://enkata.com/");
        period = new Period("Разработчик ПО",
                LocalDate.of(2007, 3, 1),
                LocalDate.of(2008, 6, 1));
        period.setDescription("Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, JMS) частей кластерного J2EE приложения (OLAP, Data mining).");
        organization.addPeriod(period);
        experienceSection.addOrganization(organization);

        organization = new Organization("Yota");
        organization.setWebsite("https://www.yota.ru/");
        period = new Period("Ведущий специалист",
                LocalDate.of(2008, 6, 1),
                LocalDate.of(2010, 12, 1));
        period.setDescription("Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\" (GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2). Реализация администрирования, статистики и мониторинга фреймворка. Разработка online JMX клиента (Python/ Jython, Django, ExtJS)");
        organization.addPeriod(period);
        experienceSection.addOrganization(organization);

        organization = new Organization("Luxoft (Deutsche Bank)");
        organization.setWebsite("http://www.luxoft.ru/");
        period = new Period("Ведущий программист",
                LocalDate.of(2010, 12, 1),
                LocalDate.of(2012, 4, 1));
        period.setDescription("Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC, SmartGWT, GWT, Jasper, Oracle). Реализация клиентской и серверной части CRM. Реализация RIA-приложения для администрирования, мониторинга и анализа результатов в области алгоритмического трейдинга. JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Highstock, Commet, HTML5.");
        organization.addPeriod(period);
        experienceSection.addOrganization(organization);

        organization = new Organization("RIT Center");
        period = new Period("Java архитектор",
                LocalDate.of(2012, 4, 1),
                LocalDate.of(2014, 10, 1));
        period.setDescription("Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы. Разработка интергационных сервисов: CMIS, BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html). Интеграция Alfresco JLAN для online редактирование из браузера документов MS Office. Maven + plugin development, Ant, Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python");
        organization.addPeriod(period);
        experienceSection.addOrganization(organization);

        organization = new Organization("Wrike");
        organization.setWebsite("https://www.wrike.com/");
        period = new Period("Старший разработчик (backend)",
                LocalDate.of(2014, 10, 1),
                LocalDate.of(2016, 1, 1));
        period.setDescription("Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.");
        organization.addPeriod(period);
        experienceSection.addOrganization(organization);

        organization = new Organization("Java Online Projects");
        organization.setWebsite("http://javaops.ru/");
        period = new Period("Автор проекта.",
                LocalDate.of(2013, 10, 1));
        period.setDescription("Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.");
        organization.addPeriod(period);
        experienceSection.addOrganization(organization);

        r1.setSection(experienceSection);

        // EDUCATION
        OrganizationSection educationSection = new OrganizationSection(SectionType.EDUCATION);

        organization = new Organization("Заочная физико-техническая школа при МФТИ");
        organization.setWebsite("https://mipt.ru/");
        period = new Period("Закончил с отличием",
                LocalDate.of(1984, 9, 1),
                LocalDate.of(1987, 6, 1));
        organization.addPeriod(period);
        educationSection.addOrganization(organization);

        organization = new Organization("Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики");
        organization.setWebsite("http://www.ifmo.ru/");
        period = new Period("Инженер (программист Fortran, C)",
                LocalDate.of(1987, 9, 1),
                LocalDate.of(1993, 7, 1));
        period.setDescription("Аспирантура (программист С, С++)");
        organization.addPeriod(period);
        educationSection.addOrganization(organization);

        organization = new Organization("Alcatel");
        organization.setWebsite("http://www.alcatel.ru/");
        period = new Period("6 месяцев обучения цифровым телефонным сетям (Москва)",
                LocalDate.of(1997, 9, 1),
                LocalDate.of(1998, 3, 1));
        organization.addPeriod(period);
        educationSection.addOrganization(organization);

        organization = new Organization("Siemens AG");
        organization.setWebsite("http://www.siemens.ru/");
        period = new Period("3 месяца обучения мобильным IN сетям (Берлин)",
                LocalDate.of(2005, 1, 1),
                LocalDate.of(2005, 4, 1));
        organization.addPeriod(period);
        educationSection.addOrganization(organization);

        organization = new Organization("Luxoft");
        organization.setWebsite("http://www.luxoft-training.ru/training/catalog/course.html?ID=22366");
        period = new Period("Курс 'Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.'",
                LocalDate.of(2011, 3, 1),
                LocalDate.of(2011, 4, 1));
        organization.addPeriod(period);
        educationSection.addOrganization(organization);

        organization = new Organization("Coursera");
        organization.setWebsite("https://www.coursera.org/course/progfun");
        period = new Period("'Functional Programming Principles in Scala' by Martin Odersky",
                LocalDate.of(2013, 3, 1),
                LocalDate.of(2013, 5, 1));
        organization.addPeriod(period);
        educationSection.addOrganization(organization);

        r1.setSection(educationSection);

        System.out.println(r1);
        System.out.println("Contacts:");
        r1.getAllAddedContactTypes().forEach(e -> System.out.println(r1.getContact(e)));

        r1.getAllAddedSectionsTypes().forEach(e -> System.out.println(r1.getSection(e)));

    }
}
