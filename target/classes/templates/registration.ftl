<#import "parts/pageTemplate.ftl" as pt>
<#import "parts/login.ftl" as l>

<@pt.page>
    <div class="container m-auto">
        <label class="mb-3 ml-5 pt-3" style="color: red; font-weight: bold">Add new user</label>
        ${message?ifExists}
        <@l.login "/registration" true/>
    </div>
</@pt.page>