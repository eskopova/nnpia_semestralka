import React, { useEffect, useState } from 'react';
import Products from './components/products-list';
import PageSwitch from './components/page-switch';
import SortingOptions from './components/sorting-options';
import './styles/paging-and-sorting.css';

function AllProducts() {
    const [sortBy, setSortBy] = useState("id");
    const [page, setPage] = useState(0);
    const [data, setData] = useState([]);
    const [isPending, setIsPending] = useState(true);
    const [error, setError] = useState();

    useEffect(() => {
        setIsPending(true);
        fetch(`${process.env.REACT_APP_TARGET_DOMAIN}/products?pageNumber=${page}&sortBy=${sortBy}`)
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
            .finally(() => setIsPending(false));
    }, [page, sortBy]);

    return (
        <div style={{ padding: "20px", maxWidth: "1200px", margin: "0 auto" }}>
            <SortingOptions sortBy={sortBy} setSortBy={setSortBy} sortOptions={[
                {"value": "id", "label": "ID"},
                {"value": "productName", "label": "Název"}]}/>
            <Products products={data} isPending={isPending} error={error} />
            <PageSwitch page={page} setPage={setPage} />
        </div>
    );
}

export default AllProducts;