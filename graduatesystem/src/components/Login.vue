<template>
  <el-form ref="AccountFrom" :model="account" :rules="rules" label-position="left" label-width="0px"
           class="demo-ruleForm login-container">
    <h2 class="title" align="center" style="color: white">毕业设计过程管理系统</h2>
    <el-form-item prop="username" style="margin-top: 80px">
      <el-input type="text" v-model="account.username" auto-complete="true" placeholder="账号"></el-input>
    </el-form-item>
    <el-form-item prop="pwd">
      <el-input type="password" v-model="account.pwd" auto-complete="off" placeholder="密码"></el-input>
    </el-form-item>
    <el-form-item style="margin-top: 30px;width: 370px" >
      <el-select v-model="selectvalue" placeholder="请选择" style="width: 100%">
        <el-option
          v-for="item in options"
          :key="item.value"
          :label="item.label"
          :value="item.value">
        </el-option>
      </el-select>
    </el-form-item>

    <el-checkbox v-model="checked" checked class="remember">记住密码</el-checkbox>
    <el-form-item style="margin-top: 30px" >
      <el-button type="primary" style=" width:100%; " @click="toHome">登录</el-button>
    </el-form-item>
    <el-form-item style="margin-top: 20px" >
      <el-button type="text" style=" width:100%;" @click="toRegister" >还没有账户？快去注册</el-button>
    </el-form-item>

  </el-form>
</template>

<script>
  import Axios from 'axios'
  import store from '../vuex/store.js';
  export default {
    name: 'Login',
    store,
    methods:{
      toConfing(str){

        const api = 'http://localhost:3000/'+str+'/verity';
        const params = new URLSearchParams();
        params.append('id', this.account.username);
        params.append('password', this.account.pwd);
        const self = this;
        const api1 = 'http://localhost:3000/'+str+'/findone/' + this.account.username;
        Axios.get(api1, {
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
          }
        })
          .then(function (response) {
            self.$store.state.id = response.data.data.name;
            console.log(self.$store.state.id+"login");

          })
          .catch(function (error) {
            console.log(error);
          });
        Axios.post(api, params,{headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
          }})
          .then(function (response) {

            if (response.data.data === true) {
              self.$router.push(str);
            }
          })
          .catch(function (error) {
            console.log(error);
            self.$message({
              message: '密码或用户名错误',
              center: true,
              type: 'warning'
            });
          });

      },
      toHome(){
        if (this.selectvalue === "选项2"){
          // this.logining = true;
          this.toConfing('student')
        }else if (this.selectvalue === "选项3") {
          this.toConfing('teacher')
        }else if(this.selectvalue === "选项1"){
          this.toConfing('administrator')
        }
      },
      toRegister(){
        this.$router.push('register');
      }
    },
    data () {
      return  {
        options: [{
          value: '选项1',
          label: '管理员'
        }, {
          value: '选项2',
          label: '学生'
        }, {
          value: '选项3',
          label: '教师'
        }],
        selectvalue: '',
        logining: false,
        account: {
          username: '',
          pwd: ''
        },
        rules: {
          username: [
            {required: true, message: '请输入账号', trigger: 'blur'},
            //{ validator: validaePass }
          ],
          pwd: [
            {required: true, message: '请输入密码', trigger: 'blur'},
            //{ validator: validaePass2 }
          ]
        },
        checked: true,

      };
    }
  }
</script>


<style>
  body{
    background-image: url("../image/background.jpg");
  }
  .login-container{
    margin-top: 8%;
    width:370px;
    margin-left:40%;
  }
</style>
