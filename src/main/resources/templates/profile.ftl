<#import "parts/pageTemplate.ftl" as pt>


<@pt.page>
    <div class="container mt-5" style="width: 50%">
        <h2>${username}</h2>

        <#if filename??>
            <style>
                #pic {
                    display: block;
                    width: 120px;
                    height: 120px;
                    border: 1px solid black;
                    border-radius: 50%;
                    margin-bottom: 20px;
                }

                img {
                    width: 120px;
                    height: 120px;
                    border: 1px solid black;
                    border-radius: 100%;
                }
            </style>
        <#else>
            <style>
                #pic {
                    display: none;
                }
            </style>
        </#if>
        <div id="pic">
            <#if filename??>
                <img src="/img/${filename}">
            <#else>
                <label>Load photo</label>
            </#if>
        </div>
        <form method="post" enctype="multipart/form-data">
            <div class="form-group row">
                <label class="col-sm-2 col-form-label" style="color: red">Password:</label>
                <div class="col-sm-6">
                    <input type="password" name="password" class="form-control" placeholder="Password"/>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label" style="color: red">Email:</label>
                <div class="col-sm-6">
                    <input type="email" name="email" class="form-control" placeholder="some@some.com"/>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label" style="color: red">Image:</label>
                <div class="col-sm-6">
                    <input type="file" name="file" style="border-radius: 5px"/>
                    <#--                    <label class="custom-file-label" > Choose file </label>-->
                </div>
            </div>

            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button class="btn btn-primary" type="submit">Save</button>
        </form>
    </div>
</@pt.page>