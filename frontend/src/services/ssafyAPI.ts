import axios, { AxiosInstance } from "axios";
import APIKEY from "../../ApiKey.json";

const BASE_URL = "https://lab.ssafy.com/api/v4";
const PRIVATE_KEY = APIKEY.SSAFYGitLab.privateKey;
const PROJECT_ID = APIKEY.SSAFYGitLab.projectID;

interface ICommitData {
  id: string;
  short_id: string;
  title: string;
  author_name: string;
  author_email: string;
  authored_date: string;
  committer_name: string;
  committer_email: string;
  committed_date: string;
  created_at: string;
  message: string;
  parent_ids: Array<String>;
}

export default {
  getCommitsPerPage(page: number): Promise<AxiosInstance> {
    return axios
      .create({
        baseURL: BASE_URL,
        withCredentials: false,
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json",
          "PRIVATE-TOKEN": PRIVATE_KEY
        }
      })
      .get(`/projects/${PROJECT_ID}/repository/commits?page=` + page + `&per_page=100`);
  },
  getRandomColor(): string {
    let letters: Array<string> = "0123456789ABCDEF".split("");
    let color: string = "#";
    for (let i: number = 0; i < 6; i++) {
      color += letters[Math.floor(Math.random() * 16)];
    }
    return color;
  },
  getChartData(dataset: Array<any>, commits: ICommitData) {
    var Chartdata: Object = {};

    for (var item in commits) {
      if (Chartdata[commits[item].committer_name] == undefined) {
        Chartdata[commits[item].committer_name] = new Array(12);
        for (var i = 0; i < 12; i++) {
          Chartdata[commits[item].committer_name][i] = 0;
        }
      }

      var day = new Date(commits[item].committed_date).getUTCDate() - 8;
      Chartdata[commits[item].committer_name][day] += 1;
    }

    for (var item in Chartdata) {
      if (dataset[item] == undefined)
      {
        dataset[item] = {
          label: item,
          data: Chartdata[item],
          borderColor: this.getRandomColor(),
          fill: false
        };
      }
      else
      {
        var length: number = dataset[item].data.length;

        for (var i = 0; i < length; i++)
        {
          dataset[item].data[i] += Chartdata[item][i];
        }
      }
    }

    return dataset;
  },
  getNumberOfCommits(): Promise<AxiosInstance> {
    return axios
      .create({
        baseURL: BASE_URL,
        withCredentials: false,
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json",
          "PRIVATE-TOKEN": PRIVATE_KEY
        }
      })
      .get(`/projects/${PROJECT_ID}?statistics=true`)
  }
};
