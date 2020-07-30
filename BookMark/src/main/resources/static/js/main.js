function fncGetMarkList(page, tag) {

    const data = {
        "size": 8,
        "page": page,
        "tag": tag,
        "useYn": "Y"
    };

    fncAjax("/mark/get.do", data, fncSetMarkList);
}

function fncSetMarkList(data, response) {

    const markList = response.markList
        , listSize = response.size
        , container = document.getElementById('mark-list-container')
        , pagination = document.getElementById('pagination')
        , page = data.page
        , tag = data.tag;

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
            + "<img src='" + markList[i].imgUrl + "' class='custom-thumbnail'>"
            + "<hr>"
            + "<div class='caption text-center'>"
            + "<h6>[" + markList[i].tagName + "]" + markList[i].markTitle + "</h6>"
            + "<button class='btn btn-primary' "
            + "data-url='" + markList[i].markUrl + "'"
            + "onclick='fncGoToMarkLink(this)'>이동</button>"
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
    if (listSize > 8) {
        const maxPage = listSize % 8 == 0 ? parseInt(listSize / 8) : parseInt(listSize / 8 + 1)
            , startPage = parseInt(page / 10) * 10
            , endPage = startPage + 9 < maxPage ? startPage + 9 : maxPage;

        pageHtml += "<li class='page-item'>"
            + "<a class='page-link' href='#' "
            + "onclick='fncGoToPrevMarkPage(" + page + ",\"" + tag + "\")'>Previous</a>"
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
            + "onclick='fncGoToNextMarkPage(" + page + "," + maxPage + ",\"" + tag + "\")'>Next"
            + "</a>"
            + "</li>";

        pagination.innerHTML = pageHtml;
    }
}

function fncGoToMarkLink(dom) {
    const url = dom.dataset.url;
    window.open(url, "_blank");
}

function fncGoToPrevMarkPage(page, tag) {
    if (page == 0) alert("첫 번째 페이지입니다.");
    else fncGetMarkList(page - 1, tag);
}

function fncGoToNextMarkPage(page, maxPage, tag) {
    if ((page + 1) == maxPage) alert("마지막 페이지입니다.");
    else fncGetMarkList(page + 1, tag);
}

function fncOpenUpdateMarkModal(dom) {
    const modal = $(dom).data("target");
    $(modal).find('.modal-content').load($(dom).data("remote"));
}
