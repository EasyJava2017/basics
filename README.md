第五章  数组
===

数组是一个基础的数据结构，它用来存储一组相同类型的元素的集合。数组非常有用，例如Java提供的集合类ArrayList、HashMap等都是基于数组来实现的。


数组是一种容器，用于存储数据。一旦定义了数组元素的类型，那么这个数组里面就只能存储这个类型的元素。需要记住的是，数组中的元素是从0开始索引。

本章我们介绍Java中的数组，主要内容包括：

数组的创建与初始化
数组元素访问
数组的常用操作
多维数组等。

## 5.1 数组的声明

一维数组的声明语法格式有两种，分别是

```
Type varName[]; // (1)
```
或
```
Type[] varName; // (2)
```

这里的Type类型可以是基本类型或任意的引用类型。
通常我们使用第(2)种方式，因为它把类型跟变量名分开，语义更加清晰。

例如，我们声明一个包含10个数字的 int 数组变量
```
int[] numbers;
```
但是，仅仅是上面的声明语句，我们还不能使用numbers变量。

![螢幕快照 2017-08-19 11.52.42.png](http://upload-images.jianshu.io/upload_images/1233356-585c4bb6feae133e.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

在 Java 中，需要对声明的数组变量进行初始化才能进行相关的操作。

```
java> int[] numbers = null;
int[] numbers = null
```
这里的 null 是引用类型的默认值。这个 null 值在 Java 中是一个非常特殊的值，我们将会在后面的章节中探讨。上面的代码会在栈内存中存储一个关于numbers数组变量的信息，我们可以用下面的图来表示


![声明数组变量 numbers](http://upload-images.jianshu.io/upload_images/1233356-ca883b99b826bf90.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

此时的numbers变量里已经存储了数组的类型信息了。
```
java> numbers instanceof  Object
java.lang.Boolean res2 = false
```
上面的数组对象的声明其实跟普通类的对象声明是一样的

```
java> class Person{}
Created type Person
java> Person p = null;
java.lang.Object p = null
java> p instanceof Person
java.lang.Boolean res12 = false
```



## 5.2 数组对象实例创建与初始化

数组在Java中其实也是一个对象，数组实例同样是使用new操作符创建的。只不过数组的声明语法比较特殊，它使用的是元素的类型加中括号 `Type[] varName` 的方式, 而普通的类型声明只需要使用 `Type varName `即可。

### 5.2.1 数组对象的创建

我们使用 new 关键字来创建一个数组对象实例。格式为：
```
数组元素类型[] 数组名 = new 数组元素类型[length]；
```
这个new 的过程会在堆空间中给我们的数组开辟内存空间。其中，length是数组的容量大小。数组是一个固定长度的数据结构，一旦声明了，那么在这个数组的生命周期内就不能改变这个数组的长度了。如果我们想动态扩容，就需要对数组进行拷贝来实现。ArrayList 的动态扩容就是使用的Arrays.copyOf方法。Arrays.copyOf 方法又使用了System.arraycopy 这个 native 本地方法。我们会在下面的小节中介绍。感兴趣的同学还可以阅读一下java.util.ArrayList类的代码。

数组是一种非常快的数据结构，如果已经知道元素的长度，那么就应该使用数组而非ArrayList等数据结构。



例如：

```
java> numbers = new int[10]
int[] numbers = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
 ```
这个过程图示如下


![创建一个数组对象实例](http://upload-images.jianshu.io/upload_images/1233356-5473270ce25f5cca.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)




因为数组是引用类型，它的元素相当于类的成员变量，因此数组分配空间后，每个元素也被按照成员变量的规则被隐式初始化。例如，没有初始化的整型数组元素都将默认值为0，没有初始化的boolean值是false， String对象数组是null。

```
java> boolean[] barray = new boolean[2]
boolean[] barray = [false, false]
```

数组的内置属性length指定了数组长度
```
java> numbers.length
java.lang.Integer res13 = 10
java> barray.length
java.lang.Integer res17 = 2
```




### 5.2.2 数组的初始化

我们既可以选择在创建数组的时候初始化数组，也可以以后初始化。

如果我们想在创建数组的同时就初始化元素，使用下面的方式

```
java> int[] numbers = new int[]{0,1,2,3,4,5,6,7,8,9}
int[] numbers = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
```
我们还可以省去 new int[], 直接使用花括号
```
java> int[] numbers = {0,1,2,3,4,5,6,7,8,9}
int[] numbers = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
```
这种极简单的形式，我们叫做数组字面量（Array Literals）。

需要注意的是, 如果我们使用一个未作初始化的数组对象，会导致空指针异常

```
java> int[] x = null;
int[] x = null
java> x[0]
java.lang.NullPointerException
```

我们也可以把数组定义以及分配内存空间的操作和赋值的操作分开进行，例如：

```
java> String[] s = new String[3];
java.lang.String[] s = [null, null, null]
java> s[0] = "abc";
java.lang.String res23 = "abc"
java> s[1]="xyz";
java.lang.String res24 = "xyz"
java> s[2]="opq";
java.lang.String res25 = "opq"
java> s
java.lang.String[] s = ["abc", "xyz", "opq"]
```

通常我们会使用 for 循环来初始化数组的元素, 例如：

```
java> int[] numbers = new int[10];
int[] numbers = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
java> for(int i = 0; i < 10; i++){
          numbers[i] = i * i;
      }

java> numbers
int[] numbers = [0, 1, 4, 9, 16, 25, 36, 49, 64, 81]
```


## 5.3 数组元素的访问

我们使用数组索引（下标）来访问数组的元素。另外，值得注意的是Java中的数组的边界检查，如果程序访问无效的数组索引，Java会抛出 `ArrayIndexOutOfBoundException` 异常。例如

```
java> String[] s = new String[3];
java.lang.String[] s = [null, null, null]
java> s[-1]
java.lang.ArrayIndexOutOfBoundsException: -1
java> s[3]
java.lang.ArrayIndexOutOfBoundsException: 3
java> s[4]
java.lang.ArrayIndexOutOfBoundsException: 4
```

我们可以看出，负数索引在Java中是无效的，会抛出ArrayIndexOutOfBoundException 。如果我们用大于等于数组长度的无效的索引来访问数组元素时也会抛出异常。


### 5.3.1 数组的索引

Java 的数组索引起始于0，[0]返回第一个元素，[length-1]返回最后一个元素。代码示例如下

```
java> int[] x = {1,2,3,4,5}
int[] x = [1, 2, 3, 4, 5]
java> x[0]
java.lang.Integer res26 = 1
java> x[x.length-1]
java.lang.Integer res27 = 5
```
我们可以看出，数组的索引index可以是整型常量或整型表达式。

需要注意的是，只有当声明定义了数组，并用运算符new为之分配空间或者把这个数组引用变量指向一个数组对象空间，才可以访问（引用）数组中的每个元素。

需要特别注意的是，这里的length是一个属性，不是方法，没有加括号（），我们这里特别说明是为了和String的length()方法做区别。

### 5.3.2 数组的存储

数组存储在Java堆的连续内存空间。如果没有足够的堆空间，创建数组的时候会抛出 `OutofMemoryError ` :

```
java> int[] xLargeArray = new int[10000000*1000000000]
java.lang.OutOfMemoryError: Java heap space
```

不同类型的数组有不同的类型，例如下面例子，intArray.getClass()不同于floatArray.getClass()

```
java> int[] intArray = new int[10]
int[] intArray = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
java> float[] floatArray = new float[10]
float[] floatArray = [0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0]

java> intArray.getClass()
java.lang.Class res5 = class [I
java> floatArray.getClass()
java.lang.Class res7 = class [F
```


我们不能存储double值在int数组中，否则导致编译错误。

```
java> intArray[5]=1.2
ERROR: incompatible types: possible lossy conversion from double to int
    intArray[5]=1.2;
                ^
```

但是反过来是可以的
 
```
java> floatArray[5]=1
java.lang.Float res8 = 1.0
```
因为 Java 有类型默认转换的机制。

### 5.3.3 遍历数组元素

for循环是一种迭代整个数组便捷方法。我们可以使用for循环初始化整个数组、访问的每个索引或更新、获取数组元素。
```
int[] numbers = new int[]{10, 20, 30, 40, 50};
 
for (int i = 0; i < numbers.length; i++) {
  System.out.println("element at index " + i + ": " + numbers[i]);
}
 ```

输出


element at index 0: 10
element at index 1: 20
element at index 2: 30
element at index 3: 40
element at index 4: 50



Java5中开始提供for each循环，使用for each循环可以避免ArrayIndexOutOfBoundException。这里是一个for each循环迭代的例子：

```
for(int i: numbers){
   System.out.println(i);
}
```
输出：
10
20
30
40
50


正如你看到的，for each循环不需要检查数组索引，如果你想逐个地访问所有的元素这是一种很好的方法。

但是同时因为我们不能访问索引，所以就不能修改数组元素的值了。


## 5.4 数组操作常用API

本节我们介绍数组的常用操作，包括Arrays 类 API、拷贝数组等。

Java API中提供了一些便捷方法通过java.utils.Arrays类去操作数组，通过使用Arrays类提供的丰富的方法，我们可以对数组进行排序，还可以快速二分查找数组元素等。

Arrays类的常用方法如下表所示：

|方法|功能说明|
|---|---|
|toString() | 将数组的元素以[1, 2, 3, 4, 5] 这样的字符串形式返回 |
|asList| 数组转List |
|copyOf() | 将一个数组拷贝到一个新的数组中|
|sort()| 将数组中的元素按照升序排列|
|binarySearch()| 二分查找方法：在数组中查找指定元素，返回元素的索引。如果没有找到返回-1。 注意：使用二分查找的时候，数组要先排好序。|

#### Arrays.toString ： 将数组转化成字符串
如果我们直接对一个数组调用 Object对象的 默认toString 方法，我们会得到如下输出
```
java> x
int[] x = [1, 2, 3, 4, 5]
java> x.toString()
java.lang.String res33 = "[I@1ddcf61f"
```
这样的信息，通常不是我们想要的。Arrays.toString()方法提供了一个更加有用的输出
```
java> Arrays.toString(x)
java.lang.String res34 = "[1, 2, 3, 4, 5]"
```
Arrays.toString针对基本类型提供了如下8个签名的方法

toString(boolean[] a)
toString(byte[] a)
toString(char[] a)
toString(double[] a)
toString(float[] a)
toString(int[] a)
toString(long[] a)
toString(short[] a)

对于引用类型，则提供了 toString(Object[] a) 方法。下面是 toString 传入一个引用类型参数的例子。

```

Person[] persons = new Person[2];
Person jack = new Person();
jack.name = "Jack";
jack.age = 18;
persons[0] = jack;
persons[1] = new Person();
println(Arrays.toString(persons));

```
输出：

[Person{name='Jack', age=18}, Person{name='null', age=0}]

其中，Person 类的代码如下

```
class Person {
    String name;
    int age;

    @Override
    public String toString() {
        return "Person{" +
            "name='" + name + '\'' +
            ", age=" + age +
            '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

```



#### Arrays.asList: 数组转List

Java中数组可以轻易的转换成ArrayList。ArrayList是一个使用频率非常高的集合类。ArrayList的优点是可以改变容量大小，ArrayList的动态扩容实现是通过创建一个容量更大的数组，然后拷贝当前数组的元素到这个新的数组来实现。

代码示例

```
        Integer[] bigX = {1,2,3};
        List<Integer> bigXlist = Arrays.asList(bigX);
        println("bigXlist size: " + bigXlist.size());
        println(JSON.toJSONString(bigXlist));

        String[] s = {"a","b","c"};
        List slist = Arrays.asList(s);
        println("slist size: " + slist.size());
        println(JSON.toJSONString(slist));
```

输出：

bigXlist size: 3
[1,2,3]
slist size: 3
["a","b","c"]


通过把数组转成 List，我们就可以方便地使用集合类的常用工具类方法了。例如，我们想要检查一个数组是否包含某个值，就可以如下实现

```
String[] s = {"a","b","c"};
List slist = Arrays.asList(s);
boolean b = slist.contains("a");  
System.out.println(b);  
// true  
```




需要注意的是，如果我们在使用基本类型来声明的数组上面调用Arrays.asList方法，结果可能并不是我们想要的 

```
        int[] x = {1,2,3};
        List<int[]> xlist = Arrays.asList(x);
        println("xlist size: " + xlist.size());
        println(JSON.toJSONString(xlist));
```
输出

xlist size: 1
[[1,2,3]]

这个 xlist 的 size 居然是 1  ?! 好奇怪。而且 int[] elementOfXList = xlist.get(0) 。这跟没调用 asList 的效果一样，我们拿到的仍然是个数组。

其实，这跟Arrays.asList的实现本身有关。

当使用 int[] 类型声明数组时， ArrayList 构造函数这里的array 参数类型是
int[1][] ，如下图所示

![使用 int[] 类型声明数组的ArrayList 构造函数array 参数](http://upload-images.jianshu.io/upload_images/1233356-58ba1acb88fbc1ba.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

而我们使用 Integer 类型声明数组时，ArrayList 构造函数这里的array 参数类型是Integer[3] ，如下图所示


![使用 Integer[] 类型声明数组的ArrayList 构造函数array 参数](http://upload-images.jianshu.io/upload_images/1233356-fd5e4845679186db.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

所以，我们不要使用Arrays.asList 方法来转换基本类型声明的数组时。如果要转换一定要使用基本类型的包装类型，这样才能得到你想要的结果。


#### Arrays.copyOf：拷贝数组


java.lang.System类提供了一个 native方法来拷贝元素到另一个数组。arraycopy方法签名如下
```
public static native void arraycopy(Object src,  int  srcPos,
                                        Object dest, int destPos,
                                        int length);
```

我们可以通过srcPos参数指定源数组 src 的拷贝下标位置，dest是目标数组，destPos是目标数组的拷贝下标位置, length参数来指定拷贝长度。。代码示例：

我们先创建源数组
```
java> String[] src = {"Java","Kotlin","Scala","JS"};
java.lang.String[] src = ["Java", "Kotlin", "Scala", "JS"]
```
目标数组
```
java> String[] dest = new String[7]
java.lang.String[] dest = [null, null, null, null, null, null, null]
```

从下标0开始拷贝，src 元素全部拷贝到 dest 中
```
System.arraycopy(src,0,dest,0,src.length)
```
结果

```
java> dest
java.lang.String[] dest = ["Java", "Kotlin", "Scala", "JS", null, null, null]
```

如果源数据数目超过目标数组边界会抛出IndexOutOfBoundsException异常
```
java> System.arraycopy(src,0,dest,0, 10)
java.lang.ArrayIndexOutOfBoundsException
```

我们可以看到，使用 System.arraycopy 方法，我们还要创建一个 dest 数组。有点费事。不用担心，Arrays 类中已经为我们准备好了 copyOf 方法。我们可以直接调用 copyOf 方法对数组进行扩容。函数定义如下
```
public static <T> T[] copyOf(T[] original, int newLength) {
        return (T[]) copyOf(original, newLength, original.getClass());
    }
```

其中方法实现里面调用的 copyOf 实现如下
```
    public static <T,U> T[] copyOf(U[] original, int newLength, Class<? extends T[]> newType) {
        @SuppressWarnings("unchecked")
        T[] copy = ((Object)newType == (Object)Object[].class)
            ? (T[]) new Object[newLength]
            : (T[]) Array.newInstance(newType.getComponentType(), newLength);
        System.arraycopy(original, 0, copy, 0,
                         Math.min(original.length, newLength));
        return copy;
    }
```
我们可以看出其内部实现也是调用了System.arraycopy方法。相当于是对System.arraycopy方法的再高一层次的抽象。在程序设计中，进行向上一层的抽象是最本质也是最实用的方法论之一。

代码示例：

```
java> s = Arrays.copyOf(s, s.length * 2)
java.lang.String[] s = ["Java", "Kotlin", "Scala", "JS", null, null, null, null]
```

#### Arrays.sort：数组元素排序
对数组元素进行升序排序。代码示例

```
java> Integer[] x = {10,2,3,4,5}
java.lang.Integer[] x = [10, 2, 3, 4, 5]
java> Arrays.sort(x)

java> x
java.lang.Integer[] x = [2, 3, 4, 5, 10]

java> String[] s = {"abc", "cba", "bca"}
java.lang.String[] s = ["abc", "cba", "bca"]
java> Arrays.sort(s)

java> s
java.lang.String[] s = ["abc", "bca", "cba"]

```

需要注意的是，调用 sort 方法时，传入的数组中的元素不能有 null 值，否则会报空指针异常
```
String[] s = {"JS", "Java", "Kotlin", "Scala", null, null, null, null}
java.lang.String[] s = ["JS", "Java", "Kotlin", "Scala", null, null, null, null]
java> Arrays.sort(s)
java.lang.NullPointerException

```



#### Arrays.binarySearch: 在传入的数组中二分查找指定的元素

我们首先使用简单的代码示例来看一下这个方法的使用

```
java> Integer[] x = {2,3,4,5,10}
java.lang.Integer[] x = [2, 3, 4, 5,10]
java> Arrays.binarySearch(x, 3)
java.lang.Integer res40 = 1
java> Arrays.binarySearch(x, 10)
java.lang.Integer res41 = 4
java> Arrays.binarySearch(x, 0)
java.lang.Integer res42 = -1
```

如果找到元素，返回其下标； 如果没找到，返回 -1 。

这个binarySearch方法定义如下

```
    public static int binarySearch(int[] a, int key) {
        return binarySearch0(a, 0, a.length, key);
    }
```

其中，binarySearch0则是标准的二分查找算法的实现

```
    private static int binarySearch0(int[] a, int fromIndex, int toIndex,
                                     int key) {
        int low = fromIndex;
        int high = toIndex - 1;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            int midVal = a[mid];

            if (midVal < key)
                low = mid + 1;
            else if (midVal > key)
                high = mid - 1;
            else
                return mid; // key found
        }
        return -(low + 1);  // key not found.
    }
```

二分查找算法要求待查找的数组必须是有序的。如果是无序的查找，我们通常只能遍历所有下标来搜索了。代码如下

```
    public int search(int[] nums, int target) {
        // 遍历每个元素
        for (int i=0; i<nums.length; i++) {
            if (nums[i] == target) {
                return i; // 找到元素，返回其下标
            }
        }

        // 如果没找到target
        return -1;
    }
```




## 5.5 多维数组

我们首先来创建一个2行3列的多维数组：

```
java> int[][] multiArray = new int[2][3]
int[][] multiArray = [[0, 0, 0], [0, 0, 0]]
```

这是一个长度是2的数组，它的每个元素 （ 例如 [0, 0, 0] ）里保存的是长度为3的数组。 多维数组其实也可以叫嵌套数组。下面是初始化多维数组的例子：

```
java> int[][] multiArray = {{1,2,3},{10,20,30}}
int[][] multiArray = [[1, 2, 3], [10, 20, 30]]

java> multiArray[0]
int[] res44 = [1, 2, 3]
java> multiArray[1]
int[] res45 = [10, 20, 30]
```

我们可以使用下面的图来形象地说明多维数组的含义


![多维数组示意图](http://upload-images.jianshu.io/upload_images/1233356-4635c7a5714aaa61.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)



多维数组就是以数组为元素的数组。上面的二维数组就是一个特殊的一维数组，其每一个元素都是一个一维数组。

我们可以先声明多维数组的第1维的长度，第2维的长度可以单独在初始化的时候再声明。例如：

我们首先声明一个2行的数组，这里我们并没有指定每一列的元素长度。代码如下
```
java> String[][] s = new String[2][]
java.lang.String[][] s = [null, null]
```

图示如下


![声明一个2行的数组](http://upload-images.jianshu.io/upload_images/1233356-7a45d27e63301db9.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)



我们来为每一行元素赋值，我们要的赋给每一行的值也是一个 String 数组

```
java> s[0] = new String[2]
java.lang.String[] res46 = [null, null]
java> s[1] = new String[3]
java.lang.String[] res47 = [null, null, null]
java> s
java.lang.String[][] s = [[null, null], [null, null, null]]
```

其中，s[0]=new String[2] 和 s[1]=new String[3] 是限制第2维各个数组的长度。

如下图所示


![s[0]=new String[2] 和 s[1]=new String[3] ](http://upload-images.jianshu.io/upload_images/1233356-0a2b883ea3748a98.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)





这个时候，我们已经基本看到了这个多维数组的结构了  [[null, null], [null, null, null]] 。 第1行是一个有2个元素的数组，第2行是一个有3个元素的数组。

然后，我们对每行每列的元素进行赋值

```
java>         s[0][0] = new String("Java");
java.lang.String res49 = "Java"
java>         s[0][1] = new String("Scala");
java.lang.String res50 = "Scala"
java>         s[1][0] = new String("Kotlin");
java.lang.String res51 = "Kotlin"
java>         s[1][1] = new String("SpringBoot");
java.lang.String res52 = "SpringBoot"
java>         s[1][2] = new String("JS");
java.lang.String res53 = "JS"

```

最终，我们的数组被初始化为

```
java> s
java.lang.String[][] s = [["Java", "Scala"], ["Kotlin", "SpringBoot", "JS"]]
```







二维数组中的元素引用方式为 arrayName[index1][index2]。 代码示例如下


```
java> s[0][1]
java.lang.String res54 = "Scala"
java> s[1][0]
java.lang.String res55 = "Kotlin"
```

访问不存在的元素，同样抛出ArrayIndexOutOfBoundsException 异常

```
java> s[0][2]
java.lang.ArrayIndexOutOfBoundsException: 2
```


## 本章小结



本章示例代码： https://github.com/EasyJava2017/basics
