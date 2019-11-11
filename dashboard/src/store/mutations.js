// https://vuex.vuejs.org/en/mutations.html

export default {
  setAccessToken (state, token) {
    state.accessToken = token
  },
  setRole (state, role) {
    state.role = role
  },
  loginSuccess (state) {
    state.isLogin = true
  },
  loginfailORlogout (state) {
    state.isLogin = false
    state.role = ''
    state.accessToken = ''
    state.languages = []
    state.posts = []
    state.users = []
  },
  setLanguages (state, languages) {
    state.languages = languages
  },
  setPosts (state, posts) {
    state.posts = posts
  },
  setUsers (state, users) {
    state.users = users
  }
}
