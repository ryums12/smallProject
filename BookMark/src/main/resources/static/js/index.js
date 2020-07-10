window.onload = () => {
    const container = document.getElementById('container-list');

    if (container) {
        const data = {
            "page":0,
            "tag":"html"
        };

        fncGetMarkList(data);
    }
};


const fncGetMarkList = data => {

    $.ajax({
        type : "GET"				//"POST", "GET"
        , async : false				//true, false
        , url : "/mark/get.do"		//Request URL
        , dataType : "json"			//전송받을 데이터의 타입
        , timeout : 20000			//제한시간 지정
        , cache : false				//true, false
        , data : data				//전송할 데이터
        , contentType: "application/x-www-form-urlencoded; charset=UTF-8"
        , error : () => {
            alert("통신 오류가 발생 하였습니다. 잠시 후 다시 시도해 주세요");
            location.reload();
        }
        , success : response => {
            alert("성공했습니다");
            console.log(response);
        }
    });
};