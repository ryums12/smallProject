function fncGetMarkList(page, tag) {

    const data = {
        "size"  : 8,
        "page"  : page,
        "tag"   : tag,
        "useYn" : "Y"
    };

    fncAjax("/mark/get.do", data, fncSetMarkList);
}

function fncSetMarkList(data, response) {

    const markList      = response.markList
        , listSize      = response.size
        , container     = document.getElementById('mark-list-container')
        , pagination    = document.getElementById('pagination')
        , size          = data.size
        , page          = data.page
        , tag           = data.tag;

    let listHtml = "<div class='row mt-4'>";
    let pageHtml = '';
    
    //dom 초기화
    container.innerHTML     = "";
    pagination.innerHTML    = "";

    //목록 innerHtml
    for (let i = 0; i < markList.length; i++) {

        //row 시작
        listHtml = i == 4 ? listHtml + "<div class='row mt-4'>" : listHtml;

        listHtml += "<div class='col-md-3 mt-4'>"
                        + "<div class='card'>"
                            + "<img src='" + markList[i].imgUrl + "' class='custom-thumbnail'>"
                            + "<hr>"
                            + "<div class='caption text-center'>"
                                //제목
                                + "<h6>[" + markList[i].tagName + "]" + markList[i].markTitle + "</h6>"
                                //이동 버튼
                                + "<button class='btn btn-primary' "
                                + "data-url='" + markList[i].markUrl + "'"
                                + "onclick='fncGoToMarkLink(this)'>이동</button>"
                                //수정 버튼
                                + "<button class='btn btn-success'"
                                + "data-toggle='modal'"
                                + "data-remote='/mark/"+ markList[i].markIdx + "'"
                                + "data-target='#modal-update-mark'"
                                + "onclick='fncOpenUpdateMarkModal(this)'>수정</button>"
                            + "</div>"
                        + "</div>"
                     + "</div>";

        //row 닫기
        listHtml = i == 3 || i == 7 ? listHtml + "</div>" : listHtml;
    }
    container.innerHTML = listHtml;

    //검색 결과가 한 페이지 이상일 경우
    if (listSize > size) {
        const paginationData = fncGetPagination(listSize, size, page)
            , maxPage = paginationData.maxPage
            , startPage = paginationData.startPage
            , endPage = paginationData.endPage;

        pageHtml += "<li class='page-item'>"
                        + "<a class='page-link' href='#'"
                           + "onclick='fncGoToPrevPage(" + page + ",\"" + tag + "\"," + fncGetMarkList +")'>Previous</a>"
                  + "</li>";

        for (let i = startPage; i < endPage; i++) {
            //JPA Pageable 페이지는 0부터 시작하기 때문에, 표시 상으로는 +1이 필요함
            const aClass = page == i ? "page-item active" : "page-item";
            pageHtml += "<li class='" + aClass + "'>"
                            + "<a href='#' class='page-link'"
                               + "onclick='fncGetMarkList(" + i + ",\"" + tag + "\")'>" + (i + 1)
                            + "</a>"
                      + "</li>";
        }

        pageHtml += "<li class='page-item'>"
                        + "<a class='page-link' href='#' "
                           + "onclick='fncGoToNextPage(" + page + "," + maxPage + ",\"" + tag + "\"," + fncGetMarkList +")'>Next"
                        + "</a>"
                  + "</li>";

        pagination.innerHTML = pageHtml;
    }
}

function fncGoToMarkLink(dom) {
    const url = dom.dataset.url;
    window.open(url, "_blank");
}

function fncOpenUpdateMarkModal(dom) {
    const modal = $(dom).data("target");
    $(modal).find('.modal-content').load($(dom).data("remote"));
}
