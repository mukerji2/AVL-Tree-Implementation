import java.util.ArrayList; // allowed for creating traversal lists
import java.util.List; // required for returning List<K>

// TODO: Implement a Binary Search Tree class here
public class BST<K extends Comparable<K>, V> implements BSTADT<K, V> {

  // Tip: Use protected fields so that they may be inherited by AVL
  protected BSTNode<K, V> root;
  protected int numKeys; // number of keys in BST

  // Must have a public, default no-arg constructor
  public BST() {

  }

  /**
   * Returns the keys of the data structure in pre-order traversal order. In the case of binary
   * search trees, the order is: V L R
   * 
   * If the SearchTree is empty, an empty list is returned.
   * 
   * @return List of Keys in pre-order
   */
  @Override
  public List<K> getPreOrderTraversal() {
    List<K> list = new ArrayList<K>();
    if (root == null) {
      return list;
    } else if (root.getLeftChild() == null && root.getRightChild() == null) {
      list.add(root.getKey());
      return list;
    } else {
      return preOrderHelper(root, list);
    }
  }

  /**
   * private helper method that recursively adds keys to list in pre-order
   * 
   * @param rootKey - the root of the tree or subtree to traverse
   * @param list    - the list containing keys in pre-order
   * @return the list of keys in pre-order
   */
  protected List<K> preOrderHelper(BSTNode<K, V> rootKey, List<K> list) {
    if (rootKey == null) {
      return list;
    }
    if (rootKey != null) {
      list.add(rootKey.getKey());
    }
    if (rootKey.getLeftChild() != null && rootKey.getRightChild() != null) {
      list.add(rootKey.getLeftChild().getKey());
      list.add(rootKey.getRightChild().getKey());

      preOrderHelper(rootKey.getLeftChild().getLeftChild(), list);
      preOrderHelper(rootKey.getLeftChild().getRightChild(), list);
      preOrderHelper(rootKey.getRightChild().getLeftChild(), list);
      preOrderHelper(rootKey.getRightChild().getRightChild(), list);
    } else if (rootKey.getRightChild() == null) {
      if (rootKey.getLeftChild() != null) {
        list.add(rootKey.getLeftChild().getKey());

        preOrderHelper(rootKey.getLeftChild().getLeftChild(), list);
        preOrderHelper(rootKey.getLeftChild().getRightChild(), list);
      }
    } else {
      list.add(rootKey.getRightChild().getKey());

      preOrderHelper(rootKey.getRightChild().getLeftChild(), list);
      preOrderHelper(rootKey.getRightChild().getRightChild(), list);
    }
    return list;
  }

  /**
   * Returns the keys of the data structure in post-order traversal order. In the case of binary
   * search trees, the order is: L R V
   * 
   * If the SearchTree is empty, an empty list is returned.
   * 
   * @return List of Keys in post-order
   */
  @Override
  public List<K> getPostOrderTraversal() {
    List<K> list = new ArrayList<K>();
    if (root == null) {
      return list;
    } else if (root.getLeftChild() == null && root.getRightChild() == null) {
      list.add(root.getKey());
      return list;
    } else {
      return postOrderHelper(root, list);
    }
  }

  /**
   * recursive helper method that helps to find post order of BST
   * 
   * @param rootKey - root of tree/subtree for which post-order is being found
   * @param list - list of keys to add to
   * @return the list of keys in post order
   */
  protected List<K> postOrderHelper(BSTNode<K, V> rootKey, List<K> list) {
    if (rootKey.getLeftChild() != null && rootKey.getRightChild() != null) {
      postOrderHelper(rootKey.getLeftChild(), list);
      postOrderHelper(rootKey.getRightChild(), list);
      list.add(rootKey.getKey());
    } else if (rootKey.getLeftChild() == null) {
      if (rootKey.getRightChild() != null) {
        postOrderHelper(rootKey.getRightChild(), list);
        list.add(rootKey.getKey());
      } else {
        list.add(rootKey.getKey());
      }
    } else if (rootKey.getRightChild() == null) {
      if (rootKey.getLeftChild() != null) {
        postOrderHelper(rootKey.getLeftChild(), list);
        list.add(rootKey.getKey());
      } else {
        list.add(rootKey.getKey());
      }
    }
    return list;
  }

  /**
   * Returns the keys of the data structure in level-order traversal order.
   * 
   * The root is first in the list, then the keys found in the next level down, and so on.
   * 
   * If the SearchTree is empty, an empty list is returned.
   * 
   * @return List of Keys in level-order
   */
  @Override
  public List<K> getLevelOrderTraversal() {
    List<K> list = new ArrayList<K>();
    if (root == null) {
      return list;
    } else {
      list.add(root.getKey());
      return levelOrderHelper(root, list);
    }
  }

