import Api from '../services/Api'

const BASE_URL = 'https://gitlab.com/api/v4'

export default {
  getRepos(userName) {
    return Api(BASE_URL).get(`/users/${userName}/projects`)
  },
  getCommits(fullName) {
    let d = new Date()
    d.setYear(d.getFullYear() - 1)
    return Api(BASE_URL).get(`/projects/${fullName}/repository/commits?since=${d.toISOString()}`)
  },
  getRecentCommit(commits) {
    var commitData = new Array(12);
    for (var i = 0; i < 12; i++) {
      commitData[i] = 0;
    }

    for (var item in commits) {
      var diff = (new Date() - new Date(commits[item].committed_date)) / 2592000000;
      diff = parseInt(diff);
      commitData[diff] = commitData[diff] + 1;
    }
    commitData.reverse();

    return commitData;
  }
}
