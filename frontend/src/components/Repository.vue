<template>
  <div class="py-3">
    <a class="repourl" v-bind:href="repo_url">
      <v-layout>
        <v-flex xs8>
          <h2 class="font-weight-regular repopath">{{repos.path_with_namespace}}</h2>
          <p
            class="subheading mb-1 grey--text text--darken-1 font-weight-light"
          >{{repos.namespace.name}}</p>
        </v-flex>
        <v-flex hidden-xs-only style="float:right;">
          <canvas v-bind:id="'commitchart' + id" width="250" height="150"></canvas>
        </v-flex>
      </v-layout>
    </a>
  </div>
</template>

<script>
import GitlabService from "../services/GitlabService";
import Chart from "chart.js/dist/Chart";

export default {
  name: "Repository",
  props: {
    repos: { type: null },
    id: { type: Number },
    repo_url: { type: String }
  },
  data() {
    return {
      stats: {}
    };
  },
  mounted() {
    this.drawStatGraph();
  },
  methods: {
    async drawStatGraph() {
      await GitlabService.getCommits(this.repos.id).then(result => {
        this.commits = result.data;
      });
      ////////////chart
      var commitData = GitlabService.getRecentCommit(this.commits);
      
      if (document.getElementById("commitchart" + this.id) != null) {
        var ctx = document
          .getElementById("commitchart" + this.id)
          .getContext("2d");
        var chart = new Chart(ctx, {
          type: "line",
          data: {
            labels: ["", "", "", "", "", "", "", "", "", "", "", ""],
            datasets: [
              {
                label: "Commit",
                backgroundColor: "rgb(255, 99, 132)",
                borderColor: "rgb(255, 99, 132)",
                data: commitData
              }
            ]
          },
          options: {}
        });
      }
    }
  }
};
</script>

<style>
.repopath {
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}
.repourl {
  text-decoration: none;
  color: black;
}
</style>
