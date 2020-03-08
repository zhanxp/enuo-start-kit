<#assign htmlTitle>
<title>Admin</title>
</#assign>

<#assign htmlHead>
</#assign>

<#assign contentBody>
<div class="container body-content">
    <h2  class="page-header">
        Profile
    </h2>
    <div class="row">
        <div class="col-md-2">
            <#include "admin/shared/_menu.ftl" />
        </div>
        <div class="col-md-10">
            <div class="panel panel-default">
                <div class="panel-heading" style="margin-bottom:20px;">
                    个人信息
                </div>
                <form class="form-horizontal" action="/admin/user/saveProfile" target="frame_save" method="post">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">Name：</label>
                        <div class="col-sm-6">
                            <input type="text" name="name" class="form-control" value="${ model.name }" required placeholder="title">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-3 col-sm-8">
                            <button type="submit" class="btn btn-primary">保 存</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</#assign>

<#assign htmlScript>
</#assign>

<#include "shared/_layout.ftl" />
