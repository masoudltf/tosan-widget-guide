<%--
  User: Mohammad Abbasi
  Date: 2/15/2021
--%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="row">
    <div class="col-12 customer-info">
        <p>
            <spring:message code="customer.name"/>:<strong class="mr-1">${customer.name}</strong>
        </p>
        <p>
            <spring:message code="customer.nationalCode"/>:<strong class="mr-1">${customer.nationalCode}</strong>
        </p>
    </div>
</div>
<div class="row">
    <div class="col-12 mt-2">
        <form action="${pageContext.request.contextPath}/deposit" method="post">
            <input type="hidden" name="bankCode" value="${customer.bankCode}">
            <input type="hidden" name="loginToken" value="${customer.loginToken}">
            <input type="hidden" name="sessionId" value="${customer.sessionId}">
            <button class="btn btn-warning" type="submit"><spring:message code="index.getDeposits.button"/></button>
        </form>
    </div>
    </div>
</div>


