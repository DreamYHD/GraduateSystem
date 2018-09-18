<template>
  <div>
    <div style="width: 40% ;float: left">
      <el-table
        :data="tableDataDisscuss"
        style="width: 100%">
        <el-table-column
          prop='studentname'
          fixed align="center"
          label="姓名"
          width="180">
        </el-table-column>
        <el-table-column
          prop='title'
          fixed align="center"
          label="题目"
          width="180">
        </el-table-column>

        <el-table-column label="操作" fixed align="center">
          <template slot-scope="scope">
            <el-button
              size="mini"
              @click="handleEdit(scope.$index, scope.row)">交流
            </el-button>
            <el-button
              size="mini"
              type="success"
              @click="handleDelete(scope.$index, scope.row)">指定评阅教师
            </el-button>
          </template>
        </el-table-column>
      </el-table>

      <el-dialog title="输入老师编号" :visible.sync="dialogFormVisible">
        <el-form :model="form">
          <el-form-item label="老师编号1" :label-width="formLabelWidth">
            <el-input v-model="form.no1" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="老师编号2" :label-width="formLabelWidth">
            <el-input v-model="form.no2" auto-complete="off"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="success" @click="sendTS">确 定</el-button>
        </div>
      </el-dialog>
    </div>
    <div id="chatdiv" style="width: 60%;float:right;display: none">
      <h4 style="text-align: center"></h4>
      <el-card class="box-card" style="margin-top: 40px; width: 90%;margin-left: 50px;margin-right: 50px; height: 600px">
        <div slot="header" class="clearfix">
          <span>聊天界面</span>
          <el-button style="float: right; padding: 3px 0" type="text" @click="backChat">退出聊天</el-button>
        </div>
        <div v-for="o in discussList" :key="o" class="text item">
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
        <el-badge v-model=filenum class="item">
          <el-button size="medium" @click="downloadfile">待下载文件</el-button>
        </el-badge>
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
        <el-button type="primary" round style="margin-left: 800px;width: 100px" @click="sendmessage">发送</el-button>


      </div>

    </div>
  </div>
</template>

