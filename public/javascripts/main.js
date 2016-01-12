$(window).load(function() {
    /* フォローしてたら青色、してなかったら白色のボタン */
    if ($('#followJudge').val() == "follow") {
        $('.follow').removeClass('btn-default');
        $('.follow').addClass('btn-primary');
    } else {
        $('.follow').removeClass('btn-primary');
        $('.follow').addClass('btn-default');
    }

    /* ツイート、フォロー、フォロワーの切り替え */
    if (location.href.match('follow')) {
        $('.userNav li').removeClass('active');
        $('.menu li:nth-child(2)').addClass('active');
//    } else if (location.href.match('')) {
//        $('.userNav li').removeClass('active');
//        $('.menu li:nth-child(3)').addClass('active');
    }

    /* 「ホーム」ボタンの下の線 */
    if (location.href == 'http://localhost:9000/top') {
        $('.navbar-home').css({
                'border-bottom': 'solid 5px #337ab7',
                'height': '50px'
        });
    }
});


/* ツイートの取得 */
var tweetHtml;
setInterval("tweetListAjax()", 1000);

$(document).on('click', '.new', function(){
    $('.tweet-list-area').html(tweetHtml);
    $('#maru').remove();
});

function tweetListAjax() {
    $.ajax({
        url: 'http://localhost:9000/top',
        type: 'GET',

    }).success(function(data){
        tweetHtml = $(data).find('.tweet-list-area').html();

        var myListLength = $('.tweet-list-area li').length;
        var ajaxListLength = $(data).find('.tweet-list-area li').length;

        if (myListLength < ajaxListLength && $('.new').length == 0 && myListLength != 0) {
            var test = $('.tweet-list-area').html();
            $('.tweet-list-area').prepend('<li class="list-group-item new">新着ツイートを表示</li>');
            $('.navbar-home').prepend('<p id="maru">●</p>');
        }

    }).error(function(data){
        console.log('error!!!');
    });
}
