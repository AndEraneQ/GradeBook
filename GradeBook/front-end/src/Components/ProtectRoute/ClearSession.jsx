
import React, { useEffect } from "react";
import { useNavigate, useLocation } from "react-router-dom";

const ClearSession = () => {
    const navigate = useNavigate();
    const location = useLocation();

    useEffect(() => {
        navigate("/home", { state: null });
    }, [navigate]);

    return null;
};

export default ClearSession;
