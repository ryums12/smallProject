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

    fncAjax("get", "/mark/", data, fncSetMarkList);
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
        listHtml = i === 4 ? listHtml + "<div class='row mt-4'>" : listHtml;

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

function fncCheckMarkValue() {

    const markTitle = document.getElementById('mark-title'),
          tagName   = document.getElementById('tag-name'),
          tagIdx    = document.getElementById('tag-idx').value,
          markUrl   = document.getElementById('mark-url');

    if (!markTitle.value) {
        alert("마크 제목을 입력해 주십시오.");
        markTitle.focus();
    } else if (!tagIdx) {
        alert("등록할 태그를 입력해 주십시오. \n선택을 통해서만 가능합니다.");
        tagName.focus();
    } else if (!markUrl.value) {
        alert("경로를 등록해 주십시오.");
        markUrl.focus();
    } else {
        return confirm("등록 하시겠습니까?");
    }

    return false;
}

function fncGetUnusedMarkList(page) {

    const data = {
        "size"  : 10,
        "page"  : page,
        "tag"   : "",
        "title" : "",
        "useYn" : "N"
    };

    fncAjax("get", "/mark", data, fncSetUnusedMarkList);
}

function fncSetUnusedMarkList(data, response) {

    const unusedMarkList    = response.markList,
          listSize          = response.size,
          unusedListTable   = document.getElementById('unused-list-table'),
          pagination        = document.getElementById('pagination'),
          size              = data.size,
          csrf              = document.getElementById('_csrf').getAttribute('content');

    let tableInnerHtml = '';

    unusedListTable.innerHTML = '';
    pagination.innerHTML = '';

    //목록 innerHtml
    for (let i = 0; i < unusedMarkList.length; i++) {
        tableInnerHtml += "<tr>"
                            + "<td class='align-middle' style='width: 15%'>" + unusedMarkList[i].tagName +"</td>"
                            + "<td class='align-middle' style='width: 70%'>" + unusedMarkList[i].markTitle +"</td>"
                            + "<td style='width: 15%'>"
                                + "<form action='/mark' method='post' onsubmit='return confirm(\"사용 하시겠습니까?\")'>"
                                    + "<input type='hidden' value='" + csrf + "' name='_csrf'>"
                                    + "<input type='hidden' value='" + unusedMarkList[i].markIdx +"' name='markIdx'>"
                                    + "<input type='hidden' value='" + unusedMarkList[i].markTitle +"' name='markTitle'>"
                                    + "<input type='hidden' value='" + unusedMarkList[i].markUrl +"' name='markUrl'>"
                                    + "<input type='hidden' value='" + unusedMarkList[i].tagIdx +"' name='tagIdx'>"
                                    + "<input type='hidden' value='Y' name='useYn'>"
                                    + "<button class='btn btn-success' type='submit'>사용</button>"
                                + "</form>"
                            + "</td>"
                          +"</tr>";
    }

    unusedListTable.innerHTML = tableInnerHtml;

    //검색 결과가 한 페이지 이상일 경우
    if (listSize > size) {
        fncSetPagination(listSize, data, pagination, fncGetUnusedMarkList);
    }
}