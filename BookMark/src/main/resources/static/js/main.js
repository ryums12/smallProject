function fncGetMarkList(page) {

    const tag   = document.getElementById('search-tag').value,
          title = document.getElementById('search-title').value;

    const data = {
        "size"  : 8,
        "page"  : page,
        "tag"   : tag,
        "title" : title,
        "useYn" : "Y"
    };

    fncAjax("/mark/get.do", data, fncSetMarkList);
}

function fncSetMarkList(data, response) {

    const markList      = response.markList,
          listSize      = response.size,
          container     = document.getElementById('mark-list-container'),
          pagination    = document.getElementById('pagination'),
          size          = data.size;

    let listHtml = "<div class='row mt-4'>";

    container.innerHTML     = "";
    pagination.innerHTML    = "";

    //목록 innerHtml
    for (let i = 0; i < markList.length; i++) {

        //row 시작
        listHtml = i == 4 ? listHtml + "<div class='row mt-4'>" : listHtml;

        listHtml += "<div class='col-md-3 mt-3'>"
                        + "<div class='card border-secondary text-center'>"
                            + "<img src='" + markList[i].imgUrl + "' class='card-img-top' alt='tag thumbnail'>"
                            + "<h6 class='card-header'>[" + markList[i].tagName + "]</h6>"
                            + "<div>"
                                + "<p class='card-text'>" + markList[i].markTitle + "</p>"
                            + "</div>"
                            + "<div class='caption text-center'>"
                                + "<button class='btn btn-primary' "
                                        + "data-url='" + markList[i].markUrl + "' "
                                        + "onclick='fncGoToMarkLink(this)'>이동</button>"
                                + "<button class='btn btn-success' "
                                        + "data-toggle='modal' "
                                        + "data-remote='/mark/"+ markList[i].markIdx + "' "
                                        + "data-target='#modal-update-mark' "
                                        + "onclick='fncOpenUpdateMarkModal(this)'>수정</button>"
                            + "</div>"
                        + "</div>"
                     + "</div>";

        //row 닫기
        listHtml = i === 3 || i === 7 ? listHtml + "</div>" : listHtml;
    }

    container.innerHTML = listHtml;

    //검색 결과가 한 페이지 이상일 경우
    if (listSize > size) {
        fncSetPagination(listSize, data, pagination, fncGetMarkList);
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
