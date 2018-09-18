<template>
  <el-form ref="AccountFrom" :model="account" :rules="rules" label-position="left" label-width="0px"
           class="demo-ruleForm login-container">
    <h2 class="title" align="center" style="color: white">用户注册</h2>
    <el-form-item  style="margin-top: 50px;width: 370px" >
      <el-select v-model="seletType" placeholder="请选择账户类型" style="width: 100%">
        <el-option
          v-for="item in options"
          :key="item.value"
          :label="item.label"
          :value="item.value">
        </el-option>
      </el-select>
    </el-form-item>
    <el-form-item prop="username" style="margin-top: 30px">
      <el-input type="text" v-model="account.username" auto-complete="off" placeholder="姓名"></el-input>

    </el-form-item>
    <el-form-item prop="usernumber" style="margin-top: 30px">
      <el-input type="text" v-model="account.usernumber" auto-complete="off" placeholder="学号/工号"></el-input>

    </el-form-item>
    <el-form-item prop="telenumber" style="margin-top: 30px">
      <el-input type="text" v-model="account.telenumber" auto-complete="off" placeholder="手机号"></el-input>

    </el-form-item>
    <el-form-item prop="pwd">
      <el-input type="password" v-model="account.pwd" auto-complete="off" placeholder="密码"></el-input>
    </el-form-item>
    <el-form-item prop="pwdCon">
      <el-input type="password" v-model="account.pwdCon" auto-complete="off" placeholder="确认密码密码"></el-input>
    </el-form-item>
    <el-form-item prop="school" style="margin-top: 30px">
      <el-input type="text" v-model="account.school" auto-complete="off" placeholder="学校"></el-input>

    </el-form-item>
    <el-form-item prop="majorin" style="margin-top: 30px">
      <el-input type="text" v-model="account.majorin" auto-complete="off" placeholder="专业"></el-input>

    </el-form-item>

    <el-form-item style="margin-top: 30px" >
      <el-button type="primary" style=" width:100%; " @click="toRegister">注册</el-button>
    </el-form-item>


  </el-form>
</template>

<script>
  import Axios from 'axios'

  export default {
    name: 'Register',
    methods:{
      toRegister(){
        const sCon = this.account.pwdCon.toString();
        const s = this.account.pwd.toString();
        if (sCon === (s)){//两次密码相同
         // this.$message.success('恭喜你，注册成功');
          if (this.seletType === "选项1"){
            //学生
            const api = 'http://localhost:3000/student/add';
            const params = new URLSearchParams();
            params.append('id', this.account.usernumber);
            params.append('password', this.account.pwd);
            params.append('major', this.account.majorin);
            params.append('name', this.account.username);
            params.append('school', this.account.school);
            params.append('telephone', this.account.telenumber);
            const self = this;
            console.log("---------");
            Axios.post(api, params, {
              headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
              }
            })
              .then(function (response) {
                console.log(response);
                this.$router.push('login');

                self.$message({
                  message: '学生注册成功',
                  center: true,
                  type: 'success'
                });

              })
              .catch(function (error) {
                self.$message({
                  message: '学生注册失败',
                  center: true,
                  type: 'warning'
                });
                console.log(error);
              });
          }else if (this.seletType === "选项2") {
            //老师注册
              const api = 'http://localhost:3000/teacher/add';
              const params = new URLSearchParams();
              params.append('id', this.account.usernumber);
              params.append('password', this.account.pwd);
              params.append('major', this.account.majorin);
              params.append('name', this.account.username);
              params.append('school', this.account.school);
              params.append('telephone', this.account.telenumber);
              const self = this;
              console.log("---------");
              Axios.post(api, params, {
                headers: {
                  'Content-Type': 'application/x-www-form-urlencoded'
                }
              })
                .then(function (response) {
                  console.log(response);
                  this.$router.push('login');

                  self.$message({
                    message: '老师注册成功',
                    center: true,
                    type: 'success'
                  });
                })
                .catch(function (error) {
                  self.$message({
                    message: '老师注册失败',
                    center: true,
                    type: 'warning'
                  });
                  console.log(error);
                });
          }

        } else {
          this.$message.error('两次密码不一致');
        }

      }
    },
    data () {
      return  {
        options: [ {
          value: '选项1',
          label: '学生'
        }, {
          value: '选项2',
          label: '教师'
        }],
        seletType:'',
        logining: false,
        account: {
          username: '',//用户名
          pwd: '',//密码
          pwdCon:'',//重复密码
          usernumber: '',//学号学工号
          school:'',//学校
          majorin:'',//主修专业
          telenumber:''//手机号

        },
        rules: {
          telenumber:[
            {required: true, message: '请输入手机号', trigger: 'blur'},

          ],
          username: [
            {required: true, message: '请输入姓名', trigger: 'blur'},
          ],
          pwd: [
            {required: true, message: '请输入密码', trigger: 'blur'},

          ],
          pwdCon: [
            {required: true, message: '请确认密码', trigger: 'blur'},

          ],
          usernumber: [
            {required: true, message: '请输入学号或工号', trigger: 'blur'},

          ],
          school: [
            {required: true, message: '请输入学校', trigger: 'blur'},

          ],
          majorin: [
            {required: true, message: '请输入主修专业', trigger: 'blur'},

          ]
        },

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
