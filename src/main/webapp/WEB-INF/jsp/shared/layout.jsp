<%--
  User: Mohammad Abbasi
  Date: 2/16/2021
--%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <%@ page isELIgnored="false" %>
    <meta charset="UTF-8">
    <title><spring:message code="widget.header.title"/></title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
</head>
<body dir="rtl">
<div class="container">
    <div class="row">
        <div class="col-12 mt-2">
            <div class="alert alert-warning text-center">
                <h2 class="pb-2"><spring:message code="widget.title"/></h2>
                <h6><spring:message code="widget.title.description"/></h6>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-12 mt-2">
            <jsp:include page="../${layout.pageName}"/>
        </div>
    </div>
</div>
</body>
</html>

