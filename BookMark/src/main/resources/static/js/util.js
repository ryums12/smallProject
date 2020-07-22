const fncPreventInput = dom => {
  dom.onkeydown = e => {
    e.preventDefault();
  }
};