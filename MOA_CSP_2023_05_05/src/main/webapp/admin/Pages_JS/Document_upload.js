$(document).ready(function() {
	

});
//Add-More-Add
var att=1;
function formopen_att(R){
	$("#att_Tb").show();
	
		 $("#id_add_att"+R).hide();
		 $("#att_id_remove"+R).hide();
		 
		 att=0;
		 att= parseInt(R)+1;
		 
		 if(att < 51){
				 
				 $("input#count_hidden_att").val(att);//current serial No
				 $("table#att_Tb").append('<tr align="center" id="tr_id_att'+att+'"><td>'+att+'</td>'
						 +'<td align="center"><input type="text" id="doc_name'+att+'" name="doc_name'+att+'"  maxlength="50" class="form-control" placeholder="Enter Docment Name" autocomplete="off" required></td>'
						 +'<td align="center"><select name="doc_type'+att+'" class="form-control" id ="doc_type'+att+'" onchange=""><option value="0">--Select--</option><option value="1">Type1</option><option value="1">Type2</option></select></td>'
						 +'<td align="center"><input type="file" accept=".pdf" id="document_upload'+att+'" name="document_upload'+att+'"class="form-control"><input type="hidden" id="document_upload_hid'+att+'" name="document_upload_hid'+att+'" class="form-control" ></td>'
						 +'<td align="center"><a class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "id_add_att'+att+'" onclick="formopen_att('+att+');" ><i class="fa fa-plus"></i></a> <a style="margin-right: 10px;"class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "att_id_remove'+att+'" onclick="formopen_re_att('+att+');"><i class="fa fa-trash"></a></td>'
			   		     +'</tr>');
		 
			}else{
					alert("Please Enter max 50 Quantity");
					 if ( att == 51){
						 att = att-1; 
						 $("#rp_id_remove"+att).show();
					 }	   
			}
		 var curcnt = $("#count_hidden_att").val();
		 $("#new_count_hidden").val(curcnt);
	
}
//Add-More-Remove
function formopen_re_att(R){
	var del_index = $("#idofprocedure"+R).val();
	if(String(del_index) == "undefined"){
		del_index="0";
	}else{
		del_index = del_index;
	}
	 var del_field_val = $("#del_id_hidden").val();
	 
	 if(del_field_val == "0,undefined"){
		 $("#del_id_hidden").val(del_index);
	 }else{
		 $("#del_id_hidden").val(del_field_val+","+del_index);
	 }
	
	 $("tr#tr_id_att"+R).remove();
	 att = att-1;
	 $("input#count_hidden_att").val(att);
	 if(R > 2){
		 $("#id_add_att"+att).show();
		 $("#att_id_remove"+att).show();
	 }
	 if(R == 2){
		 $("#id_add_att"+att).show();
	 }
	 var ncc = $("#new_count_hidden").val();
	 ncc = ncc-1;
	 $("#new_count_hidden").val(ncc);
}
