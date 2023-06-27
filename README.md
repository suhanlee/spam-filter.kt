### Spam Filter(Kt)
- 자유인 지니가 만든 주식스팸 거르기를 바탕으로 코틀린으로 포팅.

### 참고
- https://github.com/fgenie/scamtext

### 해야 할일
- [ ] 안드로이드 앱 이식 테스트
- [ ] 대량 필터 포팅

### Jar 파일 생성
```
$ kotlinc ./src/main/kotlin/main.kt -d spam_filter.jar
```

### 실행 예시
- 응답 예시
```
input_texts=[msgs, "[Web발신], [프리미엄콘텐츠] 미국주식 사관학교 1개월 이용권 3,900원이 결제되었습니다.", "[Web발신], Your Beam verification code is: 5557", "[국외발신], G-592238 is your Google verification code.", "[Web발신], [아프리카TV] 인증번호 [11382]를 입력해 주세요.", "[Web발신], [민방위 교육센터], 본인확인을 위해 인증번호 [514073]를 입력해 주세요.", "[Web발신], [한전사이버지점]고객님의 한전정보 SMS 인증번호는[290017]입니다.", "[Web발신], [삼성카드]SMS 인증번호[471636]", "[한국모바일인증(주)]본인확인 인증번호[995988]입니다. \"타인 노출 금지\"", "[Web발신], [MY COMPANY] 승인, 3101 선선일님, 134,000원 일시불, 신세계센트럴시티, 잔여한도1,866,000원", "[Web발신], [MY COMPANY] 현대카드 당월 결제 예정 금액 안내, , 회원님, 당월 법인카드 결제 예정 결제금액을 안내 해드립니다, , [상세 안내], - 대상카드 : 3101 카드, - 결제 예정 금액 : 49,700원 (05/07 기준), - 결제일 : 05/24, - 납부방식 : 농협중앙, , . 상세내역은 청구서 또는 현대카드 법인홈페이지에서 확인이 가능합니다., , [문의] 1577-6000", "[국외발신], 선선일님, [수입세금], 발생되였습니다., 금액892,624원, 사건코드(3**4), 금일 자동처리예정, 민원0269569423", "https://www.youtube.com/live/garRuI-ex6w?feature=share, 주일낮예배입니다", "[Web발신], (광고)크린토피아 내일까지! 패딩,점퍼,스웨터,코트,겨울조끼 세탁15%세일! 무료거부0807450061", "[여신금융협회] 본인확인 인증번호[506382]를 화면에 입력해주세요", "[CJ대한통운]고객님의 상품(568830418273)이 배송되었습니다.▶인수자(위탁):문앞"]
voted_spam_fraction=[0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.25, 0.0, 0.0, 0.0, 0.0, 0.25, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.25, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.5, 0.25, 0.0]
decisions=[false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, false, false]
num_functions=4
```