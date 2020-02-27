domain = "http://10.42.0.1:8000/"
//domain = "https://deedscan.herokuapp.com/"

email = "siziba.uz@gmail.com"

userEmail = "siziba.uz@gmail.com"


function setEmail(theEmail) {
    email = theEmail;
}
function openSignUp() {
    JSReceiver.openSignUp();
}

function openLogIn() {
    JSReceiver.openLogIn();
}

function requestEmail() {
    JSReceiver.requestEmail();
}


$('#signup_form').on('submit', function(event){
    event.preventDefault();
    console.log("form submitted!")  // sanity check
    signup();
  });


function signup(){
    var theName = $('#name').val();
    var theemail = $('#email').val();
    var theorganisation = $('#org').val();
    var thepassword = $('#pwd').val();
    var thepassword_confirm = $('#confirm_pwd').val();
    var theposition = $('#pos').val();

    //alert(theName)

    JSReceiver.openProgDialog();

    $.ajax({
        url:domain + "signup",
        type:"POST",
  
        data:{
          name: theName,
          email: theemail,
          organisation: theorganisation,
          password: thepassword,
          password_confirm: thepassword_confirm,
          position: theposition,
        
        },
        success:function(response) {

            //alert(response.outcome);
            JSReceiver.closeProgDialog();

            if(response.outcome == "done") {
                $('#signup_form').trigger("reset");
                JSReceiver.signUp(email, thepassword);
                JSReceiver.openMain();
            }
          
       },
       error:function(){
        //alert("error");
        JSReceiver.closeProgDialog();
        JSReceiver.openErrorDialog();
       }
  
      });


}



$('#confirm_form').on('submit', function(event){
    event.preventDefault();
    console.log("form submitted!")  // sanity check
    confirm();
  });


  function confirm(){
    var theCode = $('#confirmation_code').val();

    

    $.ajax({
        url:domain + "confirm",
        type:"POST",
  
        data:{
          ver_code: theCode,
          email: email,
        
        },
        success:function(response) {

            alert(response.outcome);

            if(response.outcome == "done") {
                $('#confirm_form').trigger("reset");
                JSReceiver.openMain();
            }
          
       },
       error:function(){
        alert("error");
       }
  
      });
}



$('#login_form').on('submit', function(event){
    event.preventDefault();
    console.log("form submitted!")  // sanity check
    login();
  });


function login(){
    var email = $('#email').val();
    var password = $('#password').val();

    //if(email == "" || )
    JSReceiver.openProgDialog();
    $.ajax({
        url:domain + "login",
        type:"POST",
  
        data:{
          password: password,
          email: email,
        
        },
        success:function(response) {

            JSReceiver.closeProgDialog();

            alert(response.outcome);

            if(response.outcome == "done") {
                $('#login_form').trigger("reset");
                JSReceiver.login(email, password);
                JSReceiver.openMain();
            }

            else {
                JSReceiver.openWronInfoDialog();
            }
          
       },
       error:function(){
        //alert("error");
        JSReceiver.closeProgDialog();
        JSReceiver.openErrorDialog();
       }
  
      });


}

