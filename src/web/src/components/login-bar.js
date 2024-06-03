import { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import '../styles/login-bar.css';

function LoginBar({ onLoginStatusChange }) {
    let navigate = useNavigate();

    const [loggedIn, setLoggedIn] = useState(false);
    const [username, setUsername] = useState("");

    useEffect(() => {
        try {
            const user = localStorage.getItem('username');
            if (user) {
                setLoggedIn(true);
                setUsername(user);
            } else {
                setLoggedIn(false);
                setUsername("");
            }
        } catch (error) {
            setLoggedIn(false);
            setUsername("");
        }

        if (onLoginStatusChange) {
            onLoginStatusChange(loggedIn);
        }
    }, [loggedIn, onLoginStatusChange]);

    const handleLogout = () => {
        try {
            localStorage.clear();
            setLoggedIn(false);
            navigate('/');
        } catch (error) {
            console.error('Error logging out:', error);
        }
    };

    const handleAccount = () => {
        navigate("/account");
    };

    const handleSignUp = () => {
        navigate('/sign-up');
    };

    const handleSignIn = () => {
        navigate("/sign-in");
    };

    return (
        <div className="login-bar">
            {loggedIn ? (
                <div className="logged-in">
                    <div className="user-info">
                        <p className="username">Přihlášen: {username}</p>
                    </div>
                    <button className="account" onClick={handleAccount}>Účet</button>
                    <button className="logout" onClick={handleLogout}>Odhlásit</button>
                </div>
            ) : (
                <div className="logged-out">
                <button className="sign-up" onClick={handleSignUp}>Registrace</button>
                    <button className="sign-in" onClick={handleSignIn}>Přihlášení</button>
                </div>
            )}
        </div>
    );
}

export default LoginBar;
