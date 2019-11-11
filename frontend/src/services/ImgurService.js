import axios from 'axios'

const APIKEY = require('../../ApiKey.json');

export default {
  getImgUrl(file) {
    var form = new FormData();
    form.append("image", file)
    var settings = {
      "url": "https://api.imgur.com/3/image",
      "method": "POST",
      "timeout": 0,
      "headers": {
        "Authorization": "Client-ID " + APIKEY.imgurClientID
      },
      "processData": false,
      "mimeType": "multipart/form-data",
      "contentType": false,
      "data": form
    }
    axios(settings).then(function (response) {
      console.log(response.data);
    }).catch(function (error) {
      console.log(error);
    })
  }
}
