# 💻 CRUD 게시판 프로젝트

### ✏️ 프로젝트 구조 소개

<div align=center>
  <table style="border:none">
    <tr>
      <td>
        <img width="300" alt="main" src="https://github.com/mansooonuna/task_prj/assets/102853354/9929bd99-a7a3-4f44-92ec-dc0cf5e31f51"/>
      </td>
      <td>
        <img align="" width="100%" src="https://github.com/mansooonuna/task_prj/assets/102853354/1d4c05bf-5967-4cec-ba07-c22063215adb">
        <ul>
            <li>레이어드 아키텍처 패턴을 기반으로 컨트롤러(Controller) - 서비스(Service) - 리포지토리(Repository)로 관심사를 분리하여 구성하였습니다.</li>
        </ul>
      </td>    
    </tr>
  </table>
</div>

### ❓ 개발환경
<ul>
<li>IntelliJ IDEA 2023.1.4 (Ultimate Edition)</li>
<li>Java 17</li>
<li>Gradle 8.2.1</li>
<li>Spring Boot 2.7.12</li>
<li>MySQL 8.0.33</li>
</ul>

### ❓ 기술 세부 스택
Spring Boot
<ul>
    <li>Spring Web</li>
    <li>Spring Data JPA</li>
    <li>MySQL Driver</li>
    <li>Lombok</li>
    <li>Spring Boot DevTools</li>
    <li>validation</li>
</ul>

### ✅ 빌드 및 실행 방법
<ol>
    <li>로컬 환경에서 사용할 MySql DB가 필요합니다.</li>
        <details>
        <summary>데이터베이스의 이름을 'board'로 설정하여 생성해주세요.</summary>
        <br/>
        <div align=center>
        <img width="100%" src='https://github.com/mansooonuna/task_prj/assets/102853354/96221ac2-e075-44c3-a3bd-586673e39235'>
        </div>
        </details>
    <li>zip 파일을 압축해제 하여 DB를 연결하고 실행합니다. (파일 실행시 더미 데이터가 생성되어 DB에 삽입됩니다.)</li>
</ol>

### ✅ Test Code
코드 커버리지 77% 구현 완료
<br>
<div align=center>
  <img width="800" src="https://github.com/mansooonuna/task_prj/assets/102853354/8853677b-8484-4593-a91b-90b0191b8985">
</div>



### ✅ API 명세
<div align=center>
<img width="800" src="https://github.com/mansooonuna/task_prj/assets/102853354/91644dd3-d3d8-430e-8924-b8921aeedb9c">
</div>

### 👀 주요 기능 한눈에 보기
<details>
<summary>게시글 단건 조회 기능</summary>
<br/>
<li>url에 입력한 id에 해당하는 게시글이 조회됩니다.</li>
    <div align=center>
    <img src='https://github.com/mansooonuna/task_prj/assets/102853354/076191a0-238c-481d-adee-eb5ffa272d9c' >
    </div>
<br/>

<li>게시글 조회 시 마다 조회수가 업데이트 됩니다.</li>
    <div align=center>
    <img src='https://github.com/mansooonuna/task_prj/assets/102853354/7f91d96e-e92a-48cd-a210-258fcb61e457' >
    </div>
<br/>
</details>

***

<details>
<summary>게시글 수정 기능</summary>
<br/>
<li>수정하고 싶은 게시글의 제목과 내용을 입력하면 게시글이 수정됩니다.</li>
    <div align=center>
    <img src='https://github.com/mansooonuna/task_prj/assets/102853354/e2eaf45a-1d7a-47cf-af82-aac7619f8016' >
    </div>
<br/>
</details>


<details>
<summary>게시글 수정 기능 - 예외처리</summary>
<br/>
<li>제목이나 내용의 입력값이 없을 경우 예외처리 진행했습니다.</li>
    <div align=center>
    <img src='https://github.com/mansooonuna/task_prj/assets/102853354/a196dc95-3e5d-4126-9c3f-4358376e76fa'>
    <img src="https://github.com/mansooonuna/task_prj/assets/102853354/12617cbc-e335-4413-a892-d8cb3b85ae4e">
    </div>
<br/>
</details>

***

<details>
<summary>게시글 삭제 기능</summary>
<br/>
<li>url에 삭제하고 싶은 게시글의 id 값을 넘겨주어 삭제합니다.</li>
    <div align=center>
    <img src="https://github.com/mansooonuna/task_prj/assets/102853354/3baf3c15-6d21-4096-94d2-05e57769a8f8">
    </div>
<br/>
</details>

***

<details>
<summary>게시글 등록 기능</summary>
<br/>
<li>게시글의 제목, 내용을 함께 넘겨주면 게시글이 등록이 됩니다. 게시판을 선택하지 않은 경우에는 기본 게시판이 자동으로 선택됩니다.</li>
    <div align=center>
    <img src="https://github.com/mansooonuna/task_prj/assets/102853354/f0bdd601-a4d0-4cc4-9e33-32a387c8cb24">
    </div>
<br/>

<li>게시판을 선택한 경우 해당 게시판으로 글이 게시됩니다.</li>
    <div align=center>
    <img src="https://github.com/mansooonuna/task_prj/assets/102853354/dc6685a5-c70a-4444-8d05-fef079457a7e">
    </div>
<br/>
</details>

<details>
<summary>게시글 등록 기능 - 예외처리</summary>
<br/>
<li>제목의 글자 수는 100자 이내로 제한하였습니다.</li>
    <div align=center>
    <img src="https://github.com/mansooonuna/task_prj/assets/102853354/e3f4ef36-8bfd-4ae3-a747-df891252e8ca">
    </div>
<br/>
</details>

***

<details>
<summary>선택한 게시판 게시글 목록 조회 및 게시글 제목 검색 기능</summary>
<br/>
<li>게시판을 선택하지 않고 목록 조회를 할 경우에 MAIN 게시판이 조회되고 10개씩 페이징 처리하여 일자 기준 최신순으로 반환합니다.</li>
    <div align=center>
    <img src="https://github.com/mansooonuna/task_prj/assets/102853354/73757947-91a2-406d-9c5c-10191f2a4c0d">
    </div>
<br/>

<li>게시판을 선택하고, 검색 키워드도 함께 param으로 넘겨주면 해당 게시판의 검색된 게시글이 조회됩니다. 검색 시 쿼리문에 LIKE를 사용하여 해당 키워드가 제목에 포함된 게시글은 모두 조회됩니다.</li>
    <div align=center>
    <img src="https://github.com/mansooonuna/task_prj/assets/102853354/cee8f4c2-b87b-4962-8068-2ee862ba7fc1">
    </div>
<br/>
</details>

***
<details>
<summary>공통 예외처리</summary>
<br/>
<li>해당 게시글이 존재하지 않을 경우 예외처리 진행하였습니다.</li>
    <div align=center>
    <img src="https://github.com/mansooonuna/task_prj/assets/102853354/77714e18-d067-4f2b-bbca-1641c2eaf991">
    </div>
<br>
<li>게시글은 작성자만 수정,삭제할 수 있도록 예외처리 진행했습니다. </li>
    <div align="center">
    <img src="https://github.com/mansooonuna/task_prj/assets/102853354/1e04620e-e2c5-4a61-bff1-7b9c2ecbb980">
    <img src="https://github.com/mansooonuna/task_prj/assets/102853354/e6afad67-df7e-41f7-bf50-1c2d3c8daa49">
    </div>
</details>

