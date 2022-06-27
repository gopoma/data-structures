import Node from "./Node.js";

export default class LinkedList {
  constructor() {
    this.first = null;
    this.size = 0;
  }
  
  isEmpty() {
    return this.size === 0;
  }
  clear() {
    this.first = null;
    this.size = 0;
  }
  unshift(x) {
    const newNode = new Node(x, this.first);
    this.first = newNode;
    this.size++;
  }
  push(x) {
    if(this.isEmpty()) {
      this.first = new Node(x, null);
    } else {
      let tmp = this.first;
      for(let i = 0; i < this.size - 1; i++) {tmp = tmp.next;}
      tmp.next = new Node(x, null);
    }
    this.size++;
  }
  shift() {
    if(this.isEmpty()) {
      return {
        success: false,
        message: "The LinkedList is Empty!"
      };
    }
    
    this.first = this.first.next;
    this.size--;
    return {
      success: true,
      message: "The first node was deleted successfully!"
    };
  }
  pop() {
    if(this.isEmpty()) {
      return {
        success: false,
        message: "The LinkedList is Empty!"
      };
    } else if(this.size === 1) {
      this.shift();
      return {
        success: true,
        message: "The last node was deleted successfully!"
      }
    } else {
      let tmp = this.first;
      for(let i = 0; i < this.size - 2; i++) {
        tmp = tmp.next;
      }
      tmp.next = null;
      this.size--;
      
      return {
        success: true,
        message: "The last node was deleted successfully!"
      }
    }
  }
  toString() {
    let serialLinkedList = "";
    let tmp = this.first;
    while(tmp !== null) {
      serialLinkedList = `${serialLinkedList}${tmp.data}-`;
      tmp = tmp.next;
    }
    return serialLinkedList.substring(serialLinkedList, serialLinkedList.length - 1);
  }
}