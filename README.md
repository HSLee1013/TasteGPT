![image](https://github.com/user-attachments/assets/229b11db-92b9-4217-90dc-f0cde261689f)

<h1 align="center">
  맛 GPT
</h1>
<p align="center">맛집 소개 사이트</p>
<p align="center">사용자가 원하는 가격대, 지역, 카테고리를 고려하여 적합한 식당을 추천하고<br>사용자 리뷰 및 평점 기능, 사업자 식당 등록 및 관리, 관리자의 사용자 문의 확인 기능을 제공하는 웹 사이트</p>

---
## 기능 설명

<p align="center">
  <h2>Built With</h2>  
</p>

   <img src="https://img.shields.io/badge/Java-%23FF7800"> <img src="https://img.shields.io/badge/Maven-%23C71A36?logo=apachemaven&logoColor=white"> <img src="https://img.shields.io/badge/HTML-%23E34F26?logo=html5&logoColor=white"> <img src="https://img.shields.io/badge/JavaScript-F7DF1E?logo=javascript&logoColor=white"> <img src="https://img.shields.io/badge/CSS-%231572B6?logo=css3&logoColor=white"> <img src="https://img.shields.io/badge/Eclipse-2C2255?logo=eclipseide&logoColor=white"> <img src="https://img.shields.io/badge/MySQL-4479A1?logo=mysql&logoColor=white">
   
   

## Contents

<p align="center">
  <a href="#what-is">What is?</a> |
  <a href="#key-features">Key Features</a> |
  <a href="#how-to-use--development-setup">How To Use</a> |
  <a href="#authors">Authors</a>
</p>

## What is?

웹 서버 개발 기술을 활용하여 팀을 구성하여 맛집 검색 사이트를 제작.<br>
사용자가 원하는 가격대, 지역, 카테고리를 고려하여 적합한 식당을 추천하고 사용자 리뷰 및 평점 기능, 사업자 식당 등록 및 관리, 관리자의 사용자 문의 확인 기능을 제공하는 웹 사이트 제작.<br>
사업주가 직접 식사의 평균 가격을 입력하여 인당 비용에 따른 검색 기능을 구현하여 차별점을 둠.<br>

## Key Features

-키워드 검색 기능
![image](https://github.com/user-attachments/assets/20863c2a-9047-44a7-987f-41d5c1ed13d8)

-지역 검색 기능
![image](https://github.com/user-attachments/assets/abce46c7-1c8b-493a-be90-7258d9cd38a2)

-가격대별 검색 기능
![image](https://github.com/user-attachments/assets/b20e15a2-18c9-44f5-bf23-ec4ff9072bbd)

-유저 관련 기능(로그인/회원가입/아이디 찾기/비밀번호 찾기)<br>
<img src="https://github.com/user-attachments/assets/463c2861-6bb6-46a1-9405-ad274c746380" width="49%" height="49%">
<img src="https://github.com/user-attachments/assets/30d3779c-0647-4b79-b7bd-b265bcb41439" width="49%" height="49%">
<img src="https://github.com/user-attachments/assets/afd2361a-589e-4b55-b477-d3c5366aa7f0" width="49%" height="49%">
<img src="https://github.com/user-attachments/assets/6237c17a-3e06-4601-8060-a5bfeb559d3f" width="49%" height="49%">

![image](https://github.com/user-attachments/assets/30d3779c-0647-4b79-b7bd-b265bcb41439)


- 핵심 기능 서술하기 위해 텍스트, 하이퍼링크와 스크린샷, 도표 등을 첨부해주세요.

> [!TIP]
> 헤딩, 단락, 링크 및 도표, 목록, 인용 코드, 체크박스 작성법 등을 알아보세요
> 
> [Markdown 기분 문법 알아보기](https://www.markdownguide.org/basic-syntax/)
>
> [Markdown Github 확장 문법 알아보기](https://docs.github.com/ko/get-started/writing-on-github/getting-started-with-writing-and-formatting-on-github/basic-writing-and-formatting-syntax)

> 도표 생성 문법이 어렵다면 생성기를 활용하세요 => [MD Table Generator](https://www.tablesgenerator.com/markdown_tables)

## How To Use / Development setup

* 사용 환경과 사용법 설명
* 또는 개발을 위한 개발환경 구축 설명하기

> To clone and run this application,
> you'll need [Git](https://git-scm.com)
> and [download Maven](https://maven.apache.org/download.cgi)
> Maven is a Java tool, so you must have Java installed in order to proceed. Set the JAVA_HOME environment variable pointing to your JDK installation or have the java executable on your PATH.
>
> From your command line:

```bash
# Clone this repository
$ git clone https://github.com/username/app-repository

# Go into the repository
$ cd app-repository

# Install dependencies
$ maven package

# Run the app
$ java -cp target/my-app-1.0-SNAPSHOT.jar com.mycompany.app.App
```

> **Note** > [Oracle JDK](https://www.oracle.com/java/technologies/downloads/) or use OpenJDK.

## Repository Structure

```sh
└─EnjoyFood_Project
     ├─README.md
     ├─.gitignore
     └─src
          └─main
              ├─java
              │  ├─cafeteria
              │  ├─config
              │  ├─enjoyfood
              │  ├─main
              │  │  └─controller
              │  └─user
              │     ├─controller
              │     ├─model
              │     └─suggestion
              └─webapp
                 ├─META-INF
                 ├─popup
                 ├─static
                 │  ├─css
                 │  ├─ico
                 │  └─js
                 └─WEB-INF
                    ├─module
                    └─view
```
    
## Authors

* 이학석 [GitHub](https://github.com/HSLee1013)


