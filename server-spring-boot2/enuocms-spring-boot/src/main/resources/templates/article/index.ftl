<#assign htmlTitle>
<title>Article</title>
</#assign>

<#assign htmlHead>
</#assign>

<#assign contentBody>
    <#import "shared/_carousel.ftl" as c>
    <@c.carousel height="220" />
<div class="container body-content">
    <div class="row">
        <div class="col-md-8">
            <div class="container body-content">
                <h2  class="page-header">
                    Article
                </h2>
                <div>
                    <#list pageList.items as item>
                        <div class="media">
                            <div class="media-left">
                                <a href="/article/detail/${ item.id }">
                                    <img class="media-object" data-src="holder.js/64x64" alt="64x64"
                                         src="data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiIHN0YW5kYWxvbmU9InllcyI/PjxzdmcgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIiB3aWR0aD0iNjQiIGhlaWdodD0iNjQiIHZpZXdCb3g9IjAgMCA2NCA2NCIgcHJlc2VydmVBc3BlY3RSYXRpbz0ibm9uZSI+PCEtLQpTb3VyY2UgVVJMOiBob2xkZXIuanMvNjR4NjQKQ3JlYXRlZCB3aXRoIEhvbGRlci5qcyAyLjYuMC4KTGVhcm4gbW9yZSBhdCBodHRwOi8vaG9sZGVyanMuY29tCihjKSAyMDEyLTIwMTUgSXZhbiBNYWxvcGluc2t5IC0gaHR0cDovL2ltc2t5LmNvCi0tPjxkZWZzPjxzdHlsZSB0eXBlPSJ0ZXh0L2NzcyI+PCFbQ0RBVEFbI2hvbGRlcl8xNzA3Y2E4ZGZjNCB0ZXh0IHsgZmlsbDojQUFBQUFBO2ZvbnQtd2VpZ2h0OmJvbGQ7Zm9udC1mYW1pbHk6QXJpYWwsIEhlbHZldGljYSwgT3BlbiBTYW5zLCBzYW5zLXNlcmlmLCBtb25vc3BhY2U7Zm9udC1zaXplOjEwcHQgfSBdXT48L3N0eWxlPjwvZGVmcz48ZyBpZD0iaG9sZGVyXzE3MDdjYThkZmM0Ij48cmVjdCB3aWR0aD0iNjQiIGhlaWdodD0iNjQiIGZpbGw9IiNFRUVFRUUiLz48Zz48dGV4dCB4PSIxMy40NjA5Mzc1IiB5PSIzNi41Ij42NHg2NDwvdGV4dD48L2c+PC9nPjwvc3ZnPg=="
                                         data-holder-rendered="true" style="width: 64px; height: 64px;">
                                </a>
                            </div>
                            <div class="media-body">
                                <h4 class="media-heading"><a href="/article/detail/${ item.id }">${ item.title }</a></h4>
                            ${ item.intro }
                            </div>
                        </div>
                    </#list>
                </div>
                <#import "shared/_pager.ftl" as q>
                <@q.pager total=pageList.total pageIndex=pageList.pageIndex pageSize=pageList.pageSize  />
            </div>
        </div>
        <div class="col-md-4">
            <#include "shared/_categorys.ftl" />
        </div>
    </div>
</div>
</#assign>

<#assign htmlScript>
</#assign>

<#include "shared/_layout.ftl" />
