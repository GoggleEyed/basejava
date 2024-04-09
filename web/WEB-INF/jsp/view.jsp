<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <jsp:useBean id="resume" type="com.goggleeyed.webapp.model.Resume" scope="request"/>
    <title>Резюме ${resume.fullName}</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <h2>${resume.fullName}&nbsp;<a href="resume?uuid=${resume.uuid}&action=edit"><img src="img/pencil.png"></a></h2>
    <p>
        <c:forEach var="contactEntry" items="${resume.contacts}">
            <jsp:useBean id="contactEntry"
                         type="java.util.Map.Entry<com.goggleeyed.webapp.model.ContactType, java.lang.String>"/>
                <%=contactEntry.getKey().toHtml(contactEntry.getValue())%><br/>
        </c:forEach>
        <c:forEach var="sectionEntry" items="${resume.sections}">
            <jsp:useBean id="sectionEntry"
                         type="java.util.Map.Entry<com.goggleeyed.webapp.model.SectionType, com.goggleeyed.webapp.model.Section>"/>
            <c:set var="type" value="${sectionEntry.key.name()}"/>
            <c:set var="section" value="${sectionEntry.value}"/>
            <h3>${sectionEntry.key.title}</h3>
            <c:choose>
                <c:when test="${type.equals('OBJECTIVE') || type.equals('PERSONAL')}">
                    ${section.content}
                </c:when>
                <c:when test="${type.equals('ACHIEVEMENT') || type.equals('QUALIFICATIONS')}">
                    <ul>
                        <c:forEach var="item" items="${section.items}">
                            <li>${item}</li>
                        </c:forEach>
                    </ul>
                </c:when>
                <c:when test="${type.equals('EXPERIENCE') || type.equals('EDUCATION')}">
                    <table>
                        <c:forEach var="organization" items="${section.organizations}">
                            <th></th>
                            <th>
                                <h3><a href="${organization.homePage.url}"> ${organization.homePage.name}</a></h3>
                            </th>
                            <c:forEach var="position" items="${organization.positions}">
                                <tr>
                                    <td>${position.getPeriod()}</td>
                                    <td>${position.title}</td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td>${position.description}</td>
                                </tr>
                                <tr><td></td></tr>
                            </c:forEach>
                        </c:forEach>
                    </table>
                </c:when>
            </c:choose>
        </c:forEach>
    <p>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
