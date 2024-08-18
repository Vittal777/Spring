document.addEventListener('DOMContentLoaded', function() {
    
    var logoutBtn = document.querySelector('.logout-btn');

    
    logoutBtn.addEventListener('click', function() {
        
        window.location.href = 'login';
    });
});
