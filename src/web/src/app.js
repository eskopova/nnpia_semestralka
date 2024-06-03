import "./styles/app.css";

import React, { useState } from "react";
import ProductForm from "./product-form";
import Home from "./home";
import Nav from "./components/navigation"
import { ByStore, ShopProducts } from "./by-store";
import { ByCategory, CategoryProducts} from "./by-category";
import AllProducts from "./all-products";
import ProductPage from "./product-page";
import SignUp from "./sign-up";
import SignIn from "./sign-in";
import AccountPage from "./account-page";
import ReviewForm from "./review-form";
import SearchProductsList from "./search-products-list";
import ShopForm from "./shop-form";

import {
    BrowserRouter as Router,
    Routes,
    Route
} from "react-router-dom";

function App() {
    const [loginStatus, setLoginStatus] = useState(false);

    const handleLoginStatusChange = (status) => {
        setLoginStatus(status);
    };

    return (
        <Router>
            <div className="app">
                <div className="navigation">
                    <Nav onLoginStatusChange={handleLoginStatusChange} />
                </div>

                <Routes>
                    <Route path="/product-form" element={<ProductForm />} />
                    <Route path="/shop-form" element={<ShopForm />} />

                    <Route path="/" element={<Home />} />

                    <Route path="/products" element={<AllProducts />} />
                    <Route path="/products/:id" element={<ProductPage />} />
                    <Route path="/by-store" element={<ByStore />} />
                    <Route path="/by-store/:name" element={<ShopProducts />} />
                    <Route path="/by-category" element={<ByCategory />} />
                    <Route path="/by-category/:name" element={<CategoryProducts />} />

                    <Route path="/sign-up" element={<SignUp onLoginStatusChange={handleLoginStatusChange} />} />
                    <Route path="/sign-in" element={<SignIn onLoginStatusChange={handleLoginStatusChange} />} />
                    <Route path="/account" element={<AccountPage />} />

                    <Route path="/add-review/:id" element={<ReviewForm />} />
                    <Route path="/search/:text" element={<SearchProductsList />} />
                </Routes>
            </div>
        </Router>
    );
}

export default App;
