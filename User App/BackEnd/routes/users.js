var express = require('express');
var router = express.Router();
var cors = require('./cors');
var User = require('../models/user');

router.use(express.json());
router.options(cors.cors,(req,resp)=>{
  resp.sendStatus(200);
});


/* GET users listing. */
router.options('*', cors.cors, (req, res) => { res.sendStatus(200); } )

router.get('/',cors.cors, (req,res,next) => {
    User.find({})
    .then(users=>{
        res.status = 200;
        res.setHeader("Content-Type",'aplication/json');
        res.json(users); 
    },err=>{next(err);})
    .catch(err=>{next(err);});
});

router.post('/signup',cors.cors,(req, res, next) => {
  //Creates a new promotion
  User.create(req.body)
  .then(user=>{
      res.status = 200;
      res.setHeader('Content-Type','application/json');
      res.json(user);
  },err=>next(err))
  .catch(err=>next(err));
});
router.route('/:userId')
.delete(cors.cors,(req , res,next)=>{
    User.findByIdAndRemove(req.params.userId)
    .then(resp=>{
      res.status = 200;
      res.setHeader("Content-Type","application/json");
      res.json(resp); 
    },err=>{next(err);}).catch(err=>{next(err);}) 
  })
.put(cors.cors,(req,res,next)=>{
    console.log(req.body);
    console.log(req.params.userId);
    User.findByIdAndUpdate(
      req.params.userId,
      {$set:req.body},{new:true,useFindAndModify:false})
      .then(user=>{
        res.status = 200;
        res.setHeader("Content-Type","application/json");
        res.json(user);
      },
      err=>next(err)).catch(err=>next(err));
    });
module.exports = router;
