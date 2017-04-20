BasicModule.controller('BasicController',['BasicService','$scope',function
(BasicService,$scope){


var me = this;


//this.greeting = 'this message is coming from angular controller';

BasicService.getGreeting().then(

    function(greeting){
me.greeting=greeting;

    },
    function(error){
        console.log(error);
    }
);

}]);