# SpringMVC based Mini Instagram (2019.07 ~ 2019.08)

## 概要

- イメージ基盤のSNSを制作
- not reload （非同期リクエスト）
- Google Map API (ユーザがどこでアップロードしたかリアルタイムで確認)
- CURD (掲載物、コメント、ユーザ情報)
- Search (部分テキスト検索：位置、ユーザ情報)
- repo: https://github.com/gaon-park/Image-Video-Upload-Post-Site
## 技術

| 分類        | 技術                                             |
|-----------|------------------------------------------------|
| Language  | - JAVA(JDK 1.8) <br/>- JavaScript              |
| Database  | - MySQL                                        |
| WAS       | - Tomcat 9                                     |
| Back End  | - Spring MVC<br/>- JDBC<br/>- MyBatis          |
| Front End | - jQuery<br/>- AJAX<br/>- JSON<br/>- Bootstrap |
| API       | Google Map API                                 |

## Service Configuration Diagram

**![](https://lh7-us.googleusercontent.com/Ds9UUaqyBxacNnPbvLXoXAROhhEosLVZIuaD5kEhIaS0lJoh6HiZGUj4-IewZCrcXo59VGAHVLzL5yzcr-aATHiLVS19e0shP9W1CidSONo_k_5XFi73_OBcYLst-OW1HRJql0bDG991UbJXamJ3RBaS=s2048)


## Database ERD

![](https://lh5.googleusercontent.com/98WLRqwPFWtMU5z-yBxbJgunM4h62DRDWSvx3Ukkrq4DJiJxU7AnaGJphfo5Wn4T62_RmE-lHBDbYYhlJrVrYC8Qhy8iphkL2vOTIYlh0P0gr2YQfCMP-mjiWpGkfZJRcjDECNr-DWcuNSQahiyE7Gwl=s2048)

## Page Samples 

1. Login Page
	1. Keep me signed in ボタンで localStorage 管理
	2. Ajaxでページ遷移なくID重複チェック
	   ![[Pasted image 20231014213428.png]]
2. User Informatin Page
	1. プロフィル写真変更
	2. 紹介変更
	3. パスワード変更
	   ![[Pasted image 20231014213134.png]]
3. Profile Page
	1. コンテンツ情報
	2. サムネールイメージ
	   ![](https://lh5.googleusercontent.com/-sKtypKwcztohfRWEENbRncp7eH3sGkEkXu7skVEGug1qogojaZZ-qz8DNp8nFx9AKcbeKPmBILrKQ517F0hovJyFjmBSrAmiGXrRPn-SFOfdyrxcoJ-uqNlaofD4CO-1jiw5KAA2jjZLSsH1TpdSyIq=s2048)
	3. ユーザが主にどこでアップロードしたかVisualization
	   ![](https://lh3.googleusercontent.com/IpIhphhMY9bUV-lqpnvDYLevEImZd0tGmCLQrxMZLBLsfACrX0G99C_jVXJMLVwkL_-3XzVhaY8uujckcAh10c161eR99JhVTxN9yxtubw7sE5tdbaFPI141UghbXGThCIHIu16ILWQey1K7GxRFUOoq=s2048)
4. Upload Page
	1. Dropzoneと Google APIを使ってアップロードを便利に
	   ![[Pasted image 20231014213311.png]]
5. Main Page
	1. ユーザ別いいね！(♥)とコメント機能
	2. Scroll Eventによるページング機能
	   ![[Pasted image 20231014213604.png]]
6. Search Page
	1. 位置情報 or テキスト内容の検索機能
	   ![[Pasted image 20231014213636.png]]
