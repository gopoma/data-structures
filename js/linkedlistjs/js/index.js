import LinkedList from "./LinkedList.js";

const list = new LinkedList();
const targetList = document.querySelector("#targetList");

const isEmpty = document.querySelector("#isEmpty");

function renderIsEmpty() {
  isEmpty.textContent = list.isEmpty() ? "YES" : "NO";
}

const amountNodes = document.querySelector("#amountNodes");

function renderAmountNodes() {
  amountNodes.textContent = list.size;
}

function createNode(nodeContent) {
  const nodeElement = document.createElement("p");
  nodeElement.textContent = nodeContent;
  nodeElement.classList.add("linkedList__node");
  return nodeElement;
}
function renderList() {
  targetList.innerHTML = "";
  renderIsEmpty();
  renderAmountNodes();
  if(!list.first) {return;}
  const serialLinkedList = list.toString();
  const nodeContents = serialLinkedList.split("-");
  nodeContents.slice(0, nodeContents.length - 1).forEach(nodeContent => {
    const nodeElement = createNode(nodeContent);
    const connectorElement = document.createElement("div");
    connectorElement.classList.add("linkedList__connector");
    targetList.appendChild(nodeElement);
    targetList.appendChild(connectorElement);
  });
  const nodeElement = createNode(nodeContents[nodeContents.length - 1]);
  targetList.appendChild(nodeElement);
}

const nodeDataInput = document.querySelector("#nodeDataInput");
const btns = document.querySelectorAll(".btn");

btns.forEach(btn => {btn.disabled = true;});
nodeDataInput.oninput = function() {
  if(nodeDataInput.value.trim()) {
    btns.forEach(btn => {btn.disabled = false;})
  } else {
    btns.forEach(btn => {btn.disabled = true;})
  }
}

const btnPush = document.querySelector("#btnPush");

btnPush.onclick = function() {
  const newNodeData = nodeDataInput.value;
  list.push(newNodeData);
  renderList();
}

const btnUnshift = document.querySelector("#btnUnshift");

btnUnshift.onclick = function() {
  const newNodeData = nodeDataInput.value;
  list.unshift(newNodeData);
  renderList();
}

const btnClear = document.querySelector("#btnClear");

btnClear.onclick = function() {
  list.clear();
  renderList();
}

const messages = document.querySelector("#messages");

function createMessage(result) {
  const message = document.createElement("div");
  message.classList.add("message");
  message.classList.add(result.success ? "message--ok" : "message--error");
  message.innerHTML = `
    <div class="message__content">${result.message}</div>
    <div class="message__closer">✘</div>
  `;
  messages.appendChild(message);
  document.querySelectorAll(".message__closer").forEach(messageCloser => {
    messageCloser.onclick = function() {
      messageCloser.parentNode.parentNode.removeChild(messageCloser.parentNode);
    }
  });
}

const btnShift = document.querySelector("#btnShift");

btnShift.onclick = function() {
  const result = list.shift();
  createMessage(result);
  renderList();
}

const btnPop = document.querySelector("#btnPop");

btnPop.onclick = function() {
  const result = list.pop();
  createMessage(result);
  renderList();
}

renderList();
renderIsEmpty();
renderAmountNodes();