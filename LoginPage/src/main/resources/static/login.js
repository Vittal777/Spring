const pass = document.getElementById('pwd');
const logo = document.getElementsByClassName('pwd-logo')[0];

logo.addEventListener('click', () => {
    if (pass.type === 'password') {
        pass.type = 'text';
        logo.innerHTML = '<i class="far fa-eye-slash"></i>';
    } else {
        pass.type = 'password';
        logo.innerHTML = '<i class="far fa-eye"></i>';
    }
});

document.addEventListener('DOMContentLoaded', function() {
    var errorMessage = document.getElementsByClassName('error-message')[0];
    if (errorMessage) {
        errorMessage.style.display = 'block';
        setTimeout(function() {
            errorMessage.style.display = 'none';
        }, 3000); 
    }
});

function showLogoutMessage() {
    const logoutMessage = document.getElementById('logout-msg');
    if (logoutMessage) {
        logoutMessage.style.display = 'block'; 
        
        setTimeout(function() {
            logoutMessage.style.display = 'none'; 
            window.location.href = 'http://localhost:1725/login'; 
        }, 3000); 
    }
}

document.addEventListener('DOMContentLoaded', function() {
    if (window.location.href.indexOf('?logout=true') !== -1) {
        showLogoutMessage(); 
    }
});