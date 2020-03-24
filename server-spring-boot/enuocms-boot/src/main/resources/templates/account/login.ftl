
<#assign htmlTitle>
<title>Login</title>
</#assign>

<#assign htmlHead>
</#assign>

<#assign contentBody>
<div class="row">
    <div class="col-md-4 col-md-offset-4">
        <div class="panel panel-default">
            <div class="panel-heading">登录</div>
            <div class="panel-body">
                <form method="post" action="${base}/account/checkLogin">
                    <div class="form-group">
                        <label for="exampleInputEmail1">UserName</label>
                        <input type="text" class="form-control" id="username" name="username" placeholder="username">
                    </div>
                    <div class="form-group">
                        <label for="exampleInputPassword1">Password</label>
                        <input type="password" class="form-control" name="password" id="password" placeholder="password">
                    </div>
                    <div class="form-group">
                        <label>
                            <input type="checkbox" value="true"  name="rememberMe" /> 记住我
                        </label>
                    </div>
                    <button type="submit" class="btn btn-primary">Submit</button>
                </form>
            </div>
        </div>
    </div>
</div>
</#assign>

<#assign htmlScript>
</#assign>

<#include "shared/_empty.ftl" />
