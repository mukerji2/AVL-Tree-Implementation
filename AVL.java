// A BST search tree that maintains its balance using AVL rotations.
public class AVL<K extends Comparable<K>, V> extends BST<K, V> {

  protected K insertKey;
  protected K removeKey;
  
  /**
   *Add the key,value pair to the data structure and increase the number of keys.
   *If key is null, throw IllegalNullKeyException;
   *If key is already in data structure, throw DuplicateKeyException();
   *
   *@param key - key to add
   *@param - value - value to add
   */
  @Override
  public void insert(K key, V value) throws IllegalNullKeyException, DuplicateKeyException {
    try {
      super.insert(key, value);
      insertKey = key;
      if (!isBalanced(root)) {
        rotate(insertKey);
      }
    } catch (Exception e) {
    }
  }

  /**
   * finds the lowest unalanced node in the tree/subtree rooted at given root
   * 
   * @param root - root of tree/subtree
   * @return the lowest unbalanced node, or null if tree is balanced
   */
  protected BSTNode<K, V> findLowestUnbalancedNode(BSTNode<K, V> root) {
    if (!this.isBalanced(root)) {
      if (getBalanceFactor(root) > 0) {
        if (this.isBalanced(root.getLeftChild())) {
          return root;
        } else {
          return findLowestUnbalancedNode(root.getLeftChild());
        }
      } else if (getBalanceFactor(root) < 0) {
        if (this.isBalanced(root.getRightChild())) {
          return root;
        } else {
          return findLowestUnbalancedNode(root.getRightChild());
        }
      }
    }
    return null;
  }

  /**
   * rotates the correct part of tree to rebalance it
   * 
   * @param key - the key that was newly inserted or removed
   */
  public void rotate(K key) {
    BSTNode<K, V> temp = findLowestUnbalancedNode(root);
    if (temp != null) {
      if (temp.getKey().compareTo(root.getKey()) == 0) {
        if (containsHelper(temp.getLeftChild(), key) != false) {
          if (temp.getLeftChild().getLeftChild().getKey().compareTo(key) == 0) {
            setRoot(rotateLeftLeft(temp));
          } else {
            setRoot(rotateLeftRight(temp));
          }
        } else if (containsHelper(temp.getRightChild(), key) != false) {
          if (temp.getRightChild().getLeftChild().getKey().compareTo(key) == 0) {
            setRoot(rotateRightLeft(temp));
          } else if (temp.getRightChild().getRightChild().getKey().compareTo(key) == 0) {
            setRoot(rotateRightRight(temp));
          }
        }
      } else if (lookup(temp.getLeftChild().getLeftChild(), key) != null) {
        rotateLeftLeft(temp);
      } else if (lookup(temp.getLeftChild().getRightChild(), key) != null) {
        rotateLeftRight(temp);
      } else if (lookup(temp.getRightChild().getRightChild(), key) != null) {
        rotateRightRight(temp);
      } else if (lookup(temp.getRightChild().getLeftChild(), key) != null) {
        rotateRightLeft(temp);
      }
    }
  }

  /**
   * performs a single right rotation 
   * 
   * @param root - the root of the unbalanced subtree
   * @return the new root of the subtree
   */
  protected BSTNode<K, V> rotateLeftLeft(BSTNode<K, V> root) {
    BSTNode<K, V> temp = root.getLeftChild();
    root.setLeftChild(temp.getRightChild());
    temp.setRightChild(root);
    return temp;
  }

  /**
   * performs a left rotation at left child of root then right rotation at root
   * 
   * @param root - the root of the unbalanced subtree
   * @return the new root of the subtree
   */
  protected BSTNode<K, V> rotateLeftRight(BSTNode<K, V> root) {
    BSTNode<K, V> rootKey = root.getLeftChild();
    rotateRightRight(rootKey);
    return rotateLeftLeft(root);
  }

  /**
   * performs a single left rotation
   * 
   * @param root - the root of the unbalanced subtree
   * @return the new root of the subtree
   */
  protected BSTNode<K, V> rotateRightRight(BSTNode<K, V> root) {
    BSTNode<K, V> temp = root.getRightChild();
    root.setRightChild(temp.getLeftChild());
    temp.setLeftChild(root);
    return temp;
  }

  /**
   * performs right rotation at right child of root then left rotation at root
   * 
   * @param root - the root of unbalanced subtree
   * @return the new root of the subtree
   */
  protected BSTNode<K, V> rotateRightLeft(BSTNode<K, V> root) {
    BSTNode<K, V> rootKey = root.getRightChild();
    rotateLeftLeft(rootKey);
    return rotateRightRight(root);
  }

  /**
   * finds out if the node argument is balanced
   * 
   * @param root - node for which balance is being determined 
   * @return true if node is balanced, false otherwise.
   */
  protected boolean isBalanced(BSTNode<K, V> root) {
    if (root == null) {
      return true;
    } else {
      if (getBalanceFactor(root) <= 1 && getBalanceFactor(root) >= -1) {
        if (isBalanced(root.getLeftChild()) && isBalanced(root.getRightChild())) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * returns balance factor of node
   * 
   * @param node - node to find balance factor for 
   * @return the balance factor of the node 
   */
  protected int getBalanceFactor(BSTNode<K, V> node) {
    int left = 0;
    int right = 0;
    if (node.getLeftChild() != null && node.getRightChild() != null) {
      left = getHeightHelper(node.getLeftChild(), 0);
      right = getHeightHelper(node.getRightChild(), 0);
    } else if (node.getLeftChild() != null) {
      left = getHeightHelper(node.getLeftChild(), 0);
    } else if (node.getRightChild() != null) {
      right = getHeightHelper(node.getRightChild(), 0);
    }
    return (left - right);
  }

  /**
   * If key is found, remove the key,value pair from the data structure and decrease num keys. If
   * key is null, throw IllegalNullKeyException If key is not found, throw KeyNotFoundException
   * 
   * @param key - the key to remove
   * @return true if key is removed, false otherwise
   */
  @Override
  public boolean remove(K key) throws IllegalNullKeyException, KeyNotFoundException {
    try {
      super.remove(key);
      removeKey = key;
      if (!this.isBalanced(root)) {
        rotate(removeKey);
        return true;
      }
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}
