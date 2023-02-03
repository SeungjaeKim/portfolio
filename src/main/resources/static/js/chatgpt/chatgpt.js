const thisDay = document.querySelector('.chat__timestamp');
const sendBoxEl = document.querySelector('#sendBox');
const today = new Date();
thisDay.textContent = today.toLocaleDateString();

$('#sendBox').keydown(function(key){
    if (key.keyCode === 13) {
        chat();
    }
})
document.getElementById('sendMsg').addEventListener('click', function(){
    chat();
});

function getCurrentTime() {
    const date = new Date();
    const hours = date.getHours();
    const minutes = date.getMinutes();
    const seconds = date.getSeconds();
    return hours + ":" + minutes + ":" + seconds;
}

function showLoading() {
    document.getElementById("loading").style.display = "flex";
}

function hideLoading() {
    document.getElementById("loading").style.display = "none";
}

function chat(){
    $('.main-chat').append(`
        <div class="message-row message-row--own">
            <div class="message-row__content">
                <div class="message__info">
                    <span class="message__bubble">${$('#sendBox').val()}</span>
                    <span class="message__time">${getCurrentTime()}</span>
                </div>
            </div>
        </div>
    `);

    const data = {
        prompt: sendBoxEl.value
    }

    showLoading();

    $.ajax({
        type: "POST",
        async: true,
        url: "/chatgpt/api/send",
        data: JSON.stringify(data),
        contentType: "application/json",
        error: function(request, status, error){

        },
        success: function (response, status, request) {
            $('.main-chat').append(`
              <div class="message-row">
                <div class="message-row__content">
                    <span class="message__author">SomeOne</span>
                    <div class="message__info">
                        <span class="message__bubble">${response.data}</span>
                        <span class="message__time">${getCurrentTime()}</span>
                    </div>
                </div>
              </div>
            `);
            $('#sendBox').val('');
            hideLoading();

        }
    });
}


