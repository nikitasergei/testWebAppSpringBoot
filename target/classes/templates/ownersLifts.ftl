<#import "parts/pageTemplate.ftl" as pt>
<@pt.page>
<#import "parts/pager.ftl" as p>
<div class="pl-5 pr-5 pt-2">
    <@p.pager url page/>
    <table class="table">
        <thead class="thead-light">
        <tr class="table-active">
            <th scope="col">Id</th>
            <th scope="col">Город</th>
            <th scope="col">Адрес</th>
            <th scope="col">Регистрационный номер</th>
            <th scope="col">Заводской номер</th>
            <th scope="col">Ввод в эксплуатацию</th>
            <th scope="col">Дата ПТО</th>
            <th scope="col">Месяц ТО2</th>
            <th scope="col">Удален</th>
            <th scope="col">Редактировать</th>
            <th scope="col">Удалить</th>
        </tr>
        <#list page.content as lift>
            <tr> ${lift.deleted?then ('<tr class="table-info">','<tr class="table-secondary">')}
                <#if lift.id??>
                    <td align="center">${lift.id}</td></#if>
                <#if lift.city??>
                    <td align="center">${lift.city}</td></#if>
                <#if lift.address??>
                    <td>${lift.address}</td></#if>
                <#if lift.regNum??>
                    <td align="center">${lift.regNum}</td></#if>
                <#if lift.factNum??>
                    <td align="center">${lift.factNum}</td></#if>
                <#if lift.activationDate??>
                    <td align="center">${lift.activationDate}</td></#if>
                <#if lift.ptoDate??>
                    <td align="center">${lift.ptoDate}</td></#if>
                <#if lift.to2Month??>
                    <td align="center">${lift.to2Month}</td></#if>
                <#if lift.deleted??>
                    <td align="center">${lift.deleted?then("true","false")}</td>
                </#if>
                <td><a href="/editLift?editLift=${lift.id}">Изменить</a></td>
                <td>${lift.deleted?then('<a href="lifts?fixLift=${lift.id}">Восстановить</a>',
                    '<a href="lifts?removeLift=${lift.id}">Удалить</a>')}
                </td>
            </tr>
        <#else>
            Lifts list is empty!
        </#list>
    </table>
    <@p.pager url page/>
    </@pt.page>
</div>