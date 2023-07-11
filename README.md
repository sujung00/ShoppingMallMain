# Shoppingmall:TREND

![mainImage](https://github.com/sujung00/ShoppingMall/assets/87317453/ee07b20d-c6d4-4e05-b6c6-2a5838abac31)


![https://hits.seeyoufarm.com/api/count/incr/badge.svg?url=https://github.com/sujung00/ShoppingMall&count_bg=#79C83D&title_bg=#555555&icon=&icon_color=#E7E7E7&title=hits&edge_flat=false](https://hits.seeyoufarm.com/api/count/incr/badge.svg?url=https://github.com/sujung00/ShoppingMall&count_bg=#79C83D&title_bg=#555555&icon=&icon_color=#E7E7E7&title=hits&edge_flat=false)


## 🛒 Shoppingmall Project : Trend

### 🔗  [http://54.180.30.140:8080/main/main_view](http://54.180.30.140:8080/main/main_view)

**👩🏻‍🔧 admin계정: admin/adminsujung**


## 🛒 프로젝트 소개


- 신상품/여성/남성 카테고리를 나누어 취향과 요구에 맞는 상품을 찾을 수 있도록 도와줍니다.
- 로그인 시 아이디와 비밀번호를 잊어버렸을 경우에 대비하여 아이디 찾기와 비밀번호 찾기 기능을 구현하였습니다. 비밀번호 찾기의 경우에는 회원 가입 시 입력했던 이메일로 임시 비밀번호를 변경한 후 이메일로 전송합니다.
- 회원 가입 후 개인 계정을 통해 주문 내역과 배송 상태를 확인할 수 있습니다. 카카오 계정을 이용하여 로그인할 경우 카카오 계정으로 회원 가입이 안 되어있는 경우에는 회원 가입 이후 로그인을 진행하고 계정이 있는 경우에는 바로 로그인할 수 있도록 기능을 구현하였습니다.
- 문의 사항을 작성할 수 있어 제품 및 배송 등 다양한 문의 사항을 관리자에게 문의할 수 있습니다.
- 또한, 리뷰를 작성할 수 있어 다른 고객들의 의견을 참고하여 더 나은 구매 결정을 도와줍니다.
- 장바구니 기능을 구현하여 구매하고 싶은 상품을 추가하고 가격을 계산하여 주문서를 작성할 수 있도록 구현하였습니다.
- 장바구니 상품들로 주문서를 작성하여 배송지 입력 및 포트원 API를 이용하여 결제 시스템과 주문 취소 시 환불까지 가능하여  편리하게 이용 가능합니다.

**시기**

- 2023.05.04 ~ 2023.06.08


## 👩🏻‍💻 개발 환경

<img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white"> <img src="https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white"> <img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"> <img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white"><br>
<img src="https://img.shields.io/badge/amazonaws-232F3E?style=for-the-badge&logo=amazonaws&logoColor=white"> <img src="https://img.shields.io/badge/apachetomcat-F8DC75?style=for-the-badge&logo=apachetomcat&logoColor=white"><br>
<img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white"> <img src="https://img.shields.io/badge/sourcetree-0052CC?style=for-the-badge&logo=sourcetree&logoColor=white">


## 📄 주요 기능

### ⭐카카오 로그인

- 카카오의 REST API를 이용하여 사용자가 카카오 로그인을 요청하면 인가 코드 받기를 요청 하고 사용자의 nickname, email을 제공 받는 것을 사용자가 동의를 한 후 인가 코드를 발급 받습니다. 토큰을 요청 한 후 발급된 access 토큰으로 사용자 정보를 받을 수 있습니다. 그 사용자 정보를 통해 존재하는 계정인지 확인합니다. 존재하는 계정이면 사용자 정보를 이용하여 로그인합니다. 존재하지 않는 계정이면 사용자 정보를 이용하여 서버에서 회원 가입 후 로그인을 진행하여 사용자는 바로 로그인 되는 것처럼 보이도록 구현하였습니다.

https://github.com/sujung00/ShoppingMall/assets/87317453/65b1561e-f984-47c2-b06b-137b4b7b2844

### ⭐우편번호(배송지 주소)

- Javascript 코드를 사용하여 Kakao 우편번호 API를 호출합니다. 사용자가 우편번호 찾기 버튼을 누르면 팝업 창을 띄운 후 검색 결과를 클릭하면 우편번호와 주소를 양식에 맞게 입력합니다.

https://github.com/sujung00/ShoppingMall/assets/87317453/9ca3a39f-e65a-4866-99cb-748e1e741e16

### ⭐결제하기

- 결제하기 버튼을 누르면 DB에 주문 정보(주문상태:’결제대기’)를 저장합니다. 포트원 결제 API를 이용하여 KG이니시스 결제창 호출 시 필요한 결제 정보들을 파라미터에 저장 한 후 결제를 요청합니다. 결제가 완료되면 반환되는 응답 객체(rsp)의 결제 성공 여부가 저장됩니다. 결제가 성공 했다면 결제번호(imp_uid)와 주문번호(merchant_uid)를 서버에 전달하여 결제금액 위변조 여부를 검증하는 로직을 구현하였습니다. 결제 및 검증이 완료되면 주문상태를 ‘결제완료’로 update하도록 구현하였습니다.
- 시연 영상은 결제 test용으로 만들어둔 -2900원 test상품입니다. test상품으로 결제 할 경우 포인트를 사용하지 않습니다.

https://github.com/sujung00/ShoppingMall/assets/87317453/2b65aeb3-d2fa-48fd-9601-1e7d5929add7

### ⭐주문 취소

- 배송 완료된 상품이거나 주문 취소가 아닌 경우에는 구매 취소 버튼을 누르면 `@OrderRestController` 의 주문/결제 취소 API를 호출합니다. 클라이언트에서 받은 주문번호(merchant_uid)를 사용해서 해당 주문의 결제정보를 확인합니다. 결제정보가 확인 되면 REST API access token을 발급받습니다. 발급받은 acceess token을 이용하여 포트원 취소 API를 호출하여 결제 취소를 요청합니다.

https://github.com/sujung00/ShoppingMall/assets/87317453/bce251cd-fbd2-4502-8458-4c92f377d6b6

