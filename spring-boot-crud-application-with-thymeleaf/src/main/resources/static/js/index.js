var x = window.location.href;
var arr = x.split("/");
var pageNumber = parseInt(arr[arr.length -1]);
var pageMax = parseInt(arr[arr.length -2]);
var check = arr[arr.length-3];
    if(check != "page")
    { pageNumber = Math.ceil(pageNumber/5);}

    if(Number.isNaN(pageNumber))
        pageNumber =3;
    if(pageNumber <= 2)
        pageNumber =3;
    if(pageNumber >= pageMax -2)
        pageNumber = pageMax-2;
    var page0 = '<a href="/page/' + (pageNumber-2) +'" class="page_box">'+ (pageNumber-2)+'</a>';
    var page1 = '<a href="/page/' + (pageNumber-1) +'" class="page_box">'+ (pageNumber-1)+'</a>';
    var pageInfo = '<a href="#" class="page_box">'+'...'+'</a>';
    var page2 ='<a href="/page/' + pageNumber +'" class="page_box">'+pageNumber+'</a>';
    var page3 ='<a href="/page/' + (pageNumber+1) +'" class="page_box">'+ (pageNumber+1) +'</a>';
    var page4 ='<a href="/page/' + (pageNumber+2) +'" class="page_box">'+ (pageNumber+2) +'</a>';
    if(pageNumber!= pageMax-2)
    {
    var text = '<div id="page_container"><a href="/page/' + 1 +'" class="page_box">'+'<<'+'</a>'+pageInfo+ page0 + page1 + page2 + page3+ page4 + pageInfo+'<a href="/page/lastPage" class="page_box">'+'>>'+'</a>'+'</div>';
    }
    else
    {
    var text = '<div id="page_container"><a href="/page/' + 1 +'" class="page_box">'+'<<'+'</a>'+pageInfo+ page0 + page1 + page2 + page3 +page4 + '</div>';
    }
    document.getElementById("page_container").innerHTML = text;





