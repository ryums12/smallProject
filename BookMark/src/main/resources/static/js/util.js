const fncPreventInput = dom => {
  dom.onkeydown = e => {
    e.preventDefault();
  }
};

const fncCheckMarkValue = () => {
  const markTitle = document.getElementById('mark-title')
      , tagName = document.getElementById('tag-name')
      , tagIdx = document.getElementById('tag-idx').value
      , markUrl = document.getElementById('mark-url');

  if (!markTitle.value) {
    alert("마크 제목을 입력해 주십시오.");
    markTitle.focus();
    return false;
  } else if (!tagIdx) {
    alert("등록할 태그를 입력해 주십시오. \n 선택을 통해서만 가능합니다.");
    tagName.focus();
    return false;
  } else if (!markUrl.value) {
    alert("경로를 등록해 주십시오.");
    markUrl.focus();
    return false;
  } else {
    return true;
  }
};

const fncSetTag = (dom) => {
    const tagName = dom.parentElement.parentElement.children[0].children[0].innerHTML
        , tagIdx = dom.parentElement.parentElement.children[0].children[1].value
        , inputTagName = document.getElementById('tag-name')
        , hiddenTagIdx = document.getElementById('tag-idx');

    inputTagName.value = tagName;
    hiddenTagIdx.value = tagIdx;
};