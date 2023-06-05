/*------------------------------
        resize - font  start 
    -------------------------------*/

var $affectedElements = $("p, h1, h2, h3, h4, h5, h6 , a , li ,ul ,i ,td ,th , label ,button, span, div"); // Can be extended, ex. $("div, p, span.someClass")

//Storing the original size in a data attribute so size can be reset
$affectedElements.each( function(){
var $this = $(this);
$this.data("orig-size", $this.css("font-size") );
});
var cnt=0;
var dcnt = 0
$("#btn-increase").click(function(){
	if(cnt< 5){
changeFontSize(1);
    cnt+=1;
    dcnt-=1;
	}
})

//console.log( "dcnt---",dcnt)

$("#btn-decrease").click(function(){
if(dcnt< 5){
	changeFontSize(-1);
    dcnt+=1;
    cnt-=1;
}
})

$("#btn-orig").click(function(){
$affectedElements.each( function(){
     var $this = $(this);
     $this.css( "font-size" , $this.data("orig-size") );
});
})

function changeFontSize(direction){
 $affectedElements.each( function(){
     var $this = $(this);
     $this.css( "font-size" , parseInt($this.css("font-size"))+direction );
 });
}

/*------------------------------
        resize - font  end 
    -------------------------------*/