  /**
   * recursive helper method that helps find level order 
   * 
   * @param rootKey - the root of tree/subtree for which level order is being found
   * @param list - list of keys in level order
   * @return the list of keys in level order
   */
  protected List<K> levelOrderHelper(BSTNode<K, V> rootKey, List<K> list) {
    if (rootKey == null) {
      return list;
    }
    if (rootKey.getLeftChild() != null) {
      list.add(rootKey.getLeftChild().getKey());
    }
    if (rootKey.getRightChild() != null) {
      list.add(rootKey.getRightChild().getKey());
    }
    levelOrderHelper(rootKey.getLeftChild(), list);
    levelOrderHelper(rootKey.getRightChild(), list);
    return list;
  }

  /**
   * Returns the keys of the data structure in sorted order. In the case of binary search trees, the
   * visit order is: L V R
   * 
   * If the SearchTree is empty, an empty list is returned.
   * 
   * @return List of Keys in-order
   */
  @Override
  public List<K> getInOrderTraversal() {
    List<K> list = new ArrayList<K>();
    if (root == null) {
      return list;
    } else {
      return inOrderHelper(root, list);
    }
  }

  /**
   * recursive helper method that helps find in order 
   * 
   * @param rootKey - root of tree/subtree for which in-order is being found
   * @param list - list of keys in in-order
   * @return the list of keys in in-order 
   */
  protected List<K> inOrderHelper(BSTNode<K, V> rootKey, List<K> list) {
    if (rootKey.getLeftChild() != null && rootKey.getRightChild() != null) {
      inOrderHelper(rootKey.getLeftChild(), list);
      list.add(rootKey.getKey());
      inOrderHelper(rootKey.getRightChild(), list);
    } else if (rootKey.getLeftChild() == null) {
      list.add(rootKey.getKey());
      if (rootKey.getRightChild() != null) {
        inOrderHelper(rootKey.getRightChild(), list);
      }
    } else if (rootKey.getRightChild() == null) {
      if (rootKey.getLeftChild() != null) {
        inOrderHelper(rootKey.getLeftChild(), list);
      }
      list.add(rootKey.getKey());
    }
    return list;
  }

  /**
   * inserts key-value pair into BST. 
   * 
   * @param key - key to insert
   * @param value - value to insert 
   */
  @Override
  public void insert(K key, V value) throws IllegalNullKeyException, DuplicateKeyException {
    if (key == null) {
      throw (new IllegalNullKeyException());
    } else if (this.contains(key)) {
      throw (new DuplicateKeyException());
    } else if (root == null) {
      root = new BSTNode<K, V>(key, value);
      ++numKeys;
    } else if (root.getKey().compareTo(key) < 0) {
      recursiveInsertSubtree(root, key, value);
      ++numKeys;
    } else {
      recursiveInsertSubtree(root, key, value);
      ++numKeys;
    }
  }

  /**
   * private recursive helper method that traverses through the right subtree and finds correct
   * location to insert a new key-value pair.
   * 
   * @param rootKey   - the node whose right subtree is to be traversed
   * @param insertKey - the key of the node to be inserted
   * @param insertVal - the value of the node to be inserted
   */
  protected void recursiveInsertSubtree(BSTNode<K, V> rootKey, K insertKey, V insertVal) {
    if (rootKey.getKey().compareTo(insertKey) < 0) {
      if (rootKey.getRightChild() == null) {
        rootKey.setRightChild(insertKey, insertVal);
      } else {
        recursiveInsertSubtree(rootKey.getRightChild(), insertKey, insertVal);
      }
    } else {
      if (rootKey.getLeftChild() == null) {
        rootKey.setLeftChild(insertKey, insertVal);
      } else {
        recursiveInsertSubtree(rootKey.getLeftChild(), insertKey, insertVal);
      }
    }
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
    if (key == null) {
      throw (new IllegalNullKeyException());
    }
    if (!this.contains(key)) {
      throw (new KeyNotFoundException());
    }
    if (root.getKey().compareTo(key) == 0) {
      BSTNode<K, V> temp = findInOrderSuccessor(root.getRightChild());
      root.setKey(temp.getKey());
      root.setVal(temp.getVal());
      temp = null;
      --numKeys;
      return true;
    } else {
      recursiveFindParentKey(root, key);
      --numKeys;
      return true;
    }
  }

