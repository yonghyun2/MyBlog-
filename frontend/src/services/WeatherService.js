import Api from './Api'
const APIKEY = require('../../ApiKey.json');

const BASE_URL = 'https://api.openweathermap.org/data/2.5/weather?q=Seoul&appid=' + APIKEY.apikey

export default {
  getWeatherInfo() {
    return Api(BASE_URL).get()
  }
}
