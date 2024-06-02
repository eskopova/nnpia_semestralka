import React, { useState } from "react";
import { useParams } from "react-router-dom";
import './styles/form.css';

function ReviewForm() {
    const { id } = useParams(); // Get the product ID from the URL parameters
    const [rating, setRating] = useState(10);
    const [comment, setComment] = useState("");
    const [error, setError] = useState(null);
    const [success, setSuccess] = useState(null);

    const handleSubmit = async (event) => {
        event.preventDefault();
        setError(null);
        setSuccess(null);

        const reviewData = {
            rating,
            comment,
            productId: id,
        };

        console.log(JSON.stringify(reviewData))

        try {
            const response = await fetch(`${process.env.REACT_APP_TARGET_DOMAIN}/reviews`, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(reviewData),
            });

            if (response.ok) {
                setSuccess("Recenze byla úspěšně přidána!");
            } else {
                const errorData = await response.json();
                setError(errorData.message || "Přidání recenze se nezdařilo");
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
