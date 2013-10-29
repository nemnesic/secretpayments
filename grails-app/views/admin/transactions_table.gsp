<strong><g:message code="${messageSourceKey}"/></strong>
<h1>${account?.name}</h1>
<div>Balance: <g:formatNumber number="${account?.balance}" type="currency" currencyCode="GBP"/></div>

<div>
    <g:if test="${transactions}">
        <h2>Transactions</h2>
        <table>
            <thead>
            <tr>
                <th>Date</th>
                <th>Type</th>
                <th>Amount</th>
            </tr>
            </thead>
            <g:each in="${transactions}" var="transaction">
                <tr>
                    <td>${transaction.dateCreated}</td>
                    <td>${transaction.type}</td>
                    <td><g:formatNumber number="${transaction.amount}" type="currency" currencyCode="GBP"/></td>
                </tr>
            </g:each>
        </table>
    </g:if>
    <g:else>
        <p>There aren't any transactions for this account..</p>
    </g:else>
</div>