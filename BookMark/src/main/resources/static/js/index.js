window.onload = () => {
    const containerList = document.getElementById('container-list');

    /** 목록 화면 */
    if (containerList) {
        // fncGetMarkList(0, "");
    }
};

const fncGetMarkList = (page, tag) => {

    const data = {
        "page": page,
        "tag": tag
    };

    $.ajax({
        type: "GET"				//"POST", "GET"
        , async: false				//true, false
        , url: "/mark/get.do"		//Request URL
        , dataType: "json"			//전송받을 데이터의 타입
        , timeout: 20000			//제한시간 지정
        , cache: false				//true, false
        , data: data				//전송할 데이터
        , contentType: "application/x-www-form-urlencoded; charset=UTF-8"
        , error: () => {
            alert("통신 오류가 발생 하였습니다. 잠시 후 다시 시도해 주세요");
        }
        , success: (response) => {
            const markList = response.markList
                , listSize = response.size
                , container = document.getElementById('container-list')
                , pagination = document.getElementById('pagination');

            container.innerHTML = "";
            pagination.innerHTML = "";
            let listHtml = "<div class='row mt-4'>";
            let pageHtml = '';

            //목록 innerHtml
            for (let i = 0; i < markList.length; i++) {

                //row 시작
                listHtml = i == 4 ? listHtml + "<div class='row mt-4'>" : listHtml;

                listHtml += "<div class='col-md-3 mt-4'>"
                    + "<div class='card'>"
                    + "<img src='https://placeimg.com/160/160/tech/" + i + "'>"
                    + "<hr>"
                    + "<div class='caption text-center'>"
                    + "<h5>[" + markList[i].tagName + "]" + markList[i].markTitle + "</h5>"
                    + "<a href='" + markList[i].markUrl + "' target='_blank'>이동</a>"
                    + "</div>"
                    + "</div>"
                    + "</div>";

                //row 닫기
                listHtml = i == 3 || i == 7 ? listHtml + "</div>" : listHtml;
            }
            container.innerHTML = listHtml;

            //검색 결과가 한 페이지 이상일 경우
            if (listSize > 8) {
                const maxPage = listSize % 8 == 0 ? parseInt(listSize / 8) : parseInt(listSize / 8 + 1)
                    , startPage = parseInt(page / 10) * 10
                    , endPage = startPage + 9 < maxPage ? startPage + 9 : maxPage;

                pageHtml += "<li class='page-item'>"
                    + "<a class='page-link' href='#' onclick='fncPrevPage(" + page + ",\"" + tag + "\")'>Previous</a>"
                    + "</li>";
                
                for (let i = startPage; i < endPage; i++) {
                    //JPA Pageable 페이지는 0부터 시작하기 때문에, 표시 상으로는 +1이 필요함
                    const aClass = page == i ? "page-item active" : "page-item";
                    pageHtml += "<li class='" + aClass + "'>"
                        + "<a href='#' class='page-link' onclick='fncGetMarkList(" + i + ",\""+ tag +"\")'>" + (i + 1)
                        + "</a></li>";
                }

                pageHtml += "<li class='page-item'>"
                    + "<a class='page-link' href='#' onclick='fncNextPage(" + page + "," + maxPage + ",\"" + tag + "\")'>Next</a>"
                    + "</li>";

                pagination.innerHTML = pageHtml;
            }
        }
    });
};

const fncPrevPage = (page, tag) => {
    if (page == 0) {
        alert("첫 번째 페이지입니다.");
    } else {
        fncGetMarkList(page - 1, tag);
    }
};

const fncNextPage = (page, maxPage, tag) => {
    if ((page + 1) == maxPage) {
        alert("마지막 페이지입니다.");
    } else {
        fncGetMarkList(page + 1, tag);
    }
};