window.onload = () => {

    const main = document.getElementById('main');
    const unusedList = document.getElementById('unused-list');

    if (main) {
        fncGetMarkList(0, "");
    } else if (unusedList) {
        fncGetUnusedMarkList(0, "");
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

function fncGetPagination(listSize, size, page) {

    const maxPage   = listSize % size == 0 ? parseInt(listSize / size) : parseInt(listSize / size + 1)
        , startPage = parseInt(page / size) * 10
        , endPage   = startPage + 9 < maxPage ? startPage + 9 : maxPage;

    const data = {
        "maxPage"   : maxPage,
        "startPage" : startPage,
        "endPage"   : endPage
    };

    return data;
}

function fncGoToPrevPage(page, tag, fnc) {
    if (page == 0) {
        alert("첫 번째 페이지입니다.");
    } else {
        fnc(page - 1, tag);
    }
}

function fncGoToNextPage(page, maxPage, tag, fnc) {
    if ((page + 1) == maxPage) {
        alert("마지막 페이지입니다.");
    } else {
        fnc(page + 1, tag);
    }
}