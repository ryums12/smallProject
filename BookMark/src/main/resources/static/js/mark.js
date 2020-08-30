function fncGetTagList(page ,tag) {

    const data = {
        "size"  : 5,
        "page"  : page,
        "tag"   : tag
    };

    fncAjax("/tag/get.do", data, fncSetTagList);
}

function fncSetTagList(data, response) {

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
                                + "<button class='btn btn-success'"
                                        + "onclick='fncSetTagToCreateMark(this)'"
                                        + "data-dismiss='modal'>선택</button>"
                            + "</td>"
                        + "</tr>";
    }

    tagTbody.innerHTML = tableInnerHtml;

    if (listSize > size) {
        fncSetPagination(listSize, data, pagination, fncGetTagList);
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

    fncAjax("/mark/get.do", data, fncSetUnusedMarkList);
}

function fncSetUnusedMarkList(data, response) {

    const unusedMarkList    = response.markList,
          listSize          = response.size,
          unusedListTable   = document.getElementById('unused-list-table'),
          pagination        = document.getElementById('pagination'),
          size              = data.size;

    let tableInnerHtml = '';

    unusedListTable.innerHTML = '';
    pagination.innerHTML = '';

    //목록 innerHtml
    for (let i = 0; i < unusedMarkList.length; i++) {
        tableInnerHtml += "<tr>"
                            + "<td class='align-middle' style='width: 15%'>" + unusedMarkList[i].tagName +"</td>"
                            + "<td class='align-middle' style='width: 70%'>" + unusedMarkList[i].markTitle +"</td>"
                            + "<td style='width: 15%'>"
                                + "<button class='btn btn-success' "
                                        + "onclick='fncSetMarkToUsable(" + JSON.stringify(unusedMarkList[i]) + ")'>사용</button>"
                            + "</td>"
                          +"</tr>";
    }

    unusedListTable.innerHTML = tableInnerHtml;

    //검색 결과가 한 페이지 이상일 경우
    if (listSize > size) {
        fncSetPagination(listSize, data, pagination, fncGetUnusedMarkList);
    }
}

function fncSetMarkToUsable(markObj) {

    if (confirm('사용 하시겠습니까?')) {

        const xhr    = new XMLHttpRequest(),
              csrf   = document.getElementById('_csrf').getAttribute('content');
        let formData = new FormData();

        formData.append("markIdx", markObj.markIdx);
        formData.append("markTitle", markObj.markTitle);
        formData.append("tagIdx", markObj.tagIdx);
        formData.append("markUrl", markObj.markUrl);
        formData.append("useYn", "Y");

        xhr.open("POST", "/mark/save.do", false);
        xhr.setRequestHeader("X-CSRF-Token", csrf);
        xhr.onreadystatechange = () => {
            if (xhr.readyState == 4 & xhr.status == 200) {
                alert("수정 되었습니다");
                window.location.href = "/";
            } else {
                alert("진행 중 오류가 발생했습니다.")
                window.location.reload();
            }
        };
        xhr.send(formData);
    }
}
