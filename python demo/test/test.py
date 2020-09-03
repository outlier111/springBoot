#!/usr/bin/env python3
# -*- coding: utf-8 -*-
__author__ = "Spring"

import re,math
import mysql_util

# 数据类型
a = 666
print("a = %s , %s"%(a , type(a)))       # int
a = 666.66
print("a = %s , %s"%(a , type(a)))        # float
a = "Hello World"
print("a = %s , %s"%(a , type(a)))         # str
a = None;
print("a = %s , %s"%(a , type(a)))          # NoneType
a = True
print("a = %s , %s"%(a , type(a)))           # bool
a = ["jige","java",2828]
print("a = %s , %s"%(a , type(a)))           # list
a = ("jige","java",2828)
print("a = %s , %s"%(a , type(a)))           # tuple
a = {"jige","java",2828}
print("a = %s , %s"%(a , type(a)))            # set
a = set(["jige","java",2828])
print("a = %s , %s"%(a , type(a)))
a = {"name":"jige","class":"java","age":28}
print("a = %s , %s"%(a , type(a)))             # dict

# 运算符
print(16//4)              # 地板除  取整
print(2 ** 3)             # 乘方

# 字符串
print(u'中文')           # 后面字符串是以Unicode编码
print(r'dddd')           # 后面字符串是普通字符串
print(b'qwerr')          # 后面是bytes

# ASCII码转换
print("98--->%s;a--->%s"%(chr(98),ord('a')))

# encode & decode
print("jige".encode("ascii"))            # b'jige'
print("鸡哥".encode("utf8"))              # b'\xe9\xb8\xa1\xe5\x93\xa5'
print(b'\xe9\xb8\xa1\xe5\x93\xa5'.decode("utf8"))           # 鸡哥

# 函数
print(len("asdfghsjdfhsdjkl"))   # 长度  16
print(len("你好啊"))                 # 长度  2
print(len("你好啊".encode("utf8")))   # 长度  9

# 替换
print("Hello World".replace("l","----"))

# 查找
print("Hello World".find("r"))         # 第一次出现的下标
print("Hello World".rfind("l"))         # 最后一次出现的下标

print("Hello World".isspace())     # 是否为空格 , 包括Tab

print("%s-----%2d------%03d"%(23, 6, 16))      # 2d不足两位左边补空格  03d 不足三位左边补0
print("%f-----%.2f"%(25.333, 66.6666))        # 正常情况下保留6位， .2f 保留2位

print("%x"%36)                 # 转换位为16进制
print("%d %% %d"%(3, 5))        # 3 % 5

l = range(1, 10)
print(list("%d"% x for x in l))                # ['1', '2', '3', '4', '5', '6', '7', '8', '9']

print("Hi {0}, 今天赚了{1:.2f}元钱".format("鸡哥", 66.6666))
print("Hi {0}, 今天赚了{1}元钱".format("鸡哥", 66.6666))
print("Hi {0}, 今天赚了{1}元钱".format("鸡哥", "%.1f"% 66.6666))

print("和".join(["猫","老鼠","狗"]))

# ======   正则表达式   =======
# email_re = "^[\w-] + (\. [\w-] + ) *@ [\w-] + (\. [\w-] + ) + $"     不能有空格么
email_re = "^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+$"
if re.match(email_re , "asdfghjkl@163.com"):
    print("ok")
else:
    print("error")

#     切分字符串
print("Hello World".split(" "))
print(re.split(r'\s+',"python : Hello World"))
print(re.split(r"[\s\ \:]+","python : Hello World"))

# 分组
match_re = re.match(r'^(\d{3})-(\d{3,8})$',"010-1234567")
print(match_re.group())
print(match_re.group(0))
print(match_re.group(1))
print(match_re.group(2))


new_line = r'截至9月2日0时，全省累计报告新型冠状病毒肺炎确诊病例653例(其中境外输入112例），' \
           r'累计治愈出院626例，死亡3例，目前在院隔离治疗24例，964人尚在接受医学观察'
new_line_re = r'^截至9月2日0时，全省累计报告新型冠状病毒肺炎确诊病例(\d+)例\(其中境外输入(\d+)例\），' \
           r'累计治愈出院(\d+)例，死亡(\d+)例，目前在院隔离治疗(\d+)例，(\d+)人尚在接受医学观察$'
new_line_match = re.match(new_line_re, new_line)
print(new_line_match.group(1))
print(new_line_match.group(2))
print(new_line_match.group(3))
print(new_line_match.group(4))
print(new_line_match.group(5))
print(new_line_match.group(6))

new_line_compile = re.compile(new_line_re)
print(re.search(new_line_compile, new_line).group(1))
print(re.search(new_line_compile, new_line).group(2))
print(re.search(new_line_compile, new_line).group(3))
print(re.search(new_line_compile, new_line).group(4))
print(re.search(new_line_compile, new_line).group(5))
print(re.search(new_line_compile, new_line).group(6))

#  list
l = ["cdsa",66,"春",25.49,None,True]
print(l)
l2 = list(range(1,10))
print(l2)
l.append("java")       # 添加
print(l)
l.insert(3, "python")   # 指定添加
print(l)
l.pop()           # 删除
print(l)
l.pop(3)           # 指定删除
print(l)

l.append(l2)
print(l)
l += l2
print(l)
l[0] = "jige"
print(l)

#  tuple
t = ("cdsa",66,"春",25.49,None,True,l)
print(r't = ', t)
t2 = tuple(range(1,10))
print(t2)

# =========== dict ============
d = {"name":"jige","age":"18","sex":"男"}
print(d)
print(d.get("name"))
print(d.get("name1","xinag"))
d["name"] = "outlier"
print(d)
d["class"] = "java"
print(d)
d.pop("class")
print(d)
print(len(d))

# =============  set  ==============
s = set(["jige",True,None,66,23.45])            # 据多次实验，set集合删除是从第一个开始   添加是无序的
s2 = {"xiang",False,None,55,12.34}
print(s)
s.add("java")
print(s)
s.pop()
print(s)
s.add("python")
# s.remove(1)
s.remove("jige")
print(s)
print(s2)
print(s & s2)
print(s | s2)

# ========= 判断语句 ==========
a = 24
if a < 10:
    print("小于10了")
elif a < 20:
    print("小于20了")
else:
    print("大于20了")

# ========== 三目运算符 ==========
a, b, c = 1, 2, 3
print(a if(b > c) else c)

# ========== 循环语句 ==========
l = list(range(1, 10))
for i in l:
    print(i)
i = 100
while (i > 90):
    print(i)
    i -= 1


# ========== 函数 ==========
def test(a):
    a *= 5
    return a
print(test(3))


def test_2(x , y = "jige"):
    print(x ,y)

def test_3(*num):                 # 可变参数
    count = 0
    for i in num:
        count += i
    return count

def test_4(name, **aji):             # 可变关键字参数
    if "city" in aji:
        print("name:%s, city:%s"%(name,aji.get("city")))
    else:
        print("name:%s, city:%s"%(name,"chengdu"))

def test_5(name, *, city):
    if not isinstance(name, (str,)):
        raise TypeError("Type error")
    print("name:%s, city:%s" % (name, city))

if __name__ == "__main__":
    print(test(6))
    test_2("xiang")
    test_2("xiang","java")
    print(test_3(*list(range(1, 10))))
    print(test_3(1,2,3,4,5,6,7,8,9,10))
    test_4("jige", **{"sex":"男"})
    test_4("jige", **{"sex":"男","city":"guiyang"})
    test_5("xiang", city="chongqing")
    # test_5(111, city="chongqing")

# ==== 内置函数 ====
print(int("22")) # 数据类型转换函数，注意，如果定义变量名和函数名一样，则不会调用该函数，会报错
print(float("22.2"))
print(str(22))
print(abs(-111)) # abs函数，求绝对值
print(max(12, 34, 123.4)) # max函数，求最大值
print(min(-21, -11, 0, 22.3)) # min函数，求最小值
print(" aa bb  cc  ".strip()) # 字符串去前后空格
print("['6K-8K']".strip('[\'\']')) # 移除字符串头尾指定的字符
print(hex(12)) # hex函数，将十进制数转十六进制
print("%x"%15)                 # 转换为16进制
print(math.sqrt(3)) # 求平方根
print(sum(range(1, 101))) # 求和
print(sum(list(range(101))))
print("cdaDcdsa".capitalize()) # 将字符串第一个字符变成大写，其他小写
