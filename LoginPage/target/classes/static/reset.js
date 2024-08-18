document.addEventListener("DOMContentLoaded", function() {
    var form = document.getElementById("form");
    var password = document.getElementById("pwd");
    var confirmPassword = document.getElementById("cpwd");
    var error = document.getElementById("error");

    form.addEventListener("submit", function(event) {
        if (password.value !== confirmPassword.value) {
            error.innerHTML = "Passwords do not match!";
            event.preventDefault(); 
        } else {
            error.innerHTML = ""; 
        }
    });

    const togglePasswordVisibility = (inputField, toggleButton) => {
        if (inputField.type === 'password') {
            inputField.type = 'text';
            toggleButton.innerHTML = '<i class="far fa-eye-slash"></i>';
        } else {
            inputField.type = 'password';
            toggleButton.innerHTML = '<i class="far fa-eye"></i>';
        }
    };

    const pass = document.getElementById('pwd');
    const logo = document.querySelector('.pwd-logo'); 
    logo.addEventListener('click', () => {
        togglePasswordVisibility(pass, logo);
    });

    const cpass = document.getElementById('cpwd');
    const clogo = document.querySelector('.cpwd-logo'); 
    clogo.addEventListener('click', () => {
        togglePasswordVisibility(cpass, clogo);
    });


    const displayMessage = (elementId) => {
        const messageElement = document.getElementById(elementId);
        if (messageElement) {
            messageElement.style.display = 'block';
            setTimeout(function() {
                messageElement.style.display = 'none';
            }, 2500); 
        }
    };

    displayMessage('resetMsg');
    displayMessage('resetFail');


    if (window.history.replaceState) {
        window.history.replaceState(null, null, window.location.href);
    }
});
