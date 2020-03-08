<!-- header start-->
<#macro header>
<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${base}/" >Home</a>
        </div>
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li>
                    <a  href="${base}/" >主页</a>
                </li>
                <#list categorys as item>
                    <li><a href="/article/${item.id}">${item.title}</a></li>
                </#list>
                <li>
                    <a  href="${base}/about" >关于</a>
                </li>
            </ul>
            <@shiro.guest>
                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <a  class="loginLink" href="${base}/account/login" >登录</a>
                    </li>
                </ul>
            </@shiro.guest>
            <@shiro.user>
                <ul class="nav navbar-nav navbar-right">
                    <li role="presentation" class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true"
                           aria-expanded="false">
                            <i class="glyphicon glyphicon-user"></i> <@shiro.principal property="userName"/> 你好！ <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu">
                            <li>
                                <a href="${base}/admin/profile">个人信息</a>
                            </li>
                            <li>
                                <a href="${base}/admin/password">修改密码</a>
                            </li>
                            <li>
                                <a href="${base}/account/logout" onClick="return confirm('确定要注销吗?');">安全退出</a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a href="${base}/admin"><i class="glyphicon glyphicon-cog"></i> 管理</a>
                    </li>
                </ul>
            </@shiro.user>
        </div>
    </div>
</div>
</#macro>
<!-- header end -->

<!-- footer start -->
<#macro footer>
<footer class="text-center">
    <p>&copy; 2017 - 我的 SpringBoot 应用程序</p>
</footer>
</#macro>
<!-- footer end -->

