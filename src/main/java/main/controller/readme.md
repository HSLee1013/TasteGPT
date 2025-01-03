
<div align="center">
<img src="https://github.com/user-attachments/assets/f55a3a05-7254-4463-9fc1-21ece90625b6">

</div>
<h1 align="center">
  MainController
</h1>
<p align="center">맛집 등록, 검색/정보 관리/관리자 페이지 서블릿 </p>
<p align="center">회원의 맛집 등록과 정보 저장, 검색, 관리자 페이지 등의 <br>
  데이터를 관리하고 DB에 저장하여 사용자에게 보여주는 서블릿</p>

--- 

## Contents

<p align="left">목 차</p>
<p align="left">
  <a href="#클래스-설명">클래스 설명</a> <br>
  <a href="#주요-코드">주요 코드</a> 
</p>

---

## 클래스 설명

| 클래스명                | 설명                                                                                                                                                       |
|----------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **[MainServlet.java](MainServlet.java)**                  |  - 초기화면 페이지 및 각 페이지 별 서블릿 연결<br> - 애플리케이션 전체 설정 관리                                |
| **[SearchCateServlet.java](SearchCateServlet.java)**                 |    - 사용자와 카페의 정보를 초기화하고, 카테고리와 가격 조건에 맞는 카페 리스트를 검색하여 JSP 페이지로 전달<br> - 검색된 카페에 대한 사진 및 태그 정보를 병합하여 결과를 제공                                     |
| **[searchAreaServlet.java](searchAreaServlet.java)**                 |    - 요청된 지역에 맞는 카페 목록을 검색하고, 해당 카페와 관련된 이미지 정보를 병합하여 JSON 형식으로 응답 <br> - 응답 상태 코드를 설정하여 성공 또는 실패를 클라이언트에 전달                                   |
| **[CafeDetailsServlet.java](CafeDetailsServlet.java)**     |    -  특정 카페의 세부 정보를 조회하여, 가게 정보, 메뉴 목록, 리뷰, 평균 평점, 카테고리, 태그 및 이미지를 JSP에 전달 <br> -  사용자 세션에서 정보를 가져와 사용자의 타입을 설정하며, 예외 발생 시 에러 페이지로 포워딩                          |
| **[OwnerPage_basic.java](OwnerPage_basic.java)**           |    - 가게 소유자가 자신의 페이지에서 가게 정보를 입력하고 전송<br> - GET 요청으로 카테고리 목록과 소유자 ID를 제공<br> - POST 요청에서는 JSON 데이터를 읽고 가게 정보, 태그, 이미지를 데이터베이스에 저장                                                 |
| **[AddMenuServlet.java](AddMenuServlet.java)**      |    - 유저로부터 메뉴 데이터를 받아 카페 번호를 확인한 뒤, 해당 카페에 여러 메뉴를 등록하는 작업을 처리<br> - 메뉴 데이터는 JSON 형식으로 전달되며, 각 메뉴 정보는 별도로 추출 및 삽입<br> -  메뉴 삽입 작업은 데이터베이스에 저장      |
| **[AdminServlet.java](AdminServlet.java)**        |    - 모든 사용자 리스트를 가져와 admin-users.jsp에 전달<br> - 사용자가 입력한 ID로 검색하여 사용자 활성화 상태를 업데이트한 후, 최신 사용자 리스트를 admin-users.jsp로 전달     |
| **[CafeReviewServlet.java](CafeReviewServlet.java)**    |    - 사용자 세션을 확인하여 특정 카페의 메뉴 및 리뷰 정보를 조회하고, 로그인한 사용자만 리뷰를 작성할 수 있도록 처리<br> - doGet 메서드는 메뉴 리스트와 사용자 정보를 조회<br> - doPost 메서드는 리뷰 데이터를 데이터베이스에 저장하는 기능을 수행               |
| **[SuggestionServlet.java](SuggestionServlet.java)**               |    - 사용자 제안 페이지로 이동하고 제안 내용을 처리<br> - doGet 메서드는 건의사항 페이지로 포워딩<br> - doPost 메서드는 로그인한 사용자의 건의사항을 데이터베이스에 저장하고, 성공 여부에 따라 메인 페이지 또는 건의사항 페이지로 리다이렉트                                             |
| **[UserPictureServlet.java](UserPictureServlet.java)**               |    - 사용자 ID로 검색된 사용자의 프로필 이미지를 가져와 반환<br> - doGet 메서드는 요청된 사용자의 Base64 인코딩된 이미지를 디코딩하여 JPEG 형식으로 출력 스트림에 전달    |
<br>

