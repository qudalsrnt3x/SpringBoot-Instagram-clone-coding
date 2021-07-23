// (1) 회원정보 수정
function update(userId, event) {

    let data = $("#profileUpdate").serialize(); // 폼태그 안에 있는 모든 인풋값이 담김
    console.log(data)

    $.ajax({
        type: 'put',
        url: '/api/user/${userId}',
        data: data,
        contentType:'application/x-www-form-urlencoded; charset=utf-8',
        dataType: 'json'
    }).done(res=> {
        console.log("성공");
    }).fail(error=>{
        console.log("실패");
    });
}