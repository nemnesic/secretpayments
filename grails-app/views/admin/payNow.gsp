<div style="border: 1px solid black; width: 100%">
    <strong><g:message code="${messageSourceKey}"/></strong>
    <ul>
        <g:eachError bean="${payNowCommand}">
            <li><g:message error="${it}"/></li>
        </g:eachError>
    </ul>
</div>