  /**
   * recursive helper method that finds the parent of the key to remove and calls another helper
   * method to remove it
   * 
   * @param rootKey - the root of the tree or subtree to be searched
   * @param key     - the key being removed
   */
  protected void recursiveFindParentKey(BSTNode<K, V> rootKey, K key) {
    if (rootKey.getKey().compareTo(key) < 0) {
      if (rootKey.getRightChild().getKey().compareTo(key) == 0) {
        recursiveRemoveRightKey(rootKey);
      } else {
        recursiveFindParentKey(rootKey.getRightChild(), key);
      }
    } else {
      if (rootKey.getLeftChild().getKey().compareTo(key) == 0) {
        recursiveRemoveLeftKey(rootKey);
      } else {
        recursiveFindParentKey(rootKey.getLeftChild(), key);
      }
    }
  }

  /**
   * helper method that removes the right child of specified key
   * 
   * @param key - parent of key to be removed
   */
  protected void recursiveRemoveRightKey(BSTNode<K, V> key) {
    if (key.getRightChild().getLeftChild() == null && key.getRightChild().getRightChild() == null) {
      key.setRightChild(null, null);
    } else if (key.getRightChild().getLeftChild() == null) {
      key.setRightChild(key.getRightChild().getRightChild().getKey(),
          key.getRightChild().getRightChild().getVal());
    } else if (key.getRightChild().getRightChild() == null) {
      key.setRightChild(key.getRightChild().getLeftChild().getKey(),
          key.getRightChild().getLeftChild().getVal());
    } else {
      BSTNode<K, V> replaceNode = findInOrderSuccessor(key.getRightChild().getRightChild());
      key.getRightChild().setKey(replaceNode.getKey());
      key.getRightChild().setVal(replaceNode.getVal());
    }
  }

  /**
   * helper method that removes left child of specified key
   * 
   * @param key - key to be removed
   */
  protected void recursiveRemoveLeftKey(BSTNode<K, V> key) {
    if (key.getLeftChild().getLeftChild() == null && key.getLeftChild().getRightChild() == null) {
      key.setLeftChild(null, null);
    } else if (key.getLeftChild().getLeftChild() == null) {
      key.setLeftChild(key.getLeftChild().getRightChild().getKey(),
          key.getLeftChild().getRightChild().getVal());
    } else if (key.getLeftChild().getRightChild() == null) {
      key.setLeftChild(key.getLeftChild().getLeftChild().getKey(),
          key.getLeftChild().getLeftChild().getVal());
    }
    BSTNode<K, V> replaceNode = findInOrderSuccessor(key.getLeftChild().getRightChild());
    key.getLeftChild().setKey(replaceNode.getKey());
    key.getLeftChild().setVal(replaceNode.getVal());
    replaceNode = null;
  }

  /**
   * finds the in-order successor of the key
   * 
   * @param key - key whose in-order successor is to be found
   * @return the in-order successor of the key
   */
  protected BSTNode<K, V> findInOrderSuccessor(BSTNode<K, V> key) {
    if (key.getLeftChild() == null) {
      BSTNode<K, V> temp = key;
      key = null;
      return temp;
    } else {
      return findInOrderSuccessor(key.getLeftChild());
    }
  }

  /**
   * Returns the value associated with the specified key Does not remove key or decrease number of
   * keys If key is null, throw IllegalNullKeyException If key is not found, throw
   * KeyNotFoundException()
   * 
   * @param key - the key whose value is to be returned
   * @return the value associated with the key parameter
   */
  @Override
  public V get(K key) throws IllegalNullKeyException, KeyNotFoundException {
    if (key == null) {
      throw (new IllegalNullKeyException());
    }
    if (!this.contains(key)) {
      throw (new KeyNotFoundException());
    }
    return lookup(root, key).getVal();
  }

  /**
   * finds and returns the node associated with the given in the tree/subtree rooted at rootKey
   * 
   * @param rootKey - root of tree/subtree
   * @param key     - key to find
   * 
   * @return the node associated with the given key
   */
  protected BSTNode<K,V> lookup(BSTNode<K, V> rootKey, K key) {
    if (rootKey.getKey().compareTo(key) == 0) {
      return rootKey;
    } else if (rootKey.getKey().compareTo(key) < 0) {
      if (rootKey.getRightChild() == null) {
        return null;
      }
      return lookup(rootKey.getRightChild(), key);
    } else {
      if (rootKey.getLeftChild() == null) {
        return null;
      }
      return lookup(rootKey.getLeftChild(), key);
    }
  }

  /**
   * Returns true if the key is in the data structure If key is null, throw IllegalNullKeyException
   * Returns false if key is not null and is not present
   * 
   * @param key - the key that is being searched for
   */
  @Override
  public boolean contains(K key) throws IllegalNullKeyException {
    if (key == null) {
      throw (new IllegalNullKeyException());
    }
    if (root == null) {
      return false;
    }
    if (root.getKey().compareTo(key) == 0) {
      return true;
    } else {
      return containsHelper(root, key);
    }
  }

