 /////////////////////////////start checkboxes//////////////////////////////////
var  new_store_id="";
var  old_store_id="";
var  add_store_id="";
var  remove_store_id="";
var field_text="";
var select_feild="";
 function initiateChkFn(newstoreid,oldstoreid,addstoreid,removestoreid,field,selectfeild){
	
	 new_store_id=newstoreid;
	 old_store_id=oldstoreid;
	 field_text=field;
	 select_feild=selectfeild;
	 add_store_id=addstoreid;
	 remove_store_id=removestoreid;
 }
 
 
 function showCheckboxes() {
	
     var checkboxes = document.getElementById("checkboxes");
     $("#checkboxes").toggle();
     $("#search_data").val('');
     $('.chklist').each(function() {
         $(this).show()
     })
 }

function chkClick() {
	
     var num = 0;
     $('#show_list').empty()
     $("input[type='checkbox'][name='multisub']").each(function() {
         if (this.checked) {
             /*$('#show_list').append("<span class='subspan'><i class='lni lni-chevron-left me-2' style='margin: 5px;  font-size: 15px; font-weight:bold;' onclick='removeChkFn(" + this.value + ")'></i>" + this.parentElement.innerText + "<span> <br>");*/
             $('#show_list').append("<li id='"+this.value+"'><a class='status-btn active-btn'>" + this.parentElement.innerText + "<i class='lni lni-close'></i></a></li>");
             num = num + 1;
             setXonclick(this.value)
         }
     });
     var paramvar = $('input[name="multisub"]:checkbox:checked').map(function() {
         return this.value;
     }).get();
    $("#"+new_store_id).val(paramvar.join(","));
     if (num != 0) {
			$("#"+select_feild).text(field_text+" ("+num+")");
			$("#"+select_feild).append("<span class='mandatory'>*</span>");
	}
     else {
			$("#"+select_feild).text(field_text+" ("+0+")");
			$("#"+select_feild).append("<span class='mandatory'>*</span>");
	}
     setAddRemove();
 }
 
 function setXonclick(obj){
	document.getElementById(''+obj).onclick = function() {
			removeChkFn(obj);
		};
}

 function removeChkFn(value) {
     $("input[type='checkbox'][name='multisub'][value='" + value + "']").prop('checked', false);
     chkClick();
 }

 function setChk(data){
	var str="";
	data = data.split(',');
	 for(i=0; i < data.length; i++) {
			$("input[type=checkbox][name='multisub'][value='" + data[i] + "']").prop("checked", true);
			if(i==0){
				str=data[i];
			}
			else{
				str=str+","+data[i];
			}
			$("#"+old_store_id).val(str);
			
		}
		chkClick();
 }
 
function fnFilterChk(val) {
    var re = new RegExp(val, "i")
    $('.chklist').each(function() {
        var text = $(this).text(),
            matches = !!text.match(re);
        $(this).toggle(matches)
    });
}
 
 
 function setAddRemove(){

	  var newArray = $("#"+new_store_id).val().split(",");
	  var oldArray = $("#"+old_store_id).val().split(",");

	  $("#"+add_store_id).val(findMissing(newArray, oldArray));
	  $("#"+remove_store_id).val(findMissing(oldArray, newArray));
 }
 
 function findMissing(a, b) {
     var n = a.length;
     var m = b.length;
     var str = "";
     for (var i = 0; i < n; i++) {
         var j;

         for (j = 0; j < m; j++)
             if (a[i] == b[j])
                 break;

         if (j == m) {
             if (str == "") {
                 str = a[i];
             } else {
                 str = str + ',' + a[i];
             }
         }

     }

     return str;
 }
 /////////////////////////////End checkboxes//////////////////////////////////