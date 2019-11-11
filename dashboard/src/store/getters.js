// https://vuex.vuejs.org/en/getters.html

export default {
  getAccessToken (state) {
    return state.accessToken
  },
  getRole (state) {
    return state.role
  },
  getIsLogin (state) {
    return state.isLogin
  },
  getLanguages (state) {
    return state.languages
  },
  getPosts (state) {
    return state.posts
  },
  getUsers (state) {
    return state.users
  }
}
