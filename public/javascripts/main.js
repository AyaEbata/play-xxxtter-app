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
});


/* ツイートの取得 */
setInterval("tweetListAjax()",5000);

function tweetListAjax() {
    $.ajax({
        url: 'http://localhost:9000/top',
        type: 'GET',

    }).success(function(data){
        var myListLength = $('.tweet-list-area li').length;
        var ajaxListLength = $(data).find('.tweet-list-area li').length;

        // ちょっと保留（新着のお知らせ的な感じに出るやつ）
//        if (myListLength < ajaxListLength && $('.new').length < 1) {
//            var test = $('.tweet-list-area').html();
//            $('.tweet-list-area').prepend('<li class="list-group-item new">新着ツイートを表示</li>');
//        }
//
//        $('.new').click(function() {
            var tweetHtml = $(data).find('.tweet-list-area').html();
            $('.tweet-list-area').html(tweetHtml);
//        });

    }).error(function(data){
        alert('error!!!');
    });
}
