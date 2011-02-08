@echo off

set ANT_HOME=%~dp0\apache-ant-1.6.3
SET JAVA_HOME=d:\IBM\WebSphere\AppServer1\java
rem SET JAVA_HOME=c:\Program Files\WebSphere\AppServer\java
SET PATH=%JAVA_HOME%\bin;%ANT_HOME%\bin;%PATH%
SET CLASSPATH=%ANT_HOME%\lib\asm-2.0.jar;%ANT_HOME%\lib\asm-util-2.0.jar;%ANT_HOME%\lib\bsf-2.3.0-rc1.jar;%ANT_HOME%\lib\groovy-all-1.0-jsr-04.zip;%CLASSPATH%

rem @echo ---- The Java Version is : -----
rem java -version
rem @echo --------------------------------
