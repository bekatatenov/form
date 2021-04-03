<#macro renderWith scripts=[] styles=[]>
    <!doctype html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6"
              crossorigin="anonymous">
        <title>iForum</title>
    </head>
    <body>
    <header>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container-fluid d-flex justify-content-center w-50">
                <a class="navbar-brand" href="#">iForum</a>
                <div class="collapse navbar-collapse justify-content-end" id="navbarNav">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link" href="/">Themes</a>
                        </li>

                        <#if user??>
                            <li class="nav-item">
                                <a class="nav-link" href="#">Profile</a>
                            </li>
                            <li>
                                <form class="flex box-128" action="/logout" method="post">
                                    <#if _csrf??>
                                        <input type="hidden" name="${(_csrf.parameterName)!'csrf'}"
                                               value="${(_csrf.token)!'--no-token--'}"/>
                                    </#if>
                                    <button style="padding: 5px 0 0 0 ;border: none;background: none;" type="submit">
                                        LogOut
                                    </button>
                                </form>
                            </li>

                        <#else>
                            <li class="nav-item">
                                <a class="nav-link" href="/login">LogIn</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="/register">SignIn</a>
                            </li>
                        </#if>
                    </ul>
                </div>
            </div>
        </nav>
    </header>

    <#list styles as styleSource>
        <link rel="stylesheet" href="${styleSource}">
    </#list>

    <#nested>


    <#list scripts as scriptSource>
        <script src="${scriptSource}"></script>
    </#list>

    </body>
    </html>
</#macro>