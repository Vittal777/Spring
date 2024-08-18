const pwdlogo = document.getElementsByClassName('pwd-logo')[0]
const pass = document.getElementById('pwd')

pwdlogo.addEventListener('click',() =>{
	if(pass.type==='password'){
		pass.type = 'text';
		pwdlogo.innerHTML = '<i class="far fa-eye-slash"></i>';
		}else{
		pass.type = 'password';
		pwdlogo.innerHTML =  '<i class="far fa-eye"></i>';
		}
});


document.addEventListener('DOMContentLoaded', function() {
var regmessage = document.getElementById('regp');
    if (regmessage) {
        regmessage.style.display = 'block';
        setTimeout(function() {
            regmessage.style.display = 'none';
            window.location.href = 'http://localhost:1725/login';
        }, 1500); 
        
    }
});