<#import "parts/pageTemplate.ftl" as pt>
<@pt.page>
    <#import "parts/pager.ftl" as p>

    <div class="pl-5 pr-5 pt-2">
        <#include "parts/filtersForOwner.ftl">
        <@p.pager url page/>
        <table class="table">
            <thead class="thead-light">
            <tr class="table-active">
                <th scope="col">Id</th>
                <th scope="col">Название</th>
                <th scope="col">Адрес</th>
                <th scope="col">Удален</th>
                <th scope="col">Редактирование</th>
                <th scope="col">Удалить</th>
                <th scope="col">Показать все лифты</th>
            </tr>
            <#list page.content as owner>
                <tr> ${owner.deleted?then ('<tr class="table-info">','<tr class="table-secondary">')}
                    <#if owner.id??>
                        <td>${owner.id}</td></#if>
                    <#if owner.name??>
                        <td>${owner.name}</td></#if>
                    <#if owner.address??>
                        <td>${owner.address}</td></#if>
                    <#if owner.deleted??>
                        <td>${owner.deleted?then("true","false")}</td>
                    </#if>
                    <td><a href="/editOwner/${owner.id}">Изменить</a></td>
                    <td>${owner.deleted?then('<a href="owners?fixOwner=${owner.id}">Восстановить</a>',
                        '<a href="owners?removeOwner=${owner.id}">Удалить</a>')}
                    </td>
                    <td><a href="/ownersLifts?ownersLifts=${owner.id}">Показать все лифты</a></td>
                </tr>
            <#else>
                Owners list is empty!
            </#list>
        </table>
        <@p.pager url page/>
        <a href="/editOwner">
            <button type="button" class="btn btn-primary">Добавить заказчика</button>
        </a>
    </div>
</@pt.page>