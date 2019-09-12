<#import "parts/pageTemplate.ftl" as pt>
<@pt.page>
    <#import 'parts/pager.ftl' as p>
    <@p.pager url page />

    <table class="table">
        <thead class="thead-light">
        <tr class="table-active">
            <th scope="col">Id</th>
            <th scope="col">Address</th>
            <th scope="col">Registration number</th>
            <th scope="col">Factory number</th>
            <th scope="col">Manufacture Year</th>
            <th scope="col">TO2</th>
            <th scope="col">PTO</th>
            <th scope="col">Unit</th>
            <th scope="col">Is deleted</th>
            <th scope="col">Edit</th>
            <th scope="col">Remove</th>
        </tr>
        </thead>
    </table>
<#--    <@p.pager url page/>-->
    <#list page.content as lift>
        <tr> ${lift.deleted?then ('<tr class="table-info">','<tr class="table-secondary">')}
            <#if lift.id??>
                <td>${lift.id}</td></#if>
            <#if lift.getAddress??>
                <td>${lift.getAddress()}</td></#if>
            <#if lift.getRegNum()??>
                <td>${lift.getRegNum()}</td></#if>
            <#if lift.getFactNum()??>
                <td>${lift.getFactNum()}</td></#if>
            <#if lift.getManufactureYear()??>
                <td>${lift.getManufactureYear()}</td></#if>
            <#if lift.getMonthTO2()??>
                <td>${lift.getMonthTO2()}</td></#if>
            <#if lift.getPtoData()??>
                <td>${lift.getPtoData()}</td></#if>
            <#if lift.getUnit()??>
                <td>${lift.getUnit()}</td></#if>
            <#if lift.deleted??>
                <td>${lift.deleted?then("true","false")}</td></#if>
            <td><a href="lifts?editLift=${lift.id}">edit</a></td>
            <td>${lift.deleted?then('<a href="lifts?fixLift=${lift.id}">fix</a>',
                '<a href="lifts?removeLift=${lift.id}">remove</a>')}
            </td>
        </tr>
    <#else>
        Lifts list is empty!
    </#list>
    </table>
</@pt.page>