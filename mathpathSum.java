class Solution {

```
int ans;

int maxPathSum(Node root) {
    if (root == null) {
        return -1;
    }

    ans = Integer.MIN_VALUE;

    maxDown(root);

    if (ans == Integer.MIN_VALUE) {
        return -1;
    }

    return ans;
}

int maxDown(Node root) {
    if (root == null) {
        return Integer.MIN_VALUE;
    }

    // Leaf node
    if (root.left == null && root.right == null) {
        return root.data;
    }

    // Only right child
    if (root.left == null) {
        return root.data + maxDown(root.right);
    }

    // Only left child
    if (root.right == null) {
        return root.data + maxDown(root.left);
    }

    // Both children exist
    int left = maxDown(root.left);
    int right = maxDown(root.right);

    // Path connecting two leaf nodes
    ans = Math.max(ans, left + root.data + right);

    // Return maximum path from current node to a leaf
    return root.data + Math.max(left, right);
}
```

}
