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

    if (location.href == 'http://localhost:9000/top') {
        /* 「ホーム」ボタンの下の線 */
        $('.navbar-home').css({
                'border-bottom': 'solid 5px #337ab7',
                'height': '50px'
        });

        /* ツイート中に@userIdがあったらリンクに置き換え */
        var test = $('.tweet-list-area').html();
        $('.tweet-list-area').html(test.replace(/@(\w+)\s/g, '<a href="/userTop/$1">@$1</a> '));
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


/* リプ用のフォーム */
$(document).on('click', '.tweet-list-area > li', function(){
    $('.reply').remove();
    var userId = $(this).find('a').eq(0).text();
    $(this).append('<form action="/top" method="POST" class="input-group reply">'
            + '<input type="text" name="tweet" class="form-control" placeholder="' + userId + 'さんへの返信" value="" />'
            + '<span class="input-group-btn">'
            + '<input type="submit" class="btn btn-default" value="ていっ！" />'
            + '</span>'
            + '</form>');
    $('.reply > input:visible').first().focus();
});

/* １文字目はひらがなか英語だけしかいけないかもしれない */
// TODO: ！！改良しろ！！
$(document).on('keyup', '.tweet-list-area > li input', function(key){
    if(key.keyCode != 8){
        var userId = $(this).parents('li').find('a').eq(0).text();
            if ($(this).val().length < userId.length + 2) {
                $(this).val(userId + ' ' + $(this).val());
        }
    }
});
