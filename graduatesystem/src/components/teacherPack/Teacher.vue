<template>
  <div>
    <el-container class="main">
      <el-aside :width="tabWidth+'px'">
        <div>
          <div class="isClossTab" @click="isClossTabFun">
            <i :class="isCollapse?'el-icon-d-arrow-right':'el-icon-d-arrow-left'" ></i>
          </div>
          <el-menu :class="'menu'"
                   default-active="1-4-1"
                   class="el-menu-vertical-demo"
                   @open="handleOpen"
                   @close="handleClose"
                   :collapse="isCollapse"
                   background-color="#283239"
                   text-color="#fff"
                   active-text-color="#ffd04b"
          >
            <el-submenu index="1">
              <template slot="title">
                <i class="el-icon-location"></i>
                <span slot="title">毕业设计题目管理</span>
              </template>
              <el-menu-item-group>
                <el-menu-item index="1-1" @click="toTopicshow">选取情况</el-menu-item>
                <el-menu-item index="1-2" @click="toTopicSend">题目发布</el-menu-item>
              </el-menu-item-group>
            </el-submenu>
            <el-submenu index="2">
              <template slot="title">
                <i class="el-icon-edit"></i>
                <span slot="title">学生管理</span>
              </template>
              <el-menu-item-group>
                <el-menu-item index="2-1" @click="toSignin">签到</el-menu-item>
                <el-menu-item index="2-2" @click="toDiscuss">交流</el-menu-item>
                <el-menu-item index="2-3" @click="toComment">评阅</el-menu-item>
              </el-menu-item-group>
            </el-submenu>
          </el-menu>
        </div>
      </el-aside>
      <el-container>
        <el-header class="main-header">
          <el-dropdown>
                        <span class="el-dropdown-link">
                            <img src="" alt="">
                        </span>
            <el-dropdown-menu slot="dropdown">
              <el-button style="height: 50px" @click="finishLogin">退出登录</el-button>
            </el-dropdown-menu>
          </el-dropdown>
        </el-header>
        <el-main style="background: white">
          <div>
            <router-view style="background: white"></router-view>
          </div>
        </el-main>
        <el-footer class="main-footer" height="50px">
          <p>NUC-课程管理系统</p>
        </el-footer>
      </el-container>
    </el-container>
  </div>
</template>
<style>
  *{
    padding: 0;
    margin: 0;
  }

</style>
<style scoped lang="scss">
  $header-height:60px;
  $background-color: #283239;
  $color: #FFF;

  .main{
    height: 100vh;
    min-width: 800px;
    min-height: 100px;
    overflow: hidden;

    aside{
      overflow: visible;
      height: 100%;
      background-color: $background-color;
      color: $color;

      .isClossTab{
        width: 100%;
        height: $header-height;
        cursor: pointer;
        font-size: 25px;
        text-align: center;
        line-height: $header-height;
        font-weight: bold;
        border-right: 1px solid #807c7c;
        box-sizing: border-box;
      }
      .menu {
        width: 100%;
        border-right:0;


      }

    }

    .main-header {
      background-color: $background-color;
      color: $color;

      .el-dropdown{
        cursor: pointer;
        float: right;
      }
      .el-dropdown-link{

        img{
          $imgMargin: (($header-height - 50)/2);
          display:inline-block;
          width:50px;
          height: 50px;
          border-radius: 25px;
          background-color: #FFF;
          margin-top: $imgMargin;
        }
      }
    }

    .crumbs {
      margin-bottom: 20px;
    }

    .main-footer{
      text-align: center;
      background-color: white;
      color: $color;
      line-height: 50px;
    }

  }

</style>

<script>
  import Axios from 'axios'

  import store from '../../vuex/store.js';
  export default {
    name: 'Teacher',
    store,
    data() {
      return {
        isCollapse: false,
        tabWidth: 200,
        test1: 1,
        intelval: null,
      };
    },
    methods: {
      finishLogin(){
        this.$router.push('login');
        this.$message.success('退出成功');
      },
      toSignin(){
        this.$router.push('teacherSignin');
        console.log(this.$route.params.valueFinal)

      },
      toDiscuss(){
        this.$router.push('teacherDiscuss');
      },
      toComment(){
        this.$router.push('teacherComment');

      },
      toTopicshow(){
        this.$router.push('teacherTopicShow');
        console.log(this.$store.state.id+"teacher");

      },
      toTopicSend(){
        console.log(this.$store.state.id+'teacherTopicSend');
        this.$router.push('teacherTopicSend');

      },
      toInfo(){
        this.$router.push('teacherInfo');

      },
      handleOpen(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath);
      },

      isClossTabFun(){
        clearInterval(this.intelval);
        if(!this.isCollapse){
          this.intelval = setInterval(()=>{
            if(this.tabWidth<= 64)
              clearInterval(this.intelval);
            this.tabWidth -= 1;
          }, 1);
        }else{
          this.tabWidth = 200;
        }
        this.isCollapse = !this.isCollapse;
      }
    }
  }
</script>
