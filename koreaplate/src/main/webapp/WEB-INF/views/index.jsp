<%@page import="org.springframework.web.context.annotation.SessionScope"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/js-cookie/2.2.1/js.cookie.js"></script>

<% request.setCharacterEncoding("utf-8"); %>

<link rel="stylesheet" href="resources/assets/style/main-nav.css" type="text/css">

<%@ include file="template/header.jsp" %>
	
	<script>
		$(function () {
		    var lastScrollTop = 0,
		        delta = 15;
		    $(window).scroll(function (event) {
		        var st = $(this).scrollTop();
		        if (Math.abs(lastScrollTop - st) <= delta) return;
		        if ((st > lastScrollTop) ) {
		            $(".nav-wrap").css("background-color", "white");
		            $(".nav-btn").css("color", "black");            
		        } if((st < lastScrollTop) ) {
		            $(".nav-wrap").css("background-color", "transparent");
		            $(".nav-btn").css("color", "white");
		        }
		        lastScrollTop = st;
		    });
		});
		
		$(function () {
		$('.HomeSearchInput').click(function () {  
		    if($(".invisible").css("display") == "none"){   
		    	$('.invisible').css("display", "block");
		    } else {  
		    	$('.invisible').css("display", "none");   
		    }  
			}); 
		});
		
		$( document ).ready(function() {
		    $(document).mouseup(function(e){
		    var container = $(".invisible");
		    if(container.has(e.target).length === 0)
		        container.hide();
		    });
		});
	</script>
	
	 <script>
	 	var json_list = Cookies.getJSON('query');
		$(function(){
			var html ='';
			console.log(json_list.length);
			console.log(json_list);
			for(var i=json_list.length-1; i>=0; i--){
				for(key in json_list[i]){
					if(json_list[i][key] !=""){
						html +='<div class="item"><a href="searchPage?main-search='+json_list[i][key]+'">'+json_list[i][key]+'</a></div>';
					}
				}
			}
			$('.invisible').html(html);
		});
	</script> 
	
	<div class="header-wrap">
		<div class="title-wrap">
			<p class="title">솔직한 리뷰, 믿을 수 있는 평점!</p>
			<h1 class="title">코리아 플레이트</h1>
		</div>
		
 		<div class="search-wrap">
	 		<div class="main-search">
				<div class="icon-box"><i class="fas fa-search"></i></div>
				<form class="search-form" action="searchPage">
					<input id="search-input" class="HomeSearchInput" name="main-search" type="text" maxlength="50" placeholder="지역명, 맛집 이름"	autocomplete="off" />
					<input class="btn-search" type="submit" value="검색" />
    			</form>
			</div>					
		</div>
		
		<video autoplay loop muted>
			<source src="<c:url value="/resources/assets/video/indexvideo01.mp4" />" type="video/mp4">
		</video>
	</div>
	
<%@ include file="main.jsp" %>
<%@ include file="template/footer.jsp" %>
