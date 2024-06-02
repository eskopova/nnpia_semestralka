import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import CategoryList from "./components/category-list.js";
import Products from "./components/products-list.js";
import SortingOptions from "./components/sorting-options";
import PageSwitch from "./components/page-switch";

function ByCategory() {
    return (
        <CategoryList address="product-categories" link="by-category" heading="Produkty podle kategorie pečiva"></CategoryList>
    );
}

function CategoryProducts() {
    const [sortBy, setSortBy] = useState("id");
    const [page, setPage] = useState(0);
    const { name } = useParams();
    const [products, setProducts] = useState([]);
    const [isPending, setIsPending] = useState(true);
    const [error, setError] = useState();

    useEffect(() => {
        fetch(`${process.env.REACT_APP_TARGET_DOMAIN}/products/by-category/${name}?pageNumber=${page}&sortBy=${sortBy}`)
            .then(response => response.json())
            .then(json => setProducts(json))
            .catch(error => setError(error.message))
            .finally(() => setIsPending(false));
    }, [name, page, sortBy]);

    return (
        <div className="container">
            <h1 className="heading">Produkty podle kategorie: {name}</h1>
            <SortingOptions sortBy={sortBy} setSortBy={setSortBy} sortOptions={[
                {"value": "id", "label": "ID"},
                {"value": "productName", "label": "Název"}]}/>
            <Products products={products} isPending={isPending} error={error} />
            <PageSwitch page={page} setPage={setPage} />
        </div>
    );
}

export { ByCategory, CategoryProducts };