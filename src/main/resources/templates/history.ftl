<#import "parts/pageTemplate.ftl" as pt>
<#import "parts/pager.ftl" as p>
<@pt.page>
    <div class="pl-5 pr-5 pt-2">
        <@p.pager url page/>
        <table class="table">
            <thead class="thead-light">
            <tr class="table-active" align="center">
                <th scope="col">Id</th>
                <th scope="col">Lift</th>
                <th scope="col">PTO</th>
                <th scope="col">TO-1</th>
                <th scope="col">TO-2</th>
                <th scope="col">User</th>
                <th scope="col">Is Done</th>
                <th scope="col">Performance Mark</th>

            </tr>
            </thead>
            <#list page.content as histories>
                <tbody>
                <tr ${histories.done?then("class='table-success'","class='alert-danger'")}>
                    <#if histories.id??>
                        <td align="center">${histories.id}</td></#if>
                    <#if histories.lift??>
                        <td align="center">${histories.lift.regNum}</td></#if>
                    <#if histories.ptoDate??>
                        <td align="center">${histories.ptoDate}</td></#if>
                    <#if histories.to1Date??>
                        <td align="center">${histories.to1Date}</td></#if>
                    <#if histories.to2Month??>
                        <td align="center">${histories.to2Month}</td></#if>
                    <#if histories.user.id??>
                        <td align="center">${histories.user.username}</td></#if>
                    <td align="center">${histories.done?then("Done","Not Done")}</td>
                    <td align="center">
                        <button type="button"
                                class="btn btn-dark">${histories.done?then('<a href="history?done=${histories.id}">Not Done</a>',
                            '<a href="history?notDone=${histories.id}">Done</a>')}</button>
                    </td>
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
    </div>
</@pt.page>