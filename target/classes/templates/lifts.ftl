<#import "parts/pageTemplate.ftl" as pt>
<@pt.page>
    <#if message??>
        <div class="alert alert-${messageType}" role="alert">
            ${message}
        </div>
    <#else>
        <#import "parts/pager.ftl" as p>
        <div class="pl-5 pr-5 pt-2">
            <#include "parts/newNav.ftl">
            <@p.pager url page/>
            <table class="table">
                <thead class="thead-light">
                <tr class="table-active" align="center">
                    <th scope="col">Id</th>
                    <th scope="col">Город</th>
                    <th scope="col">Адрес</th>
                    <th scope="col">Регистрационный номер</th>
                    <th scope="col">Заводской номер</th>
                    <th scope="col">Ввод в эксплуатацию</th>
                    <th scope="col">Is deleted</th>
                    <th scope="col">Show Tech History</th>
                    <th scope="col">Edit</th>
                    <th scope="col">Remove</th>
                </tr>
                <#list page.content as lift>
                    <tr> ${lift.deleted?then ('<tr class="table-info">','<tr class="table-secondary">')}
                        <#if lift.id??>
                            <td align="center">${lift.id}</td></#if>
                        <#if lift.city??>
                            <td align="center">${lift.city}</td></#if>
                        <#if lift.address??>
                            <td align="center">${lift.address}</td></#if>
                        <#if lift.regNum??>
                            <td align="center">${lift.regNum}</td></#if>
                        <#if lift.factNum??>
                            <td align="center">${lift.factNum}</td></#if>
                        <#if lift.activationDate??>
                            <td align="center">${lift.activationDate}</td></#if>
                        <#if lift.deleted??>
                            <td align="center">${lift.deleted?then("true","false")}</td>
                        </#if>
                        <td align="center"><a href="techHistory/${lift.id}">Show</a></td>
                        <td align="center"><a href="editLift/${lift.id}">Изменить</a></td>
                        <td align="center">${lift.deleted?then('<a href="lifts?fixLift=${lift.id}">Восстановить</a>',
                            '<a href="lifts?removeLift=${lift.id}">Удалить</a>')}
                        </td>
                    </tr>
                <#else>
                    <p style="color: #c80201">
                        Lifts list is empty!
                    </p>
                </#list>
            </table>
            <@p.pager url page/>
            <a href="/editLift">
                <button type="button" class="btn btn-primary">Добавить лифт</button>
            </a>

        </div>
    </#if>
</@pt.page>
