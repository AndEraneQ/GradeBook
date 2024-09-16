import React, { useEffect, useState } from "react";
import NavBarService from "../../Services/NavBarService";
import { getUser } from "../../utils/userUtils";
import './UserData.css';
import GoBackButton from "../../components/GoBackButton/GoBackButton";
import { useLocation, useNavigate } from "react-router-dom";
import UserService from "../../Services/UserService";
import NavBarNavigator from "../../components/NavBarNavigator/NavBarNavigator";

function UserData() {
    const location = useLocation();
    const navigate = useNavigate();
    const [userToCheckData, setUserToCheckData] = useState(location.state?.user || null); 
    const [residence, setResidence] = useState(null);
    const [error, setError] = useState(null); 
    const [loading, setLoading] = useState(true);
    const currentUser = getUser(); 
    

    useEffect(() => {
        const fetchUserData = async () => {
            try {
                const id = userToCheckData!== null && currentUser.id!==userToCheckData.id ? userToCheckData.id : currentUser.id; 
                const fetchedUser = await NavBarService.getUserData(id); 
                const fetchedResidence = await NavBarService.getUserResidence(id);
                console.log(fetchedUser);
                console.log(fetchedResidence);
                setUserToCheckData(fetchedUser.data); 
                setResidence(fetchedResidence.data);
            } catch (err) {
                setError(err.global || "Failed to fetch user data.");
            } finally {
                setLoading(false);
            }
        };

        fetchUserData(); 
    }, [currentUser.id]); 

    const handleDeleteUser = async () => {
        try{
        const response = await UserService.deleteUser(userToCheckData.id);
        console.log(response.data);
        navigate('/users', {state: {response: response.data} });
        } catch(err){
            setError(err.response.data);
        }
    }

    if (loading) {
        return <div className="loading-message">Loading user data...</div>; 
    }

    if (error) {
        return <div className="error-message">{error}</div>; 
    }

    if (!userToCheckData) {
        return <div className="error-message"><h1>Try login one more time.</h1></div>;
    }

    return (
        <div className="user-data-page">
            <NavBarNavigator/>                       
            <div className="user-data-container">
                <h1 className="header">User Data:</h1>
                <ul>
                    <li><strong>First name:</strong> {userToCheckData.firstName}</li>
                    <li><strong>Last name:</strong> {userToCheckData.lastName}</li>
                    <li><strong>Email:</strong> {userToCheckData.email}</li>
                    <li><strong>Role:</strong> {userToCheckData.role}</li>
                </ul>
                <h1 className="header">Residence:</h1>
                <ul>
                    <li><strong>City:</strong> {residence.city}</li>
                    <li><strong>Street:</strong> {residence.street}</li>
                    <li><strong>Building number:</strong> {residence.buildingNumber}</li>
                    <li><strong>Apartment number:</strong> {residence.apartmentNumber}</li>
                </ul>
                <div className="buttons-container">
                    {currentUser.role=="ADMIN" &&
                        <div>
                        {currentUser.id!==userToCheckData.id && (
                            <button
                            className="delete-button"
                            onClick={handleDeleteUser}>
                                Delete user
                                </button>
                            )}  
                        </div>
                    }
                    <button 
                        className="edit-button"
                        onClick={() => navigate('/edit-data', { state: { user: userToCheckData, residence } })}>
                            Edit data
                    </button>
                </div>
            </div>
        </div>
    );
}

export default UserData;