## 주요 코드

1. 가게 검색 코드(MainServlet.java)
 - 파라미터로 받아온 값을 통해 검색어를 저장하고 쿼리를 초기화한 뒤 검색어의 여부를 판단하여 어떤 목록을 반환할지 선택하고, 검색어가 있으면 검색된 Cafeteria와 CafePic을 병합하여 요청 속성으로 설정
     
```
    private void initSearchQuery(HttpServletRequest req) {
        String searchQuery = req.getParameter("searchQuery");
        log.info("검색어: " + searchQuery);

        List<Cafeteria> searchResults;

        // 검색어가 존재할 경우 검색 실행, 없으면 전체 목록을 반환
        if (searchQuery != null && !searchQuery.trim().isEmpty()) {
            searchResults = service.searchByAll(searchQuery, searchQuery, searchQuery, searchQuery, searchQuery);
        } else {
            searchResults = service.selectAll(); // 검색어가 없으면 전체 목록 반환
        }

        // 검색된 Cafeteria 결과에 맞는 CafePic 리스트와 병합
        List<CafeteriaWithPicDTO> mergedList = new ArrayList<>();
        for (Cafeteria cafeteria : searchResults) {
            List<CafePic> cafePicList = service.selectPicsByCafeNum(cafeteria.getCafeNum());
            mergedList.add(new CafeteriaWithPicDTO(cafeteria, cafePicList));
        }

        // 병합된 리스트를 요청 속성으로 설정하여 JSP로 전달
        req.setAttribute("mergedList", mergedList);

        // 태그 정보 초기화
        initTags(req, searchResults);
    }

```
<br>

2. 가게 정보 조회 코드(CafeDetail.java)
 - 카페 이름을 가져와 해당 카페의 상세 정보를 조회하고, 메뉴, 리뷰, 카테고리, 태그, 이미지 등의 정보를 요청 속성에 설정하여 cafeDetails.jsp로 포워딩
 - 평균 점수와 결제 금액도 계산하여 전달하며, 예외 발생 시 에러 페이지로 포워딩
 - 전체적으로 카페의 상세 정보를 사용자에게 보여주는 기능을 수행
```
try {
			initUser(req); // 사용자 정보를 초기화
			
			// 요청 파라미터로 가게 이름을 가져와 해당 가게 정보를 조회
			String cafeName = req.getParameter("cafeName");
			Cafeteria cafeteria = service.selectByName(cafeName);
			int cafeNum = cafeteria.getCafeNum(); // 가게 번호 조회

			// 메뉴 목록 조회 및 저장
			List<Menu> menuList = service.selectMenu(cafeNum);

			// 가게 리뷰 목록 및 평균 점수 조회
			List<CafeReview> cafeReviewList = service.selectCafeReview(cafeteria.getCafeNum());
			double score = service.selectAvg(cafeNum);
			score = Math.round(score * 100) / 100.0; // 소수 둘째 자리까지 반올림

			// 카테고리 번호 및 카테고리 객체 목록 조회
			List<Integer> cafeCategoryNumList = service.selectCategoryNum(cafeNum);
			List<CafeCategory> cafeCategoryList = new ArrayList<CafeCategory>();

			// 각 카테고리 번호에 대해 카테고리 객체를 조회하여 리스트에 추가
			for (int i = 0; i < cafeCategoryNumList.size(); i++) {
				CafeCategory cafeCategory = service.selectCategory(cafeCategoryNumList.get(i));
				cafeCategoryList.add(cafeCategory);
			}

			// 가게 태그 목록 조회
			List<CafeTag> cafeTagList = service.selectCafeTag(cafeNum);

			// 가게 이미지 조회
			CafePic cafePic = service.selectCafePic(cafeNum);

			// 평균 결제 금액 조회
			Integer customerPaymentAvg = service.selectAvgPayment(cafeNum);

			// 조회된 정보를 JSP에 전달하기 위해 요청 속성에 설정
			req.setAttribute("cafePic", cafePic.getCafePic());
			req.setAttribute("menuList", menuList);
			req.setAttribute("cafeteria", cafeteria);
			req.setAttribute("cafeReviewList", cafeReviewList);
			req.setAttribute("cafeCategoryList", cafeCategoryList);
			req.setAttribute("cafeTagList", cafeTagList);
			if (customerPaymentAvg != null) {
				req.setAttribute("customerPaymentAvg", customerPaymentAvg); // 결제 평균이 null이 아닐 경우에만 설정
			}
			req.setAttribute("score", score); // 가게 평점 설정

			// cafeDetails.jsp로 포워딩하여 렌더링
			req.getRequestDispatcher("/WEB-INF/view/cafeDetails.jsp").forward(req, resp);
		} catch (Exception e) {
			// 예외 발생 시 에러 로그 출력 후 에러 페이지로 포워딩
			log("Error fetching cafeteria details", e);
			req.getRequestDispatcher("/WEB-INF/view/error.jsp").forward(req, resp);
		}
```
<br>

