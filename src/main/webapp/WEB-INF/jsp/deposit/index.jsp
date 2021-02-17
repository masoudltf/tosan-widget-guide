<%--
  User: Mohammad Abbasi
  Date: 2/16/2021
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="row">
    <div class="col-12 mt-2">
        <table class="table table-stripped">
            <thead>
            <tr>
                <th><spring:message code="deposit.depositNumber"/></th>
                <th><spring:message code="deposit.balance"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${deposits}" var="deposit">
                <tr>
                    <td>${deposit.depositNumber}</td>
                    <td>${deposit.balance}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>