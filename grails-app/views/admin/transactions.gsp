<%@ page import="com.nemnesic.Account" %>
<html>
<head>
    <title>See transactions</title>
    <meta name="layout" content="main"/>
</head>

<body>
<div>
    <h2>Transactions</h2>
    <br/>

    <g:form>
        <b>Person:</b> <g:select name="id" from="${Account.list()}" optionValue="name" optionKey="id"/>
        <br/>
        <b><g:submitToRemote url="[action: 'transactions']" update="transaction-table-wrapper" value="Show transactions!" /></b>
    </g:form>
    ------------------------------------------------------
    <div id="transaction-table-wrapper"></div>
</div>
</body>
</html>
 