3. 카테고리 검색 코드(SearchCateServlet.java)
 - 사용자가 선택한 카테고리와 추가 입력값에 따라 식당 리스트를 초기화하며, 최대 가격을 기반으로 검색 결과를 필터링
 - 검색된 식당 정보와 관련 사진을 병합하여 요청 속성에 설정한 후, JSP에 전달
```
// 선택된 카테고리에 따라 식당 리스트를 초기화하는 메서드
private void initCafeteriaListByCategory(HttpServletRequest req) {
    String searchByPrice = (String) req.getParameter("max-price"); // 최대 가격 파라미터를 가져옴
    String countPpl = (String) req.getParameter("people-combo"); // 인원수 파라미터를 가져옴
    int searchByPriceAvg = (int) Math.round(Double.parseDouble(searchByPrice) / Double.parseDouble(countPpl)); // 1인당 평균 가격 계산

    String[] categories = req.getParameterValues("category"); // 선택된 카테고리 목록을 가져옴
    String additionalCategory = req.getParameter("additionalCategory"); // 추가 카테고리 입력 값을 가져옴
    List<Cafeteria> searchResults; // 검색 결과를 저장할 리스트
    List<String> chkList = new ArrayList<>(); // 체크된 카테고리를 저장할 리스트

    // 체크된 카테고리와 추가 카테고리 처리
    if (categories != null) { // 선택된 카테고리가 있는 경우
        for (String category : categories) { // 각 카테고리를 체크리스트에 추가
            chkList.add(category);
        }
    }
    if (additionalCategory != null && !additionalCategory.trim().isEmpty()) { // 추가 카테고리가 있는 경우
        chkList.add(additionalCategory); // 체크리스트에 추가
    }
    // 가격 필터링이 있는 경우, 가격과 태그를 기반으로 카페 검색
    if (searchByPrice != null && !searchByPrice.trim().isEmpty()) {
        searchResults = service.getCafeByPriceAndTags(searchByPriceAvg, chkList); // 가격과 태그로 카페 검색
    } else {
        searchResults = service.selectAll(); // 검색 조건이 없으면 전체 카페 목록 반환
    }

    // 검색된 카페와 해당 사진 정보 병합
    List<CafeteriaWithPicDTO> mergedList = new ArrayList<>(); // 병합된 리스트 초기화
    for (Cafeteria cafeteria : searchResults) { // 각 카페에 대해
        List<CafePic> cafePicList = service.selectPicsByCafeNum(cafeteria.getCafeNum()); // 카페 사진 리스트 가져옴
        mergedList.add(new CafeteriaWithPicDTO(cafeteria, cafePicList)); // DTO로 병합하여 추가
    }

    // 병합된 결과를 JSP로 전달
    req.setAttribute("mergedList", mergedList); // 병합된 리스트를 요청 속성으로 설정

    // 검색 결과에 해당하는 태그 정보를 초기화
    initTags(req, searchResults); // 검색된 카페에 대한 태그 정보 초기화
}

```
<br>

4. 가게 정보 삽입 코드(OwnerPage_basic.java)
 -  클라이언트로부터 JSON 형식의 카페 정보를 받아, 가게 데이터를 추출하고 Cafeteria 객체를 생성하여 데이터베이스에 삽입
 -  카페에 대한 카테고리와 태그 정보를 처리하며, 최대 5개의 태그만 유지하여 삽입
 -  응답 코드와 메시지를 설정하여 클라이언트에 결과를 반환
