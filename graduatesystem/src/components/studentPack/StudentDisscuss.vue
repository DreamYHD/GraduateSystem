<template>
  <div>

    <div id="chatdiv" style="width: 60%;float:left;background: #d9ecff;margin-left: 300px">
      <h4 style="text-align: center"></h4>
      <el-card class="box-card" style="margin-top: 40px; width: 90%;margin-left: 50px;margin-right: 50px;height: 550px">
        <div slot="header" class="clearfix">
          <span>聊天界面</span>
          <el-button style="float: right; padding: 3px 0" type="text" @click="backChat">退出聊天</el-button>
        </div>
        <div v-for="o in disscussList" :key="o" class="text item">
          {{'' + o }}
        </div>
      </el-card>
      <el-input
        style="margin-top: 4px; width: 90%;margin-left: 50px;margin-right: 50px; solid-color: #bbbbbb"
        type="textarea"
        :rows="6"
        placeholder="请输入内容"
        v-model="textarea">
      </el-input>
      <div style="margin-top: 4px; width: 90%;margin-left: 50px;margin-right: 50px;">
        <el-upload style="margin-right: 0"
                   class="upload-demo"
                   action="http://localhost:3000/upload"
                   :on-preview="handlePreview"
                   :on-remove="handleRemove"
                   :before-remove="beforeRemove"
                   :on-success="handleSuccess"
                   multiple
                   :limit="3"
                   :on-exceed="handleExceed"
                   :file-list="fileList">
          <el-button size="small" type="primary" round style="margin-top: 4px;margin-right: 0px">上传文件</el-button>
        </el-upload>>
        <el-button type="primary" round style="margin-left: 900px;width: 100px;margin-bottom: 5px" @click="sendmessage">发送</el-button>
      </div>
    </div>
    <div>
      <el-badge v-model= filenum class="item">
        <el-button size="medium" @click="downloadfile">待下载文件</el-button>
      </el-badge>
    </div>

  </div>
