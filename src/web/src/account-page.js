import React from 'react';
import './styles/account-page.css';

function AccountPage() {
    const username = localStorage.getItem('username');

    return (
        <div className="account-page-container">
            <h2>Účet</h2>
            <div className="account-info">
                <label className="account-label">Uživatelské jméno:</label>
                <p className="account-username">{username}</p>
            </div>
        </div>
    );
}

export default AccountPage;