```
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setCharacterEncoding("UTF-8"); // 요청의 문자 인코딩을 UTF-8로 설정

    WebUtil webUtil = new WebUtil();
    String json = webUtil.readBody(req); // 요청 본문에서 JSON 데이터 읽기
    JsonMapper jsonMapper = new JsonMapper();

    // JSON에서 가게 정보를 추출
    String cafeName = JsonPath.read(json, "$.cafeName"); // 가게 이름
    String cafeExplain = JsonPath.read(json, "$.cafeExplain"); // 가게 설명
    String cafePhoneNumber = JsonPath.read(json, "$.cafePhoneNumber"); // 가게 전화번호
    String cafePrice = JsonPath.read(json, "$.cafePrice"); // 가게 가격
    String cafeAddress = JsonPath.read(json, "$.cafeAddress"); // 가게 주소
    String cafeStartTime = JsonPath.read(json, "$.start-time"); // 시작 시간
    String cafeEndTime = JsonPath.read(json, "$.end-time"); // 종료 시간
    String tagCountStr = JsonPath.read(json, "$.tagCount"); // 태그 개수
    String cafePic64 = JsonPath.read(json, "$.cafePic64"); // 카페 사진(64비트 인코딩)
    String cafeOwner = JsonPath.read(json, "$.cafeOwner"); // 카페 소유자

    // 사용자 지정 시간 처리를 위한 조건문
    if (cafeStartTime.equals("custom-start")) { // 사용자 지정 시작 시간이면
        cafeStartTime = JsonPath.read(json, "$.custom-start-time"); // 사용자 지정 시작 시간으로 대체
    }
    if (cafeEndTime.equals("custom-end")) { // 사용자 지정 종료 시간이면
        cafeEndTime = JsonPath.read(json, "$.custom-end-time"); // 사용자 지정 종료 시간으로 대체
    }

    // 시작 및 종료 시간을 연결하여 영업시간을 설정
    String cafeOpenTime = cafeStartTime.concat(" - ").concat(cafeEndTime); // "시작시간 - 종료시간" 형식으로 설정

    // 카테고리 값 추출 후 정수형으로 변환
    String cafeCategoryStr = JsonPath.read(json, "$.cafeCategory"); // 카테고리 문자열을 읽음
    int cafeCategory = Integer.parseInt(cafeCategoryStr); // 카테고리 문자열을 정수형으로 변환

    // JSON 형태로 Cafeteria 객체 생성에 필요한 데이터 구성
    String resultJsonForCafeteria = "{" +
            "\"cafeName\": \"" + cafeName + "\"," +
            "\"cafeExplain\": \"" + cafeExplain + "\"," +
            "\"cafePhoneNumber\": \"" + cafePhoneNumber + "\"," +
            "\"cafePrice\": \"" + cafePrice + "\"," +
            "\"cafeAddress\": \"" + cafeAddress + "\"," +
            "\"cafeOpenTime\": \"" + cafeOpenTime + "\"," +
            "\"cafeOwner\": \"" + cafeOwner + "\"" +
            "}";

    // Cafeteria 객체 생성 및 삽입
    Cafeteria cafeteria = jsonMapper.readValue(resultJsonForCafeteria, Cafeteria.class); // JSON에서 Cafeteria 객체로 변환
    int cafeNum = service.insert(cafeteria); // 새로운 가게를 삽입 후 번호 가져오기
    service.insertCategoryM(cafeNum, cafeCategory); // 가게 번호와 카테고리 삽입

    List<String> cafeTagList = new ArrayList<>(); // 태그 목록 초기화

    // 태그 개수 확인 및 설정
    int tagCount = 0; // 태그 카운터 초기화
    if (tagCountStr != null && !tagCountStr.isEmpty()) { // 태그 개수가 null이 아니고 비어있지 않은 경우
        tagCount = Integer.parseInt(tagCountStr); // 태그 개수 설정
    } else {
        System.out.println("tagCount is null or empty"); // 태그 개수가 null이거나 비어있을 경우 로그 출력
    }

    // 태그를 JSON에서 추출하고 유효한 태그만 추가
    for (int i = 1; i <= tagCount; i++) { // 태그 개수만큼 반복
        String tagParam = JsonPath.read(json, "$.tagInput-" + i); // 각 태그를 JSON에서 읽기
        if (tagParam != null && !tagParam.isEmpty()) { // 태그가 유효한 경우
            cafeTagList.add(tagParam); // 유효한 태그 추가
        }
    }

    // 태그 목록에서 최대 5개만 유지
    if (cafeTagList.size() > 5) { // 태그가 5개 초과일 경우
        cafeTagList = cafeTagList.subList(0, 5); // 처음 5개만 유지
    }

    // 태그 삽입
    for (int i = 0; i < cafeTagList.size(); i++) { // 각 태그에 대해
        service.insertTag(cafeNum, cafeTagList.get(i)); // 태그 삽입
    }

    // 이미지 삽입
    int result = service.insertPic(cafeNum, cafePic64); // 카페 사진 삽입

    // 응답 코드와 MIME 타입 설정 및 응답 본문 작성
    webUtil.setCodeAndMimeType(resp, 201, "json"); // 응답 코드 201과 MIME 타입 설정
    webUtil.writeBodyJson(resp, "됨"); // 응답 본문에 "됨" 메시지 작성
}

```
<br>

