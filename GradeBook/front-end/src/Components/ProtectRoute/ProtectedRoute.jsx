// src/Components/ProtectedRoute.js
import React from "react";
import { Navigate } from "react-router-dom";

// Sprawdź, czy token jest ważny
const isTokenValid = () => {
    const user = localStorage.getItem('user_data'); 
    if (!user) return false;

    const parsedUser = JSON.parse(user); 
    const token = parsedUser.token; 

    if (!token) return false; 

    const payload = JSON.parse(atob(token.split(".")[1]));
    const currentTime = Date.now() / 1000; 

    return payload.exp > currentTime; 
};

const ProtectedRoute = ({ element }) => {
    const user = localStorage.getItem('user_data'); 
    const parsedUser = user ? JSON.parse(user) : null;
    if (parsedUser?.token) {
        return isTokenValid() ? element : (
            <Navigate 
                to="/login" 
                state={{ 
                    message: "Session expired. You need to login!" 
                }} 
            />
        );
    } else {
        return (
            <Navigate 
                to="/login" 
                state={{ 
                    message: "You need to login!" 
                }} 
            />
        );
    }
};

export default ProtectedRoute;
