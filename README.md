## 개요

- 이미지 기반의 SNS를 반응형 웹으로 설계&제작
- not reload (편리성)
- Google Map API (사용자가 어디서 업로드 하는지 실시간 확인)
- CURD (게시글, 댓글, 사용자 정보)
- Search (부분 텍스트 검색 - 위치, 사용자 정보)
- repo: https://github.com/gaon-park/Image-Video-Upload-Post-Site
## 기술 스택

| 분류        | 기술                                             |
|-----------|------------------------------------------------|
| Language  | - JAVA(JDK 1.8) <br/>- JavaScript              |
| Database  | - MySQL                                        |
| WAS       | - Tomcat 9                                     |
| Back End  | - Spring MVC<br/>- JDBC<br/>- MyBatis          |
| Front End | - jQuery<br/>- AJAX<br/>- JSON<br/>- Bootstrap |
| API       | Google Map API                                 |
## Service Configuration Diagram

![](https://lh6.googleusercontent.com/exhO4LxvTl5Zwau-vnRHctk1gAKok4x8aBdfQOHoX9KYL7Ik2CHqUYoWST_NiYkvNX_FOpULDL74NIBJZjqQ96m6r2OXlfjudHH6tRFfiIUuDKvy7I_JpGcShLck0bHXzCgQyvmGvfYTqvTcZ_4lCl2m=s2048)


## Database ERD

![](https://lh5.googleusercontent.com/98WLRqwPFWtMU5z-yBxbJgunM4h62DRDWSvx3Ukkrq4DJiJxU7AnaGJphfo5Wn4T62_RmE-lHBDbYYhlJrVrYC8Qhy8iphkL2vOTIYlh0P0gr2YQfCMP-mjiWpGkfZJRcjDECNr-DWcuNSQahiyE7Gwl=s2048)

## Page Samples 

1. Login Page
	1. Keep me signed in 버튼을 통해 localStorage 관리	
	2. Ajax를 통해 페이지 전환 없이 아이디 중복성 체크
	   ![[Pasted image 20231014213428.png]]
2. User Informatin Page
	1. 프로필 사진 변경
	2. 소개 변경
	3. 패스워드 변경
	   ![[Pasted image 20231014213134.png]]
3. Profile Page
	1. 콘텐츠에 대한 정보
	2. 썸네일 이미지
	   ![](https://lh5.googleusercontent.com/-sKtypKwcztohfRWEENbRncp7eH3sGkEkXu7skVEGug1qogojaZZ-qz8DNp8nFx9AKcbeKPmBILrKQ517F0hovJyFjmBSrAmiGXrRPn-SFOfdyrxcoJ-uqNlaofD4CO-1jiw5KAA2jjZLSsH1TpdSyIq=s2048)
	3. 사용자가 어디에서 이미지를 자주 업로드 하는지 보여줌 
	   ![](https://lh3.googleusercontent.com/IpIhphhMY9bUV-lqpnvDYLevEImZd0tGmCLQrxMZLBLsfACrX0G99C_jVXJMLVwkL_-3XzVhaY8uujckcAh10c161eR99JhVTxN9yxtubw7sE5tdbaFPI141UghbXGThCIHIu16ILWQey1K7GxRFUOoq=s2048)
4. Upload Page
	1. Dropzone과 Google API를 이용한 업로드 편의성
	   ![[Pasted image 20231014213311.png]]
5. Main Page
	1. 사용자별 좋아요(♥)와 댓글 기능
	2. Scroll Event에 따른 페이징 기능
	   ![[Pasted image 20231014213604.png]]
6. Search Page
	1. 위치 정보 or 텍스트 내용의 검색 기능
	   ![[Pasted image 20231014213636.png]]
