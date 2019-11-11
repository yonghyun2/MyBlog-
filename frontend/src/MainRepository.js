import store from './store/index';

const SERVER_CONFIG = require("../serverconfig.json");
const BASE_URL = SERVER_CONFIG.SERVER_BASE_URL;

export default {
    loginManager: {
        async loginProcess(signObject) {
            await store.dispatch('login/doLogin', signObject);
            if (store.getters['login/isLogin'] == true)
            {
                store.dispatch('ui/closeLoginDialog');
            }
        },
        logoutProcess() {
            store.dispatch('login/doLogout');
            window.location.href=BASE_URL;
        },
        getMemberInfo() {
            return store.dispatch('login/getMemberInfo');
        },
        getAccessToken() {
            let token = store.getters['login/getAccessToken'];

            if (token != null)
            {
                return token;
            }
            else 
            {
                return store.getters['login/getLocalAccessToken'];
            }
        },
        getRefreshToken() {
            let token = store.getters['login/getRefreshToken'];

            if (token != null)
            {
                return token;
            }
            else 
            {
                return store.getters['login/getLocalRefreshToken'];
            }
        },
        getCurrentUser() {
            return store.getters['login/getUserInfo'];
        }
    },
    uiManager: {
        openLoginDialog() {
            store.dispatch("ui/openLoginDialog");
        },
        closeLoginDialog() {
            store.dispatch("ui/closeLoginDialog");
        },
        updateLoginDialog(flag) {
            store.dispatch("ui/updateLoginDialog", flag);
        },
        getLoginDialogStatus() {
            return store.getters['ui/getLoginDialogStatus'];
        },
        openJoinDialog() {
            store.dispatch("ui/openJoinDialog");
        },
        closeJoinDialog() {
            store.dispatch("ui/closeJoinDialog");
        },
        updateJoinDialog(flag) {
            store.dispatch("ui/updateJoinDialog", flag);
        },
        getJoinDialogFlag() {
            return store.getters['ui/getJoinDialogStatus'];
        }
    },
    languageManager: {
        getAllLanguages() {
            store.dispatch('language/getAllLanguages');
        },
        getLanguages() {
            return store.getters['language/getLanguageList'];
        }
    },
    postManager: {
        getAllPosts() {
            store.dispatch('post/getAllPosts');
        },
        getPosts() {
            return store.getters['post/getPostList'];
        }
    }
}