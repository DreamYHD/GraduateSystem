import Vue from 'vue'
import Router from 'vue-router'
import Teacher from '../components/teacherPack/Teacher.vue'
import TeacherComment from '../components/teacherPack/TeacherComment'
import TeacherDiscuss from '../components/teacherPack/TeacherDiscuss'
import TeacherSignin from '../components/teacherPack/TeacherSignin'
import TeacherTopicSend from '../components/teacherPack/TeacherTopicSend'
import TeacherTopicShow from '../components/teacherPack/TeacherTopicShow'
import Login from '../components/Login.vue'
import Register from '../components/Register.vue'
import Administrator from '../components/adminPack/Administrator'
import Student from '../components/studentPack/Student'
import StudentComment from '../components/studentPack/StudentComment'
import StudentDissucss from '../components/studentPack/StudentDisscuss'
import StudentSignin from '../components/studentPack/StudentSignin'
import StudentTopicShow from '../components/studentPack/StudentTopicShow'


Vue.use(Router)

const routes =[
  { path: '/register', name: 'Register', component: Register},
  { path: '/login', name: 'Login', component: Login},
  { path: '/administrator', name: 'Administrator', component: Administrator,redirect:'',
    children: []

  },
  { path: '/student', name: 'Student', component: Student,redirect:'/studentSignin',
    children: [
      { path: '/studentComment', component: StudentComment,name:'StudentComment' },
      { path: '/studentDisscuss', component: StudentDissucss,name:'StudentDissucss' },
      { path: '/studentSignin', component: StudentSignin,name:'StudentSignin' },
      { path: '/studentTopicShow', component: StudentTopicShow,name:'StudentTopicShow' },
      { path: '*', redirect: '/studentSignin' }]
  },
  { path: '/teacher', name: 'Teacher', component: Teacher, redirect:'/teacherTopicSend',
    children: [
      { path: '/teacherComment', component: TeacherComment,name:'TeacherComment' },
      { path: '/teacherDiscuss', component: TeacherDiscuss,name:'TeacherDiscuss' },
      { path: '/teacherSignin', component: TeacherSignin,name:'TeacherSignin' },
      { path: '/teacherTopicSend', component: TeacherTopicSend,name:'TeacherTopicSend' },
      { path: '/teacherTopicShow', component: TeacherTopicShow,name:'TeacherTopicShow' },
      { path: '*', redirect: '/teacherTopicSend' }
    ]},
  { path: '*', redirect: '/login' }
  ]

//3.实例化VueRouter  注意：名字
const router = new Router({
  mode: 'history',   /*hash模式改为history*/
  routes // （缩写）相当于 routes: routes
})
export default router;
