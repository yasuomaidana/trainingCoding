const express = require('express');
const cors = require('cors');

const app = express();
const whitelist = ['http://localhost:4200','http://localhost:3000'];
var corsOptionsDelegate = (req,callback)=>{
    var corsOptions;
    if (whitelist.indexOf(req.header('Origin'))!=-1){corsOptions = {origin:true};}
    else{corsOptions={origin:false};}
    callback(null,corsOptions);
}

app.use(function(req, res, next) {
    res.header("Access-Control-Allow-Origin", whitelist); // update to match the domain you will make the request from
    res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
    next();
  });

exports.cors = cors();
exports.corsWithOptions = cors(corsOptionsDelegate);