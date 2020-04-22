<#import "parts/common.ftl" as c>
<@c.page>
List of users

<table>
    <thead>
    <tr>
        <th>Name</th>
        <th>Role</th>
        <th>Command</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <#list users as user>
        <td>${user.username}</td>
        <td><#list user.roles as role>${role}<#sep>, </#list></td>
        <td><a href="/user/${user.id}">edit</a></td>
    </tr>
    </tbody>
</#list>
</table>

</@c.page>