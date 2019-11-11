<template>
  <v-layout column px-4>
    <v-flex v-for="i in repositories.length > l ? l : repositories.length" v-bind:key="i">
      <v-divider v-if="i === 1"></v-divider>
      <Repository
        :repos="repositories[i - 1]"
        :id="i"
        :repo_url="repositories[i - 1].http_url_to_repo"
      ></Repository>
      <v-divider></v-divider>
    </v-flex>
    <v-flex xs12 text-xs-center round my-5 v-if="more">
      <v-btn color="info" dark v-on:click="loadMorerepositories">
        <v-icon size="25" class="mr-2">fa-plus</v-icon>더 보기
      </v-btn>
    </v-flex>
    <v-flex hidden-xs-only>
      <canvas id="projectCommit" width="300" height="150"></canvas>
    </v-flex>
  </v-layout>
</template>

<script>
import Repository from "../components/Repository";
import GitlabService from "../services/GitlabService";
import Chart from "chart.js/dist/Chart";
import ssafyAPI from "../services/ssafyAPI.js";

export default {
  name: "RepositoryList",
  props: {
    limits: { type: Number, default: 1 },
    loadMore: { type: Boolean, default: true }
  },
  data() {
    return {
      repositories: [],
      chartData: [],
      l: this.limits,
      more: this.loadMore
    };
  },
  components: {
    Repository
  },
  mounted() {
    this.getGitlabRepos("hackurity01");
    this.drawProjectChart();
  },
  methods: {
    async getGitlabRepos(userName) {
      const response = await GitlabService.getRepos(userName);
      if (response.status !== 200) {
        return;
      }

      this.repositories = response.data;
    },
    loadMorerepositories() {
      this.more = false
      this.l = this.repositories.length + 1
    },
    async drawProjectChart() {
      var numberOfCommit = (await ssafyAPI.getNumberOfCommits()).data.statistics.commit_count;
      var pages = Math.ceil(numberOfCommit / 100);
      var tempChartdata = [];
      for (var i = 1; i <= pages; i++)
      {
        tempChartdata = ssafyAPI.getChartData(tempChartdata, (await ssafyAPI.getCommitsPerPage(i)).data);
      }

      for (var item in tempChartdata)
      {
        this.chartData.push(tempChartdata[item]);
      }
      
      if(document.getElementById("projectCommit") != null) {
        var ctx = document.getElementById("projectCommit").getContext("2d");
        var chart = new Chart(ctx, {
          type: "line",
          data: {
            labels: [
              "7.8",
              "7.9",
              "7.10",
              "7.11",
              "7.12",
              "7.13",
              "7.14",
              "7.15",
              "7.16",
              "7.17",
              "7.18",
              "7.19"
            ],
            datasets: this.chartData
          },
          options: {
            title: {
              display: true,
              position: "top",
              text: "Committed Count in Web Project(Develop Branch" + " Commit Count : " + numberOfCommit + ")",
              fontSize: "20"
            }
          }
        });
      }
    }
  }
};
</script>
