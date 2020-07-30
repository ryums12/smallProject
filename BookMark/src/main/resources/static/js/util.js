window.onload = () => {

    const main = document.getElementById('main');

    if(main) {
        fncGetMarkList(0,"");
    }

};

function fncAjax(url, data, fnc) {

    $.ajax({
        type: "GET"				    //"POST", "GET"
        , async: false				//true, false
        , url: url		            //Request URL
        , dataType: "json"			//전송받을 데이터의 타입
        , timeout: 20000			//제한시간 지정
        , cache: false				//true, false
        , data: data				//전송할 데이터
        , contentType: "application/x-www-form-urlencoded; charset=UTF-8"
        , error: () => {
            alert("통신 오류가 발생 하였습니다. 잠시 후 다시 시도해 주세요");
        }
        , success: (response) => {
            fnc(data, response);
        }
    });

}

function fncPreventInput(dom) {
    dom.onkeydown = e => e.preventDefault();
};