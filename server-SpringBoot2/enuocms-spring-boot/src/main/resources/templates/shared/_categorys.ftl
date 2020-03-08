<h2 class="page-header">
    分类
</h2>
<div>
    <ul class="nav nav-pills nav-stacked">
        <#list categorys as item>
        <li><a href="/article/${item.id}">${item.title}</a></li>
        </#list>
    </ul>
</div>