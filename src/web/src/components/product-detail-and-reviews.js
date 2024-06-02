import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import Reviews from "./review-list";
import PageSwitch from "./page-switch";
import SortingOptions from "./sorting-options";
import ProductDetail from "./product-detail";

function ProductDetailAndReviews(props) {
    let navigate = useNavigate();
    const product = props.product;
    const id = product.id;
    const [sortBy, setSortBy] = useState("reviewDate");
    const [page, setPage] = useState(0);
    const [data, setData] = useState([]);
    const [areReviewsLoaded, setAreReviewsLoaded] = useState(false);
    const [error, setError] = useState();

    useEffect(() => {
        setAreReviewsLoaded(false);
        fetch(`${process.env.REACT_APP_TARGET_DOMAIN}/reviews/by-product/${id}?pageNumber=${page}&sortBy=${sortBy}`)
            .then(response => {
                if (response.ok) {
                    return response.json();
                }
                throw new Error(`Unable to get data: ${response.statusText}`);
            })
            .then(json => {
                setData(json);
            })
            .catch(err => setError(err.message))
            .finally(() => setAreReviewsLoaded(true));
    }, [id, page, sortBy]);

    return (
        <div style={{
            display: "flex",
            flexDirection: "column",
            alignItems: "center",
            padding: "20px",
        }}>
            <ProductDetail product={product} />
            <button
                onClick={() => navigate(`/add-review/${id}`)}
                style={{
                    padding: "10px 20px",
                    fontSize: "1rem",
                    margin: "20px 0",
                    backgroundColor: "#ccc",
                    color: "white",
                    border: "none",
                    borderRadius: "5px",
                    cursor: "pointer",
                    transition: "background-color 0.3s",
                }}
            >
                Přidat recenzi
            </button>
            <SortingOptions sortBy={sortBy} setSortBy={setSortBy}
                            sortOptions={[
                                {"value": "reviewDate", "label": "Datum"},
                                {"value": "rating", "label": "Hodnocení"}]} />
            <Reviews data={data} isPending={!areReviewsLoaded} error={error} />
            <PageSwitch page={page} setPage={setPage} />
        </div>
    );
}

export default ProductDetailAndReviews;
