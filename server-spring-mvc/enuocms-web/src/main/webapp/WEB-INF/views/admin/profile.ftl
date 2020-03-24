<#assign htmlTitle>
<title>Admin</title>
</#assign>

<#assign htmlHead>
</#assign>

<#assign contentBody>
<h2  class="page-header">
    Profile
</h2>
<form class="form-horizontal">
    <div class="form-group">
        <label  class="col-sm-2 control-label">ID</label>
        <div class="col-sm-10">
            <p  class="form-control-static" >${profile.id}</p>
        </div>
    </div>
    <div class="form-group">
        <label  class="col-sm-2 control-label">Name</label>
        <div class="col-sm-10">
            <p  class="form-control-static" >${profile.name}</p>
        </div>
    </div>
</form>
</#assign>

<#assign htmlScript>
</#assign>

<#include "shared/_layout.ftl" />
