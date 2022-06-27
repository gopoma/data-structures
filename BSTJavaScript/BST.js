class BST {
  constructor(data) {
    this.data = data;
    this.left = null;
    this.right = null;
  }
  
  insert(value) {
    if(value < this.data) {
      if(!this.left) {
				this.left = new BST(value);        
      } else {
        this.left.insert(value);
      }
    } else {
      if(!this.right) {
        this.right = new BST(value);
      } else {
        this.right.insert(value);
      }
    }
  }
}

const tree = new BST(20);
tree.insert(75);
tree.insert(12);
tree.insert(13);
tree.insert(100);
tree.insert(120);
tree