<template>
  <div style=" width: 100%">
    <div style="float: left;width: 60%">
      <el-table
        :data="tableDataStudent"
      >
        <el-table-column
          fixed align="center"
          prop="id"
          label="题目编号"
          width="150">
        </el-table-column>
        <el-table-column
          fixed align="center"
          prop="title"
          label="题目名称"
          width="150">
        </el-table-column>
        <el-table-column
          fixed align="center"
          prop="teachername"
          label="发布老师"
          width="150">
        </el-table-column>
        <el-table-column
          fixed align="center"
          prop="startime"
          label="开始时间"
          width="120">
        </el-table-column>
        <el-table-column
          fixed align="center"
          prop="description"
          label="题目描述"
          width="350">
        </el-table-column>
        <el-table-column
          fixed align="center"
          fixed="right"
          label="操作"
          width="120">
          <template slot-scope="scope">
            <el-button
              @click.native.prevent="selectRow(scope.$index, tableDataStudent)"
              type="text"
              size="small">
              选择
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div style="float: right;width: 30%">
      <el-card class="box-card">
        <div slot="header" class="clearfix">
          <span>我的课题</span>
        </div>
        <div style="height: 350px">
          题目：{{myTitle}}<br><br>
          教师名字: {{myTeacherName}}<br><br>
          开始时间: {{myStartTime}}<br><br>
          结束时间: {{myEndTime}}<br><br>
          具体要求: {{myDescription}}<br><br>
        </div>
      </el-card>
    </div>

  </div>
</template>

<script>
  import Axios from 'axios'
  import store from '../../vuex/store.js';
  export default {
    store,
    mounted(){
      this.initMySelect();
      const api1 = 'http://localhost:3000/gtitle/findbyis/'+'是/'+'否';
      const self = this;
      Axios.get(api1, {
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded'
        }
      })
        .then(function (response) {
          self.tableDataStudent = response.data.data;
          console.log(self.tableDataStudent[0].title+self.tableDataStudent[0].teachername +"222222222222222222222")
        })
        .catch(function (error) {
        });
    },
    methods: {
      initMySelect(){
        console.log(this.$store.state.id+"initmyselect");
        const api1 = 'http://localhost:3000/gtitle/findbystudentname/'+this.$store.state.id;
        const self = this;
        Axios.get(api1, {
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
          }
        })
          .then(function (response) {
            console.log(response.data.data[0].description);
            console.log(response.data.data[0].title+"mytitle"+response.data.data[0].teachername);
            self.myTitle = response.data.data[0].title;
            self.myDescription = response.data.data[0].description;
            self.myStartTime = response.data.data[0].startime;
            self.myEndTime = response.data.data[0].endtime;
            self.myTeacherName = response.data.data[0].teachername;
            console.log(self.teacherName);
            self.hasTitle = true;
          })
          .catch(function (error) {
            console.log(error)
          });
      },
      selectRow(index, rows) {
        if (!this.hasTitle){
          const  selectId = this.tableDataStudent[index].id;
          console.log(selectId+"studenttopicshow");
          const api = 'http://localhost:3000/gtitle/update/' + selectId;
          const params = new URLSearchParams();
          params.append('id', this.tableDataStudent[index].id);
          params.append('description', this.tableDataStudent[index].description);
          params.append('teachername', this.tableDataStudent[index].teachername);
          console.log(this.tableDataStudent[index].teachername);
          params.append('endtime', this.tableDataStudent[index].endtime);
          console.log(this.tableDataStudent[index].endtime);
          params.append('startime', this.tableDataStudent[index].startime);
          params.append('isselect', '是');
          params.append('isthrough', this.tableDataStudent[index].isthrough);
          params.append('studentname', this.$store.state.id);
          if (this.tableDataStudent[index].filepath == null){
            params.append('filepath', "");
          } else {
            params.append('filepath', this.tableDataStudent[index].filepath);
          }
          if (this.tableDataStudent[index].discuss == null){
            params.append('discuss', "");
          } else {
            params.append('discuss', this.tableDataStudent[index].discuss);
          }
          if (this.tableDataStudent[index].teacher1name === null){
            params.append('teacher1name',"");
          } else {
            params.append('teacher1name', this.tableDataStudent[index].teacher1name);
          }
          if (this.tableDataStudent[index].teacher2name === null){
            params.append('teacher2name', "");
          } else {
            params.append('teacher2name', this.tableDataStudent[index].teacher2name);
          }
          params.append('title', this.tableDataStudent[index].title);
          const self = this;
          console.log("------------------------------------------------------");
          Axios.post(api, params, {
            headers: {
              'Content-Type': 'application/x-www-form-urlencoded'
            }
          })
            .then(function (response) {
              console.log(response);
              self.$message({
                message: '选择成功',
                center: true,
                type: 'success'
              });
              rows.splice(index, 1);
            })
            .catch(function (error) {
              self.$message({
                message: '选择失败',
                center: true,
                type: 'warning'
              });
              console.log(error);
            });
        }else {
          this.$message({
            message: '你已经有毕设设计题目',
            center: true,
            type: 'success'
          });
        }
      }
    },
    data() {
      return {
        myTeacherName: '',
        myStartTime:'',
        myEndTime:'',
        myTitle : '',
        myDescription: '',
        hasTitle : false,
        tableDataStudent: []
      }
    }
  }
</script>
<style>
  .text {
    font-size: 14px;
  }

  .item {
    margin-bottom: 18px;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }
  .clearfix:after {
    clear: both
  }

  .box-card {
    width: 480px;
  }
</style>
