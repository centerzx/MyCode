#include <jni.h>
JNIEXPORT jstring JNICALL
Java_test_center_com_mylibrary_jni_IotJniUtils_getUrlString(JNIEnv *env, jobject instance,jint type) {
    if(type==1){
        return (*env)->NewStringUTF(env, "http://183.230.40.222:8180/httpopenapi/auth/nio/serviceManager");  
    }else{
        return (*env)->NewStringUTF(env, "http://183.230.40.222:8180/httpopenapi/noauth/nio/serviceManager");
    }
}

JNIEXPORT jstring JNICALL
Java_test_center_com_mylibrary_jni_IotJniUtils_getLocalKeys(JNIEnv *env, jobject instance) {
return (*env)->NewStringUTF(env, "app.heluyou@2014");
}

JNIEXPORT jstring JNICALL
                  Java_test_center_com_mylibrary_jni_IotJniUtils_getAccessSecrey(JNIEnv *env, jobject instance) {
    return (*env)->NewStringUTF(env, "910fb531-4d0");
}