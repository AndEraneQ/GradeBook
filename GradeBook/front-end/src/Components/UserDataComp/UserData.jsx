import React, { useEffect, useState } from "react";
import NavBarService from "../../Services/NavBarService";
import { getUser } from "../../utils/userUtils";
import './UserData.css';
import GoBackButton from "../GoBackButtonComp/GoBackButton";

function UserData() {
    const [user, setUser] = useState(null); 
    const [residence, setResidence] = useState(null);
    const [error, setError] = useState(null); 
    const [loading, setLoading] = useState(true);
    const user_data = getUser(); 

    useEffect(() => {
        const fetchUserData = async () => {
            try {
                const fetchedUser = await NavBarService.getUserData(user_data.id); 
                const fetchedResidence = await NavBarService.getUserResidence(user_data.id);
                setUser(fetchedUser.data); 
                setResidence(fetchedResidence.data);
            } catch (err) {
                setError(err.global || "Failed to fetch user data.");
            } finally {
                setLoading(false);
            }
        };

        fetchUserData(); 
    }, [user_data.id]); 

    const handleEditData = () => {
        
    }

    if (loading) {
        return <div className="loading-message">Loading user data...</div>; 
    }

    if (error) {
        return <div className="error-message">{error}</div>; 
    }

    if (!user) {
        return <div className="error-message"><h1>Try login one more time.</h1></div>;
    }

    return (
        <div className="user-data-page">            
            <GoBackButton path='/home'/>            
            <div className="user-data-container">
                <h1 className="header">User Data:</h1>
                <ul>
                    <li><strong>First name:</strong> {user.firstName}</li>
                    <li><strong>Last name:</strong> {user.lastName}</li>
                    <li><strong>Email:</strong> {user.email}</li>
                    <li><strong>Role:</strong> {user.role}</li>
                </ul>
                <h1 className="header">Residence:</h1>
                <ul>
                    <li><strong>City:</strong> {residence.city}</li>
                    <li><strong>Street:</strong> {residence.street}</li>
                    <li><strong>Building number:</strong> {residence.buildingNumber}</li>
                    <li><strong>Apartment number:</strong> {residence.apartmentNumber}</li>
                </ul>
                {user.role=="ADMIN" &&
                    <div className="edit-data-container">
                        <div className="edit-data-button-container">
                            <button 
                            className="edit-button"
                            onClick={handleEditData}>
                                Edit data
                            </button>
                        </div>
                    </div>
                }
            </div>
        </div>
    );
}

export default UserData;
