<#assign htmlTitle>
<title>Article-${model.title}</title>
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
                    ${model.title}
                </h2>
                <div>
                    ${ model.content }
                </div>
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
