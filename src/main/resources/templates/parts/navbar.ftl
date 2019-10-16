<#include "security.ftl">
<#import "login.ftl" as l>


<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/">GrodnoLift Web App</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <#if !message??>
            <li class="nav-item">
                <a class="nav-link" href="/home">Home</a>
            </li>
            </#if>
            <#if isAdmin>
                <li class="nav-item">
                    <a class="nav-link" href="/user">User list</a>
                </li>
            </#if>
            <#if user??>
                <li class="nav-item">
                    <a class="nav-link" href="/lifts">Lifts list</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/user/profile">Profile</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/owners">Owner list</a>
                </li>
            </#if>
        </ul>
        <div class="navbar-text mr-3"><#if user??>${name}<#else>Please, login</#if></div>
        <@l.logout />
    </div>
</nav>