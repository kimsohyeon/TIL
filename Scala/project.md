# 스칼라 프로젝트 구조

스칼라 프로젝트를 시작하면 sbt 구조를 따라서 짜는게 좋다.

- sbt - directory structure
- https://www.scala-sbt.org/1.x/docs/Directories.html


# sbt-assembly

fat JAR (모든 dependency를 포함하는 하나의 jar)을 만들 수 있는 라이브러리.
https://github.com/sbt/sbt-assembly
- test in assembly := {} => skip test
- shading : 두 개의 프로젝트의 라이브러리 버전을 맞춰줌 
