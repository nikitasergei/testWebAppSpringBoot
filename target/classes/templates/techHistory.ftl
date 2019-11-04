<#import "parts/pageTemplate.ftl" as pt>
<@pt.page>
<#-- <#if message??>-->
<#--            <div class="alert alert-${messageType}" role="alert">-->
<#--                ${message}-->
<#--            </div>-->
<#--        <#else>-->
    <#import "parts/pager.ftl" as p>
    <div class="pl-5 pr-5 pt-2">
        <#include "parts/newNav.ftl">
        <@p.pager url page/>
        <table class="table">
            <thead class="thead-light">
            <tr class="table-active" align="center">
                <th scope="col">PTO</th>
                <th scope="col">TO-1</th>
                <th scope="col">TO-2</th>
                <th scope="col">Is Done</th>
                <th scope="col">User</th>
            </tr>
            <#list page.content as techHistory>
            <tr> ${techHistory.done?then ('<tr class="table-info">','<tr class="table-secondary">')}
                <#if techHistory.ptoDate??>
                    <td align="center">${techHistory.ptoDate}</td></#if>
                <#if techHistory.to1Date??>
                    <td align="center">${techHistory.to1Date}</td></#if>
                <#if techHistory.to2Month??>
                    <td align="center">${techHistory.to2Month}</td></#if>
                <#if techHistory.done??>
                    <td align="center">${techHistory.done?then( "true"," false")}</td></#if>
                <#if techHistory.user.id??>
                    <td align="center">${techHistory.user.username}</td></#if>

                </#list>
        </table>
    </div>
</@pt.page>