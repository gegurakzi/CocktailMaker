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
        if(data.username == "" || data.password == ""){
            alert("정보를 다시 확인해주세요");
            return;
        }
        // ajax 호출 시 default: 비동기 호출
        // ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 INSERT 요청
        $.ajax({
            type : "POST",       //INSERT 할꺼고
            url : "/login",   // user 테이블 사용할꺼
            data : JSON.stringify(data),  // http body 데이터
            contentType : "application/json; charset=utf-8", // body 데이터가 어떤 타입인지
        }).done(function(response){
            console.log(response)
            if(response.status == 500){
                alert("something went wrong");
                return;
            }
            if(response == "OK"){
                location.href = "/";
            }
        }).fail(function(error){
            if(error.status == 500){
                alert(error.responseText);
            }
        });
    }
}

index.init();