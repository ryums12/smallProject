function fncCheckMarkValue() {

    const markTitle = document.getElementById('mark-title')
        , tagName   = document.getElementById('tag-name')
        , tagIdx    = document.getElementById('tag-idx').value
        , markUrl   = document.getElementById('mark-url');

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
};

function fncSetTagToCreateMark(dom) {

    const tagName       = dom.parentElement.parentElement.children[0].children[0].innerHTML
        , tagIdx        = dom.parentElement.parentElement.children[0].children[1].value
        , inputTagName  = document.getElementById('tag-name')
        , hiddenTagIdx  = document.getElementById('tag-idx');

    inputTagName.value = tagName;
    hiddenTagIdx.value = tagIdx;
};

function fncGetUnusedMarkList(page, tag) {

    const data = {
        "size"  : 10,
        "page"  : page,
        "tag"   : tag,
        "useYn" : "N"
    };

    fncAjax("/mark/get.do", data, fncSetUnusedMarkList);
}

function fncSetUnusedMarkList(data, response) {

    let tableInnerHtml = '', pageInnerHtml = '';

    const unusedMarkList    = response.markList
        , listSize          = response.size
        , unusedListTable   = document.getElementById('unused-list-table')
        , pagination        = document.getElementById('pagination')
        , size              = data.size
        , page              = data.page
        , tag               = data.tag;

    unusedListTable.innerHTML = '';
    pagination.innerHTML = '';

    for(let i = 0; i < unusedMarkList.length; i++) {
        tableInnerHtml += "<tr>"
                            + "<td class='align-middle'>" + unusedMarkList[i].tagName +"</td>"
                            + "<td class='align-middle'>" + unusedMarkList[i].markTitle +"</td>"
                            + "<td>"
                                + "<button class='btn btn-success' "
                                        + "onclick='fncSetMarkToUsable(" + JSON.stringify(unusedMarkList[i]) + ")'>사용</button>"
                            + "</td>"
                          +"</tr>";
    }

    unusedListTable.innerHTML = tableInnerHtml;

    if (listSize > size) {
        const paginationData    = fncGetPagination(listSize, size, page)
            , maxPage           = paginationData.maxPage
            , startPage         = paginationData.startPage
            , endPage           = paginationData.endPage;

        pageInnerHtml += "<li class='page-item'>"
                            + "<a class='page-link' href='#'"
                               + "onclick='fncGoToPrevPage(" + page + ",\"" + tag + "\"," + fncGetUnusedMarkList + ")'>Previous</a>"
                       + "</li>";

        for (let i = startPage; i < endPage; i++) {
            //JPA Pageable 페이지는 0부터 시작하기 때문에, 표시 상으로는 +1이 필요함
            const aClass = page == i ? "page-item active" : "page-item";
            pageInnerHtml += "<li class='" + aClass + "'>"
                                + "<a href='#' class='page-link'"
                                   + "onclick='fncGetUnusedMarkList(" + i + ",\"" + tag + "\")'>" + (i + 1)
                                + "</a>"
                           + "</li>";
        }

        pageInnerHtml += "<li class='page-item'>"
                            + "<a class='page-link' href='#' "
                               + "onclick='fncGoToNextPage(" + page + "," + maxPage + ",\"" + tag + "\"," + fncGetUnusedMarkList +")'>Next"
                            + "</a>"
                       + "</li>";

        pagination.innerHTML = pageInnerHtml;
    }
}

function fncSetMarkToUsable(markObj) {

    if (confirm('사용 하시겠습니까?')) {

        let formData = new FormData();
        const xhr    = new XMLHttpRequest()
            , csrf   = document.getElementById('_csrf').getAttribute('content');

        formData.append("markIdx", markObj.markIdx);
        formData.append("markTitle", markObj.markTitle);
        formData.append("tagIdx", markObj.tagIdx);
        formData.append("markUrl", markObj.markUrl);
        formData.append("useYn", "Y");

        xhr.open("POST", "/mark/save.do");
        xhr.setRequestHeader("X-CSRF-Token", csrf);
        xhr.send(formData);
    }
}