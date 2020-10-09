C:/Users/1096470/Desktop/java-native/jdk1.8.0_231/bin/javac Code.java
C:/Users/1096470/Desktop/java-native/jdk1.8.0_231/bin/javah Code
gcc -Wl,--add-stdcall-alias -shared -I C:/Users/1096470/Desktop/java-native/jdk1.8.0_231/include -I C:/Users/1096470/Desktop/java-native/jdk1.8.0_231/include/win32 -masm=intel Code.c -o Code.dll
pause