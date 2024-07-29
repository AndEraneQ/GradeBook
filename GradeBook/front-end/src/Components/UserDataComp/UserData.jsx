import React, { useEffect, useState } from "react";
import NavBarService from "../../Services/NavBarService";
import { getUser } from "../../utils/userUtils";
import './UserData.css';
import useNavBarNavigation from "../../utils/navBarNavigation";

function UserData() {
    const [user, setUser] = useState(null); 
    const [error, setError] = useState(null); 
    const user_data = getUser(); 
    const navBarNavigation = useNavBarNavigation();

    function handleGoBack(){
        navBarNavigation(user_data.role);
    }

    useEffect(() => {
        const fetchUserData = async () => {
            try {
                const fetchedUser = await NavBarService.getUserData(user_data.id); 
                setUser(fetchedUser.data); 
            } catch (err) {
                setError(err.global || "Failed to fetch user data.");
            }
        };

        fetchUserData(); 
    }, [user_data.id]); 

    if (error) {
        return <div className="error-message">{error}</div>; 
    }

    if (!user) {
        return <div className="error-message"><h1>Server error, try again later</h1></div>;
    }

    const userRole = user.role.name.substring(5).toLowerCase();

    return (
        <div className="user-data-page">
            <div>
                <button className="back-button" onClick={handleGoBack}>
                    <img src="src\assets\goBackArrow.png" alt="Button Image" />
                </button>
            </div>
            <div className="user-data-container">
            <h1 className="header">User Data:</h1>
            <ul>
                <li><strong>First name:</strong> {user.firstName}</li>
                <li><strong>Last name:</strong> {user.lastName}</li>
                <li><strong>Email:</strong> {user.email}</li>
                <li><strong>Role:</strong> {userRole}</li>
            </ul>
            <h1 className="header">Residence:</h1>
            <ul>
                <li><strong>City:</strong> {user.city}</li>
                <li><strong>Street:</strong> {user.street}</li>
                <li><strong>Building number:</strong> {user.buildingNumber}</li>
                <li><strong>Apartment number:</strong> {user.apartmentNumber}</li>
            </ul>
            </div>
        </div>
    );
}

export default UserData;