</template>
<script>
  import Axios from 'axios'
  import store from '../../vuex/store.js';
  export default {
    mounted(){
      setInterval(this.inittableStudentDisscuss,1000);
    },
    store,
    name: 'StudentDiscuss',
    data() {
      return {
        filenum:'',
        form: {
          name1: '',
          name2: ''
        },
        fileList: [],
        textarea: "",
        disscussList:'',
        GTid:'',
        formLabelWidth: '80px',
        tableDataStudentDiscuss: [],
        dialogFormVisible: false,
        index: 1
      }
    },

    methods: {
      downloadfile(){
        console.log(this.tableDataStudentDiscuss[0].filepath);
        window.open(this.tableDataStudentDiscuss[0].filepath, '_blank');
        this.updataFile("",1)
      },
      updataFile(fileList,str){
        const pushId = this.GTid;
        console.log(pushId);
        const api = 'http://localhost:3000/gtitle/update/' + pushId;
        const params = new URLSearchParams();
        params.append('id', this.tableDataStudentDiscuss[0].id);
        params.append('description', this.tableDataStudentDiscuss[0].description);
        params.append('teachername', this.tableDataStudentDiscuss[0].teachername);
        console.log(this.tableDataStudentDiscuss[0].teachername);
        params.append('endtime', this.tableDataStudentDiscuss[0].endtime);
        console.log(this.tableDataStudentDiscuss[0].endtime);
        params.append('startime', this.tableDataStudentDiscuss[0].startime);
        params.append('isselect', this.tableDataStudentDiscuss[0].isselect);
        if (str === 0){
          params.append('filepath', fileList[0].url);
          this.filenum = 1;
        } else {
          params.append('filepath', "");
          this.filenum = 0;
        }

        if (this.tableDataStudentDiscuss[0].discuss == null) {
          params.append('discuss', "");
        } else {
          params.append('discuss', this.tableDataStudentDiscuss[0].discuss);
        }
        params.append('isthrough', '是');
        if (this.tableDataStudentDiscuss[0].studentname === null) {
          params.append('studentname', "");

        } else {
          params.append('studentname', this.tableDataStudentDiscuss[0].studentname);
        }
        if (this.tableDataStudentDiscuss[0].teacher1name === null) {
          params.append('teacher1name', "");

        } else {
          params.append('teacher1name', this.tableDataStudentDiscuss[0].teacher1name);
        }
        if (this.tableDataStudentDiscuss[0].studentname === null) {
          params.append('teacher2name', "");

        } else {
          params.append('teacher2name', this.tableDataStudentDiscuss[0].teacher2name);

        }
        params.append('title', this.tableDataStudentDiscuss[0].title);
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
              message: '操作成功',
              center: true,
              type: 'success'
            });
          })
          .catch(function (error) {
            self.$message({
              message: '操作失败',
              center: true,
              type: 'warning'
            });
            console.log(error);
          });
      },
      handleSuccess(response, file, fileList) {
        this.updataFile(fileList,0);
      },
      inittableStudentDisscuss(){
        console.log(this.index++);
        console.log(this.$store.state.id+"initmyselect");
        const api1 = 'http://localhost:3000/gtitle/findbystudentname/'+this.$store.state.id;
        const self = this;
        Axios.get(api1, {
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
          }
        })
          .then(function (response) {
            self.tableDataStudentDiscuss = response.data.data;
            if (response.data.data[0].filepath!==null&&response.data.data[0].filepath!==""){
              self.filenum = 1;
            } else {
              self.filenum = 0;
              self.fileList = [];
            }
            console.log(response.data.data[0].title+"mytitle"+response.data.data[0].teachername);
            self.GTid = response.data.data[0].id;
            self.disscussList = (response.data.data[0].discuss).toString().split("|");
          })
          .catch(function (error) {
            console.log(error)
          });
      },
      sendmessage() {
        //textarea
        const pushId = this.tableDataStudentDiscuss[0].id;
        console.log(pushId);
        const api = 'http://localhost:3000/gtitle/update/' + pushId;
        const params = new URLSearchParams();
        params.append('id', this.tableDataStudentDiscuss[0].id);
        params.append('description', this.tableDataStudentDiscuss[0].description);
        params.append('teachername', this.tableDataStudentDiscuss[0].teachername);
        params.append('endtime', this.tableDataStudentDiscuss[0].endtime);
        params.append('startime', this.tableDataStudentDiscuss[0].startime);
        if (this.tableDataStudentDiscuss[0].filepath == null) {
          params.append('filepath', "");
        } else {
          params.append('filepath', this.tableDataStudentDiscuss[0].filepath);
        }
        if (this.tableDataStudentDiscuss[0].discuss == null) {
          params.append('discuss', "");
        } else {
          params.append('discuss', this.tableDataStudentDiscuss[0].discuss + this.$store.state.id + ": " + this.textarea + "|");
        }
        params.append('isselect', "是");
        params.append('isthrough', '是');
        if (this.tableDataStudentDiscuss[0].studentname === null) {
          params.append('studentname', "");

        } else {
          params.append('studentname', this.tableDataStudentDiscuss[0].studentname);
        }
        if (this.tableDataStudentDiscuss[0].teacher1name === null) {
          params.append('teacher1name', "");

        } else {
          params.append('teacher1name', this.tableDataStudentDiscuss[0].teacher1name);
        }
        if (this.tableDataStudentDiscuss[0].studentname === null) {
          params.append('teacher2name', "");

        } else {
          params.append('teacher2name', this.tableDataStudentDiscuss[0].teacher2name);

        }
        params.append('title', this.tableDataStudentDiscuss[0].title);
        const self = this;
        console.log("------------------------------------------------------");
        Axios.post(api, params, {
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
          }
        })
          .then(function (response) {
            console.log(response);
            self.textarea = '';
            self.$message({
              message: '发送成功',
              center: true,
              type: 'success'
            });


          })
          .catch(function (error) {
            self.$message({
              message: '发送失败',
              center: true,
              type: 'warning'
            });
            console.log(error);
          });


      },
      backChat() {
        document.getElementById("chatdiv").style.display = "none";
      },
      sendTeacherNo() {
        this.dialogFormVisible = false;

      },
      handleEdit(index, row) {
        console.log(index, row);
        document.getElementById("chatdiv").style.display = "";
      },
      handleDelete(index, row) {
        this.dialogFormVisible = true;

        console.log(index, row);
      },
      handleRemove(file, fileList) {
        console.log(file, fileList);
      },
      handlePreview(file) {
        console.log(file);
      },
      handleExceed(files, fileList) {
        this.$message.warning(`当前限制选择 3 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
      },
      beforeRemove(file, fileList) {
        return this.$confirm(`确定移除 ${ file.name }？`);
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
