<!doctype html>
<html lang="en">
 <head>
  <meta charset="UTF-8">
  <meta name="Generator" content="EditPlus®">
  <meta name="Author" content="Sumi">
  <meta name="Keywords" content="Panel">
  <meta name="Description" content="Insert Noun Popup">
  <title>Insert Noun</title>
  <link href="F:\ALL\Tutorial\JAVA\GAME\NetBeans Project\Semantic Analyzer\webcontent\resources\bootstrap\css\bootstrap.min.css" rel="stylesheet">
  <!-- <link href='http://fonts.googleapis.com/css?family=Roboto:300' rel='stylesheet' type='text/css'> -->
   <script src="F:\ALL\Tutorial\JAVA\GAME\NetBeans Project\Semantic Analyzer\webcontent\resources\bootstrap\js\jquery.js"></script>
   <script src="F:\ALL\Tutorial\JAVA\GAME\NetBeans Project\Semantic Analyzer\webcontent\resources\bootstrap\js\bootstrap.min.js"></script>
  <style>
       body{
           font:12px/15px Roboto, "Helvetica Neue", Helvetica, sans-serif;
       }
       select,
       input,
       .btn {
           font-family: "Helvetica Neue",Helvetica,Arial,sans-serif;
       }
       #wrapper{
           margin:0 auto;
       }
       .main-form {
           width: 360px;
           min-height: 360px;
           background: #fff;
           border-radius: 60px;
           margin:0px auto 20px;
           padding: 20px;
       }
       .form-logo {
           font-size: 100px;
           color: #708090;
       }
   </style>
</head>
<body>
<script>
function newAccount() {
   var firstName = document.getElementById("firstname").value;
   var lastName = document.getElementById("lastname").value;
   var phone = document.getElementById("phone").value;
   var email = document.getElementById("email").value;
   // Call Java callback function and pass text fields values.
   onCreateAccount(firstName, lastName, phone, email);
}
</script>
<div id="wrapper">
   <div class="main-form">
       <form action="#" method="POST">
           <fieldset>
               <div class="text-center">
                   <span class="form-logo glyphicon glyphicon-user"></span>
               </div>
               <div class="form-body">
                   <h1 class="form-title text-center">New Account</h1>
                   <div class="form-group">
                       <input class="form-control" type="text" id="firstname" name="firstname" placeholder="First Name">
                   </div>
                   <div class="form-group">
                       <input class="form-control" type="text" id="lastname" name="surname" placeholder="Last Name">
                   </div>
                   <div class="form-group">
                       <input class="form-control" type="text" id="phone" name="phone" placeholder="Phone">
                   </div>
                   <div class="form-group">
                       <input class="form-control" type="email" id="email" name="email" placeholder="Email">
                   </div>
                   <div class="form-group text-center">
                       <button class="btn btn-default btn-lg" type="button" onclick="newAccount();">New Account</button>
                   </div>
               </div>
           </fieldset>
       </form>
   </div>
</div>
</body>
</html>
