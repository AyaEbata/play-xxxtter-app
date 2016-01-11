$(window).load(function() {
    if ($('#followJudge').val() == "follow") {
        $('.follow').removeClass('btn-default');
        $('.follow').addClass('btn-primary');
    } else {
        $('.follow').removeClass('btn-primary');
        $('.follow').addClass('btn-default');
    }

    if (location.href.match('follow')) {
        $('.userNav li').removeClass('active');
        $('.menu li:nth-child(2)').addClass('active');
//    } else if (location.href.match('')) {
//        $('.userNav li').removeClass('active');
//        $('.menu li:nth-child(3)').addClass('active');
    }
});
