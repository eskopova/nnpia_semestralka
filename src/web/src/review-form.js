import React, { useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import api from './api';
import './styles/form.css';

function ReviewForm() {
    let navigate = useNavigate();
    const { id } = useParams(); // Get the product ID from the URL parameters
    const [rating, setRating] = useState(10);
    const [comment, setComment] = useState("");
    const [error, setError] = useState(null);
    const [success, setSuccess] = useState(null);

    const userId = localStorage.getItem('userId')

    const handleSubmit = async (event) => {
        event.preventDefault();
        setError(null);
        setSuccess(null);

        console.log(userId)

        const reviewData = {
            rating,
            comment,
            productId: id,
            userId: userId,
        };

        try {
            const response = await api.post('/reviews', reviewData);

            if (response.status === 200) {
                setSuccess("Recenze byla úspěšně přidána!");
                navigate(`/products/${id}`)
            } else {
                setError(response.data.message || "Přidání recenze se nezdařilo");
            }
        } catch (err) {
            setError("Nastala chyba: " + err.message);
        }
    };

    return (
        <div className="form-container">
            <h2>Přidat recenzi</h2>
            <form onSubmit={handleSubmit} className="form">
                <div className="form-group">
                    <label className="form-label">Hodnocení :</label>
                    <input
                        className="rating-field"
                        type="range"
                        min="1"
                        max="10"
                        value={rating}
                        onChange={(e) => setRating(e.target.value)}
                        required
                    />
                    <span className="rating-value">{rating}</span>
                </div>
                <div className="form-group">
                    <label className="form-label">Komentář:</label>
                    <textarea
                        className="comment-field"
                        value={comment}
                        onChange={(e) => setComment(e.target.value)}
                        required
                    />
                </div>
                <button
                    className="button"
                    type="submit">
                    Přidat recenzi
                </button>
                {error && <p style={{ color: "red" }}>{error}</p>}
                {success && <p style={{ color: "green" }}>{success}</p>}
            </form>
        </div>
    );
}

export default ReviewForm;
