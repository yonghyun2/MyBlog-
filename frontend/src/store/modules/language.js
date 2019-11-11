import API from '../../services/Api'

const SERVER_CONFIG = require("../../../serverconfig.json");
const BASE_URL = SERVER_CONFIG.SERVER_BASE_URL;

const state = {
    languageList: []
}

const getters = {
    getLanguageList(state) {
        return state.languageList;
    }
}

const actions = {
    getAllLanguages({ commit }) {
        API(BASE_URL)
            .get("/rest/languages")
            .then(response => {
                let languages = response.data;
                
                for (var language of languages)
                {
                    var showdown  = require('showdown'),
                    converter = new showdown.Converter(),
                    text = language.l_body,
                    html = converter.makeHtml(text);
                    language.l_preview = html;
                }

                commit('setLanguages', languages);
            })
    }
}

const mutations = {
    setLanguages(state, payload) {
        state.languageList = payload;
    }
}

export default {
    namespaced: true,
    state,
    getters,
    actions,
    mutations
}