  /**
   * recursive helper method that helps contain method
   * 
   * @param rootKey - root of tree/subtree 
   * @param key - key that is being searched for
   * @return true if key is found, false otherwise
   */
  protected boolean containsHelper(BSTNode<K, V> rootKey, K key) {
    if (rootKey.getKey().compareTo(key) == 0) {
      return true;
    }
    if (rootKey.getKey().compareTo(key) < 0) {
      if (rootKey.getRightChild() == null) {
        return false;
      } else {
        return containsHelper(rootKey.getRightChild(), key);
      }
    } else {
      if (rootKey.getLeftChild() == null) {
        return false;
      } else {
        return containsHelper(rootKey.getLeftChild(), key);
      }
    }
  }

  /**
   * Returns the number of key,value pairs in the data structure
   */
  @Override
  public int numKeys() {
    return this.numKeys;
  }

  /**
   * Returns the key that is in the root node of this BST. If root is null, returns null.
   * 
   * @return key found at root node, or null
   */
  @Override
  public K getKeyAtRoot() {
    if (root == null) {
      return null;
    }
    return root.getKey();
  }

  /**
   * Tries to find a node with a key that matches the specified key. If a matching node is found, it
   * returns the returns the key that is in the left child. If the left child of the found node is
   * null, returns null.
   * 
   * @param key A key to search for
   * @return The key that is in the left child of the found key
   * 
   * @throws IllegalNullKeyException if key argument is null
   * @throws KeyNotFoundException    if key is not found in this BST
   */
  @Override
  public K getKeyOfLeftChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
    if (key == null) {
      throw (new IllegalNullKeyException());
    }
    if (!this.contains(key)) {
      throw (new KeyNotFoundException());
    }
    if (lookup(root, key).getLeftChild() == null) {
      return null;
    }
    return lookup(root, key).getLeftChild().getKey();
  }

  /**
   * Tries to find a node with a key that matches the specified key. If a matching node is found, it
   * returns the returns the key that is in the right child. If the right child of the found node is
   * null, returns null.
   * 
   * @param key A key to search for
   * @return The key that is in the right child of the found key
   * 
   * @throws IllegalNullKeyException if key is null
   * @throws KeyNotFoundException    if key is not found in this BST
   */
  @Override
  public K getKeyOfRightChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
    if (key == null) {
      throw (new IllegalNullKeyException());
    }
    if (!this.contains(key)) {
      throw (new KeyNotFoundException());
    }
    if (lookup(root, key).getRightChild() == null) {
      return null;
    }
    return lookup(root, key).getRightChild().getKey();
  }

  /**
   * Returns the height of this BST. H is defined as the number of levels in the tree.
   * 
   * If root is null, return 0 If root is a leaf, return 1 Else return 1 + max( height(root.left),
   * height(root.right) )
   * 
   * Examples: A BST with no keys, has a height of zero (0). A BST with one key, has a height of one
   * (1). A BST with two keys, has a height of two (2). A BST with three keys, can be balanced with
   * a height of two(2) or it may be linear with a height of three (3) ... and so on for tree with
   * other heights
   * 
   * @return the number of levels that contain keys in this BINARY SEARCH TREE
   */
  @Override
  public int getHeight() {
    if (root == null) {
      return 0;
    } else {
      return getHeightHelper(root, 0);
    }
  }

  /**
   * recursive helper method that recursively finds height
   * 
   * @param rootKey - root of tree/subtree of which height is being found
   * @param height - height of rootKey parameter.
   * @return the height of the tree/subtree rooted at rootKey
   */
  protected int getHeightHelper(BSTNode<K, V> rootKey, int height) {
    if (rootKey != null) {
      height++;
    }
    if (rootKey.getLeftChild() != null && rootKey.getRightChild() != null) {
      return Math.max(getHeightHelper(rootKey.getLeftChild(), height),
          getHeightHelper(rootKey.getRightChild(), height));
    } else if (rootKey.getLeftChild() != null) {
      return getHeightHelper(rootKey.getLeftChild(), height);
    } else if (rootKey.getRightChild() != null) {
      return getHeightHelper(rootKey.getRightChild(), height);
    }
    return height;
  }
  
  /**
   * sets the root of the BST
   * 
   * @param root - new node to set root to 
   */
  protected void setRoot(BSTNode<K,V> root) {
    this.root = root;
  }
  
  /**
   * returns the root of BST
   * 
   * @return the root of the BST
   */
  protected BSTNode<K,V> getRoot() {
    return this.root;
  }
}
