import React, { useState } from "react";
import './AllUsersPage.css';
import { useEffect } from "react";
import GoBackButton from "../GoBackButtonComp/GoBackButton";
import NavBarService from "../../Services/NavBarService";
import { useNavigate } from "react-router-dom";

function AllUsersPage(){

    const [users,setUsers] = useState([]);
    const [error, setError] = useState(null);
    const navigate = useNavigate();
    

    const getAllUsers = async () => {
        try {
            const response = await NavBarService.getAllUsers();
            setUsers(response.data);
            console.log(response.data);
        } catch (error) {
            console.error('There was an error fetching the subjects!', error);
            setError("Failed to fetch subjects. Please try again later.");
        }
    };

    const handleAddUser = () => {
        navigate("/register-user");
    }

    useEffect(() => {
        getAllUsers();
    }, []);
    
    return (
        <div className="all-users-page">
            <GoBackButton path='/home'/>
            <h1>Users:</h1>
            <div className="all-users-container">
                {error ? (
                    <div className="error-message">{error}</div>
                ) : (
                    <div>
                        <div className="search-and-add-container">
                            <input className="search-input" type="text" placeholder="🔍 Search:"/>
                            <button className="add-user-button" onClick={handleAddUser}> Add New User</button>
                        </div>
                            <ul>
                            {users.map((user, index) => (
                                <li key={user.id} className="user-item">
                                    {index + 1}.
                                    First name: <b> { user.firstName}</b>&nbsp;
                                    Last name: <b>{ user.lastName}</b>  &nbsp;
                                    Email: <b>{ user.email}</b> &nbsp;
                                    Role: <b>{ user.role}</b>
                                </li>
                            ))}
                        </ul>
                    </div>
                )}
            </div>
        </div>
    );
}

export default AllUsersPage