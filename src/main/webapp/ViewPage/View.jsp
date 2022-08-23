<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="View.css">

    <script src="https://kit.fontawesome.com/f696815b8c.js" crossorigin="anonymous"></script>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Rubik:wght@500&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Jua:400" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Cute+Font&family=Rubik:wght@500&display=swap" rel="stylesheet">

    <title>글목록 페이지</title>
</head>
<body>
<div class="bodyy">
    <div>
        <h1 id="list">글목록 게시판</h1>
        <div class="listcontents">
            <table class="tablee">
                <tr class="formm">
                    <th id="num">번호</th>
                    <th id="subject">제목</th>
                    <th id="date">작성일</th>
                    <th id="view">조회수</th>
                </tr>
                <tr>
                    <td>7</td>
                    <td><a href="https://namu.wiki/w/%EA%B8%B0%EB%9F%AC%EA%B8%B0" target="_blank">기러기</a></td>
                    <td>2022-08-04</td>
                    <td>252</td>
                </tr>
                <tr>
                    <td>6</td>
                    <td><a href="https://namu.wiki/w/%ED%86%A0%EB%A7%88%ED%86%A0" target="_blank">토마토</a></td>
                    <td>2022-08-03</td>
                    <td>666</td>
                </tr>
                <tr>
                    <td>5</td>
                    <td><a href="https://namu.wiki/w/%EC%8A%A4%EC%9C%84%EC%8A%A4" target="_blank">스위스</a></td>
                    <td>2022-07-28</td>
                    <td>343</td>
                </tr>
                <tr>
                    <td>4</td>
                    <td><a href="https://namu.wiki/w/%EC%9D%B8%EB%8F%84%EC%9D%B8" target="_blank">인도인</a></td>
                    <td>2022-07-27</td>
                    <td>343</td>
                </tr>
                <tr>
                    <td>3</td>
                    <td><a href="https://namu.wiki/w/%EC%9C%A0%EC%84%B1" target="_blank">별똥별</a></td>
                    <td>2022-07-21</td>
                    <td>10710</td>
                </tr>
                <tr>
                    <td>2</td>
                    <td><a href="https://namu.wiki/w/%EC%97%AD%EC%82%BC%EC%97%AD" target="_blank">역삼역</a></td>
                    <td>2022-07-20</td>
                    <td>575</td>
                </tr>
                <tr>
                    <td>1</td>
                    <td><a href="https://namu.wiki/w/%EC%9A%B0%EC%98%81%EC%9A%B0" target="_blank">우영우</a></td>
                    <td>2022-06-29</td>
                    <td>353</td>
                </tr>
            </table>
            <div class="go">
                <div class="gobt1">
                    <button type="submit" id="bwg">글 작성하기</button>
                </div>
                <div class="gobt2">
                    <button type="submit" id="bhg">홈으로 가기</button>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    var text = document.querySelector('#subject1');
    text.onclick = function() {
        text.style.color = 'purple';
    }

</script>
</body>
</html>