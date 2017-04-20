RegisterModule.controller('RegisterController',['RegisterService',function
(RegisterService){


var me = this;
me.user = {};
me.message="";
me.register=function(){
 RegisterService.register(me.user).then(
                    function(data) {
                        me.message = 'Thanks' + data.firstName + 'for' + data.responseMessage;
                        console.log(response);
                    },
                    function(error) {
                        console.log(error);
                    }
                );
        }
}]);