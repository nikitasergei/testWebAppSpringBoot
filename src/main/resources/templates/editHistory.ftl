<#import "parts/pageTemplate.ftl" as pt>
<@pt.page>
    <div class="container mt-5" style="width: 50%">
        <h3> Edit history </h3>
        <#if savingReport??>${savingReport}</#if>
        <div class="form-group mt-3">
            <form method="post">

                <input type="hidden" name="id"
                       value="<#if techServiceHistory?? && techServiceHistory.id??>${techServiceHistory.id}</#if>">
                <div class="form-group">
                    <input type="text" name="lift"
                           class="form-control ${(liftError??)?string('is-invalid', '')}"
                           value="<#if techServiceHistory?? && techServiceHistory.lift?? >${techServiceHistory.lift.id}</#if>"
                           placeholder="Enter the lift Id">
                </div>
                <div class="form-group">
                    <input type="text" name="ptoDate"
                           class="form-control ${(ptoDateError??)?string('is-invalid', '')}"
                           value="<#if techServiceHistory?? && techServiceHistory.ptoDate?? >${techServiceHistory.ptoDate}</#if>"
                           placeholder="Enter the pto Date">
                </div>
                <div class="form-group">
                    <input type="text" name="to1Date"
                           class="form-control ${(to1DateError??)?string('is-invalid', '')}"
                           value="<#if techServiceHistory?? && techServiceHistory.to1Date?? >${techServiceHistory.to1Date}</#if>"
                           placeholder="Enter the TO1 Date">
                </div>
                <div class="form-group">
                    <input type="text" name="to2Month"
                           class="form-control ${(to2MonthError??)?string('is-invalid', '')}"
                           value="<#if techServiceHistory?? && techServiceHistory.to2Month?? >${techServiceHistory.to2Month}</#if>"
                           placeholder="Enter the TO2 Date">
                </div>
                <#--                <div class="form-group">-->
                <#--                    <input type="text" name="done"-->
                <#--                           value="<#if techServiceHistory??> ${techServiceHistory.done?then( "true"," false")}</#if>"-->
                <#--                           placeholder="Enter the owner Id">-->
                <#--                </div>-->
                <div class="form-group">
                    <input type="text" name="user"
                           class="form-control ${(userError??)?string('is-invalid', '')}"
                           value="<#if techServiceHistory?? && techServiceHistory.user??>${techServiceHistory.user.id}</#if>"
                           placeholder="Enter the user Id">
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <div class="form-group mt-2">
                    <input class="btn btn-primary" type="submit" value="Submit"/>
                </div>
            </form>
        </div>
    </div>
</@pt.page>