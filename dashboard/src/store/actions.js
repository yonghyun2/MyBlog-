// https://vuex.vuejs.org/en/actions.html

import axios from 'axios'
import router from '../router/index'

const baseURL = 'http://52.78.3.193:9000'
const loginURL = baseURL.concat('/admin/login')
const authURL = baseURL.concat('/jwt/auth')
const languagesURL = baseURL.concat('/rest/languages')
const deleteLanguagesURL = baseURL.concat('/rest/language')
const deletePostURL = baseURL.concat('/rest/Post')
const postURL = baseURL.concat('/rest/Post')
const userURL = baseURL.concat('/admin/memberList')
const upRoleURL = baseURL.concat('/admin/team')
const downRoleURL = baseURL.concat('/admin/visitor')
const deleteUserURL = baseURL.concat('/admin/getout')

export default {
  async login ({ commit }, signObj) {
    try {
      let response
      response = await axios.post(loginURL, signObj)
      const token = response.data
      if (token !== null) {
        commit('setAccessToken', token)
        response = await axios.post(authURL, {}, { headers: { 'access-token': token } })
        commit('loginSuccess')
        commit('setRole', response.data.m_role)
        alert('로그인 성공!')
        router.push('/dashboard')
      } else {
        alert('이메일과 비밀번호를 확인해주세요')
        commit('loginfailORlogout')
      }
    } catch (e) {
      alert('이메일과 비밀번호를 확인해주세요')
      commit('loginfailORlogout')
    }
  },
  logout ({ commit }) {
    alert('로그아웃이 되었습니다!')
    commit('loginfailORlogout')
    router.push('/')
  },
  async loadLanguages ({ commit }) {
    const languages = await axios.get(languagesURL)
    commit('setLanguages', languages.data)
  },
  async loadPosts ({ commit }) {
    const posts = await axios.get(postURL)
    commit('setPosts', posts.data)
  },
  async loadUsers ({ commit }) {
    const users = await axios.get(userURL)
    commit('setUsers', users.data)
  },
  async deleteLanguage ({ dispatch }, no) {
    await axios.delete(deleteLanguagesURL + '/' + no)
    dispatch('loadLanguages')
  },
  async deletePost ({ dispatch }, no) {
    await axios.delete(deletePostURL + '/' + no)
    dispatch('loadPosts')
  },
  async upRole ({ dispatch }, email) {
    await axios.get(upRoleURL + '/' + email)
    dispatch('loadUsers')
  },
  async downRole ({ dispatch }, email) {
    await axios.get(downRoleURL + '/' + email)
    dispatch('loadUsers')
  },
  async deleteUser ({ dispatch }, email) {
    await axios.get(deleteUserURL + '/' + email)
    dispatch('loadUsers')
  }
}
