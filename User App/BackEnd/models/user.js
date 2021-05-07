var mongoose = require("mongoose");
var Schema = mongoose.Schema;

var User = new Schema({
    name:{
        type:String,
        required:true
    },
    lastname:{
        type:String,
        required:true
    },
    phone:{
        type:Number,
        unique:true,
        required:true
    },
    email:{
        type:String,
        unique:true,
        required:true
    },
    company:{
        type:String,
        default:''
    }
});
module.exports = mongoose.model('User',User);