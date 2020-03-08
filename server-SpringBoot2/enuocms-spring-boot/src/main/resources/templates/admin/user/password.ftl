<#assign htmlTitle>
<title>Admin</title>
</#assign>

<#assign htmlHead>
</#assign>

<#assign contentBody>
<div class="container body-content">
    <h2  class="page-header">
        Password
    </h2>
    <div class="row">
        <div class="col-md-2">
            <#include "admin/shared/_menu.ftl" />
        </div>
        <div class="col-md-10">
            <div class="panel panel-default">
                <div class="panel-heading" style="margin-bottom:20px;">
                    修改密码
                </div>
                <form class="form-horizontal" action="/admin/user/savePassword" target="frame_save"  method="post">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">原密码：</label>
                        <div class="col-sm-6">
                            <input type="text" name="old_password" class="form-control"
                                   value="" required placeholder="原密码">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">新密码：</label>
                        <div class="col-sm-6">
                            <input type="text" name="new_password" class="form-control"
                                   value="" required placeholder="新密码">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">确认密码：</label>
                        <div class="col-sm-6">
                            <input type="text" name="comfirm_password" class="form-control"
                                   value="" required placeholder="再次输入新密码">
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
