let index = {
    init: function(){
        $("#register").on("click", ()=>{
            this.save();
        });
    },

    save: function(){
        let data={
           username : $("#username").val(),
           password : $("#password").val(),
           nickname : $("#nickname").val()
        }
        if(!data.username.includes("@", 1) || data.password == "" || data.nickname == ""){
            alert("정보를 다시 확인해주세요");
            return;
        }
        // ajax 호출 시 default: 비동기 호출
        // ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 INSERT 요청
        $.ajax({
                type : "POST",       //INSERT 할꺼고
                url : "/api/register",   // user 테이블 사용할꺼
                data : JSON.stringify(data),  // http body 데이터
                contentType : "application/json; charset=utf-8", // body 데이터가 어떤 타입인지
            }).done(function(response){
                alert(Object.values(response)[0]);
                location.href = "/";

            }).fail(function(error){
            if(error.status == 500){
                alert(error.responseText);
            }
            if(error.status == 400){
                alert(Object.values(error.responseJSON)[0]);
            }
        });
    }
}

index.init();