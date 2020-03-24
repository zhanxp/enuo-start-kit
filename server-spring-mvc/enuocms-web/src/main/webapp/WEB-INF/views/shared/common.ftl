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
                <li>
                    <a href="${base}/category" >分类</a>
                </li>
                <li>
                    <a  href="${base}/article" >文章</a>
                </li>
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
                    <li>
                        <a  href="${base}/admin" > <@shiro.principal property="userName"/> 你好！</a>
                    </li>
                    <li>
                        <a   href="${base}/account/logout" >注销</a>
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
<footer>
    <p>&copy; 2017 - 我的 SpringMvc 应用程序</p>
</footer>
</#macro>
<!-- footer end -->

