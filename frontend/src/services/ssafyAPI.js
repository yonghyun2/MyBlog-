"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
exports.__esModule = true;
var axios_1 = __importDefault(require("axios"));
var ApiKey_json_1 = __importDefault(require("../../ApiKey.json"));
var BASE_URL = "https://lab.ssafy.com/api/v4";
var PRIVATE_KEY = ApiKey_json_1["default"].SSAFYGitLab.privateKey;
var PROJECT_ID = ApiKey_json_1["default"].SSAFYGitLab.projectID;
exports["default"] = {
    getCommitsPerPage: function (page) {
        return axios_1["default"]
            .create({
            baseURL: BASE_URL,
            withCredentials: false,
            headers: {
                Accept: "application/json",
                "Content-Type": "application/json",
                "PRIVATE-TOKEN": PRIVATE_KEY
            }
        })
            .get("/projects/" + PROJECT_ID + "/repository/commits?page=" + page + "&per_page=100");
    },
    getRandomColor: function () {
        var letters = "0123456789ABCDEF".split("");
        var color = "#";
        for (var i = 0; i < 6; i++) {
            color += letters[Math.floor(Math.random() * 16)];
        }
        return color;
    },
    getChartData: function (dataset, commits) {
        var Chartdata = {};
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
            if (dataset[item] == undefined) {
                dataset[item] = {
                    label: item,
                    data: Chartdata[item],
                    borderColor: this.getRandomColor(),
                    fill: false
                };
            }
            else {
                var length = dataset[item].data.length;
                for (var i = 0; i < length; i++) {
                    dataset[item].data[i] += Chartdata[item][i];
                }
            }
        }
        return dataset;
    },
    getNumberOfCommits: function () {
        return axios_1["default"]
            .create({
            baseURL: BASE_URL,
            withCredentials: false,
            headers: {
                Accept: "application/json",
                "Content-Type": "application/json",
                "PRIVATE-TOKEN": PRIVATE_KEY
            }
        })
            .get("/projects/" + PROJECT_ID + "?statistics=true");
    }
};
