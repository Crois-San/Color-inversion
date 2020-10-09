#include <jni.h>

void invertPixels(long int *row, long int *a, jint count);

JNIEXPORT void JNICALL Java_Code_blue(JNIEnv *env, jclass class, jintArray array, jintArray a, jint count) {
	jint *arrayPointer = (*env) -> GetIntArrayElements(env, array, 0);
	jint *aa = (*env) -> GetIntArrayElements(env, a, 0);
	//printf("Before: %d, ", *arrayPointer);
	invertPixels(arrayPointer, aa, count);
	//printf("After: %d\n", *arrayPointer);
	(*env) -> ReleaseIntArrayElements(env, array, arrayPointer, 0);
}

void invertPixels(jint *row, jint* a, jint count) {
	asm("mov edi,[ebp+8]");
    asm("mov esi,[ebp+12]");
    asm("mov ecx,[ebp+16]");
    asm("for:");

    asm("movdqu xmm0,[edi]");
    asm("movdqu xmm1,[esi]");
    asm("paddd xmm0, xmm1");
    asm("movdqu [edi],xmm0");

    asm("add edi,16");
    asm("sub ecx,1");
    asm("jnz for");
}