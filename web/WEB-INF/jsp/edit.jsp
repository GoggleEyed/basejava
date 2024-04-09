<%@ page import="com.goggleeyed.webapp.model.ContactType" %>
<%@ page import="com.goggleeyed.webapp.model.SectionType" %>
<%@ page import="com.goggleeyed.webapp.model.ListSection" %>
<%@ page import="com.goggleeyed.webapp.model.OrganizationSection" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <jsp:useBean id="resume" type="com.goggleeyed.webapp.model.Resume" scope="request"/>
    <title>Резюме ${resume.fullName}</title
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <form method="post" action="resume" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="uuid" value="${resume.uuid}">
        <dl>
            <dt>Имя:</dt>
            <dd><input type="text" name="fullName" size=50 value="${resume.fullName}"></dd>
        </dl>
        <h3>Контакты:</h3>
        <c:forEach var="type" items="<%=ContactType.values()%>">
            <dl>
                <dt>${type.title}</dt>
                <dd><input type="text" name="${type.name()}" size=30 value="${resume.getContact(type)}"></dd>
            </dl>
        </c:forEach>
        <h3>Секции:</h3>
        <c:forEach var="type" items="<%=SectionType.values()%>">
            <jsp:useBean id="type" type="com.goggleeyed.webapp.model.SectionType"/>
            <c:set var="type_name" value="${type.name()}"/>
            <c:choose>
                <c:when test="${type_name.equals('OBJECTIVE') || type_name.equals('PERSONAL')}">
                    <dl>
                        <dt>${type.title}</dt>
                        <dd><input type="text" name="${type_name}" size=30 value="${resume.getSection(type).content}">
                        </dd>
                    </dl>
                </c:when>
                <c:when test="${type_name.equals('ACHIEVEMENT') || type_name.equals('QUALIFICATIONS')}">
                    <dl>
                        <dt>${type.title}</dt>
                        <dd>
                            <textarea name="${type_name}" rows="5" cols="30"
                            ><%=String.join("\n", ((ListSection) resume.getSection(type)).getItems())%></textarea>
                        </dd>
                    </dl>
                </c:when>
                <c:when test="${type_name.equals('EXPERIENCE') || type_name.equals('EDUCATION')}">
                    <h4>${type.title}</h4>
                    <c:forEach var="organization" varStatus="counter"
                               items="<%=((OrganizationSection) resume.getSection(type)).getOrganizations()%>">
                        <jsp:useBean id="organization" type="com.goggleeyed.webapp.model.Organization"/>
                        <dl>
                            <dt>Название</dt>
                            <dd>
                                <input type="text" name="${type_name}" size=30
                                       value="${organization.homePage.name}">
                            </dd>
                        </dl>
                        <dl>
                            <dt>Ссылка</dt>
                            <dd>
                                <input type="text" name="${type_name}url" size=30
                                       value="${organization.homePage.url}">
                            </dd>
                        </dl>
                        <button>Удалить</button>
                        <hr>
                        <section style="margin-left: 32px">
                            <c:set var="organization_name" value="<%=type.name() + organization.getHomePage().getName()%>"/>
                            <c:forEach var="position" items="${organization.positions}">
                                <dl>
                                    <dt>Должность</dt>
                                    <dd><input type="text" name="${type_name}${counter.index}title" size=26 value="${position.title}">
                                    </dd>
                                </dl>
                                <dl>
                                    <dt>Начальная дата</dt>
                                    <dd><input type="month" name="${type_name}${counter.index}startDate" size=26
                                               value="${position.startDate.format(DateTimeFormatter.ofPattern("yyyy-MM"))}">
                                    </dd>
                                </dl>
                                <dl>
                                    <dt>Конечная дата</dt>
                                    <dd><input type="month" name="${type_name}${counter.index}endDate" size=26
                                               value="${position.endDate.format(DateTimeFormatter.ofPattern("yyyy-MM"))}">
                                    </dd>
                                </dl>
                                <dl>
                                    <dt>Описание</dt>
                                    <dd><input type="text" name="${type_name}${counter.index}description" size=26
                                               value="${position.description}">
                                    </dd>
                                </dl>
                                <button>Удалить</button>
                                <hr>
                            </c:forEach>
                            <button>Добавить</button>
                        </section>
                        <hr>
                    </c:forEach>
                    <button>Добавить</button>
                </c:when>
            </c:choose>
        </c:forEach>
        <hr>
        <button type="submit">Сохранить</button>
        <button type="reset" onclick="window.history.back()">Отменить</button>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>