5. 이미지 불러오기 코드(UserPictureServlet.java)
 - 파라미터로 요청받은 사용자의 프로필 사진을 Base64 형식에서 디코딩하여 클라이언트에게 이미지로 반환
 - 사용자의 사진 정보가 없거나 유효하지 않을 경우 아무것도 반환하지 않음
   
```
@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId"); // 요청 파라미터에서 userId 가져오기
        if (userId != null) { // userId가 null이 아닐 경우
            User user = service.userInfo(userId); // 사용자 정보를 조회
            if (user != null && user.getUserPicture() != null) { // 사용자 정보가 존재하고 프로필 사진이 있을 경우
                String userPicture = user.getUserPicture(); // 사용자 사진 정보 가져오기
                
                // Base64 데이터에서 "data:image/png;base64," 부분 제거
                if (userPicture.startsWith("data:image/png;base64,")) {
                    userPicture = userPicture.substring("data:image/png;base64,".length()); // Base64 문자열 추출
                }
                
                byte[] imageBytes = Base64.getDecoder().decode(userPicture); // Base64 문자열을 바이트 배열로 디코딩

                resp.setContentType("image/jpeg"); // 응답 콘텐츠 타입을 이미지 JPEG로 설정
                resp.getOutputStream().write(imageBytes); // 디코딩된 이미지 바이트를 응답으로 작성
                resp.getOutputStream().flush(); // 응답 스트림 플러시
                return; // 이미지를 반환하고 메서드 종료
            } 
        }
```
<br>

6. 지역별 검색 코드(SearchAreaServlet.java)
 - JSON 형식의 요청 본문을 읽어 해당 주소에 맞는 카페 리스트를 조회한 후, 각 카페와 관련된 이미지 정보를 병합하여 DTO 리스트를 생성
 - 결과 리스트는 JSON 형식으로 응답되며, 요청의 성공 여부에 따라 HTTP 응답 코드를 설정
 - 성공 시에는 200 코드, 실패 시에는 400 코드 반환

```
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    WebUtil webUtil = new WebUtil(); // 웹 유틸리티 객체 생성하여 요청 및 응답 처리에 사용

    // JSON 형식으로 전달된 요청 본문을 읽어서 List 형태로 변환
    List<String> bodyJson = webUtil.readBodyJson(req, List.class);
    
    // 주소 목록을 CafeSQLProvider에 전달하여 SQL 쿼리 생성
    String cafesByAddress = CafeSQLProvider.getCafesByAddress(bodyJson);

    // 주소 조건에 맞는 Cafeteria 리스트를 가져옴
    List<Cafeteria> list = service.selectByArea(bodyJson);

    // Cafeteria와 관련된 CafePic 리스트를 병합하여 DTO 리스트 생성
    List<CafeteriaWithPicDTO> mergedList = mergeCafeteriaAndPic(list);

    // JSON 형식으로 응답 본문에 병합된 리스트를 작성
    webUtil.writeBodyJson(resp, mergedList);

    // 응답 코드 및 MIME 타입 설정
    if (list != null) {
        webUtil.setCodeAndMimeType(resp, 200, "json"); // 성공 응답 (HTTP 200)
    } else {
        webUtil.setCodeAndMimeType(resp, 400, "json"); // 실패 응답 (HTTP 400)
    }
}

```

