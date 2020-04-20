一般在虚拟机里面安装软件都是放在/usr/local下面  所以此次的redis放在/usr/local/redis

想学redis 必须看懂redis.conf

当centos是新安装的，必须安装gcc环境
yum install gcc


在redis.conf中

然后修改里面的127.0.0.1  因为是分布式的。所以要很多服务器都可以访问，需要改成0.0.0.0

daemonize no 改为yes （允许后台执行）

先开启看。 redis-server ./redis.conf
这个时候开起来，然后查看redis是否开启，代码为
ps -ef | grep redis

开启客户端 redis-cli
使用get/set得取值

退出命令为exit

为了安全起见，给redis加密码，在redis.conf中添加
在conf中这种文件的时候，需要查找内容时。使用   ：/搜索的内容 按entry建即可查找
这次修改密码 requirepass 后跟密码
然后需要重启redis

reids 下的utils 有个install_server.sh




//todo：关于数据库
当想把手机号以int类型插入数据库，但是发现navicate中int只能达到10位数，不能存储11位，所以数据库选用bigint
来修饰， java里使用long类型


不提倡在service引入别人的dao，要引的话就引别人的service


//todo：关于启动jar包

先使用idea的Life。。下的package 双击。产生的target文件下的。。。jar拖到服务器下

在服务器上启动jar包，  nohub java -jar jar包的名字 &

然后按确定，就会在jar包的同文件夹下产生一个tomcat的日志文件夹，

//todo：关于redis放到centos的步骤
查看虚拟机是否有jcc编译环境
1.去官网下载tar.gz的包。
2.放到centos 的tmp目录，进行解压，解压命令 tar -zvxf 包名
3.把这个解压出来的redis-5.0.4 移到usr的local下面  mv 包名 地址（/usr/local/redis（在这个local包下取名为redis。可以先
不创建））
4.进入这个usr/local/redis   然后输入make MALLOC=libc（输入make是错误。一直报错）
5.make install

然后找到redis.conf 修改里面 #127.0.0.1  还有不保护线程  允许后台运行 设置密码。有个什么pass的

去utils这个文件夹。执行install_server.sh

todo：关于centos7的防火墙
查看防火墙状态
firewall-cmd --state

停止firewall
systemctl stop firewalld.service

开启
systemctl start firewalld

禁止firewall开机启动
systemctl disable firewalld.service


添加端口
firewall-cmd --zone=public --add-port=61001-62000/tcp --permanent

删除端口
add改成remove


>>>重启防火墙，添加完端口必须写这句话

firewall-cmd --reload






需要看拦截器，就是不需要每次都查看有没有用户登录没有
@NeedLogin这个标签

mycat 阿里巴巴分库分表中间件

todo：关于文件修改
vi /etc/profile
即时保存
source /etc/profile

todo：安装rabbitmq
(先安装erlang，在安装rabbitmq)
首先安装依赖erlang
1.进入erlang官网
www.erlang.org 下载tar.gz文件

2.进入rabbitmq官网，拉倒中下download的地方，选择Linux,BSD那一行的Generic下载下面那个

都放到usr/src下面

3.yum install ncurses-devel   解决centos下缺ncurses库的问题


4.解压otp这个玩意
tar xf otp_src_22.0.tar.gz
进入解压之后的文件夹  （./configure --prefix=/usr 意思就是安装到什么位置）
./configure --prefix=/usr/local/erlang22 -without-javac
make
make install

测验erlang安装成功没有
去/usr/local/erlang22 下的bin 目录，运行erl文件
能看到Eshell 就表示成功
退出用        halt（）.


先使用xz -d rabbit这个压缩包名字，等解压出来一个tar包，就使用tar xf raabbit...tar包


安装rabbitmq需要加一些插件

1.安装pyhton 依赖
yum install python -y

2. 安装 xmlto
yum install xmlto -y

2. 安装 python-simplejson
yum install python-simplejson -y

这个rabbitmq的tar包解压出来不需要make 和makeinstall ，直接移到usr/local/rabbit下


todo:在启动rabbitmq时发生了错误，后来重新安装了一下openssl，在删除了erlang

在springboot项目使用rabbitmq，首先添加pom文件，然后在app添加配置文件
4种交换机模式

todo:学习 swagger



todo:关于nginx的使用
1.先查看是否有gcc编译环境，使用gcc -v 查看有没有
2.没有的话就 yum install gcc-c++
3.安装pcre  yum install prce-devel
4.安装zlib yum install zlib zlib-devel
5.安装openssl yum install openssl openssl-devel

使用综合命令   yum -y  install   gcc zlib zlib-devel  prce-devel  openssl openssl-devel



下载nginx.tar 包  解压  ，解压完进入nginx包  ./configure  make  make install

//todo：想安装某个软件
先使用rpm -qa | grep 软件名称来检测是否下载了该软件


todo：关于mysql远程登录
GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' IDENTIFIED BY '123456' WITH GRANT OPTION;      表示授权所有权限给root名称  密码123456 的客户端

//todo：关于git
首先去git官网下载 linux版本的压缩包
然后去yum 一大堆的依赖包
解压 git包

居然没有./cong  这一步
make的时候可以写成  make prefix=/usr/local all
make install改成 make prefix=/usr/local install

查看版本的时候  git --version （两个-）
然后从二、 开始看，我连接的是github
https://blog.csdn.net/wugenqiang/article/details/81215396














