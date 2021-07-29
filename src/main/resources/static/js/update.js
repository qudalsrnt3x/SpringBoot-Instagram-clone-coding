// (1) 회원정보 수정
function update(userId, event) {

    let data = $("#profileUpdate").serialize(); // 폼태그 안에 있는 모든 인풋값이 담김
    console.log(data)

    $.ajax({
        type: 'put',
        url: `/api/user/${userId}`,
        data: data,
        contentType:'application/x-www-form-urlencoded; charset=utf-8',
        dataType: 'json'
    }).done(res=> { // json을 파싱해서 가져온다. res는 자바스크립트 오브젝트가 된다.
        console.log("update 성공");
        location.href=`/user/${userId}`;
    }).fail(error=>{
        console.log("update 실패");
    });
}