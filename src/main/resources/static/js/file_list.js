$(function() {
    let data = {};
    data.userId = "andrew.choe";

    $.ajax({
        method: 'GET',
        url: '/v1/users/file/list',
        data: data,
        contentType: 'application/json;charset=UTF-8',
        success: function(response) {
            alert("성공적으로 조회되었습니다.");
        },
        error: function(err) {
            alert("조회에 실패했습니다.");
            console.error(err);
        }
    });
});