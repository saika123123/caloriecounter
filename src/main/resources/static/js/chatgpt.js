$(function () {
    $('#send-button').on('click', function () {
        const message = $('#input-field').val();
        sendRequest(message);
    });//■ onの終わり
});//■ 1番上のready関数の最後

//OpenAI APIにリクエストを送信する関数
function sendRequest(message) {
    $.ajax({
        url: "/chat/openAiChat",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify({
            message: message
        })
    }).done(function (response, textStatus, jqXHR) {
        console.log(response);
    }).fail(function (jqXHR, textStatus, errorThrown) {
        console.log("きてない");
    });
}
