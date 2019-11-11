import axios from 'axios'
import router from '../../router';

const SERVER_CONFIG = require("../../../serverconfig.json");
const BASE_URL = SERVER_CONFIG.SERVER_BASE_URL;

const state = {
    login: false,
    loginError: false,
    userInfo: null,
    accessToken: null,
    refreshToken: null
}

const getters = {
    isLogin(state) {
        return state.login;
    },
    isLoginError(state) {
        return state.loginError;
    },
    getUserInfo(state) {
        return state.userInfo;
    },
    getAccessToken(state) {
        return state.accessToken;  
    },
    getRefreshToken(state) {
        return state.refreshToken;
    },
    getLocalAccessToken() {
        return localStorage.getItem("sprite_access_token");
    },
    getLocalRefreshToken() {
        return localStorage.getItem("sprite_refresh_token");
    }
}

const actions = {
    async doLogin({ dispatch, commit }, signObject) {
        let result;
        await axios.post(BASE_URL + "/jwt/create", signObject)
        .then(response => {
            result = response.data;
        });

        if (result[0] != '') {
            let accessToken = result[0];
            let refreshToken = result[1];

            commit('setAccessToken', accessToken);
            commit('setRefreshToken', refreshToken);

            let memberInfo;
            await dispatch('getMemberInfo').then(response => {
                memberInfo = response;
            });

            if (memberInfo == true) {
                router.push("/");
            }
            else {
                alert("잠시후 다시 시도해주세요");
            }
        } else {
            alert("이메일과 비밀번호를 확인해주세요");
        }
    },
    async getMemberInfo({ getters, commit }) {
        let token = getters['getAccessToken'];
        if (token == null) {
            token = getters['getLocalAccessToken'];
            if (token != null)
                commit('setAccessToken', token);
        }

        let config = {
            headers: {
                "access-token": token
            }
        };
        if (token != null) {
            let ret = null;
            await axios.post(BASE_URL + "/jwt/auth", {}, config)
            .then(response => {
                ret = response.data;
            })
            .catch(error => {
                if (error.response.status == 404) {
                    localStorage.removeItem('sprite_access_token');
                    // 404 error
                }
            });

            if (ret != null) {
                commit('setLogin', true);
                commit('setLoginError', false);
                commit('setUserInfo', ret);

                return new Promise(function (resolve, reject) {
                    resolve(true);
                });
            }
            else { //access_token이 끝났을때 refresh_token 보내기
                commit('removeAccessToken'); //기존의 access_token 비우기

                let refreshToken = getters['getRefreshToken'];
                if (refreshToken == null) {
                    refreshToken = getters['getLocalRefreshToken'];
                    if (refreshToken != null)
                        commit('setRefreshToken', refreshToken);
                }
                let refreshConfig = {
                    headers: {
                        "refresh-token": refreshToken
                    }
                }

                let refreshResult;
                // refresh token 송신
                axios.post(BASE_URL + "/jwt/refresh", {}, refreshConfig)
                .then(response => {
                    if (response.data != false) {
                        refreshResult = response.data;
                        commit('setAccessToken', refreshResult);

                        let isOk = {
                            headers: {
                                "access-token": getters['getAccessToken']
                            }
                        }

                        let result = null;
                        axios.post(BASE_URL + "/jwt/auth", {}, isOk)
                        .then(response => {
                            result = response.data;
                            commit('setLogin', true);
                            commit('setLoginError', false);
                            commit('setUserInfo', result);
                            
                            return new Promise(function (resolve, reject) {
                                resolve(true);
                            });
                        })
                    } else {
                        commit('setLogin', false);
                        commit('setLoginError', false);
                        commit('setUserInfo', null);
                        localStorage.clear();

                        return new Promise(function (resolve, reject) {
                            resolve(false);
                        });
                    }
                })
                .catch(error => {
                    if (error.response.status == 404) {
                        // 404 error
                        localStorage.clear();
                        window.location.href = BASE_URL;
                    }
                })

            }
        }
    },
    doLogout({ commit }) {
        commit('setLogin', false);
        commit('setLoginError', false);
        commit('setUserInfo', null);
        localStorage.clear();
    }
}

const mutations = {
    setLogin(state, status) {
        state.login = status;
    },
    setLoginError(state, status) {
        state.loginError = status;
    },
    setUserInfo(state, payload) {
        state.userInfo = payload;
    },
    setAccessToken(state, token) {
        state.accessToken = token;
        localStorage.setItem('sprite_access_token', token);
    },
    setRefreshToken(state, token) {
        state.refreshToken = token;
        localStorage.setItem('sprite_refresh_token', token);
    },
    removeAccessToken() {
        localStorage.removeItem('sprite_access_token');
    },
    removeRefreshToken() {
        localStorage.removeItem('sprite_refresh_token');
    }
}

export default {
    namespaced: true,
    state,
    getters,
    actions,
    mutations
}