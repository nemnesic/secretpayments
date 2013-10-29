<%@ page import="com.nemnesic.Account" %>
<html>
<head>
    <title>Pay Some Person</title>
    <meta name="layout" content="main"/>
</head>

<body>
<div id="error-wrapper">

</div>

<div>
    <g:form name="transaction-form" action="payNow">
        <h2>Pay</h2>
        <br/>
        <b>From:</b> <g:select name="transferFrom.id" from="${Account.list()}" optionValue="name" optionKey="id"/>

        <br/>
        <b>To:</b> <g:select name="transferTo.id" from="${Account.list()}" optionValue="name" optionKey="id"/>
        <br/>
        <b>Amount:</b> <g:textField name="amount" value="0"/>
        <br/>
        <b><g:submitToRemote url="[action: 'payNow']" update="error-wrapper" value="Pay Now!" /></b>
    </g:form>
</div>
</body>
</html>