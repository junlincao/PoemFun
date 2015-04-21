# PoetryFan
大诗歌


-关于.proto编译，若修改了.proto文件，则需要重新编译成java文件，放在com.cjl.poetryfan.proto包内
-建议使用 protocal buffer compiler插件，修改后自动编译（指定编译后文件路径为PoetryFan\app\src\main）。
-由于和poetryfanServer公用同一套proto文件所以需要同时更新poetryfanServer的.proto文件