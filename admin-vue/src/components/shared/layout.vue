<template>
  <div >
      <Header title="Home" :userInfo="userInfo" :appName="appName" :menus="menus"  @onLogout="onLogout" />
      <div class="container body-content">
          <router-view ></router-view>
          <Footer  />
      </div>  
       <!-- <Wrapper>
        <div slot="slot1">this is slot01</div>
        <div slot="slot2">this is slot02</div>
        <div>aaa</div>
        <div>bbb</div>
      </Wrapper>  -->
  </div>
</template>

<script>

import { mapState,mapActions } from "vuex"
import Vue from 'vue'

import Header from 'components/shared/header'
import Footer from 'components/shared/footer'
import Wrapper from 'components/shared/wrapper'

import {setTicket} from '../../http';

export default {
  data() {
    return {
    }
  },
  created(){
     
  },
  mounted() {
     if (!this.userInfo.id) {
        this.getUserProfile()
      }
  },
  components: {
     Header, 
     Footer,
     Wrapper
  },
  computed: {
    //访问 vuex store 中的数据
    //此处用到 es6 stage-2 才有的三个点展开对象的语法, 对应 .babelrc 中的配置
    ...mapState([
      "appName",
      "userInfo",
      "menus"
    ])
  },
  methods: {
    ...mapActions(['getUserProfile']),
    onLogout(){
       setTicket('');
       this.$router.push({ path: '/login' });
    }
  }, 
  watch: {
      userInfo: function (newValue){
          if (!newValue.id) {
              this.$router.push('/login');
          }
      }
	}
}
</script>

<style lang="less" scoped>
  .body-content{
    margin-top:50px; 
  }

</style>


