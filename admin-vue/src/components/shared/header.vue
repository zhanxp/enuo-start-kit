
<template>
    <header class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                <a class="navbar-brand" href="/">
                    {{ appName }}
                </a>
            </div>
            <div class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                    <li v-for="(item,index) in menus" :key="index">
                        <router-link   tag="a" :exact="item.path == '/'" :to="item.path">
                        {{item.text}} 
                        </router-link>
                    </li>
                    <!-- <li>
                        <a href="/">主页</a>
                    </li>
                    <li>
                        <a href="/category">分类</a>
                    </li>
                    <li>
                        <a href="/article">文章</a>
                    </li>
                    <li>
                        <a href="/about">关于</a>
                    </li> -->
                </ul>

                <ul v-if="!userInfo.id" class="nav navbar-nav navbar-right">
                    <li>
                        <router-link class="loginLink" to="/login">登录</router-link>
                    </li>
                </ul>

                <ul v-if="userInfo.id" class="nav navbar-nav navbar-right">
                    <li>
                        <router-link  to="/admin"> {{ userInfo.name }} 你好！</router-link >
                    </li>
                    <li>
                        <a  @click="onLogout" >注销</a>
                    </li>
                </ul>

            </div>
        </div>
    </header>
</template>

<script>
export default {
     props: {
        appName: {
            default: "EnuoCMS"
        },
        userInfo:{
            _id:'',
            userName:''
        },
        menus : {
            default : () => []
        }
    },
    computed: {
        path(){
            return location.pathname;
        }
    },
    methods: {
        onLogout(){
            this.$emit('onLogout');
        }
    }
}
</script>

<style lang="less" scoped>

</style>

