<#import "partials/main.ftlh" as main />

<@main.renderWith scripts=["/js/main.js"] styles=[]>

    <div class="container" style="padding: 10px">
        <div class="list-group justify-content-center w-50 mx-auto">
            <a href="#" class="list-group-item list-group-item-action list-group-item-dark">
                List Of Themes
            </a>
            <#list themes as theme>
                <a href="/theme/${theme.id}"
                   class="list-group-item list-group-item-action d-flex justify-content-between">
                    <p style="padding: 0 5px">${theme.name}</p>
                    <p style="padding: 0 5px">${theme.time}</p>
                    <p style="padding: 0 5px"> ${theme.user.email}</p>
                    <p style="padding: 0 5px">  ${theme.qty}</p></a>
            </#list>
        </div>

        <div class="d-flex justify-content-between w-50 mx-auto">
            <div class="justify-content-center w-50 mx-auto" style="padding: 10px">
                <#if page!=1>
                    <a class="pageButton" style="color: black; text-align: center" href="?page=${page-1}"><<</a>
                </#if>
            </div>
            <div class="justify-content-center w-50 mx-auto" style="padding: 10px">
                <#list 1..pages as x>
                    <a class="pageButton" style="color: black; text-align: center" href="?page=${x}">${x}</a>
                </#list>
            </div>
            <div class="justify-content-center w-50 mx-auto" style="padding: 10px">
                <#if lastPage != page>
                    <a class="pageButton" style="color: black; text-align: center" href="?page=${page+1}">>></a>
                </#if>
            </div>
        </div>
    </div>
</@main.renderWith>