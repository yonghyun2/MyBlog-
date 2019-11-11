import API from '../../services/Api'

const SERVER_CONFIG = require("../../../serverconfig.json");
const BASE_URL = SERVER_CONFIG.SERVER_BASE_URL;

const state = {
    postList: []
}

const getters = {
    getPostList(state) {
        return state.postList;
    }
}

const actions = {
    getAllPosts({ commit }) {
        API(BASE_URL)
            .get("/rest/Post")
            .then(response => {
                commit('setPosts', response.data);
            });
    }
}

const mutations = {
    setPosts(state, payload) {
        state.postList = payload;
    }
}

export default {
    namespaced: true,
    state,
    getters,
    actions,
    mutations
}