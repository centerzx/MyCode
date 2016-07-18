# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in D:\Android\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
 #设置混淆的压缩比率 0 ~ 7 
-optimizationpasses 5

#混淆时不会产生形形色色的类名,包名不混合大小写
#-dontusemixedcaseclassnames 

#不去忽略非公共的库类
#-dontskipnonpubliclibraryclasses

 #优化  不优化输入的类文件
#-dontoptimize

 #预校验
#-dontpreverify

 #混淆时是否记录日志
#-verbose

#保护注解
-keepattributes *Annotation*
-keep class * extends java.lang.annotation.Annotation { *; }
#忽略警告
-ignorewarning

#####################记录生成的日志数据,gradle build时在本项目根目录输出################
#apk 包内所有 class 的内部结构
-dump class_files.txt
#未混淆的类和成员
-printseeds seeds.txt
#列出从 apk 中删除的代码
-printusage unused.txt
#混淆前后的映射
-printmapping mapping.txt

#####################记录生成的日志数据，gradle build时 在本项目根目录输出-end################


################混淆保护自己项目的部分代码以及引用的第三方jar包library#########################
#-libraryjars libs/umeng-analytics-v5.2.4.jar

#保留一个完整的包
#-keep class com.veidy.mobile.common.** {
#    *;
# }

#保持自定义控件类不被混淆
#-keepclasseswithmembers class * {
#    public <init>(android.content.Context, android.util.AttributeSet);
#}

#-keepclasseswithmembers class * {
#    public <init>(android.content.Context, android.util.AttributeSet, int);
#}
#保持 Parcelable 不被混淆
#-keep class * implements android.os.Parcelable {
#  public static final android.os.Parcelable$Creator *;
#}

#保持 Serializable 不被混淆
#-keepnames class * implements java.io.Serializable

#不混淆资源类
#-keepclassmembers class **.R$* {
#    public static <fields>;
#}

# 保持 native 方法不被混淆 
#-keepclasseswithmembernames class * {       
#    native <methods>;  
#}

#-keep class test.center.com.mylibrary.http.*{
# public <fields>;
# public <methods>;
# }
 
-keep class test.center.com.mylibrary.http.HttpResponseHandler{
 public <fields>;
 public <methods>;
 }
 
 -keep class test.center.com.mylibrary.http.HttpTaskListener{
  public <fields>;
  public <methods>;
 }
 
-keep class test.center.com.mylibrary.http.IotHttpClient{
   public <fields>;
   public <methods>;
 }
 
-keep class test.center.com.mylibrary.http.BaseResponse{
    public <fields>;
    public <methods>;
 }

-keep class test.center.com.mylibrary.http.HttpCmds{
    public <fields>;
    public <methods>;
 }

-keep class test.center.com.mylibrary.http.ResultCode{
    public <fields>;
    public <methods>;
 }

-keepclassmembers class * implements test.center.com.mylibrary.http.HttpTaskListener{
  public <fields>;
  public <methods>;
 }
 
#-keep class test.center.com.mylibrary.encrypt.*{
#public <fields>;
#public <methods>;
#}

-keep class test.center.com.mylibrary.encrypt.SecurityUtils{
public <fields>;
public <methods>;
}

-keep class test.center.com.mylibrary.encrypt.AESTools{
public <fields>;
public <methods>;
}

-keep class test.center.com.mylibrary.encrypt.DigestUtils{
public <fields>;
public <methods>;
}

-keep class test.center.com.mylibrary.common.*{
public <fields>;
public <methods>;
}