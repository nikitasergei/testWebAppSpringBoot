<#import "pager.ftl" as p>
<@p.pager url page/>
<table class="table">
    <thead class="thead-light">
    <tr class="table-active">
        <th scope="col">Id</th>
        <th scope="col">Lift</th>
        <th scope="col">PTO</th>
        <th scope="col">TO-1</th>
        <th scope="col">TO-2</th>
        <th scope="col">Is Done</th>
        <th scope="col">User</th>
    </tr>
    </thead>
    <#list page.content as history>
        <tbody>
        <tr ${history.done?then("class='table-success'","class='alert-danger'")}>
            <#if history.id??>
                <td>${history.id}</td></#if>
            <#if history.lift??>
                <td>${history.lift.regNum}</td></#if>
            <#if history.ptoDate??>
                <td>${history.ptoDate}</td></#if>
            <#if history.to1Date??>
                <td>${history.to1Date}</td></#if>
            <#if history.to2Month??>
                <td>${history.to2Month}</td></#if>
            <#if history.done??>
                <td>${history.done?then( "true"," false")}</td></#if>
            <#if history.user.id??>
                <td>${history.user.username}</td></#if>

        </tr>
        </tbody>
    <#else>
        <tr> History list is empty!</tr>
    </#list>
</table>
<a href="editHistory">
    <button type="button" class="btn btn-primary">Добавить запись</button>
</a>
<@p.pager url page/>