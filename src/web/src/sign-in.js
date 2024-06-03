import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import api from './api';
import './styles/form.css';

function SignIn({ onLoginStatusChange }) {
    let navigate = useNavigate();
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState(null);
    const [success, setSuccess] = useState(null);

    const handleSubmit = async (event) => {
        event.preventDefault();
        setError(null);
        setSuccess(null);

        const loginData = { username, password };

        try {
            const response = await api.post('/auth/login', loginData);

            if (response.status === 200) {
                const { token, user } = response.data;

                localStorage.setItem('authToken', token);
                localStorage.setItem('userId', user.id);
                localStorage.setItem('username', user.username);
                setSuccess("Přihlášení proběhlo úspěšně!");

                onLoginStatusChange(true);
                navigate("/account");
            } else {
                setError(response.data.message || "Přihlášení se nezdařilo");
            }
        } catch (err) {
            setError("Nastala chyba: " + err.message);
        }
    };

    return (
        <div className="form-container">
            <h2>Přihlášení uživatele</h2>
            <form onSubmit={handleSubmit} className="form">
                <div className="form-group">
                    <label className="form-label">Uživatelské jméno:</label>
                    <input
                        className="input-field"
                        type="text"
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                        required
                    />
                </div>
                <div className="form-group">
                    <label className="form-label">Heslo:</label>
                    <input
                        className="input-field"
                        type="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        required
                    />
                </div>
                <button
                    className="button"
                    type="submit">
                    Přihlásit
                </button>
                {error && <p style={{ color: "red" }}>{error}</p>}
                {success && <p style={{ color: "green" }}>{success}</p>}
            </form>
        </div>
    );
}

export default SignIn;
