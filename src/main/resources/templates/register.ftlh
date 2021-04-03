<#import "partials/main.ftlh" as main />

<@main.renderWith scripts=["/js/main.js"] styles=[]>

    <div class="login-form">


        <form action="/register" method="post"  class="w-25 mx-auto">
            <#if _csrf??>
                <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
            </#if>
            <h2 class="text-center">Register</h2>

            <div class="form-group">
                <input type="text" class="form-control" name = "email" value="${(form.email)!''}" placeholder="Username" required="required">
            </div>
            <div class="form-group">
                <input type="password" name ="password" class="form-control" value="" placeholder="Password" required="required">
            </div>
            <div class="form-group justify-content-center">
                <button type="submit" class="btn btn-dark btn-block" style="margin-left: 143px;">Register</button>
            </div>

        </form>
        <p class="text-center"><a href="/login">LogIn</a></p>
    </div>

</@main.renderWith>