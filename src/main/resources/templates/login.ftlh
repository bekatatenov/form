<#import "partials/main.ftlh" as main />

<@main.renderWith scripts=["/js/main.js"] styles=[]>

    <div class="login-form">


        <form action="/login" method="post" class="w-25 mx-auto">
            <#if _csrf??>
                <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
            </#if>
            <h2 class="text-center">Log in</h2>
            <div class="form-group">
                <input type="text" class="form-control" name="email" value="${(form.email)!''}" placeholder="Username"
                       required="required">
            </div>
            <div class="form-group">
                <input type="password" class="form-control" placeholder="Password" name="password"
                       value="" required="required">
            </div>
            <div class="form-group justify-content-center">
                <button type="submit" class="btn btn-dark btn-block" style="margin-left: 143px;">Log in</button>
            </div>
        </form>
        <p class="text-center"><a href="/register">Create an Account</a></p>
    </div>

</@main.renderWith>