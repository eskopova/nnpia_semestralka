import React, { useEffect, useState } from 'react';
import './styles/paging-and-sorting.css';
import { useParams } from "react-router-dom";
import ProductDetailAndReviews from "./components/product-detail-and-reviews";

function ProductPage() {
    const { id } = useParams();
    const [product, setProduct] = useState(null);
    const [isProductLoaded, setIsProductLoaded] = useState(false);
    const [error, setError] = useState();

    useEffect(() => {
        setIsProductLoaded(false);
        fetch(`${process.env.REACT_APP_TARGET_DOMAIN}/products/${id}`)
            .then(response => {
                if (response.ok) {
                    return response.json();
                }
                throw new Error(`Unable to get data: ${response.statusText}`);
            })
            .then(json => {
                setProduct(json);
            })
            .catch(err => setError(err.message))
            .finally(() => setIsProductLoaded(true));
    }, []);

    return (
        <div style={{ padding: "20px", maxWidth: "1200px", margin: "0 auto" }}>
            {isProductLoaded && !error && product && <ProductDetailAndReviews product={product} />}
        </div>
    );
}

export default ProductPage;
