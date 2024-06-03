import React, {useEffect, useState} from 'react';
import "../styles/review.css";

function Review({ review }) {
    const [user, setUser] = useState(null)

    useEffect(() => {
        fetch(`${process.env.REACT_APP_TARGET_DOMAIN}/users/username/${review.userId}`)
            .then(response => {
                if (response.ok) {
                    return response.text();
                }
                throw new Error(`Unable to get user: ${response.statusText}`);
            })
            .then(username => {
                setUser(username);
            })
    }, []);

    return (
        <div className="review-container">
            <div className="review-header">
                <span className="review-rating">Hodnocení: {review.rating}</span>
                <span className="review-date">{new Date(review.reviewDate).toLocaleDateString()}</span>
            </div>
            <div className="review-body">
                <p className="review-comment">{review.comment}</p>
            </div>
            <div className="review-footer">
                <span className="review-user">Uživatel: {user}</span>
            </div>
        </div>
    );
}

export default Review;
