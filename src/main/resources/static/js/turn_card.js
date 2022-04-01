const time = 400;

function changeVisibility(){
  const front = document.querySelector('.front');
  const back = document.querySelector('.back');

  turnAnimation(front);

  setTimeout(() => {
    hideElement(front);
    showElement(back);
  }, time);
}

function hideElement(element){
  element.classList.remove("front");
  element.classList.add("back");
}

function showElement(element){
  element.classList.remove("back");
  element.classList.add("front");
}

function turnAnimation(element){
  element.classList.add("turn__anim");
  element.firstElementChild.classList.add("hide");
  setTimeout(() => {
    element.firstElementChild.classList.remove("hide");
    element.classList.remove("turn__anim");
  }, time);
}