import { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import '../styles/login-bar.css';

function LoginBar() {
    let navigate = useNavigate();

    const [loggedIn, setLoggedIn] = useState(false);
    const [username, setUsername] = useState("");

    useEffect(() => {
        try {
            const user = localStorage['username'];
            if (user != null) {
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
    }, []);

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
                <div id="logged-in">
                    <div className="user-info">
                        <p id="username">{username}</p>
                        <button id="account" onClick={handleAccount}>Účet</button>
                    </div>
                    <button id="logout" onClick={handleLogout}>Odhlásit</button>
                </div>
            ) : (
                <div id="logged-out">
                    <button id="sign-up" onClick={handleSignUp}>Registrace</button>
                    <button id="sign-in" onClick={handleSignIn}>Přihlášení</button>
                </div>
            )}
        </div>
    );
}

export default LoginBar