<template>
  <v-container
    fill-height
    fluid
    grid-list-xl
  >
    <v-layout wrap>
      <v-flex
        md12
        lg6
      >
        <div id="embed-api-auth-container"/>
        <div id="chart-container"/>
        <div id="view-selector-container"/>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
export default {
  created () {
    (function (w, d, s, g, js, fs) {
      g = w.gapi || (w.gapi = {}); g.analytics = { q: [], ready: function (f) { this.q.push(f) } }
      js = d.createElement(s); fs = d.getElementsByTagName(s)[0]
      js.src = 'https://apis.google.com/js/platform.js'
      fs.parentNode.insertBefore(js, fs); js.onload = function () { g.load('analytics') }
      g.analytics.ready(function () {
        g.analytics.auth.authorize({
          container: 'embed-api-auth-container',
          clientid: '922197027793-c8es291unh8ve739i9cfcopk6f2dhlss.apps.googleusercontent.com'
        })
        var viewSelector = new g.analytics.ViewSelector({
          container: 'view-selector-container'
        })
        viewSelector.execute()
        var dataChart = new g.analytics.googleCharts.DataChart({
          query: {
            metrics: 'ga:sessions',
            dimensions: 'ga:date',
            'start-date': '30daysAgo',
            'end-date': 'yesterday'
          },
          chart: {
            container: 'chart-container',
            type: 'LINE',
            options: {
              width: '100%'
            }
          }
        })
        viewSelector.on('change', function (ids) {
          dataChart.set({ query: { ids: ids } }).execute()
        })
      })
    }(window, document, 'script'))
  }
}
</script>
