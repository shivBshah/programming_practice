
function validateForm()
{
    var fname = document.vform.firstname.value;
    var lname = document.vform.lastname.value;
    var emailID = document.vform.email.value;
    var phone = document.vform.phone.value;
    
    if (fname=="") {
        alert("Please enter your first name");
        return false;
    }
    else {
        var namePattern = /^[A-z ]+$/;
        if (!fname.match(namePattern))
            {
                alert("Please enter correct first name.");
                return false;
            }
    }
    
    
    if (lname=="") {
        alert("Please enter your last name");
        return false;
    }
    else {
        var namePattern = /^[A-z ]+$/;
        if (!lname.match(namePattern))
            {
                alert("Please enter correct last name.");
                return false;
            }
    }
    
    if (emailID=="") {
        alert("Please enter your email address");
        return false;
    }
    
    if (phone=="") {
        alert("Please enter your phone number.");
        return false;
    }
   
        
    if (!validEmail(emailID))
        {
            alert("Please enter valid email address.");
            document.vform.email.focus();
            return false;
        }
    
     if (!validPhone(phone))
     {
        alert("Please enter valid phone number.");
        document.vform.phone.focus(); 
         return false;
     }
    
    return true;
}

function validEmail(email) {
    
        atpos = email.indexOf("@");
         dotpos = email.lastIndexOf(".");
         
         if (atpos < 1 || ( dotpos - atpos < 2 )) 
         {
            return false;
         }
         return true;
}

function validPhone(phone){
    
        var phonePattern = /^\[0-9]{10}$/;  
        if(phone.match(phonePattern))  
        {  
            return true;  
        }  

        return false;   
}