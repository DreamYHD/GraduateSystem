<template>
  <el-table
    :data="tableData4"
    style="width: 100%"
    max-height="100%">
    <el-table-column
      fixed align="center"
      prop="id"
      label="题目编号"
      width="100">
    </el-table-column>
    <el-table-column
      fixed align="center"
      prop="title"
      label="题目"
      width="220">
    </el-table-column>
    <el-table-column
      fixed align="center"
      prop="startime"
      label="开始时间"
      width="220">
    </el-table-column>
    <el-table-column
      fixed align="center"
      prop="endtime"
      label="结束时间"
      width="220">
    </el-table-column>
    <el-table-column
      fixed align="center"
      prop="description"
      label="具体要求"
      width="465">
    </el-table-column>
    <el-table-column
      fixed align="center"
      style="align-content: center"
      prop="isthrough"
      label="是否审核通过"
      width="150">
    </el-table-column>
    <el-table-column
      fixed align="center"
      prop="isselect"
      label="是否被选取"
      width="150">
    </el-table-column>

    <el-table-column
      fixed align="center"
      prop="studentname"
      label="选取学生姓名"
      width="250">
    </el-table-column>
    <el-table-column
      fixed align="center"
      fixed="right"
      label="操作"
      width="110">
      <template slot-scope="scope">
        <el-button
          @click.native.prevent="deleteRow(scope.$index, tableData4)"
          type="text"
          size="small">
          移除
        </el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
  import Axios from 'axios'
  import store from '../../vuex/store.js';

  export default {
    store,
    name:'TeacherTopicShow',
    mounted(){

      const api1 = 'http://localhost:3000/gtitle/findbyteachername/'+this.$store.state.id;
      const self = this;
      Axios.get(api1, {
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded'
        }
      })
        .then(function (response) {
          //console.log(response.data.data+ "111111111111111111111111111111111");
          self.tableData4  = response.data.data;
          console.log(self.tableData4[0].title+self.tableData4[0].isselect +"222222222222222222222")
        })
        .catch(function (error) {
        });
    },
    methods: {
      deleteRow(index, rows) {
        const  deleteid = this.tableData4[index].id;
        console.log(deleteid)
        const api = 'http://localhost:3000/gtitle/delete/' + deleteid;
        const self = this;
        Axios.delete(api, {
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
          }
        })
          .then(function (response) {
            self.$message({
              message: '删除成功',
              center: true,
              type: 'success'
            });
          })
          .catch(function (error) {
            self.$message({
              message: '删除失败',
              center: true,
              type: 'warning'
            });
            console.log(error);
          });
        rows.splice(index, 1);
      }
    },
    data() {
      return {
        tableData4: []
      }
    }
  }
</script>
