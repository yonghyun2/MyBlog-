const state = {
    loginDialog: false,
    joinDialog: false
}

const getters = {
    getLoginDialogStatus(state) {
        return state.loginDialog;
    },
    getJoinDialogStatus(state) {
        return state.joinDialog;
    }
}

const actions = {
    openLoginDialog({ commit }) {
        commit("setLoginDialog", true);
    },
    closeLoginDialog({ commit }) {
        commit("setLoginDialog", false);
    },
    updateLoginDialog({ commit }, status) {
        commit("setLoginDialog", status);
    },
    openJoinDialog({ commit }) {
        commit("setJoinDialog", true);
    },
    closeJoinDialog({ commit }) {
        commit("setJoinDialog", false);
    },
    updateJoinDialog({ commit }, status) {
        commit("setJoinDialog", status);
    }
}

const mutations = {
    setLoginDialog(state, status) {
        state.loginDialog = status;
    },
    setJoinDialog(state, status) {
        state.joinDialog = status;
    }
}

export default {
    namespaced: true,
    state,
    getters,
    actions,
    mutations
}