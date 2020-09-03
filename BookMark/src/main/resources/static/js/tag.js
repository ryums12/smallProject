function fncGetSearchTagList(page ,tag) {

    const data = {
        "size"  : 5,
        "page"  : page,
        "tag"   : tag
    };

    fncAjax("/tag/get.do", data, fncSetSearchTagList);
}

function fncSetSearchTagList(data, response) {

    const tagList       = response.tagList,
          listSize      = response.size,
          tagTbody      = document.getElementById('tag-modal-tbody'),
          pagination    = document.getElementById('modal-pagination'),
          size          = data.size;

    let tableInnerHtml = "";
    tagTbody.innerHTML = "";
    pagination.innerHTML = "";

    for (let i = 0; i < tagList.length; i++) {
        tableInnerHtml += "<tr>"
                            + "<td class='align-middle' style='width: 50%'>"
                                + "<span>" + tagList[i].tagName +"</span>"
                                + "<input type='hidden' value='" + tagList[i].tagIdx + "'>"
                            + "</td>"
                            + "<td class='align-middle' style='width: 50%'>"
                                + "<button class='btn btn-success' "
                                        + "onclick='fncSetTagToCreateMark(this)' "
                                        + "data-dismiss='modal'>선택</button>"
                            + "</td>"
                        + "</tr>";
    }

    tagTbody.innerHTML = tableInnerHtml;

    if (listSize > size) {
        fncSetPagination(listSize, data, pagination, fncGetSearchTagList);
    }

}

function fncSetTagToCreateMark(dom) {

    const tagName       = dom.parentElement.parentElement.children[0].children[0].innerHTML,
          tagIdx        = dom.parentElement.parentElement.children[0].children[1].value,
          inputTagName  = document.getElementById('tag-name'),
          hiddenTagIdx  = document.getElementById('tag-idx');

    inputTagName.value = tagName;
    hiddenTagIdx.value = tagIdx;
}

function fncGetTagList(page) {

    const data = {
        "size"  : 10,
        "page"  : page,
        "tag"   : ""
    };

    fncAjax("/tag/get.do", data, fncSetTagList);
}

function fncSetTagList(data, response) {

    const tagList           = response.tagList,
          listSize          = response.size,
          tagListTable      = document.getElementById('tag-list-table'),
          pagination        = document.getElementById('pagination'),
          size              = data.size;

    let tableInnerHtml = '';

    // tagListTable.innerHTML = '';
    pagination.innerHTML = '';

    //목록 innerHtml
    for (let i = 0; i < tagList.length; i++) {
        tableInnerHtml += "<tr>"
                            + "<td class='align-middle h-50px'>"
                                + "<img src='" + tagList[i].imgUrl + "' alt='tag thumbnail' class='img-thumbnail h-100'>"
                            + "</td>"
                            + "<td class='align-middle h-50px'>" + tagList[i].tagName +"</td>"
                            + "<td class='align-middle h-50px'>"
                                + "<button class='btn btn-success' "
                                        + "data-toggle='modal' "
                                        + "data-remote='/tag/"+ tagList[i].tagIdx + "' "
                                        + "data-target='#modal-update-tag' "
                                        + "onclick='fncOpenUpdateMarkModal(this)'>수정</button>"
                            + "</td>"
                        + "</tr>";
        }

    tagListTable.innerHTML += tableInnerHtml;

    //검색 결과가 한 페이지 이상일 경우
    if (listSize > size) {
        fncSetPagination(listSize, data, pagination, fncGetTagList);
    }
}