import React, { useEffect, useState } from "react";
import Reviews from "./review-list";
import PageSwitch from "./page-switch";
import SortingOptions from "./sorting-options";

function ProductDetail(props) {
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
            justifyContent: "center",
            alignItems: "center",
            flexDirection: "column",
            marginBottom: "40px",
            marginRight: "60px"
        }}>
            <div style={{
                display: "flex",
                justifyContent: "center",
                alignItems: "center",
                marginBottom: "20px"
            }}>
                <div style={{
                    display: "flex",
                    justifyContent: "center",
                    alignItems: "center",
                    height: "300px",
                    width: "300px",
                    overflow: "hidden",
                    marginRight: "50px",
                    padding: "10px"
                }}>
                    <img src={"/img/" + product.pathToImage} alt={product.productName} style={{
                        height: "100%",
                        width: "100%",
                        objectFit: "contain"
                    }}/>
                </div>
                <div style={{
                    display: "flex",
                    flexDirection: "column",
                    justifyContent: "center",
                    maxWidth: "500px"
                }}>
                    <h2 style={{
                        fontSize: "2.2rem",
                        margin: "0"
                    }}>{product.productName}</h2>
                    <h3 style={{
                        fontSize: "1.4rem",
                        color: "#888",
                        margin: "10px 0"
                    }}>{product.category}</h3>
                    <p style={{
                        fontSize: "1.2rem",
                        margin: "10px 0"
                    }}>{product.description}</p>
                </div>
            </div>
        </div>
    );
}

export default ProductDetail;
