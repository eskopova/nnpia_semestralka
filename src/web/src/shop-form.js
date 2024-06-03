import React, { useState } from 'react';
import api from './api';
import './styles/form.css';

function ShopForm() {
    const [shopName, setShopName] = useState('');
    const [error, setError] = useState(null);
    const [success, setSuccess] = useState(null);

    const handleSubmit = async (event) => {
        event.preventDefault();
        setError(null);
        setSuccess(null);

        try {
            const response = await api.post('/shops', { shopName: shopName });

            if (response.status === 200) {
                setSuccess("Obchod byl úspěšně vytvořen!");
            } else {
                setError(response.data.message || "Vytvoření obchodu se nezdařilo");
            }
        } catch (err) {
            setError("Nastala chyba: " + err.message);
        }
    };

    return (
        <div className="form-container">
            <h2>Přidat obchod</h2>
            <form onSubmit={handleSubmit} className="form">
                <div className="form-group">
                    <label className="form-label">Název obchodu:</label>
                    <input
                        className="input-field"
                        type="text"
                        value={shopName}
                        onChange={(e) => setShopName(e.target.value)}
                        required
                    />
                </div>
                <button className="button" type="submit">Vytvořit obchod</button>
            </form>
            {error && <p style={{ color: "red" }}>{error}</p>}
            {success && <p style={{ color: "green" }}>{success}</p>}
        </div>
    );
}

export default ShopForm;
