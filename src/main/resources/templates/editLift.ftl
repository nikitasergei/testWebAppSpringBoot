<#import "parts/pageTemplate.ftl" as pt>
<@pt.page>
    <div class="container mt-5" style="width: 50%">
        <h3> Edit lift </h3>
        <#if savingReport??>${savingReport}</#if>
        <div class="form-group mt-3">
            <form method="post">
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <input type="hidden" name="id" value="<#if lift?? && lift.id?? >${lift.id}</#if>">
                <div class="form-group">
                    <input type="text" name="city"
                           class="form-control ${(cityError??)?string('is-invalid', '')}"
                           value="<#if lift?? && lift.city?? >${lift.city}</#if>"
                           placeholder="Enter the city">
                    <div class="invalid-feedback">
                        <#if cityError??>${cityError}</#if>
                    </div>
                </div>
                <div class="form-group">
                    <input type="text" name="address" class="form-control ${(addressError??)?string('is-invalid', '')}"
                           value="<#if lift?? && lift.address??>${lift.address}</#if>"
                           placeholder="Enter the address">
                    <div class="invalid-feedback">
                        <#if addressError??>${addressError}</#if>
                    </div>
                </div>
                <div class="form-group">
                    <input type="text" name="regNum" class="form-control ${(regNumError??)?string('is-invalid', '')}"
                           value="<#if lift?? && lift.regNum??>${lift.regNum}</#if>"
                           placeholder="Enter registration number">
                    <div class="invalid-feedback">
                        <#if regNumError??>${regNumError}</#if>
                    </div>
                </div>
                <div class="form-group">
                    <input type="text" name="factNum" class="form-control ${(factNumError??)?string('is-invalid', '')}"
                           value="<#if lift?? && lift.factNum??>${lift.factNum}</#if>"
                           placeholder="Enter the factory number">
                    <div class="invalid-feedback">
                        <#if factNumError??>${factNumError}</#if>
                    </div>
                </div>
                <div class="form-group">
                    <input type="date" name="activationDate"
                           class="form-control ${(activationDateError??)?string('is-invalid', '')}"
                           value="<#if lift?? && lift.activationDate??>${lift.activationDate}</#if>"
                           placeholder="Enter the activation Date">
                    <div class="invalid-feedback">
                        <#if activationDateError??>${activationDateError}</#if>
                    </div>
                </div>
                <div class="form-group">
                    <input type="text" name="owner"
                           class="form-control ${(ownerError??)?string('is-invalid', '')}"
                           value="<#if lift?? && lift.owner??>${lift.owner.name}</#if>"
                           placeholder="Chose the owner">
                    <div class="invalid-feedback">
                        <#if ownerError??>${ownerError}</#if>
                    </div>
                </div>
                <div class="form-group mt-2">
                    <input type="submit" class="btn btn-primary" value="Submit">
                </div>
            </form>
        </div>
    </div>
</@pt.page>