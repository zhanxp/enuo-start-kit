<#assign htmlTitle>
    <title>Home</title>
</#assign>

<#assign htmlHead>
</#assign>

<#assign contentBody>
<h2  class="page-header">
    Home
</h2>
<p>
Hello！<@shiro.principal property="userName"/>
</p>
</#assign>

<#assign htmlScript>
</#assign>

<#include "shared/_layout.ftl" />
