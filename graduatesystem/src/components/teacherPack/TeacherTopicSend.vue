<template>
  <el-form ref="form" :model="form" label-width="100px" style="margin-top: 50px">

    <el-form-item label="课程设计名称" style="width: 53%">
      <el-input v-model="form.topicName" size="large"></el-input>
    </el-form-item>
    <el-form-item label="开始时间">
      <el-col :span="11">
        <el-date-picker type="date"
                        placeholder="选择日期"
                        v-model="form.startTime"
                        style="width: 50%;"
                        format="yyyy 年 MM 月 dd 日"
                        value-format="yyyy-MM-dd">

        </el-date-picker>
      </el-col>
      <el-col class="line" :span="2">-</el-col>
    </el-form-item>
    <el-form-item label="结束时间">
      <el-col :span="11">
        <el-date-picker type="date" placeholder="选择日期" v-model="form.endTime" style="width: 50%;"
                        format="yyyy 年 MM 月 dd 日"
                        value-format="yyyy-MM-dd"></el-date-picker>
      </el-col>
      <el-col class="line" :span="2">-</el-col>
    </el-form-item>



    <el-form-item label="主要功能" style="width: 50% ">
      <el-input
        type="textarea"
        :rows="5"
        placeholder="请输入内容"
        v-model="form.topicDesc">
      </el-input>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="onSubmit">立即创建</el-button>
      <el-button type="info" @click="setNull">清空</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
  import store from '../../vuex/store.js';
  import Axios from 'axios'
  export default {
    store,
    name:'TeacherTopicSend',
    data() {
      return {
        form: {
          topicName: '',
          schoolName: '',
          majorName: '',
          startTime: '',
          endTime: '',
          topicDesc: '',
          isselect: '否',
          isthrough:'否'
        }
      }
    },
    methods: {
      setNull(){
          this.form.topicName ='',
          this.form.schoolName= '',
          this.form.majorName = '',
          this.form.startTime = '',
          this.form.endTime = '',
          this.form.topicDesc = '',
          this.form.isselect = '否',
          this.form.isselect = '否'
      },
      onSubmit() {
        const api = 'http://localhost:3000/gtitle/add';
        const params = new URLSearchParams();
        params.append('title', this.form.topicName);
        params.append('startime', this.form.startTime.toString());
        params.append('endtime', this.form.endTime.toString());
        params.append('isselect', this.form.isselect);
        params.append('isthrough', this.form.isthrough);
        params.append('description', this.form.topicDesc);
        params.append('teachername',this.$store.state.id);
        params.append('discuss',"");
        params.append('filepath',"");
        console.log(this.$store.state.id+"+++++++++++++=");
        const self = this;
        Axios.post(api, params, {
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
          }
        })
          .then(function (response) {
            self.$message({
              message: '录入成功',
              center: true,
              type: 'success'
            });
            self.setNull();
            console.log(response);
          })
          .catch(function (error) {
            self.$message({
              message: '录入失败',
              center: true,
              type: 'warning'
            });
            console.log(error);
          });


      }
    }
  }
</script>
