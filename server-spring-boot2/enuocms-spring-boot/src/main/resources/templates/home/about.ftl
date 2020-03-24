<#assign htmlTitle>
<title>About</title>
</#assign>

<#assign htmlHead>
</#assign>

<#assign contentBody>
    <#import "shared/_carousel.ftl" as c>
    <@c.carousel height="200" />
    <div class="container body-content">
        <div class="row">
            <div class="col-md-8">
                <h2  class="page-header">
                    About
                </h2>

                <h3>${message}</h3>

                <p>Use this area to provide additional information.</p>

                <address>
                    <strong>Support:</strong>
                    <a href="mailto:zhanxp@me.com">zhanxp@me.com</a>
                    <br />
                </address>
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
