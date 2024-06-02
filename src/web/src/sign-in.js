import React, { useState } from "react";
import './styles/form.css'; // Import the CSS file

function SignIn() {
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
            const response = await fetch(`${process.env.REACT_APP_TARGET_DOMAIN}/auth/login`, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(loginData),
            });

            if (response.ok) {
                setSuccess("Přihlášení proběhlo úspěšně!");
            } else {
                const errorData = await response.json();
                setError(errorData.message || "Přihlášení se nezdařilo");
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
