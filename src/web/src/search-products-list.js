import {useParams} from "react-router-dom";
import React, {useEffect, useState} from "react";
import SortingOptions from "./components/sorting-options";
import Products from "./components/products-list";
import PageSwitch from "./components/page-switch";

function SearchProductsList() {
    const [sortBy, setSortBy] = useState("id");
    const [page, setPage] = useState(0);
    const { text } = useParams();
    const [products, setProducts] = useState([]);
    const [isPending, setIsPending] = useState(true);
    const [error, setError] = useState();

    useEffect(() => {
        fetch(`${process.env.REACT_APP_TARGET_DOMAIN}/products/text-search/${text}?pageNumber=${page}&sortBy=${sortBy}`)
            .then(response => response.json())
            .then(json => setProducts(json))
            .catch(error => setError(error.message))
            .finally(() => setIsPending(false));
    }, [ text, page, sortBy]);

    return (
        <div className="container">
            <h1 className="heading">Výsledky vyhledávání: {text}</h1>
            <SortingOptions sortBy={sortBy} setSortBy={setSortBy} sortOptions={[
                {"value": "id", "label": "ID"},
                {"value": "productName", "label": "Název"}]}/>
            <Products products={products} isPending={isPending} error={error} />
            <PageSwitch page={page} setPage={setPage} />
        </div>
    );
}

export default SearchProductsList