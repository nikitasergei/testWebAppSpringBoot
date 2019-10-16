<#import "parts/pageTemplate.ftl" as pt>

<@pt.page>
    <div class="container mt-5" style="width: 50%">
        <h1>List of users</h1>
        <table class="table">
            <thead class="thead-light">
            <tr class="table-active">
                <th scope="col">Имя</th>
                <th scope="col">Роль</th>
                <th scope="col"> Изменить</th>
            </tr>
            </thead>
            <tbody>
            <#list users as user>
                <tr>
                    <td>${user.username}</td>
                    <td><#list user.roles as role>${role}<#sep>, </#list></td>
                    <td><a href="/user/${user.id}">edit</a></td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
</@pt.page>