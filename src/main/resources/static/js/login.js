let index = {
    init: function(){
        $("#login").on("click", ()=>{
            this.login();
        });
    },

    login: function(){
        let data={
           username : $("#username").val(),
           password : $("#password").val()
        }
        console.log(data)
        if(!data.username.includes("@", 1) || data.password == ""){
            alert("정보를 다시 확인해주세요");
        }
    }
}

index.init();