<script>
  import store from '../../vuex/store.js';
  import Axios from 'axios'

  export default {
    store,
    mounted() {

      setInterval( this.findByTeachername, 1000)
    },
    name: 'TeacherDiscuss',
    data() {
      return {
        form: {
          no1: '',
          no2: ''
        },
        filenum: '',
        selectrow: '',
        fileList: [],
        discussList: [],
        textarea: "",
        teachername11: '',
        teachername22: '',
        formLabelWidth: '80px',
        tableDataDisscuss: [],
        dialogFormVisible: false
      }
    },

    methods: {
      updataFile(fileList,str){
        {

          const api = 'http://localhost:3000/gtitle/update/' + this.tableDataDisscuss[this.selectrow].id;
          const params = new URLSearchParams();
          params.append('id', this.tableDataDisscuss[this.selectrow].id);
          params.append('description', this.tableDataDisscuss[this.selectrow].description);
          params.append('teachername', this.tableDataDisscuss[this.selectrow].teachername);
          console.log(this.tableDataDisscuss[this.selectrow].teachername);
          params.append('endtime', this.tableDataDisscuss[this.selectrow].endtime);
          console.log(this.tableDataDisscuss[this.selectrow].endtime);
          params.append('startime', this.tableDataDisscuss[this.selectrow].startime);
          params.append('isselect', this.tableDataDisscuss[this.selectrow].isselect);
          if (str === 0){
              params.append('filepath', fileList[0].url);
            this.filenum = 1;

          } else {
            params.append('filepath', "");
            this.fileList = [];
            this.filenum = 0;
          }
          if (this.tableDataDisscuss[this.selectrow].discuss == null) {
            params.append('discuss', "");
          } else {
            params.append('discuss', this.tableDataDisscuss[this.selectrow].discuss);
          }
          params.append('isthrough', '是');
          if (this.tableDataDisscuss[this.selectrow].studentname === null) {
            params.append('studentname', "");

          } else {
            params.append('studentname', this.tableDataDisscuss[this.selectrow].studentname);
          }
          if (this.tableDataDisscuss[this.selectrow].teacher1name === null) {
            params.append('teacher1name', "");

          } else {
            params.append('teacher1name', this.tableDataDisscuss[this.selectrow].teacher1name);
          }
          if (this.tableDataDisscuss[this.selectrow].studentname === null) {
            params.append('teacher2name', "");

          } else {
            params.append('teacher2name', this.tableDataDisscuss[this.selectrow].teacher2name);

          }
          params.append('title', this.tableDataDisscuss[this.selectrow].title);
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
        }
      },
      handleSuccess(response, file, fileList) {
        this.updataFile(fileList,0);
      },
      downloadfile(){
        console.log(this.tableDataDisscuss[this.selectrow].filepath);
        window.open(this.tableDataDisscuss[this.selectrow].filepath, '_blank');
        this.updataFile("",1)
      },
      sendmessage() {
        //textarea
        const pushId = this.tableDataDisscuss[this.selectrow].id;
        console.log(pushId);
        const api = 'http://localhost:3000/gtitle/update/' + pushId;
        const params = new URLSearchParams();
        params.append('id', this.tableDataDisscuss[this.selectrow].id);
        params.append('description', this.tableDataDisscuss[this.selectrow].description);
        params.append('teachername', this.tableDataDisscuss[this.selectrow].teachername);
        console.log(this.tableDataDisscuss[this.selectrow].teachername);
        params.append('endtime', this.tableDataDisscuss[this.selectrow].endtime);
        console.log(this.tableDataDisscuss[this.selectrow].endtime);
        params.append('startime', this.tableDataDisscuss[this.selectrow].startime);
        if (this.tableDataDisscuss[this.selectrow].filepath == null) {
          params.append('filepath', "");
        } else {
          params.append('filepath', this.tableDataDisscuss[this.selectrow].filepath);
        }
        if (this.tableDataDisscuss[this.selectrow].discuss == null) {
          params.append('discuss', "");
        } else {
          params.append('discuss', this.tableDataDisscuss[this.selectrow].discuss + this.$store.state.id + ": " + this.textarea + "|");
        }
        params.append('isselect', "是");
        params.append('isthrough', '是');
        if (this.tableDataDisscuss[this.selectrow].studentname === null) {
          params.append('studentname', "");

        } else {
          params.append('studentname', this.tableDataDisscuss[this.selectrow].studentname);
        }
        if (this.tableDataDisscuss[this.selectrow].teacher1name === null) {
          params.append('teacher1name', "");

        } else {
          params.append('teacher1name', this.tableDataDisscuss[this.selectrow].teacher1name);
        }
        if (this.tableDataDisscuss[this.selectrow].studentname === null) {
          params.append('teacher2name', "");

        } else {
          params.append('teacher2name', this.tableDataDisscuss[this.selectrow].teacher2name);

        }
        params.append('title', this.tableDataDisscuss[this.selectrow].title);
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
            self.findByTeachername();
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
      findByTeachername() {
        const api1 = 'http://localhost:3000/gtitle/findbyts/' + this.$store.state.id + '/是';
        console.log(api1);
        const self = this;
        Axios.get(api1, {
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
          }
        })
          .then(function (response) {
            if (self.selectrow !== '' && self.selectrow !== null) {

              self.discussList = (response.data.data[self.selectrow].discuss).toString().split("|");
              console.log((response.data.data[self.selectrow].discuss).split("|").length);
              if (response.data.data[self.selectrow].filepath!==null&&response.data.data[self.selectrow].filepath!==""){
                self.filenum = 1;
              }else {
                self.filenum = 0;
                self.fileList= [];
              }
            }

            console.log(response.data.data + "111111111111111111111111111111111");
            console.log((response.data.data[0].studentname));
            self.tableDataDisscuss = response.data.data;
          })
          .catch(function (error) {
            console.log(error);
          });
      },
      findTeachernameById(teacherid, str) {
        const self = this;
        const api1 = 'http://localhost:3000/teacher/findone/' + teacherid;
        console.log(api1);
        Axios.get(api1, {
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
          }
        })
          .then(function (response) {
            if (str === 1) {
              self.teachername11 = response.data.data.name;
              console.log(self.teachername11 + "teachername11");
            } else {
              self.teachername22 = response.data.data.name;
              console.log(self.teachername22 + "teachername22");

              console.log("selectrow=" + self.selectrow + "teacher1name" + self.teachername11 + "teacher2name" + self.teachername22);
              const api = 'http://localhost:3000/ts/add';
              const params = new URLSearchParams();
              params.append('gtname', self.tableDataDisscuss[self.selectrow].title);
              console.log(self.tableDataDisscuss[self.selectrow].title + "gtnam");
              params.append('studentname', self.tableDataDisscuss[self.selectrow].studentname);
              params.append('teachername1', self.teachername11);
              params.append('teachername2', self.teachername22);
              const self2 = self;
              console.log("---------");
              Axios.post(api, params, {
                headers: {
                  'Content-Type': 'application/x-www-form-urlencoded'
                }
              })
                .then(function (response) {
                  console.log(response);
                  self2.$message({
                    message: '指定成功',
                    center: true,
                    type: 'success'
                  });
                })
                .catch(function (error) {
                  self2.$message({
                    message: '指定失败',
                    center: true,
                    type: 'warning'
                  });
                  console.log(error);
                });

              self.dialogFormVisible = false;
            }
          })
          .catch(function (error) {
            console.log(error);
          });

      },
      finByStudentname(studentname) {

      },
      backChat() {
        document.getElementById("chatdiv").style.display = "none";
      },
      sendTS() {
        this.findTeachernameById(this.form.no1, 1);
        this.findTeachernameById(this.form.no2, 2);
      },
      handleEdit(index, row) {
        this.findByTeachername();
        console.log(index, row);
        this.selectrow = index;
        document.getElementById("chatdiv").style.display = "";


      },
      handleDelete(index, row) {
        this.dialogFormVisible = true;
        this.selectrow = index;
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
