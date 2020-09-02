window.onload = () => {

    const main = document.getElementById('main');
    const unusedList = document.getElementById('unused-list');

    if (main) {
        fncGetMarkList(0);
    } else if (unusedList) {
        fncGetUnusedMarkList(0);
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

function fncSetPagination(listSize, data, pagination, fnc) {

    const size      = data.size,
          page      = data.page,
          maxPage   = listSize % size == 0 ? parseInt(listSize / size) : parseInt(listSize / size + 1),
          startPage = parseInt(page / size) * 10,
          endPage   = startPage + 9 < maxPage ? startPage + 9 : maxPage;

    let pageInnerHtml = '';

    pageInnerHtml += "<li class='page-item'>"
                        + "<a class='page-link' href='#' "
                           + "onclick='fncGoToPrevPage(" + page + "," + fnc.name + ")'>Previous</a>"
                   + "</li>";

    for (let i = startPage; i < endPage; i++) {
        const aClass = page == i ? "page-item active" : "page-item";
        pageInnerHtml += "<li class='" + aClass + "'>"
                            + "<a href='#' class='page-link' "
                               + "onclick='" + fnc.name + "(" + i + ")'>" + (i + 1)
                            + "</a>"
                       + "</li>";
    }

    pageInnerHtml += "<li class='page-item'>"
                        + "<a class='page-link' href='#' "
                           + "onclick='fncGoToNextPage(" + page + "," + maxPage + "," + fnc.name + ")'>Next"
                        + "</a>"
                   + "</li>";

    pagination.innerHTML = pageInnerHtml;
}

function fncGoToPrevPage(page, fnc) {
    if (page == 0) {
        alert("첫 번째 페이지입니다.");
    } else {
        fnc(page - 1);
    }
}

function fncGoToNextPage(page, maxPage, fnc) {
    if ((page + 1) == maxPage) {
        alert("마지막 페이지입니다.");
    } else {
        fnc(page + 1);
    }
}