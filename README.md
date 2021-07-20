# wxnUtils
一个工具库  

**征集名字ing**  

# 脚本工具
src.wxn.util.scripts下有一些工具脚本

## email
email_delete.py 可删除符合条件的邮件，可用于删除报警邮件，参照[利用python读取邮件](https://blog.csdn.net/lys_828/article/details/104120339)食用

注意：在163邮箱测试失败（[163作怪](http://stackoverflow.com/questions/27797705/python-login-163-mail-server)），qq邮箱可用

## txt
一些对txt做变换的脚本


# 数组工具
## Tools
数组的工具方法  

**功能：**
1. exchange：交换数组两个元素  
2. insertionSort：插入排序  
3. quickSort：快速排序  
  (1) partitionLomuto：Lomuto划分法  
  (2) partitionHoare：Hoare划分法（默认）  
4. countingSort：计数排序  
  元素要求：元素为整数 元素值范围 range [0, k]  
5. radixSort：基数排序  
  根据数位排序  
6. bucketSort：桶排序  
  元素要求：小数，元素值范围 range [0, 1)，元素均匀、独立地分布在[0, 1)区间上。  
  即使不服从均匀分布，只要所有桶大小的平方和与总的元素数呈线性关系，也能在线性时间完成。  
  
**待补充：**
1. 目前只实现了int数组的排序，待补充其他类型数组的排序  
2. 待补充更多的排序方法  

  
# 数据结构  
## Heap  
数组实现的最大堆  

**功能：**
1. maxHeapify: 从某个索引处堆化 (siftUp)  
2. buildMaxHeap: 从数组建堆  
3. maxHeapInsert: 插入元素  
4. heapIncreaseKey: 增加某个元素的值  
5. heapExtractMax: 弹出最大值  
6. heapMax: 获取最大值  
7. get: 获取某个索引处元素的值  
8. heapSort: 堆排序  
9. length：数组长度  
10. size：数组中可用元素个数  

**待补充：**  
1. 增加comparator  
2. 分别用 Comparable 和 Comparator 两种方法作比较，并根据此实现功能方法  
  
  
## AbstractTree  
抽象树  

**功能：**  
1. levelTraversal: 层序遍历  
2. preorder: 先序遍历  
3. inorder: 中序遍历  
4. postorder：后序遍历  
  
  
## BinarySearchTree  
二叉搜索树  
继承AbstractTree  
支持用 Comparable 和 Comparator 两种方法作比较  

**功能：**
1. BinarySearchTree: 从数组，集合，根结点数据，根结点数据+比较器 建树  
2. insert: 插入一个数据  
3. contains: 判断某个数据是否存在于树中    
4. successor: 获取某个数据的后继数据  
  不存在抛 NoSuchElementException  
5. predecessor：获取某个数据的前驱数据  
  不存在抛 NoSuchElementException  
6. min: 获取树中最小数据  
7. max: 获取树中最大数据  
8. delete：删除某个数据所在结点  


# 测试
test包中有一些测试类  
