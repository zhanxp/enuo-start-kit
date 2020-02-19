<template>
    <div class="row login-box">
        <div class="col-md-4 col-md-offset-4">
            <div class="panel panel-default">
                <div class="panel-heading">登录</div>
                <div class="panel-body">
                    <form @submit.prevent="onLogin" method="post" id="loginForm">
                        <div class="form-group">
                            <label htmlFor="username">UserName</label>
                            <input type="text" class="form-control" v-model="loginForm.username"  required name="username" placeholder="username" />
                        </div>
                        <div class="form-group">
                            <label htmlFor="password">Password</label>
                            <input type="password" class="form-control" v-model="loginForm.password"  required name="password" placeholder="password" />
                        </div>
                        <div class="form-group">
                            <label>
                                <input type="checkbox" value="true"  v-model="loginForm.rememberMe"   name="rememberMe" /> 记住我
                            </label>
                        </div>

                        <button type="submit" class="btn btn-primary">Submit</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import {login} from './service'
import {mapActions, mapState,mapMutations} from 'vuex'
import {setTicket} from '../../http';


export default {
    data(){
        return {
            loginForm: {
                username: '',
                password: '',
                rememberMe:false
            },
        }
    },
    mounted(){
        if (!this.userInfo.id) {
            this.getUserProfile()
        }
    },
    computed: {
        ...mapState(['userInfo']),
    },
    methods: {
        ...mapActions(['getUserProfile']),
        async onLogin(){
            console.log(this.loginForm);
            var result = await login(this.loginForm.username,this.loginForm.password);
            if(result.success){
                setTicket(result.data.ticket);
                this.userInfo.id = result.data.id;
                this.$router.push({
                    path: '/'
                });
            }else{
                alert('登录失败！');
            }
            return false;
          }
    },
    // watch: {
    //     userInfo: function (newValue){
    //         if (newValue.id) {
    //             // this.$message({
    //             //     type: 'success',
    //             //     message: '检测到您之前登录过，将自动登录'
    //             // });
    //             this.$router.push('');
    //         }
    //     }
	// }
}
</script>

<style lang="less" scoped>
.login-box{
    margin-top:150px; 
}
</style>

