import Vue from 'vue'
import Router from 'vue-router'
import HomePage from '../views/HomePage.vue'
import PostPage from '../views/PostPage.vue'
import LanguagePage from '../views/LanguagePage.vue'
import LoginPage from '../views/LoginPage.vue'
import LanguageWritePage from '../views/LanguageWritePage.vue'
import LanguageModifyPage from '../views/LanguageModifyPage.vue'
import PostWritePage from '../views/PostWritePage.vue'
import PostModifyPage from '../views/PostModifyPage.vue'
import RepositoryList from '../components/RepositoryList'
import MyPage from '../views/MyPage.vue'
import LanguageViewPage from '../views/LanguageViewPage'
import PostViewPage from '../views/PostViewPage'
import MainRepository from '../MainRepository'

Vue.use(Router)

const requireAuth = () => (to, from, next) => {
  if (MainRepository.loginManager.getAccessToken() == null) {
    alert('로그인 후 이용 가능한 서비스 입니다')
    return;
  }
  
  return next();
};

export default new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [{
      path: '/',
      name: 'home',
      component: HomePage
    },
    {
      path: '/post',
      name: 'post',
      component: PostPage,
      beforeEnter: requireAuth()
    },
    {
      path: '/language',
      name: 'language',
      component: LanguagePage,
      beforeEnter: requireAuth()
    },
    {
      path: '/login',
      name: 'login',
      component: LoginPage
    },
    {
      path: '/languagewrite',
      name: 'languagewrite',
      component: LanguageWritePage,
      beforeEnter: requireAuth()
    },
    {
      path: '/repository',
      name: 'repository',
      component: RepositoryList,
      beforeEnter: requireAuth()
    },
    {
      path: '/mypage',
      name: 'mypage',
      component: MyPage,
      beforeEnter: requireAuth()
    },
    {
      path: '/language/:num',
      name: 'languageviewpage',
      component: LanguageViewPage,
      beforeEnter: requireAuth(),
      props: true
    },
    {
      path: '/languagemodify',
      name: 'languagemodify',
      component: LanguageModifyPage,
      beforeEnter: requireAuth(),
      props: true
    },
    {
      path: '/postwrite',
      name: 'postwrite',
      component: PostWritePage,
      beforeEnter: requireAuth()
    },
    {
      path: '/post/:num',
      name: 'postviewpage',
      component: PostViewPage,
      beforeEnter: requireAuth(),
      props: true
    },
    {
      path: '/postmodify',
      name: 'postmodify',
      component: PostModifyPage,
      beforeEnter: requireAuth(),
      props: true
    }
  ]
})
